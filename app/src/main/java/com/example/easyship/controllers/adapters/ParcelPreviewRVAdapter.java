package com.example.easyship.controllers.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyship.R;
import com.example.easyship.application.Easyship;
import com.example.easyship.models.uidesign.ParcelPreviewItem;

import java.util.ArrayList;

public class ParcelPreviewRVAdapter extends RecyclerView.Adapter<ParcelPreviewRVAdapter.ViewHolder>{
    private static final String TAG = "ParcelPreviewRVAdapter";
    private ArrayList<ParcelPreviewItem> mApercus;
    private Context mContext;

    public ParcelPreviewRVAdapter(Context context, ArrayList<ParcelPreviewItem> apercus) {
        mApercus = apercus;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parcel_preview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        final ParcelPreviewItem apercuActif = mApercus.get(position);

        // Image
        holder.mImage.setImageResource(apercuActif.getImage());

        // Bouton supprimer
        holder.mBtnSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Layout
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: clicked on: "+ notificationActive.getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return mApercus.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout mLayout;
        ImageView mImage;
        ImageButton mBtnSupprimer;

        public ViewHolder(View itemView){
            super(itemView);

            mLayout = itemView.findViewById(R.id.item_parcel_preview_layout);
            mImage = itemView.findViewById(R.id.item_parcel_preview_image);
            mBtnSupprimer = itemView.findViewById(R.id.item_parcel_preview_delete_btn);
        }
    }
}
