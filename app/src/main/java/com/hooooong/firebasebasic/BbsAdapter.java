package com.hooooong.firebasebasic;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hooooong.firebasebasic.model.Bbs;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Android Hong on 2017-10-30.
 */
public class BbsAdapter extends RecyclerView.Adapter<BbsAdapter.ViewHolder> {

    private List<Bbs> bbsList = new ArrayList<>();

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bbs, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bbs bbs = bbsList.get(position);
        holder.setTextTitle(bbs.getTitle());
        holder.setTextContent(bbs.getContent());
        holder.setTextDate(bbs.getDate());
    }

    @Override
    public int getItemCount() {
        return bbsList.size();
    }

    public void modifyBbsData(List<Bbs> data) {
        this.bbsList = data;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle, textContent, textDate;

        public ViewHolder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.textTitle);
            textContent = itemView.findViewById(R.id.textContent);
            textDate = itemView.findViewById(R.id.textDate);
        }

        public void setTextTitle(String title){
            textTitle.setText(title);
        }

        public void setTextContent(String content){
            textContent.setText(content);
        }
        public void setTextDate(String date){
            textDate.setText(date);
        }

    }
}
