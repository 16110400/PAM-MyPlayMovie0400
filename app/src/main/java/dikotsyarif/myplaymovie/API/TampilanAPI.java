package dikotsyarif.myplaymovie.API;

import dikotsyarif.myplaymovie.AmbilData.Example;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by dikot on 29/10/18.
 */

public interface TampilanAPI {

    public static String DB_API="1686a4d5e4ed2c5dd51cf6d3ac65e8b0";

//    @GET("popular?api_key="+DB_API)
//    Call<Dates>getDates();

//    @GET("popular?api_key="+DB_API)
//    Call<Example>getPopular();

    @GET("top_rated?api_key="+DB_API)
    Call<Example>getRated();

}
