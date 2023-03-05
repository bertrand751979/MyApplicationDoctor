package com.example.myapplicationdoctor.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnCircleDetailsClickedAction;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.UserDoctor;
import com.example.myapplicationdoctor.viewHolder.SearchViewHolder;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchViewHolder> {
    private ArrayList<UserDoctor> searchDrListAdapter = new ArrayList<>();
    private OnCircleDetailsClickedAction onCircleDetailsClickedAction;

    public SearchAdapter(OnCircleDetailsClickedAction onCircleDetailsClickedAction) {
        this.onCircleDetailsClickedAction = onCircleDetailsClickedAction;
    }

    public void setSearchDrListAdapter(ArrayList<UserDoctor> searchDrListAdapter) {
        this.searchDrListAdapter = searchDrListAdapter;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.raw_dr_by_skill_list,parent,false);
        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        holder.bind(searchDrListAdapter.get(position),onCircleDetailsClickedAction);
    }

    @Override
    public int getItemCount() {
        return searchDrListAdapter.size();
    }
}
