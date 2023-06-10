package com.ake.kronometre;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import com.ake.kronometre.databinding.ActivityMainBinding;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    Handler handler;
    Runnable runnable;
    int saniye = 0;
    int milisaniye = 0;
    int dakika = 0;
    Boolean durum = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.baslaButton.setOnClickListener(v -> {

            if(durum == false){
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        /*binding.kronometre.setText(String.valueOf(sayac));
                        sayac++;
                        binding.kronometre.setText(String.valueOf(sayac));
                        handler.postDelayed(runnable,1000);*/
                        milisaniye++;
                        if (milisaniye == 99) {
                            milisaniye = 0;
                            saniye++;
                        }
                        if (saniye == 60) {
                            saniye = 0;
                            dakika++;
                        }

                        binding.salise.setText(String.format(Locale.getDefault(), "%02d", milisaniye));
                        binding.saniye.setText(String.format(Locale.getDefault(), "%02d", saniye));
                        binding.dakika.setText(String.format(Locale.getDefault(), "%02d", dakika));

                        handler.postDelayed(this, 1); // 1 milisaniye sonra runnable'ı tekrar çağırır
                    }
                };
                handler.post(runnable);
                binding.baslaButton.setText("Durdur");
                Drawable yeniDrawble = getResources().getDrawable(R.drawable.pause);
                binding.imageView.setImageDrawable(yeniDrawble);
                durum = true;
            }
            else{
                // Toast.makeText(this,"Deneme",Toast.LENGTH_LONG).show();
                handler.removeCallbacks(runnable);
                Drawable yeniDrawble = getResources().getDrawable(R.drawable.start);
                binding.imageView.setImageDrawable(yeniDrawble);
                binding.baslaButton.setText("Başla");
                durum=false;
            }

        });

        binding.tekrarButton.setOnClickListener(v -> {
            handler.removeCallbacks(runnable);
            binding.dakika.setText("00");
            binding.saniye.setText("00");
            binding.salise.setText("00");

            dakika = 0;
            saniye = 0;
            milisaniye = 0;

            Drawable yeniDrawble = getResources().getDrawable(R.drawable.start);
            binding.imageView.setImageDrawable(yeniDrawble);
            binding.baslaButton.setText("Başlat");
            durum = false;
            //handler.post(runnable);

        });

    }

}