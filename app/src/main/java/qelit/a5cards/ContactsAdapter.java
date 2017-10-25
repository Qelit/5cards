package qelit.a5cards;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Qelit on 25.10.2017.
 */

public class ContactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Contact> list;

    public ContactsAdapter(List<Contact> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Contact contact = list.get(position);
        ((ViewHolder)holder).id.setText(String.valueOf(contact.getMyId()));
        ((ViewHolder)holder).name.setText(String.valueOf(contact.getName()));
        ((ViewHolder)holder).phone.setText(String.valueOf(contact.getPhone()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView name;
        TextView phone;
        public ViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.idContacts);
            name = (TextView) itemView.findViewById(R.id.nameContacts);
            phone = (TextView) itemView.findViewById(R.id.phoneContacts);
        }
    }

}
