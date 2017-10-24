package qelit.a5cards;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Qelit on 23.10.2017.
 */

public class App extends Application {
    private Retrofit retrofit;
    private static PostsApi postsApi;
    private static CommentsApi commentsApi;
    private static UsersApi usersApi;
    private static PhotosApi photosApi;
    private static TodosApi todosApi;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        postsApi = retrofit.create(PostsApi.class);
        commentsApi = retrofit.create(CommentsApi.class);
        usersApi = retrofit.create(UsersApi.class);
        photosApi = retrofit.create(PhotosApi.class);
        todosApi = retrofit.create(TodosApi.class);
    }
    public static PostsApi getPostsApi(){
        return postsApi;
    }
    public static CommentsApi getCommentsApi(){
        return commentsApi;
    }

    public static UsersApi getUsersApi(){
        return usersApi;
    }

    public static PhotosApi getPhotosApi(){
        return photosApi;
    }

    public static TodosApi getTodosApi(){
        return todosApi;
    }
}
