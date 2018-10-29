package dikotsyarif.myplaymovie;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import dikotsyarif.myplaymovie.API.Client;
import dikotsyarif.myplaymovie.API.TampilanAPI;
import dikotsyarif.myplaymovie.Adapter.Adapter;
import dikotsyarif.myplaymovie.AmbilData.Example;
import dikotsyarif.myplaymovie.AmbilData.Result;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView moView;
    Adapter adapView;
    List<Result> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moView = (RecyclerView)findViewById(R.id.movieView);
        moView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1));

        movieLoad("popular");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_sort_setting, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.popular){
            movieLoad("popular");
        }else if (id ==R.id.setting){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void movieLoad(String value){
        TampilanAPI api = Client.getRetrofit().create(TampilanAPI.class);
        Call<Example> call= api.getPopular();
//        if (value.equals("popular")){
//            call = api.getPopular();
//        }else if (value.equals("top_rated")){
//            call = api.getRated();
//        }
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                Example movie = response.body();
                adapView = new Adapter(results);
                adapView.setData(movie.getResults());
                moView.setAdapter(adapView);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

            }
        });
    }

}
