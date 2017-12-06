package com.pratap.ninja.soundcheck.adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.pratap.ninja.soundcheck.MainActivity;
import com.pratap.ninja.soundcheck.R;
import com.pratap.ninja.soundcheck.models.Sound;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by darsh on 6/12/17.
 */

public class SoundAdapter extends RecyclerView.Adapter<SoundAdapter.SoundViewHolder> {

    private Context context;
    private ArrayList<Sound> tracks = new ArrayList<>();
    private static final String TAG = "NL";

    public SoundAdapter(Context context) {
        this.context = context;
    }

    public void updateTracks(ArrayList<Sound> tracks) {
        Log.d(TAG, "updateNews: " + tracks.size());
        this.tracks = tracks;
        notifyDataSetChanged();
    }

    @Override
    public SoundViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new SoundViewHolder(inflater.inflate(R.layout.item_list_tracks, parent, false));
    }

    @Override
    public void onBindViewHolder(SoundViewHolder holder, int position) {
        holder.bindView(tracks.get(position));
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    class SoundViewHolder extends RecyclerView.ViewHolder {

        TextView track_name, track_genre;
        Button play;
        public SoundViewHolder(View itemView) {
            super(itemView);
            track_genre = itemView.findViewById(R.id.track_genre);
            track_name = itemView.findViewById(R.id.track_name);
            play = itemView.findViewById(R.id.btn_play);
        }

        void bindView(final Sound sound) {
            track_name.setText(sound.getTitle());
            track_genre.setText(sound.getGenre());
            /*play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MediaPlayer mediaPlayer;
                    mediaPlayer = new MediaPlayer();
                    mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    try {
                        mediaPlayer.setDataSource(sound.getStreamUrl() + "?client_id=iq13rThQx5jx9KWaOY8oGgg1PUm9vp3J");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    mediaPlayer.start();
                }
            });*/
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new PlayerASync().execute(sound.getStreamUrl());
                }
            });
        }


        public class PlayerASync extends AsyncTask<String,Void,String> {
            ProgressDialog progressDialog;
            @Override
            protected String doInBackground(String... strings) {
                MediaPlayer mediaPlayer;
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                try {
                    mediaPlayer.setDataSource(strings[0] + "?client_id=iq13rThQx5jx9KWaOY8oGgg1PUm9vp3J");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mediaPlayer.start();
                return "yo";
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressDialog = new ProgressDialog(context);
                progressDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                progressDialog.dismiss();
            }
        }




    }
}
