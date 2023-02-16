package com.example.myapplicationdoctor.viewHolder;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnLayoutClickedAction;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.SkillDoctor;
import com.example.myapplicationdoctor.model.UserDoctor;

public class PageDoctorsViewHolder extends RecyclerView.ViewHolder {
    private TextView skillDr;
    private LinearLayout linearLayout;

    public PageDoctorsViewHolder(@NonNull View view) {
        super(view);
        skillDr = view.findViewById(R.id.raw_skill);
        linearLayout = view.findViewById(R.id.linear_layout);
    }

    public TextView getSkillDr() {
        return skillDr;
    }

    public void setSkillDr(TextView skillDr) {
        this.skillDr = skillDr;
    }

   public void bind (SkillDoctor skillDoctor, OnLayoutClickedAction onLayoutClickedAction){
        skillDr.setText(skillDoctor.getSkill());
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLayoutClickedAction.goToListDoctorsBySkills(skillDoctor);
            }
        });
    }



}
