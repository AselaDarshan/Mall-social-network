package com.example.android;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bluetoothchat.Comment;
import com.example.android.bluetoothchat.Msg;
import com.example.android.bluetoothchat.R;

import java.util.List;

/**
 * Created by thilina on 10/2/16.
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{

    private List<Comment> itemList;
    static Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTextView;
        public TextView mTimeStamp;

        public ViewHolder(final View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.comment_text);
            mImageView = (ImageView) view.findViewById(R.id.comment_imageView);
            mTimeStamp = (TextView) view.findViewById(R.id.comment_timestamp);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public CommentAdapter(List<Comment> myItemList, Context mContext) {
        this.itemList = myItemList;
        this.mContext = mContext;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(itemList.get(position).getText());
        holder.mTimeStamp.setText(itemList.get(position).getTimeStamp());
        if(itemList.get(position).getImage() != null)
            holder.mImageView.setImageResource(R.drawable.bluetooth_logo);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return itemList.size();
    }
}
