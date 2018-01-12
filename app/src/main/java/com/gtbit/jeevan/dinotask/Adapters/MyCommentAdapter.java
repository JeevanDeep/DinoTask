package com.gtbit.jeevan.dinotask.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.gtbit.jeevan.dinotask.ModelClass.CommentData;
import com.gtbit.jeevan.dinotask.R;

import java.util.List;

/**
 * Created by jeevan on 12/1/18.
 */

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.MyViewHolder> {

    private List<CommentData> commentDataList;
    private Context context;
    private int postId;
    private LayoutInflater inflater;

    public MyCommentAdapter(List<CommentData> commentDataList, Context context, int postId) {
        this.commentDataList = commentDataList;
        this.context = context;
        this.postId = postId;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_items_comments, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CommentData currentCommentData = commentDataList.get(position);

        ColorGenerator generator = ColorGenerator.MATERIAL;

        int color = generator.getColor(currentCommentData.getEmail().charAt(0));
        TextDrawable.IBuilder builder = TextDrawable.builder()
                .beginConfig()
                .withBorder(4)
                .endConfig()
                .round();
        TextDrawable textDrawable = builder.build(String.valueOf(currentCommentData.getEmail().charAt(0)),color);
        holder.email.setText("Email: " + currentCommentData.getEmail());
        holder.name.setText("Name: " + currentCommentData.getName());
        holder.body.setText(currentCommentData.getBody());
        holder.imageView.setImageDrawable(textDrawable);
    }

    @Override
    public int getItemCount() {
        return commentDataList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, body;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.commentImageView);
            name = itemView.findViewById(R.id.nameTextView);
            email = itemView.findViewById(R.id.emailTextView);
            body = itemView.findViewById(R.id.bodyTextViewDetailed);
        }
    }
}
