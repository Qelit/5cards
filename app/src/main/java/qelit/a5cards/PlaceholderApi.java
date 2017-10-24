package qelit.a5cards;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Qelit on 23.10.2017.
 */

interface PostsApi {
    @GET("/posts/{num}")
    Call<Card1> getPostsByNum(@Path("num") int num);
}
