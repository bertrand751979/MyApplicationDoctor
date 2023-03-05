package com.example.myapplicationdoctor.viewHolder;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplicationdoctor.OnCircleDetailsClickedAction;
import com.example.myapplicationdoctor.R;
import com.example.myapplicationdoctor.model.UserDoctor;

public class SearchViewHolder extends RecyclerView.ViewHolder {
    private TextView vhSearchDrName;
    private TextView vhSearchCity;
    private TextView vhSearchOpenOffice;
    private TextView vhSearchDrSkill;
    private Button vhSearchGoToDetail;
    private TextView vhSearchCloseOffice;

    public SearchViewHolder(@NonNull View view) {
        super(view);
        vhSearchDrName = view.findViewById(R.id.raw_search_dr_name);
        vhSearchCity = view.findViewById(R.id.raw_search_dr_city);
        vhSearchOpenOffice = view.findViewById(R.id.raw_search_dr_openHour);
        vhSearchDrSkill = view.findViewById(R.id.raw_search_dr_skill);
        vhSearchGoToDetail = view.findViewById(R.id.raw_search_btn_go_to_detail);
        vhSearchCloseOffice = view.findViewById(R.id.raw_search_dr_closeHour);
    }

    public TextView getVhSearchDrName() {
        return vhSearchDrName;
    }

    public void setVhSearchDrName(TextView vhSearchDrName) {
        this.vhSearchDrName = vhSearchDrName;
    }

    public TextView getVhSearchCity() {
        return vhSearchCity;
    }

    public void setVhSearchCity(TextView vhSearchCity) {
        this.vhSearchCity = vhSearchCity;
    }

    public TextView getVhSearchOpenOffice() {
        return vhSearchOpenOffice;
    }

    public void setVhSearchOpenOffice(TextView vhSearchOpenOffice) {
        this.vhSearchOpenOffice = vhSearchOpenOffice;
    }

    public TextView getVhSearchDrSkill() {
        return vhSearchDrSkill;
    }

    public void setVhSearchDrSkill(TextView vhSearchDrSkill) {
        this.vhSearchDrSkill = vhSearchDrSkill;
    }

    public Button getVhSearchGoToDetail() {
        return vhSearchGoToDetail;
    }

    public void setVhSearchGoToDetail(Button vhSearchGoToDetail) {
        this.vhSearchGoToDetail = vhSearchGoToDetail;
    }

    public TextView getVhSearchCloseOffice() {
        return vhSearchCloseOffice;
    }

    public void setVhSearchCloseOffice(TextView vhSearchCloseOffice) {
        this.vhSearchCloseOffice = vhSearchCloseOffice;
    }

    public void bind(UserDoctor userDoctor, OnCircleDetailsClickedAction onCircleDetailsClickedAction){
        vhSearchDrName.setText(userDoctor.getDoctorName());
        vhSearchCity.setText(userDoctor.getDoctorCity());
        vhSearchOpenOffice.setText(userDoctor.getDoctorOpenOffice());
        vhSearchDrSkill.setText(userDoctor.getDoctorSkill());
        vhSearchGoToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCircleDetailsClickedAction.goToDrDetails(userDoctor);
            }
        });
        vhSearchCloseOffice.setText(userDoctor.getDoctorCloseOffice());
    }
}
