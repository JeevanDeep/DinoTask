package com.gtbit.jeevan.dinotask.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.gtbit.jeevan.dinotask.Activity.DetailActivity;
import com.gtbit.jeevan.dinotask.ModelClass.PostData;
import com.gtbit.jeevan.dinotask.R;

import java.util.List;

/**
 * Created by jeevan on 12/1/18.
 */

public class MyPostAdapter extends RecyclerView.Adapter<MyPostAdapter.MyViewHolder> {
    Context context;
    private List<PostData> arrayList;
    private LayoutInflater inflater;

    public MyPostAdapter(List<PostData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PostData currentPost = arrayList.get(position);

        ColorGenerator generator = ColorGenerator.MATERIAL;

        int color = generator.getColor(currentPost.getUserId());
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .endConfig()
                .round();
        TextDrawable textDrawable = builder.build(String.valueOf(currentPost.getUserId()), color);
        holder.imageView.setImageDrawable(textDrawable);
        holder.body.setText(currentPost.getBody());
        holder.title.setText(currentPost.getTitle());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title, body;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxtView);
            body = itemView.findViewById(R.id.bodyTxtView);
            imageView = itemView.findViewById(R.id.imageView);
            imageView.setOnClickListener(this);
            body.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.bodyTxtView || v.getId() == R.id.imageView) {
                int postId = arrayList.get(getAdapterPosition()).getId();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("postId", postId);
                context.startActivity(intent);
            }
        }
    }
}
