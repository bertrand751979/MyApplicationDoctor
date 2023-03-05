package com.example.myapplicationdoctor.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnCircleDetailsClickedAction;
import com.example.myapplicationdoctor.OnCircleMapClickedAction;
import com.example.myapplicationdoctor.OnCirclePhoneClickedAction;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.UserDoctor;

public class UserDoctorViewHolder extends RecyclerView.ViewHolder {
    private TextView allergoName;
    private TextView allergoSkills;
    private ImageView circlePhone;
    private ImageView circlePlace;
    private ImageView circleDetails;


    public UserDoctorViewHolder(@NonNull View view) {
        super(view);
        allergoName = view.findViewById(R.id.raw_choice_dr_name);
        allergoSkills = view.findViewById(R.id.raw_choice_dr_skill);
        circlePhone = view.findViewById(R.id.raw_choice_dr_circle_phone);
        circlePlace = view.findViewById(R.id.raw_choice_dr_circle_place);
        circleDetails = view.findViewById(R.id.raw_choice_dr_circle_three_points);
    }

    public TextView getAllergoName() {
        return allergoName;
    }

    public void setAllergoName(TextView allergoName) {
        this.allergoName = allergoName;
    }

    public TextView getAllergoSkills() {
        return allergoSkills;
    }

    public void setAllergoSkills(TextView allergoSkills) {
        this.allergoSkills = allergoSkills;
    }

    public ImageView getCirclePhone() {
        return circlePhone;
    }

    public void setCirclePhone(ImageView circlePhone) {
        this.circlePhone = circlePhone;
    }

    public ImageView getCirclePlace() {
        return circlePlace;
    }

    public void setCirclePlace(ImageView circlePlace) {
        this.circlePlace = circlePlace;
    }

    public ImageView getCircleDetails() {
        return circleDetails;
    }

    public void setCircleDetails(ImageView circleDetails) {
        this.circleDetails = circleDetails;
    }

    public void bind (UserDoctor userDoctor, OnCircleDetailsClickedAction onCircleDetailsClickedAction,
                      OnCircleMapClickedAction onCircleMapClickedAction, OnCirclePhoneClickedAction onCirclePhoneClickedAction){
        allergoName.setText(userDoctor.getDoctorName());
        allergoSkills.setText(userDoctor.getDoctorSkill());
        circlePhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCirclePhoneClickedAction.callDr(userDoctor);
            }
        });
        circleDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCircleDetailsClickedAction.goToDrDetails(userDoctor);
            }
        });
        circlePlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCircleMapClickedAction.goToDrMapLocation(userDoctor);
            }
        });
    }
}
