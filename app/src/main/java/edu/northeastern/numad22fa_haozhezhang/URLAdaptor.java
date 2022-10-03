package edu.northeastern.numad22fa_haozhezhang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class URLAdaptor extends RecyclerView.Adapter<URLViewHolder> {

    private final ArrayList<URL> urlList;
    private LinkClickListener linkClickListener;

    public URLAdaptor(ArrayList<URL> urlList) {
        this.urlList = urlList;

    }

    public void setLinkClickListener(LinkClickListener linkClickListener) {
        this.linkClickListener = linkClickListener;
    }

    @NonNull
    @Override
    public URLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_url_item, parent,false);

        return new URLViewHolder(itemView, linkClickListener);

    }

    @Override
    public void onBindViewHolder(@NonNull URLViewHolder holder, int position) {
        holder.urlName.setText(urlList.get(position).getUrlName());
        holder.urlAddress.setText(urlList.get(position).getUrlAddress());

    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    public interface LinkClickListener {
        void onLinkClick(int position);
    }
}
