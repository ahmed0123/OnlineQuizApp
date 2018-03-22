package com.example.android.onlinequizapp.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.android.onlinequizapp.Interface.ItemClickListiner;
import com.example.android.onlinequizapp.R;

/**
 * Created by Ahmed El Hendawy on 2018/03/22.
 */

public class RankingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
	
	public TextView txtName, txtScore;
	private ItemClickListiner itemClickListiner;
	
	public RankingViewHolder(View itemView) {
		super(itemView);
		txtName = itemView.findViewById(R.id.txt_name);
		txtScore = itemView.findViewById(R.id.txt_score);
		itemView.setOnClickListener(this);
		
	}
	
	public void setItemClickListiner(ItemClickListiner itemClickListiner) {
		this.itemClickListiner = itemClickListiner;
	}
	
	@Override
	public void onClick(View view) {
		itemClickListiner.onClick(view, getAdapterPosition(), false);
		
	}
}
