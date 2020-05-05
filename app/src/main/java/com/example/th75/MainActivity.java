package com.example.th75;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    Context context = null;
    EditText nimi;
    EditText kirjoita;
    TextView näkymä;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        nimi = (EditText) findViewById(R.id.editText);
        kirjoita = (EditText) findViewById(R.id.editText2);
        näkymä= (TextView) findViewById(R.id.textView);
    }

    public void readFile(View v){
        try {
            String s = nimi.getText().toString();
            String teksti;

            InputStream ips = context.openFileInput(s); //TODO arvo tähän
            InputStreamReader isr = new InputStreamReader(ips);

            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();


            while ((teksti = br.readLine()) != null) {
                sb.append(teksti).append("\n");
            }
            näkymä.setText(sb.toString());
            ips.close();


        }catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        }finally{
            System.out.println("Onnistui");
        }

    }

    public void writeFile(View v){
        try {
            String s = nimi.getText().toString();
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(s, context.MODE_PRIVATE));

            String w = kirjoita.getText().toString();
            osw.write(w); //ehkä w.getBytes()//
            osw.close();

        }catch (IOException e){
            Log.e("IOException", "Virhe syötteessä");
        }finally{
            System.out.println("Onnistui");
            System.out.println(context.getFilesDir());
        }
    }
}
