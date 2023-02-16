package com.example.myapplicationdoctor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnPhoneImageActionClicked;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.Emergency;
import com.example.myapplicationdoctor.viewHolder.EmergencyViewHolder;

import java.util.ArrayList;

public class EmergencyAdapter extends RecyclerView.Adapter<EmergencyViewHolder> {
    private ArrayList<Emergency> listEmergencyAdapter = new ArrayList<>();
    private OnPhoneImageActionClicked onPhoneImageActionClicked;

    public EmergencyAdapter(OnPhoneImageActionClicked onPhoneImageActionClicked) {
        this.onPhoneImageActionClicked = onPhoneImageActionClicked;
    }

    public void setListEmergencyAdapter(ArrayList<Emergency> listEmergencyAdapter) {
        this.listEmergencyAdapter = listEmergencyAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public EmergencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_emergency_profil,parent,false);
        return new EmergencyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmergencyViewHolder holder, int position) {
        holder.bind(listEmergencyAdapter.get(position),onPhoneImageActionClicked );
    }

    @Override
    public int getItemCount() {
        return listEmergencyAdapter.size();
    }
}
