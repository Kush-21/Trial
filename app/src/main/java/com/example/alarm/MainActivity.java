package com.example.alarm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button addal,start,end;
    private TextView textView,textView2;
    int q=0;
    private ItemViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addal = findViewById(R.id.button3);
        start = findViewById(R.id.start);
        end = findViewById(R.id.end);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.alarm2);
        viewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        viewModel.getselecteditem().observe(this,item ->{
            if(textView.getText().toString().length()==0){
                textView.setText(item);
            } else {
                textView2.setText(item);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String a = "kuch nahi";

                Intent intent1 = new Intent(MainActivity.this ,MyService.class);
                Intent intent2 = new Intent(MainActivity.this ,MyService.class);
                String d = textView.getText().toString();
                String e = textView2.getText().toString();
                if(!d.isEmpty()){
                    String[] arr1 = d.split(":");

//                    servicecaller(intent1,Integer.parseInt(arr1[0]),Integer.parseInt(arr1[1]));
                    startAlarmService(arr1);
                }
                if(!e.isEmpty()) {
                    String[] arr2 = e.split(":");
                    a = d.toString();

//                    servicecaller(intent2,Integer.parseInt(arr2[0]),Integer.parseInt(arr2[1]));
                    startAlarmService(arr2);
                }

                Toast.makeText(MainActivity.this, "Service Starting ......." +a, Toast.LENGTH_SHORT).show();
            }
        });
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this,MyService.class));
                Toast.makeText(MainActivity.this, "service ended", Toast.LENGTH_SHORT).show();
                Log.d("end","Service Ended");
            }
        });
    }

    public void count(View view){
        replacefragment(new Alarm111111());
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        intentFilter.addAction(Intent.ACTION_BATTERY_LOW);
        intentFilter.addAction(Intent.ACTION_BATTERY_OKAY);
        intentFilter.addAction(Intent.ACTION_NEW_OUTGOING_CALL);
    }

    private void replacefragment(Fragment alarm111111) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,alarm111111);
        fragmentTransaction.commit();
    }

    // saving the instance for rotation #######################
    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
//        Log.i(LOG_TAG,"in saved instance");
        String g = String.valueOf(textView.getText());
        CharSequence h = textView2.getText();
        outState.putString("count",g);
        outState.putCharSequence("text2",h);
    }
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String i = savedInstanceState.getString("count");
        CharSequence k = savedInstanceState.getCharSequence("text2");
        textView.setText(i);
        textView2.setText(k);
    }
    // #########################################################

    private void startAlarmService(String[] arr) {
        Intent intent = new Intent(this, MyService.class);

        intent.putExtra("alarmhr",Integer.parseInt(arr[0]));
        intent.putExtra("alarmmin",Integer.parseInt(arr[1]));
        startService(intent);
    }
}