package com.example.sqliteapps;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BioAdapter extends RecyclerView.Adapter<BioAdapter.bioViewHolder> {

    ArrayList<Biodata> listBio;

    public BioAdapter(ArrayList<Biodata> listBio) {
        this.listBio = listBio;
    }

    @Override
    public BioAdapter.bioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.bio_item,parent,false);
        return new bioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BioAdapter.bioViewHolder holder, int position) {
        holder.tvNama.setText(listBio.get(position).getNama() + "( "+ listBio.get(position).getJKel()+" )");
        holder.tvId.setText(listBio.get(position).getId());
        holder.tvAlamat.setText(listBio.get(position).getAlamat());
    }

    @Override
    public int getItemCount() {
        if (listBio!=null){
            return listBio.size();
        }else {
            return 0;
        }
    }

    class bioViewHolder extends RecyclerView.ViewHolder{
        private TextView tvId, tvNama, tvAlamat;

        public bioViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvNama = itemView.findViewById(R.id.tvNama);
            tvAlamat = itemView.findViewById(R.id.tvAlamat);
        }
    }
}
