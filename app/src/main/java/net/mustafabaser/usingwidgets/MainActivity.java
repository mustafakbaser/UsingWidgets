package net.mustafabaser.usingwidgets;

import androidx.appcompat.app.AppCompatActivity;

import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SeekBar;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.timepicker.MaterialTimePicker;
import com.google.android.material.timepicker.TimeFormat;

import net.mustafabaser.usingwidgets.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private boolean checkToggle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.buttonOku.setOnClickListener(view -> {
            String receivedData = binding.editTextGirdi.getText().toString();
            binding.textViewSonuc.setText(receivedData);
        });

        binding.buttonResim1.setOnClickListener(view -> {
            binding.imageView.setImageResource(R.drawable.icon1);
        });

        binding.buttonResim2.setOnClickListener(view -> {
            //binding.imageView.setImageResource(R.drawable.icon2);
            // Görsel isminden görseli getirme
            binding.imageView.setImageResource(getResources().getIdentifier("icon2", "drawable", getPackageName()));
        });

        // Progress Bar Visibility
        binding.buttonBasla.setOnClickListener(view -> {
            binding.progressBar.setVisibility(View.VISIBLE);
        });

        binding.buttonDur.setOnClickListener(view -> {
            binding.progressBar.setVisibility(View.INVISIBLE);
        });

        // Using Switch
        binding.switch1.setOnCheckedChangeListener((bSwitch, isChecked) -> {
            if(isChecked){
                Log.e("Sonuç", "Switch: ON");
            }else{
                Log.e("Sonuç", "Switch: OFF");
            }
        });

        binding.toggleButton.addOnButtonCheckedListener(((group, checkedId, isChecked) -> {
            checkToggle = isChecked;
            if(checkToggle){
                Button selectedButton = findViewById(binding.toggleButton.getCheckedButtonId()); // checkedId = binding.toggleButton.getCheckedButtonId() ikisi aynı sonucu verir ama binding daha kesin
                String whichButton = selectedButton.getText().toString();
                Log.e("Sonuç", "Toogle Durum: " + whichButton);
            }
        }));

        ArrayList<String> ulkeler = new ArrayList<>();
        ulkeler.add("Türkiye");
        ulkeler.add("Macaristan");
        ulkeler.add("İspanya");

        // Adapter ile Auto Complete listeyi tanımlama ve yukarıdaki ülkeri atama
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ulkeler);
        binding.autoCompleteTextView.setAdapter(arrayAdapter);

        binding.textViewSlider.setText("Slider : " + binding.slider.getProgress());

        binding.slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                binding.textViewSlider.setText("Slider : " + i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        binding.buttonSaat.setOnClickListener(view -> {
            MaterialTimePicker timePicker = new MaterialTimePicker.Builder()
                    .setTitleText("Saat Seç")
                    .setTimeFormat(TimeFormat.CLOCK_24H)
                    .build();

            timePicker.show(getSupportFragmentManager(), "");

            timePicker.addOnPositiveButtonClickListener(view1 -> {
                binding.editTextSaat.setText(timePicker.getHour() + "." + timePicker.getMinute());
            });
        });

        binding.buttonTarih.setOnClickListener(view -> {
            MaterialDatePicker datePicker = MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Tarih Seç")
                    .build();

            datePicker.show(getSupportFragmentManager(), "");

            datePicker.addOnPositiveButtonClickListener(view1 -> {
                // Date Format (DD/MM/YYYY):
                SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd/MM/yyyy", Locale.getDefault());
                String date = dateFormat.format(view1);
               binding.editTextTarih.setText(date);
            });
        });

        binding.buttonGoster.setOnClickListener(v -> {
            Log.e("Sonuç", "Switch Durum: " + binding.switch1.isChecked());
            if(checkToggle){
                Button selectedButton = findViewById(binding.toggleButton.getCheckedButtonId()); // checkedId = binding.toggleButton.getCheckedButtonId() ikisi aynı sonucu verir ama binding daha kesin
                String whichButton = selectedButton.getText().toString();
                Log.e("Sonuç", "Toogle Durum: " + whichButton);
            }
            String ulke = binding.autoCompleteTextView.getText().toString();
            Log.e("Sonuç", "Ülke : " + ulke);
            Log.e("Sonuç", "Slider : " + binding.slider.getProgress());
        });
    }
}