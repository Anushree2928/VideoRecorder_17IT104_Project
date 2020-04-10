package com.example.videorecorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.RecordHolder> {
    Context context;
    ArrayList<RecordItem> dataList;
    OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(RecordItem item);
    }

    public VideoAdapter(Context context, ArrayList<RecordItem> dataList, OnItemClickListener clickListener) {
        this.context = context;
        this.dataList = dataList;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public RecordHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.record_item, viewGroup, false);
        return new RecordHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecordHolder holder, final int position) {
        final RecordItem item = dataList.get(position);
        holder.path_name.setText(item.getPath());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onItemClick(item);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class RecordHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView path_name;

        public RecordHolder(@NonNull View itemView) {
            super(itemView);
            path_name = itemView.findViewById(R.id.path_name);
            cardView = itemView.findViewById(R.id.card_view);
        }
    }
}
