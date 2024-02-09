package com.tripex.tripexmobile.Views.Adapters;

import static com.tripex.tripexmobile.Services.BaseService.BASE_URL;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.tripex.tripexmobile.Models.Vehicle;
import com.tripex.tripexmobile.OnItemClick;
import com.tripex.tripexmobile.R;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder>{

    private final List<Vehicle> vehicles;
    private OnItemClick<String> listener;

    public VehicleAdapter(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public void setOnClickListener(OnItemClick<String> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public VehicleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_vehicle, parent, false);

        return new VehicleAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.ViewHolder holder, int position) {
        Vehicle vehicle = vehicles.get(position);
        if (vehicle.getImages() != null) {
            Picasso.get()
                    .load(BASE_URL + vehicle.getImages().get(0).getImageLink())
                    .resize(350, 350)
                    .centerCrop()
                    .into(holder.imageView);
        }
        holder.plateNumber.setText(vehicle.getPlateNumber());
        holder.name.setText(vehicle.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(vehicle.getId());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehicles.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final ImageView imageView;
        private final TextView plateNumber, name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_vehicle);
            plateNumber = itemView.findViewById(R.id.textview_plate_number);
            name = itemView.findViewById(R.id.textview_vehicle_name);
        }
    }
}
