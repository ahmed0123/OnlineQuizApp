package com.example.android.onlinequizapp.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.onlinequizapp.R;
import com.example.android.onlinequizapp.interfac.ItemClickListiner;

/**
 * Created by Ahmed El Hendawy on 2018/03/19.
 */

public class CategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
	
	public ImageView categoryImage;
	public TextView categoryName;
	public ItemClickListiner itemClickListiner;
	
	public CategoryViewHolder(View itemView) {
		super(itemView);
		categoryImage = itemView.findViewById(R.id.categoryImage);
		categoryName = itemView.findViewById(R.id.categoryName);
		itemView.setOnClickListener(this);
	}
	
	public void setItemClickListiner(ItemClickListiner itemClickListiner) {
		this.itemClickListiner = itemClickListiner;
	}
	
	@Override
	public void onClick(View v) {
		itemClickListiner.onClick(v, getAdapterPosition(), false);
	}
}
