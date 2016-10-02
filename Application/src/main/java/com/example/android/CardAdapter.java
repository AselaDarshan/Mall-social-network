package com.example.android;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
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
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    private List<Msg> itemList;
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
                Toast.makeText(v.getContext(), "RATE BTN PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
            }else if (v.getId() == commentBtn.getId()) {
                Toast.makeText(v.getContext(), "COMMENT BTN PRESSED = " + String.valueOf(getAdapterPosition()), Toast.LENGTH_SHORT).show();
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
    public CardAdapter(List<Msg> myItemList, Context mContext) {
        this.itemList = myItemList;
        this.mContext = mContext;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(CardAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(itemList.get(position).getText());
        holder.mRatingBar.setRating(itemList.get(position).getRank());
        holder.mImageView.setImageResource(R.drawable.bluetooth_logo);
        /*holder.rateBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                rankDialog = new Dialog(mContext, R.style.TextAppearance_AppCompat_Small);
                rankDialog.setContentView(R.layout.rank_dialog);
                rankDialog.setCancelable(true);

                Button updateButton = (Button) rankDialog.findViewById(R.id.rank_dialog_button);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rankDialog.dismiss();
                    }
                });
                //now that the dialog is set up, it's time to show it
                rankDialog.show();
            }
        });*/
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return itemList.size();
    }
}
