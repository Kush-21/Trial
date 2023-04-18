package com.example.alarm;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
//import androidx.fragment.app.TimePickerDialog;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Alarm111111 extends Fragment {

    private Button done;
    private TextView selectedTime;
    private TimePicker timePicker;
    View view;
    ItemViewModel viewModel;

    public interface Alarm1Listener {
        void onAlarm1sent(String alarm1);
    }
    public Alarm111111() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_alarm111111, container, false);
        done = view.findViewById(R.id.button5);
        selectedTime = view.findViewById(R.id.textView2);
        timePicker = view.findViewById(R.id.timepicker);

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String geth = String.valueOf(timePicker.getHour());
                int get = timePicker.getMinute();
                String getm;
                if(get < 10){
                    getm = "0"+String.valueOf(timePicker.getMinute());
                }
                else{
                    getm = String.valueOf(get);
                }
//                String getm = String.valueOf(timePicker.getMinute());
                String time = (geth +":"+getm);

                selectedTime.setText(time);
                Toast.makeText(getActivity(), "Alarm Set for "+time, Toast.LENGTH_SHORT).show();
                Bundle alarm = new Bundle();
                alarm.putString("key1",time);

                getParentFragmentManager().setFragmentResult("alarm1",alarm);

                Fragment secfrag = new Alarm222222();
                FragmentTransaction fm = getActivity().getSupportFragmentManager().beginTransaction();
                fm.replace(R.id.frameLayout,secfrag).commit();
            }

        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        done = view.findViewById(R.id.button5);
        selectedTime = view.findViewById(R.id.textView2);
        timePicker = view.findViewById(R.id.timepicker);

    }

}
