package com.example.myapplicationdoctor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnLayoutClickedAction;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.viewHolder.PageDoctorsViewHolder;

import java.util.ArrayList;

public class PageDoctorsAdapter extends RecyclerView.Adapter<PageDoctorsViewHolder> {
    private ArrayList<SkillDoctor> listDrSkillAdapter = new ArrayList<>();
    private OnLayoutClickedAction onLayoutClickedAction;

    public PageDoctorsAdapter(OnLayoutClickedAction onLayoutClickedAction) {
        this.onLayoutClickedAction = onLayoutClickedAction;
    }

    public void setListDrSkillAdapter(ArrayList<SkillDoctor> listDrSkillAdapter) {
        this.listDrSkillAdapter = listDrSkillAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PageDoctorsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_page_skills_dr,parent,false);
        return new PageDoctorsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PageDoctorsViewHolder holder, int position) {
        holder.bind(listDrSkillAdapter.get(position),onLayoutClickedAction );
    }

    @Override
    public int getItemCount() {
        return listDrSkillAdapter.size();
    }
}
