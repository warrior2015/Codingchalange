package com.sample.virtusademo.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.sample.virtusademo.R;
import com.sample.virtusademo.models.WeatherData.Weather;

import java.util.List;
/**
 * Created by neerajt on 10/10/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<Weather> data;
    private Context context;

    public HomeAdapter(Context context, List<Weather> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.context = context;
    }


    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(HomeAdapter.ViewHolder holder, int position) {
        holder.tvWeatherDesc.setText(data.get(position).getMain()+" : "+data.get(position).getDescription());

        String imagesurl = "http://openweathermap.org/img/w/"+data.get(position).getIcon()+".png";

        Glide.with(context)
                .load(imagesurl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .skipMemoryCache(true)
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public interface OnItemClickListener {
        void onClick(Weather Item);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvWeatherDesc;
        ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            tvWeatherDesc = (TextView) itemView.findViewById(R.id.tv_weatherdesc);
            imageView = (ImageView) itemView.findViewById(R.id.iv_weathericon);

        }

    }


}
