package qelit.a5cards;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Qelit on 24.10.2017.
 */

public interface PhotosApi {
        @GET("/photos/{num}")
        Call<Card4> getPhotosByNum(@Path("num") int num);
    }
