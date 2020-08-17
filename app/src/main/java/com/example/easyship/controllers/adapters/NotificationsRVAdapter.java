package com.example.easyship.controllers.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyship.R;
import com.example.easyship.application.Easyship;
import com.example.easyship.models.Notification;

import java.util.ArrayList;

public class NotificationsRVAdapter extends RecyclerView.Adapter<NotificationsRVAdapter.ViewHolder>{
    private static final String TAG = "NotificationsRVAdapter";
    private ArrayList<Notification> mNotifications;
    private Context mContext;

    public NotificationsRVAdapter(Context context, ArrayList<Notification> notifications) {
        mNotifications = notifications;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        final Notification notificationActive = mNotifications.get(position);

        // Texte
        holder.mText.setText(mContext.getString(notificationActive.getIdTexte()));

        // Image
        holder.mImage.setImageResource(notificationActive.getImage());

        // Date
        String date = String.format(mContext.getString(R.string.fmt_date_at_hour), Easyship.getDateFromCalendar(notificationActive.getDate()), Easyship.getTimeFromCalendar(notificationActive.getDate()));
        holder.mDate.setText(date);

        // Layout
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: clicked on: "+ notificationActive.getId());
                //Intent intent = new Intent(mContext, ShipmentStudioActivity.class);

                // Je convertis l'objet marque choisi en String grâce à Gson et je l'envoie à l'autre activité avec un putExtra
//                Gson gson = new GsonBuilder().create();
//                intent.putExtra(BrandModelsActivity.EXTRA_CURRENT_BRAND, gson.toJson(livraisonActive));
//                mContext.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mNotifications.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        RelativeLayout mLayout;
        ImageView mImage;
        TextView mText;
        TextView mDate;

        public ViewHolder(View itemView){
            super(itemView);

            mLayout = itemView.findViewById(R.id.item_notification_layout);
            mImage = itemView.findViewById(R.id.item_notification_image);
            mText = itemView.findViewById(R.id.item_notification_text);
            mDate = itemView.findViewById(R.id.item_notification_date);
        }
    }
}
