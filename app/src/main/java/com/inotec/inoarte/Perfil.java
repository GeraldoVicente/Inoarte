package com.inotec.inoarte;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;



import java.io.IOException;

public class Perfil extends AppCompatActivity {


    private Button btnChangeImage;
    private GridView gridView;
    private ImageView imageViewProfile, profilepic;
    private static final int PICK_IMAGE_REQUEST = 1;

    private static final String[] IMAGE_URLS = {
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUDLlfm0caar3Y1PEIxltfRabcQbKHHqnJ-XbQLl35yw&s",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSPWoLd8Za4aALn_S35crY9nsBUIlLsb8YqTIUCDrOgrD4qduHP6OqeKwqfVvwQ2-l-jOM&usqp=CAU",

            // Adicione mais URLs de imagens conforme necessário
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        imageViewProfile = findViewById(R.id.profilepic);
        btnChangeImage = findViewById(R.id.btnChangeImage);
        gridView = findViewById(R.id.gridView);

        btnChangeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        gridView.setAdapter(new ImageAdapter(this, IMAGE_URLS));

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(getApplicationContext(), "Image " + (position + 1) + " selected", Toast.LENGTH_SHORT).show();
                // Adicione aqui o código para abrir a imagem em tela cheia ou outra ação desejada
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                profilepic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
