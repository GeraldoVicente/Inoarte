package com.inotec.inoarte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity { CalendarView calendarView;
    private TextView text_tela_cadastro;
    private TextView esqueciSenha;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        IniciarComponentes();

        text_tela_cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,FormRegisto.class);
                startActivity(intent);

            }
        });
        esqueciSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( MainActivity.this,ResetSenha.class);
                startActivity(intent);
            }
        });
    }

    private  void  IniciarComponentes(){
        text_tela_cadastro = findViewById(R.id.text_tela_cadastro);
        esqueciSenha = findViewById(R.id.esqueciSenha);
    }
}