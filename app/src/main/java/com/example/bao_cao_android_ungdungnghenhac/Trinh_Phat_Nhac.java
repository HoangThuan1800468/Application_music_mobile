package com.example.bao_cao_android_ungdungnghenhac;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;

public class Trinh_Phat_Nhac extends AppCompatActivity {

    TextView TenBH, TGPhat, TGHet;
    SeekBar CayThoiGian;
    Button btnPlay, btnStop, btnPrev, btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trinh__phat__nhac);
        AnhXa();
        btnPlay.setText("Pause");
        TenBH.setText(MainActivity.arraytcbh.get(MainActivity.i).getTenbh());
        DatGioHat();
        DatGioHet();
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.mdp.isPlaying()){
                    MainActivity.mdp.pause();
                    btnPlay.setText("Play");
                }
                else{
                    MainActivity.mdp.start();
                    btnPlay.setText("Pause");
                }
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mdp.stop();
                MainActivity.mdp.release();
                btnPlay.setText("Play");
                TaoBaiHat();
            }
        });
        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.i--;
                if(MainActivity.i < 0){
                    MainActivity.i = MainActivity.arraytcbh.size() - 1;
                }
                if(MainActivity.mdp.isPlaying()){
                    MainActivity.mdp.stop();
                }
                TaoBaiHat();
                MainActivity.mdp.start();
                DatGioHat();
                DatGioHet();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.i++;
                if(MainActivity.i > MainActivity.arraytcbh.size() - 1){
                    MainActivity.i = 0;
                }
                if(MainActivity.mdp.isPlaying()){
                    MainActivity.mdp.stop();
                }
                TaoBaiHat();
                MainActivity.mdp.start();
                DatGioHat();
                DatGioHet();
            }
        });
        CayThoiGian.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                MainActivity.mdp.seekTo(CayThoiGian.getProgress());
            }
        });
    }

    public void DatGioHat(){
        final Handler hd = new Handler();
        hd.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat gio = new SimpleDateFormat("mm:ss");
                TGPhat.setText(gio.format(MainActivity.mdp.getCurrentPosition()));
                CayThoiGian.setProgress(MainActivity.mdp.getCurrentPosition());
                MainActivity.mdp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        MainActivity.i++;
                        if(MainActivity.i > MainActivity.arraytcbh.size() - 1){
                            MainActivity.i = 0;
                        }
                        if(MainActivity.mdp.isPlaying()){
                            MainActivity.mdp.stop();
                        }
                        TaoBaiHat();
                        MainActivity.mdp.start();
                        DatGioHat();
                        DatGioHet();
                    }
                });
                hd.postDelayed(this, 100);
            }
        }, 100);
    }
    private void DatGioHet(){
        SimpleDateFormat gio = new SimpleDateFormat("mm:ss");
        TGHet.setText(gio.format(MainActivity.mdp.getDuration()));
        CayThoiGian.setMax(MainActivity.mdp.getDuration());
    }
    private void TaoBaiHat(){
        MainActivity.mdp = MediaPlayer.create(Trinh_Phat_Nhac.this, MainActivity.arraytcbh.get(MainActivity.i).getFile());
        TenBH.setText(MainActivity.arraytcbh.get(MainActivity.i).getTenbh());
    }

    private void AnhXa(){
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPrev = (Button) findViewById(R.id.btnPrev);
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        CayThoiGian = (SeekBar) findViewById(R.id.CayThoiGian);
        TGPhat = (TextView) findViewById(R.id.TGPhat);
        TGHet = (TextView) findViewById(R.id.TGHet);
        TenBH = (TextView) findViewById(R.id.TenBH);
    }
}