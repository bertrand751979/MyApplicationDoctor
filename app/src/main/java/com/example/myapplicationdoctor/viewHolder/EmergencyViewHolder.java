package com.example.myapplicationdoctor.viewHolder;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnPhoneImageActionClicked;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.Emergency;

public class EmergencyViewHolder extends RecyclerView.ViewHolder {
    private TextView vhEmergencyName;
    private TextView vhEmergencyPhoneNumber;
    private RelativeLayout vhPhone;
    public EmergencyViewHolder(@NonNull View view) {
        super(view);
        vhEmergencyName = view.findViewById(R.id.raw_emergency_name);
        vhEmergencyPhoneNumber = view.findViewById(R.id.raw_emergency_number);
        vhPhone = view.findViewById(R.id.raw_relative_layout_phone);

    }

    public TextView getVhEmergencyName() {
        return vhEmergencyName;
    }

    public void setVhEmergencyName(TextView vhEmergencyName) {
        this.vhEmergencyName = vhEmergencyName;
    }

    public TextView getVhEmergencyPhoneNumber() {
        return vhEmergencyPhoneNumber;
    }

    public void setVhEmergencyPhoneNumber(TextView vhEmergencyPhoneNumber) {
        this.vhEmergencyPhoneNumber = vhEmergencyPhoneNumber;
    }

    public RelativeLayout getVhPhone() {
        return vhPhone;
    }

    public void setVhPhone(RelativeLayout vhPhone) {
        this.vhPhone = vhPhone;
    }

    public void bind(Emergency emergency, OnPhoneImageActionClicked onPhoneImageActionClicked){
        vhEmergencyName.setText(emergency.getEmergencyName());
        vhEmergencyPhoneNumber.setText(emergency.getEmergencyPhoneNumber());
        vhPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPhoneImageActionClicked.call(emergency);
            }
        });
    }
}
