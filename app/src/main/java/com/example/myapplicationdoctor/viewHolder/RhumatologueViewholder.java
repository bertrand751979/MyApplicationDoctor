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

public class RhumatologueViewholder extends RecyclerView.ViewHolder {
    private TextView rhumatoName;
    private TextView rhumatoSkills;
    private ImageView circlePhone;
    private ImageView circlePlace;
    private ImageView circleDetails;

    public RhumatologueViewholder(@NonNull View view) {
        super(view);
        rhumatoName = view.findViewById(R.id.raw_choice_dr_name);
        rhumatoSkills = view.findViewById(R.id.raw_choice_dr_skill);
        circlePhone = view.findViewById(R.id.raw_choice_dr_circle_phone);
        circlePlace = view.findViewById(R.id.raw_choice_dr_circle_place);
        circleDetails = view.findViewById(R.id.raw_choice_dr_circle_three_points);
    }

    public TextView getRhumatoName() {
        return rhumatoName;
    }

    public void setRhumatoName(TextView rhumatoName) {
        this.rhumatoName = rhumatoName;
    }

    public TextView getRhumatoSkills() {
        return rhumatoSkills;
    }

    public void setRhumatoSkills(TextView rhumatoSkills) {
        this.rhumatoSkills = rhumatoSkills;
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
        rhumatoName.setText(userDoctor.getDoctorName());
        rhumatoSkills.setText(userDoctor.getDoctorSkill());
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
