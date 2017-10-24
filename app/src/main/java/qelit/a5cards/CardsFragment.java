package qelit.a5cards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Qelit on 23.10.2017.
 */

public class CardsFragment extends Fragment {
    RecyclerView recyclerView;
    List<Card> list = new ArrayList<>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //создание RecyclerView
        recyclerView = (RecyclerView) inflater.inflate(
                R.layout.fragment_cards, container, false);
        //обращение к методам вызова
        createPosts(1);
        sleep(1000);
        createComments(1);
        sleep(1000);
        createUsers(1);
        sleep(1000);
        createPhotos(3);
        sleep(1000);
        createTodos(1);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        MyAdapter adapter = new MyAdapter(list);
        recyclerView.setAdapter(adapter);
        return recyclerView;
    }

        //создание первого списка из 5 Card
    public void createPosts(int a){
        App.getPostsApi().getPostsByNum(a).enqueue(new Callback<Card1>() {
            @Override
            public void onResponse(Call<Card1> call, Response<Card1> response) {
                list.add(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Card1> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createComments(int b){
        App.getCommentsApi().getCommentsByNum(b).enqueue(new Callback<Card2>() {
            @Override
            public void onResponse(Call<Card2> call, Response<Card2> response) {
                list.add(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Card2> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createUsers(int c){
        App.getUsersApi().getUserssByNum(c).enqueue(new Callback<Card3>() {
            @Override
            public void onResponse(Call<Card3> call, Response<Card3> response) {
                list.add(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Card3> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createPhotos(int d){
        App.getPhotosApi().getPhotosByNum(d).enqueue(new Callback<Card4>() {
            @Override
            public void onResponse(Call<Card4> call, Response<Card4> response) {
                list.add(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Card4> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void createTodos(int e){
        App.getTodosApi().getTodosByNum(e).enqueue(new Callback<Card5>() {
            @Override
            public void onResponse(Call<Card5> call, Response<Card5> response) {
                list.add(response.body());
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<Card5> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void sleep(int q){
        try {
            Thread.sleep(q);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
