package com.example.myapplicationdoctor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnCircleDetailsClickedAction;
import com.example.myapplicationdoctor.OnCircleMapClickedAction;
import com.example.myapplicationdoctor.OnCirclePhoneClickedAction;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.viewHolder.UserDoctorViewHolder;

import java.util.ArrayList;

public class UserDoctorAdapter extends RecyclerView.Adapter<UserDoctorViewHolder> {
    private ArrayList<UserDoctor> listUserDoctorAdapter = new ArrayList<>();
    private OnCircleDetailsClickedAction onCircleDetailsClickedAction;
    private OnCirclePhoneClickedAction onCirclePhoneClickedAction;
    private OnCircleMapClickedAction onCircleMapClickedAction;

    public UserDoctorAdapter(OnCircleDetailsClickedAction onCircleDetailsClickedAction, OnCirclePhoneClickedAction onCirclePhoneClickedAction, OnCircleMapClickedAction onCircleMapClickedAction) {
        this.onCircleDetailsClickedAction = onCircleDetailsClickedAction;
        this.onCirclePhoneClickedAction = onCirclePhoneClickedAction;
        this.onCircleMapClickedAction = onCircleMapClickedAction;
    }

    public void setListUserDoctorAdapter(ArrayList<UserDoctor> listUserDoctorAdapter) {
        this.listUserDoctorAdapter = listUserDoctorAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserDoctorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_dr_skil_choice,parent,false);
        return new UserDoctorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserDoctorViewHolder holder, int position) {
        holder.bind(listUserDoctorAdapter.get(position),onCircleDetailsClickedAction,onCircleMapClickedAction,onCirclePhoneClickedAction);
    }

    @Override
    public int getItemCount() {
        return listUserDoctorAdapter.size();
    }
}
