package edu.northeastern.numad22fa_haozhezhang;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



public class URLViewHolder extends RecyclerView.ViewHolder {

    public TextView urlName;
    public TextView urlAddress;

    public URLViewHolder(@NonNull View itemView, final URLAdaptor.LinkClickListener linkClickListener) {
        super(itemView);
        this.urlName= itemView.findViewById(R.id.url_name_pair);
        this.urlAddress = itemView.findViewById(R.id.url_address_pair);

        itemView.setOnClickListener(view -> {
            int layoutPosition = getLayoutPosition();
            if (layoutPosition != RecyclerView.NO_POSITION) {
                linkClickListener.onLinkClick(layoutPosition);
            }
        });

    }
}
