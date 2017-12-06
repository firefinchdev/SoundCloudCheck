package com.pratap.ninja.soundcheck;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.pratap.ninja.soundcheck.adapters.SoundAdapter;
import com.pratap.ninja.soundcheck.api.SoundService;
import com.pratap.ninja.soundcheck.models.Sound;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    EditText etSearch;
    Button btnSearch;
    public static final String TAG = "MAIN";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_tracks);
        etSearch = findViewById(R.id.et_search);
        btnSearch = findViewById(R.id.btnSearch);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        final SoundAdapter adapter = new SoundAdapter(this);
        rv.setAdapter(adapter);



        SoundService.getSoundApi().getTracks().enqueue(new Callback<ArrayList<Sound>>() {
            @Override
            public void onResponse(Call<ArrayList<Sound>> call, Response<ArrayList<Sound>> response) {
                Log.d(TAG,"here =====> " + response.body());
                adapter.updateTracks(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Sound>> call, Throwable t) {

            }
        });


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = etSearch.getText().toString();
                SoundService.getSoundApi().getSearchResults2(search).enqueue(new Callback<ArrayList<Sound>>() {
                    @Override
                    public void onResponse(Call<ArrayList<Sound>> call, Response<ArrayList<Sound>> response) {
                        Log.d(TAG,"here =====> " + response.body());
                        adapter.updateTracks(response.body());
                    }

                    @Override
                    public void onFailure(Call<ArrayList<Sound>> call, Throwable t) {

                    }
                });
            }
        });

    }

    public class PlayerASync extends AsyncTask<String,String,String> {
        ProgressDialog progressDialog;
        @Override
        protected String doInBackground(String... strings) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressDialog.dismiss();
        }
    }


}






