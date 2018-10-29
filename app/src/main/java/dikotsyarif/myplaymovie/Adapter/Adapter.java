package dikotsyarif.myplaymovie.Adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

import dikotsyarif.myplaymovie.AmbilData.Result;
import dikotsyarif.myplaymovie.MovieDetail;
import dikotsyarif.myplaymovie.R;

/**
 * Created by dikot on 29/10/18.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MovieList> {

    List<Result> results;

    public Adapter(List<Result> results){
        this.results = results;
    }

    @Override
    public Adapter.MovieList onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.playlist , parent ,false);

        return new MovieList(view);
    }
    @Override
    public void onBindViewHolder(final MovieList holder, final int position) {
        Picasso.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w185/"+results.get(position).getPosterPath())
                .into(holder.Poster, new Callback() {
                    @Override
                    public void onSuccess() {
                        holder.Judul.setText(results.get(position).getTitle());
                        holder.Sinopsis.setText(results.get(position).getOverview());
                    }
                    @Override
                    public void onError() {

                    }
                });
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Result data = results.get(position);
                final Intent detail = new Intent(holder.itemView.getContext(), MovieDetail.class);
                detail.putExtra("movie", new GsonBuilder().create().toJson(data));
                holder.itemView.getContext().startActivity(detail);
            }
        });
    }



    @Override
    public int getItemCount() {
        return results.size();
    }
    public void setData(List<Result> results){
        this.results = results;
    }

    class MovieList extends RecyclerView.ViewHolder {
        ImageView Poster;
        TextView Judul, Sinopsis;
        android.widget.Button Button;
        public MovieList(View itemView){
            super(itemView);
            Poster = (ImageView)itemView.findViewById(R.id.poster);
            Judul = (TextView)itemView.findViewById(R.id.title_main);
            Sinopsis = (TextView)itemView.findViewById(R.id.sinopsis);
        }
    }
}
