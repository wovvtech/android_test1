package com.wovvtech.myapplication;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import com.wovvtech.myapplication.model.MainResponse;
import com.wovvtech.myapplication.model.ResponseData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private MainResponse mainResponse;
    int row_index = -1;
    Context context;
    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;
    List<Integer> selectedPos = new ArrayList<Integer>();
    // RecyclerView recyclerView;
    public RecyclerViewAdapter(Context ctx, MainResponse response) {
        this.context = ctx;
        this.mainResponse = response;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);


            return new ItemViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return new LoadingViewHolder(view);
        }


    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof ItemViewHolder) {
            if(mainResponse != null) {
                final ResponseData myListData = mainResponse.getResponseDataArrayList().get(position);
                ((ItemViewHolder) holder).txtTitle.setText(myListData.getTitle());

                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
                    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    Date dt = sdf.parse(myListData.getCreated_at());
                    ((ItemViewHolder) holder).txtCreatedBy.setText(output.format(dt));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(row_index != -1)
                {
                    if(row_index == position) {
                        ((ItemViewHolder) holder).switchBtn.setChecked(true);
                    }
                }


            }
            ((ItemViewHolder) holder).rlView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),"click on item: "+position,Toast.LENGTH_LONG).show();
                    row_index=position;
                    selectedPos.add(position);
                    ((MainActivity)context).setTitle(selectedPos.size());
                    notifyDataSetChanged();
                }
            });

            if(row_index==position){
                ((ItemViewHolder) holder).rlView.setBackgroundColor(Color.parseColor("#567845"));
                ((ItemViewHolder) holder).txtTitle.setTextColor(Color.parseColor("#ffffff"));
                ((ItemViewHolder) holder).txtCreatedBy.setTextColor(Color.parseColor("#ffffff"));
            }
            else
            {
                ((ItemViewHolder) holder).rlView.setBackgroundColor(Color.parseColor("#ffffff"));
                ((ItemViewHolder) holder).txtTitle.setTextColor(Color.parseColor("#000000"));
                ((ItemViewHolder) holder).txtCreatedBy.setTextColor(Color.parseColor("#000000"));
            }
        } else if (holder instanceof LoadingViewHolder) {
            showLoadingView((LoadingViewHolder) holder, position);
        }



    }
    private void showLoadingView(LoadingViewHolder viewHolder, int position) {
        //ProgressBar would be displayed
        Log.e("Showloading","showloading");
    }

    @Override
    public int getItemCount() {
        return mainResponse == null ? 0 : mainResponse.getResponseDataArrayList().size();
    }

    public int getItemViewType(int position) {
        return mainResponse.getResponseDataArrayList().get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;
    }
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView txtTitle;
        public TextView txtCreatedBy;
        public Switch switchBtn;
        public RelativeLayout rlView;
        public ItemViewHolder(View itemView) {
            super(itemView);
            this.txtTitle = (TextView) itemView.findViewById(R.id.txtTitle);
            this.txtCreatedBy = (TextView) itemView.findViewById(R.id.txtCreatedAt);
            this.switchBtn = (Switch) itemView.findViewById(R.id.switchbtn);
            this.rlView = (RelativeLayout)itemView.findViewById(R.id.rlView);
        }
    }

    private class LoadingViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBar);
        }
    }
}