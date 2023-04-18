package com.example.alarm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Alarm222222 extends Fragment {

    ItemViewModel viewModel;
    RecyclerView recyclerView;

    public Alarm222222() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_alarm222222, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(ItemViewModel.class);

        getParentFragmentManager().setFragmentResultListener("alarm1", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {

                String data = result.getString("key1");

                viewModel.setAlarm(data);
            }
        });

        String[] items = {"Apple", "Banana", "Orange", "Mango", "Grapes"};

        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        RecyclerAdapter ra = new RecyclerAdapter(items);
        recyclerView.setAdapter(ra);

        return view;
    }
}