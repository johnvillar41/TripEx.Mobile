package com.tripex.tripexmobile.Views.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tripex.tripexmobile.Models.FareMatrix;
import com.tripex.tripexmobile.R;

import java.util.List;

public class FareMatrixAdapter extends RecyclerView.Adapter<FareMatrixAdapter.ViewHolder> {

    private final List<FareMatrix> fareMatrices;

    public FareMatrixAdapter(List<FareMatrix> fareMatrices) {
        this.fareMatrices = fareMatrices;
    }

    @NonNull
    @Override
    public FareMatrixAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_fare_matrix, parent, false);

        return new FareMatrixAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FareMatrixAdapter.ViewHolder holder, int position) {
        FareMatrix fareMatrix = fareMatrices.get(position);
        holder.txtId.setText(String.valueOf(fareMatrix.getId()));
        holder.txtDistance.setText(String.valueOf(fareMatrix.getDistance()));
        holder.txtRegularFare.setText(String.valueOf(fareMatrix.getRegularFare()));
        holder.txtDiscountedFare.setText(String.valueOf(fareMatrix.getDiscountedFare()));
    }

    @Override
    public int getItemCount() {
        return fareMatrices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtId, txtDistance, txtRegularFare, txtDiscountedFare;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.txtId);
            txtDistance = itemView.findViewById(R.id.txtDistanceValue);
            txtRegularFare = itemView.findViewById(R.id.txtRegularFareValue);
            txtDiscountedFare = itemView.findViewById(R.id.txtDiscountedFareValue);
        }
    }
}
