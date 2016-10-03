package com.example.android;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.bluetoothchat.Msg;
import com.example.android.bluetoothchat.R;

import java.util.List;

/**
 * Created by thilina on 10/1/16.
 */
public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{

    public final static String MESSAGE_ID = "com.example.mall-social-network.MESSAGE";
    private static List<Msg> itemList;
    Dialog rankDialog;
    static Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTextView;
        public RatingBar mRatingBar;
        public Button commentBtn;
        public Button rateBtn;

        public ViewHolder(final View view) {
            super(view);
            mTextView = (TextView) view.findViewById(R.id.timelineitem_text);
            mImageView = (ImageView) view.findViewById(R.id.timelineitem_imageView);
            mRatingBar = (RatingBar) view.findViewById(R.id.timelineitem_ratingBar);
            rateBtn = (Button) view.findViewById(R.id.rate_btn);
            commentBtn = (Button) view.findViewById(R.id.comment_btn);
            view.setOnClickListener(this);
            rateBtn.setOnClickListener(this);
            commentBtn.setOnClickListener(this);
        }

        // onClick Listener for view
        @Override
        public void onClick(View v) {

            if (v.getId() == rateBtn.getId()) {
                Intent intent = new Intent(mContext, Rating.class);
                mContext.startActivity(intent);
            }else if (v.getId() == commentBtn.getId()) {
                Intent intent = new Intent(mContext, CommentList.class);
                intent.putExtra(MsgAdapter.MESSAGE_ID, itemList.get(getAdapterPosition()).getUID());
                mContext.startActivity(intent);
            } else {
                Toast.makeText(v.getContext(), "ROW PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }
        }


        //onLongClickListener for view
        @Override
        public boolean onLongClick(View v) {

            return true;
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MsgAdapter(List<Msg> myItemList, Context mContext) {
        this.itemList = myItemList;
        this.mContext = mContext;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.msg_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MsgAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(itemList.get(position).getText());
        holder.mRatingBar.setRating(itemList.get(position).getRank());
        if(itemList.get(position).getImage()!= null) {
            Log.e("INFO", itemList.get(position).getImage());
            String encodedImage = itemList.get(position).getImage();
            byte[] decodedString = Base64.decode(encodedImage, Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

            holder.mImageView.setImageBitmap(decodedByte);
        } else{
            holder.mImageView.setVisibility(View.GONE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return itemList.size();
    }
}
