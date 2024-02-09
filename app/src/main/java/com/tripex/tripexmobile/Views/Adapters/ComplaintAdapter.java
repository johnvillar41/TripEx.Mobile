package com.tripex.tripexmobile.Views.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tripex.tripexmobile.Models.Complaints.Complaint;
import com.tripex.tripexmobile.OnItemClick;
import com.tripex.tripexmobile.R;

import java.util.List;

public class ComplaintAdapter extends RecyclerView.Adapter<ComplaintAdapter.ViewHolder> {

    private final List<Complaint> complaints;
    private OnItemClick<String> listener;

    public ComplaintAdapter(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public void setOnClickListener(OnItemClick<String> listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ComplaintAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_complaint, parent, false);

        return new ComplaintAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintAdapter.ViewHolder holder, int position) {
        Complaint complaint = complaints.get(position);
        holder.ratingBar.setRating(complaint.getComplaintLevel());
        holder.complaintFor.setText("Complaint for: " + complaint.getComplaintForName() + "(" + complaint.getComplaintFor() + ")");
        holder.isResolved.setText("IsResolved: " + complaint.isResolved());
        holder.resolvedBy.setText("Resolved By: " + complaint.getResolvedBy() == null ? complaint.getResolvedBy() : "Resolved By: Not yet resolved");
        holder.complaint.setText("Complaint: " + complaint.getComplaint());
    }

    @Override
    public int getItemCount() {
        return complaints.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final RatingBar ratingBar;
        private final TextView complaintFor, isResolved, resolvedBy, complaint;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ratingBar = itemView.findViewById(R.id.rating);
            complaintFor = itemView.findViewById(R.id.textview_complaint_for);
            isResolved = itemView.findViewById(R.id.textview_is_resolved);
            resolvedBy = itemView.findViewById(R.id.textview_resolved_by);
            complaint = itemView.findViewById(R.id.textview_complaint);
        }
    }
}