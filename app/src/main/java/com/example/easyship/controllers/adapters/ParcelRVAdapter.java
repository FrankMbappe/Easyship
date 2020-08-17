package com.example.easyship.controllers.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.easyship.R;
import com.example.easyship.application.Easyship;
import com.example.easyship.models.Colis;
import com.example.easyship.models.Livraison;

import java.util.ArrayList;

public class ParcelRVAdapter extends RecyclerView.Adapter<ParcelRVAdapter.ViewHolder>{
    private static final String TAG = "ParcelRVAdapter";
    private ArrayList<Livraison> mLivraisons;
    private Context mContext;

    public ParcelRVAdapter(Context context, ArrayList<Livraison> livraisons) {
        mLivraisons = livraisons;
        mContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_parcel, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Log.d(TAG,"onBindViewHolder: called.");

        final Livraison livraisonActive = mLivraisons.get(position);

        // Image
        holder.mImage.setImageResource(livraisonActive.getColis().get(0).getIdImage());

        // Nom
        holder.mNom.setText(livraisonActive.getColis().get(0).getNom());

        // Prix
        String prix = String.format(mContext.getString(R.string.fmt_fcfa), livraisonActive.getPrix());
        holder.mPrix.setText(prix);

        // Adresse d'origine
        holder.mAdresseOrigine.setText(livraisonActive.getOrigine().toString());

        // Adresse de destination
        holder.mAdresseDestination.setText(livraisonActive.getDestination().toString());

        // S'engager
        holder.mBtnSengager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Ajouter aux favoris
        holder.mBtnAjoutAuxFavoris.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Partager
        holder.mBtnPartager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Layout
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: "+ livraisonActive.getId());
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
        ImageView mImage;
        TextView mNom;
        TextView mPrix;
        TextView mAdresseOrigine;
        TextView mAdresseDestination;
        ImageButton mBtnSengager;
        ImageButton mBtnAjoutAuxFavoris;
        ImageButton mBtnPartager;

        public ViewHolder(View itemView){
            super(itemView);

            mLayout = itemView.findViewById(R.id.item_parcel_layout);
            mImage = itemView.findViewById(R.id.item_parcel_image);
            mNom = itemView.findViewById(R.id.item_parcel_name);
            mPrix = itemView.findViewById(R.id.item_parcel_price);
            mAdresseOrigine = itemView.findViewById(R.id.item_parcel_from);
            mAdresseDestination = itemView.findViewById(R.id.item_parcel_destination);
            mBtnSengager = itemView.findViewById(R.id.item_parcel_engaged_icon);
            mBtnAjoutAuxFavoris = itemView.findViewById(R.id.item_parcel_favorite_icon);
            mBtnPartager = itemView.findViewById(R.id.item_parcel_share_btn);
        }
    }
}
