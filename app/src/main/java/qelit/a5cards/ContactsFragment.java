package qelit.a5cards;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by Qelit on 24.10.2017.
 */

public class ContactsFragment extends Fragment {
    RecyclerView recyclerView;
    Cursor cursor;
    ArrayList<Contact> list;
    String number;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) inflater.inflate(R.layout.fragment_contacts, container, false);
        showContacts();
        return recyclerView;
    }

    private void showContacts() {
        // Долго искал, что не так, нашел этот вариаент решения, чуть переделал.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getActivity().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            list = getContactNames();
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(layoutManager);
            ContactsAdapter adapter = new ContactsAdapter(list);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showContacts();
            } else {
                Toast.makeText(getContext(), "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }
    //создание листа Contact
    public ArrayList<Contact> getContactNames() {
        ArrayList<Contact> list = new ArrayList<>();
        ContentResolver cr = getContext().getContentResolver();
        cursor = cr.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                null,
                null,
                null
        );
        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact();
                //Получаем данные из курсора
                contact.setMyId(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID)));
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                contact.setName(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                if (Integer.parseInt(cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
                    Cursor pCur = getActivity().getContentResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " =?",
                            new String[]{id}, null);
                    while (pCur.moveToNext()) {
                        number = pCur.getString(pCur.getColumnIndex("data1"));
                    }
                    pCur.close();
                }
                contact.setPhone(number);
                list.add(contact);
            } while (cursor.moveToNext());
        }

        // Закрываем курсор
        cursor.close();

        return list;
    }
}
