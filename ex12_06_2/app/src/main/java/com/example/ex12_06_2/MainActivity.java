package com.example.ex12_06_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.ex12_06_2.Phone;
import com.example.ex12_06_2.PhoneAdapter;
import com.example.ex12_06_2.PhoneService;
import com.example.ex12_06_2.R;
import com.example.ex12_06_2.Retrofit2Client;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PhoneAdapter phoneAdapter;
    LinearLayoutManager manager;
    private FloatingActionButton floatinBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        floatinBtn = findViewById(R.id.floatingBtn);


        floatinBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addContact();
            }
        });

        PhoneService phoneService =  Retrofit2Client.getInstance().getPhoneService();
        Call<List<Phone>> call = phoneService.findAll();

        call.enqueue(new Callback<List<Phone>>() {
            @Override
            public void onResponse(Call<List<Phone>> call, Response<List<Phone>> response) {
                List<Phone>  phoneList = response.body();
                recyclerView = findViewById(R.id.recyclerView);
                manager
                        = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);

                recyclerView.setLayoutManager(manager);

                phoneAdapter = new PhoneAdapter(phoneList);
                recyclerView.setAdapter(phoneAdapter);
                Log.d("data", "onResponse: ?????? ?????? ????????? :"+phoneList);

            }

            @Override
            public void onFailure(Call<List<Phone>> call, Throwable t) {

            }
        });
    }

    private void addContact(){
        View dialogView = LayoutInflater.from(getApplicationContext())
                .inflate(R.layout.layout_add_concat, null);

        final EditText etName = dialogView.findViewById(R.id.etname);
        final EditText etTel = dialogView.findViewById(R.id.ettel);

        AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
        dlg.setTitle("????????? ??????");
        dlg.setView(dialogView);
        dlg.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Phone phoneDto = new Phone();
                phoneDto.setName(etName.getText().toString());
                phoneDto.setTel(etTel.getText().toString());

                Log.d("insert", "onClick: ?????? ????????? ??? ??????" + phoneDto);

                PhoneService phoneService = Retrofit2Client.getInstance().getPhoneService();
                Call<Phone> call = phoneService.save(phoneDto);

                call.enqueue(new Callback<Phone>() {
                    @Override
                    public void onResponse(Call<Phone> call, Response<Phone> response) {

                        Log.d("insert response", "onResponse: phoneDto" + phoneDto);

                        phoneAdapter.addItem(response.body());
                    }

                    @Override
                    public void onFailure(Call<Phone> call, Throwable t) {

                    }
                });

                //createContact(etName.getText().toString(), etEmail.getText().toString());
            }
        });
        dlg.setNegativeButton("??????", null);
        dlg.show();


    }
}