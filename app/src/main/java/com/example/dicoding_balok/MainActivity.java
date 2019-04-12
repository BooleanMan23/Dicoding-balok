package com.example.dicoding_balok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String STATE_RESULT = "state_result";
    EditText panjangEditText, lebarEditText, tinggiEditText;
    Button hasilButton;
    TextView hasilTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    panjangEditText = (EditText) findViewById(R.id.edt_length);
    lebarEditText = (EditText) findViewById(R.id.edt_width);
    tinggiEditText = (EditText) findViewById(R.id.edt_height);
    hasilButton = (Button) findViewById(R.id.btn_calculate);
    hasilTextView = (TextView) findViewById(R.id.tv_result);

        if (savedInstanceState != null) {
            String result = savedInstanceState.getString(STATE_RESULT);
            hasilTextView.setText(result);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_calculate){
            String inputPanjang = panjangEditText.getText().toString().trim();
            String inputLebar = lebarEditText.getText().toString().trim();
            String inputTinggi = tinggiEditText.getText().toString().trim();

            //check jika salah satu field ada yg kosong
            boolean apakahFieldKosong = false;
            if(TextUtils.isEmpty(inputPanjang)){
                apakahFieldKosong = true;
                panjangEditText.setError("Panjang balok  tidak boleh kosong");
            }

            if(TextUtils.isEmpty(inputLebar)){
                apakahFieldKosong = true;
                lebarEditText.setError("Lebar balok tidak boleh kosong");
            }

            if(TextUtils.isEmpty(inputTinggi)){
                apakahFieldKosong = true;
                tinggiEditText.setText("Tinggi balok tidak boleh kosong");
            }

            boolean isInvalidDouble = false;
            Double panjang = toDouble(inputPanjang);
            Double lebar = toDouble(inputLebar);
            Double tinggi = toDouble(inputTinggi);

            if(panjang == null){
                isInvalidDouble = true;
                panjangEditText.setError("Panjang harus berisi nomor yang valid");
            }
            if(lebar == null){
                isInvalidDouble = true;
                lebarEditText.setError("Lebar harus berisi nomor yang valid");
            }
            if(tinggi == null){
                isInvalidDouble = true;
                tinggiEditText.setError("Tinggi harus berisi nomor yang valid");
            }

            //jika tidak ada field yang kosong atau tidak ada inputan yang salah
            if(!isInvalidDouble && !apakahFieldKosong){
                double volume = panjang * lebar * tinggi;
                hasilTextView.setText(String.valueOf(volume));
            }





        }

    }

    Double toDouble(String str) {
        try {
            return Double.valueOf(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_RESULT, hasilTextView.getText().toString());
    }
}
