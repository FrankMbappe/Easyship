package com.example.easyship.controllers.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyship.R;
import com.example.easyship.application.Easyship;
import com.example.easyship.models.Colis;

import java.util.ArrayList;

public class ListParcelRVAdapter extends RecyclerView.Adapter<ListParcelRVAdapter.ViewHolder>{
    private static final String TAG = "ListParcelRVAdapter";
    private ArrayList<Colis> mColis;
    private Context mContext;

    public ListParcelRVAdapter(Context context, ArrayList<Colis> colis) {
        mColis = colis;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_parcel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        final Colis colisActif = mColis.get(position);

        // Nom
        holder.mNom.setText(colisActif.getNom());

        // Id
        holder.mId.setText(colisActif.getId());

        // Date
        String date = String.format(mContext.getString(R.string.fmt_date_at_hour), Easyship.getDateFromCalendar(colisActif.getDateCreation()), Easyship.getTimeFromCalendar(colisActif.getDateCreation()));
        holder.mDate.setText(date);

        // Layout
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+ colisActif.getId());
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
        return mColis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout mLayout;
        TextView mNom;
        TextView mId;
        TextView mDate;

        public ViewHolder(View itemView){
            super(itemView);

            mLayout = itemView.findViewById(R.id.item_list_parcel_layout);
            mNom = itemView.findViewById(R.id.item_list_parcel_name);
            mId = itemView.findViewById(R.id.item_list_parcel_id);
            mDate = itemView.findViewById(R.id.item_list_parcel_date);
        }
    }
}
