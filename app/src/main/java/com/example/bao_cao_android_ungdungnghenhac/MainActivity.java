package com.example.bao_cao_android_ungdungnghenhac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //khai báo biến
    public ListView lvdsn;
    public static ArrayList<classBaiHat> arraytcbh, arraydsyt;
    public static AdapterNhac adn;
    public static MediaPlayer mdp;
    public static int i=0;
    RadioButton Allbh, Dsyt;
    RadioGroup ChonDS;
    Button btnChon, btnTat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        ThemBaiHat();
        arraydsyt = new ArrayList<>();
        btnChon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Allbh.isChecked()){
                    adn = new AdapterNhac(MainActivity.this,R.layout.dong_nhac, arraytcbh);
                    lvdsn.setAdapter(adn);

                    lvdsn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int vitri, long id) {
                            if(mdp != null && mdp.isPlaying()) {
                                mdp.stop();
                                mdp = MediaPlayer.create(MainActivity.this, arraytcbh.get(vitri).getFile());
                                mdp.start();
                            }
                            else {
                                mdp = MediaPlayer.create(MainActivity.this, arraytcbh.get(vitri).getFile());
                                mdp.start();
                            }
                            Intent intent = new Intent(MainActivity.this, Trinh_Phat_Nhac.class);
                            startActivity(intent);
                            i = vitri;
                        }
                    });

                    lvdsn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int vitri, long id) {
                            arraydsyt.add(arraytcbh.get(vitri));
                            Toast.makeText(MainActivity.this, "Đã thêm vào danh sách ưa thích", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                }
                else{
                    adn = new AdapterNhac(MainActivity.this,R.layout.dong_nhac, arraydsyt);
                    lvdsn.setAdapter(adn);
                    lvdsn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int vitri, long id) {
                            if (mdp == null){
                                mdp = MediaPlayer.create(MainActivity.this, arraydsyt.get(vitri).getFile());
                                mdp.start();
                            }
                            else{
                                mdp.stop();
                                mdp = MediaPlayer.create(MainActivity.this, arraydsyt.get(vitri).getFile());
                                mdp.start();
                            }
                            Intent intent = new Intent(MainActivity.this, Trinh_Phat_Nhac_2.class);
                            startActivity(intent);
                            i = vitri;
                        }
                    });
                    lvdsn.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                        @Override
                        public boolean onItemLongClick(AdapterView<?> parent, View view, int vitri, long id) {
                            arraydsyt.remove(vitri);
                            adn.notifyDataSetChanged();
                            Toast.makeText(MainActivity.this, "Đã xóa", Toast.LENGTH_SHORT).show();
                            return true;
                        }
                    });
                }
            }
        });

        btnTat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mdp != null && mdp.isPlaying()){
                    mdp.stop();
                }else{
                    Toast.makeText(MainActivity.this, "Không có bài hát nào đang phát", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    public void ThemBaiHat(){
        arraytcbh = new ArrayList<>();
        arraytcbh.add(new classBaiHat("Cõi Vắng", R.raw.coi_vang));
        arraytcbh.add(new classBaiHat("Để Nhớ Một Thời Ta Đã Yêu", R.raw.de_nho_mot_thoi_ta_da_yeu));
        arraytcbh.add(new classBaiHat("Dường Như Ta Đã", R.raw.duong_nhu_ta_da));
        arraytcbh.add(new classBaiHat("Giấc Mơ Mùa Thu", R.raw.giac_mo_mua_thu));
        arraytcbh.add(new classBaiHat("Một Cõi Tình Phai", R.raw.mot_coi_tinh_phai));
        arraytcbh.add(new classBaiHat("Ngơ Ngác Cỏ Mây", R.raw.ngo_ngac_co_may));
        arraytcbh.add(new classBaiHat("Nỗi Nhớ Vô Hình", R.raw.noi_nho_vo_hinh));
        arraytcbh.add(new classBaiHat("Phố Mùa Đông", R.raw.pho_mua_dong));
        arraytcbh.add(new classBaiHat("Riêng Một Góc Trời", R.raw.rieng_mot_goc_troi));
        arraytcbh.add(new classBaiHat("Ru Em Từng Ngón Xuân Nồng", R.raw.ru_em_tung_ngon_xuan_nong));
        arraytcbh.add(new classBaiHat("Sao Ta Lặng Im", R.raw.sao_ta_lang_im));
        arraytcbh.add(new classBaiHat("Thanh Xuân Thuộc Về Ai", R.raw.thanh_xuan_thuoc_ve_ai));
    }
    public void AnhXa(){
        lvdsn = (ListView) findViewById(R.id.lvTatCaNhac);
        Allbh = (RadioButton) findViewById(R.id.Allbh);
        Dsyt = (RadioButton) findViewById(R.id.Dsbh);
        ChonDS = (RadioGroup) findViewById(R.id.ChonDS);
        btnChon = (Button) findViewById(R.id.btnChon);
        btnTat = (Button) findViewById(R.id.btnTat);
    }
//    public void KiemTra(){
//        for(int kt = 0; kt <= arraydsyt.size(); kt++){
//            String a = arraydsyt.get(kt).getTenbh();
//            Toast.makeText(this, a.toString(), Toast.LENGTH_SHORT).show();
//        }
//    }
}