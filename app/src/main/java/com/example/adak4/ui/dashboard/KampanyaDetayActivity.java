package com.example.adak4.ui.dashboard;

import static com.example.adak4.ui.dashboard.KampanyalarAdapter.KAMPANYA_CODE;
import static com.example.adak4.ui.dashboard.KampanyalarAdapter.KAMPANYA_DESCRIPTION;
import static com.example.adak4.ui.dashboard.KampanyalarAdapter.KAMPANYA_IMAGE;
import static com.example.adak4.ui.dashboard.KampanyalarAdapter.KAMPANYA_KEY;
import static com.example.adak4.ui.dashboard.KampanyalarAdapter.KAMPANYA_NAME;
import static com.example.adak4.ui.dashboard.KampanyalarAdapter.KAMPANYA_TITLE;
import static com.example.adak4.ui.dashboard.KampanyalarAdapter.KAMPANYA_TITLE_IMAGE;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_BRAND;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_DESCRIPTION;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_IMAGE;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_KEY;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_NAME;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_PRICE;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_QUANTITY;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_SHIP_DESCROPTION;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_SHIP_IMAGE;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_SIZEL;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_SIZEM;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_SIZEXL;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_TYPE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.adak4.R;

public class KampanyaDetayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kampanya_detay);
        getSupportActionBar().hide();

        ImageView kampanyaBack = findViewById(R.id.kampanyaBack);
        kampanyaBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String SkampanyaName = intent.getStringExtra(KAMPANYA_NAME);
        String SkampanyaTitleImage = intent.getStringExtra(KAMPANYA_TITLE_IMAGE);
        String SkampanyaTitle = intent.getStringExtra(KAMPANYA_TITLE);
        String SkampanyaDescription = intent.getStringExtra(KAMPANYA_DESCRIPTION);

        String SkampanyaCode = intent.getStringExtra(KAMPANYA_CODE);
        String SkampanyaImage = intent.getStringExtra(KAMPANYA_IMAGE);
        String SkampanyaKey = intent.getStringExtra(KAMPANYA_KEY);

        TextView kampanyaName = findViewById(R.id.kampanyaName);
        TextView kampanyaDescription = findViewById(R.id.kampanyaDescription);
        TextView kampanyaTitle = findViewById(R.id.kampanyaTitle);
        ImageView kampanyaTitleImage = findViewById(R.id.kampanyaTitleImage);

        Glide.with(this).load(SkampanyaTitleImage).into(kampanyaTitleImage);
        kampanyaName.setText(SkampanyaName);
        kampanyaTitle.setText(SkampanyaTitle);
        kampanyaDescription.setText(SkampanyaDescription);


    }
}