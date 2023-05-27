package com.example.quanlyrapphim;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AddFilmActivity extends AppCompatActivity {
    private EditText edtFilmName;
    private EditText edtFilmType;
    private EditText edtFilmCountry;
    private EditText edtFilmCast;
    private EditText edtFilmContent;
    private ImageView imgFilmImage;
    private ImageButton btnEditImage;
    private Button btnAddFilm;
    private int SELECT_PICTURE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_film);

        btnEditImage = findViewById(R.id.addfilm_btnEditImage);
        imgFilmImage = findViewById(R.id.addFilm_Image);

        btnEditImage.setOnClickListener(view -> {
            imageChooser();
        });
    }

    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    // this function is triggered when user
    // selects the image from the imageChooser
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imgFilmImage.setImageURI(selectedImageUri);
                }
            }
        }
    }
}