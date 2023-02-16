package com.example.myapplicationdoctor.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.myapplicationdoctor.R;

public class PopupFirstConnexionAlertDialogFragment extends DialogFragment {
    private TextView txtOk;

    public PopupFirstConnexionAlertDialogFragment(){}

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_popup_first_connexion,container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        txtOk = view.findViewById(R.id.alertDialogBtnCancel);
        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    public static PopupFirstConnexionAlertDialogFragment newInstance(String title){
      PopupFirstConnexionAlertDialogFragment frag = new PopupFirstConnexionAlertDialogFragment();
      Bundle args = new Bundle();
      frag.setArguments(args);
        return frag;
    }




}
