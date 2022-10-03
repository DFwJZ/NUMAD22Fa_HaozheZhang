package edu.northeastern.numad22fa_haozhezhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class URLAdaptor extends RecyclerView.Adapter<URLViewHolder> {

    private final List<URL> urlList;
    private final Context context;

    public URLAdaptor(List<URL> urlList, Context context) {
        this.urlList = urlList;
        this.context = context;
    }



    @NonNull
    @Override
    public URLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new URLViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_link_collector, null));
    }

    @Override
    public void onBindViewHolder(@NonNull URLViewHolder holder, int position) {

        holder.urlAddress.setText(urlList.get(position).getUrlAddress());

        holder.itemView.setOnClickListener(view -> {
            Toast.makeText(context, urlList.get(position).getUrlAddress(), Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }
}
