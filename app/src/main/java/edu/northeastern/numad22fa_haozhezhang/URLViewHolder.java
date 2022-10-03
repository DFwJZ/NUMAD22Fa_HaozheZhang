package edu.northeastern.numad22fa_haozhezhang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class URLViewHolder extends RecyclerView.ViewHolder {

    public TextView urlAddress;
    public URLViewHolder(@NonNull View itemView) {
        super(itemView);
        this.urlAddress = itemView.findViewById(R.id.add_website_link_btn);

    }
}
