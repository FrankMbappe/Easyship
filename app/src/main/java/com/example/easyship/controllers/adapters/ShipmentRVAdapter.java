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
import com.example.easyship.data.basic.Statuses;
import com.example.easyship.models.Livraison;
import com.example.easyship.models.uidesign.StatusItem;

import java.util.ArrayList;

public class ShipmentRVAdapter extends RecyclerView.Adapter<ShipmentRVAdapter.ViewHolder>{
    private static final String TAG = "ShipmentRVAdapter";
    private ArrayList<Livraison> mLivraisons;
    private Context mContext;

    public ShipmentRVAdapter(Context context, ArrayList<Livraison> livraisons) {
        mLivraisons = livraisons;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shipment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        final Livraison livraisonActive = mLivraisons.get(position);

        // Statut
        StatusItem statut = new Statuses().getAll().get(position);
        holder.mStatut.setText(statut.getName());
        holder.mStatut.setBackgroundColor(statut.getBackgroundColor());
        holder.mStatut.setTextColor(statut.getForegroundColor());

        // Date
        String date = String.format(mContext.getString(R.string.fmt_initiated_on), Easyship.getDateFromCalendar(livraisonActive.getDateCreation()));
        holder.mDate.setText(date);

        // Nom
        String nom = String.format(mContext.getString(R.string.fmt_shipment_n), livraisonActive.getId());
        holder.mNom.setText(nom);

        // Id
        holder.mId.setText(livraisonActive.getId());

        // Adresse d'origine
        holder.mAdresseOrigine.setText(livraisonActive.getOrigine().toString());

        // Adresse de destination
        holder.mAdresseDestination.setText(livraisonActive.getDestination().toString());

        // Nom du transporteur
        holder.mNomTransporteur.setText(livraisonActive.getTransporteur().getNomComplet());

        // Layout
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+ livraisonActive.getUid());
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
        return mLivraisons.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        LinearLayout mLayout;
        TextView mStatut;
        TextView mDate;
        TextView mNom;
        TextView mId;
        TextView mAdresseOrigine;
        TextView mAdresseDestination;
        TextView mNomTransporteur;

        public ViewHolder(View itemView){
            super(itemView);

            mLayout = itemView.findViewById(R.id.item_shipment_layout);
            mStatut = itemView.findViewById(R.id.item_shipment_status);
            mDate = itemView.findViewById(R.id.item_shipment_date);
            mNom = itemView.findViewById(R.id.item_shipment_name);
            mId = itemView.findViewById(R.id.item_shipment_id);
            mAdresseOrigine = itemView.findViewById(R.id.item_shipment_from);
            mAdresseDestination = itemView.findViewById(R.id.item_shipment_destination);
            mNomTransporteur = itemView.findViewById(R.id.item_shipment_carrier);
        }
    }
}
