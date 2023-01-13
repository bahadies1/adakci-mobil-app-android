package com.example.adak4.ui.home.menu.supermenu;

import static com.example.adak4.ui.home.menu.supermenu.SuperKatalogAdapter.SUPER_MENU_DESCRIPTION;
import static com.example.adak4.ui.home.menu.supermenu.SuperKatalogAdapter.SUPER_MENU_DESCRIPTION_TITLE;
import static com.example.adak4.ui.home.menu.supermenu.SuperKatalogAdapter.SUPER_MENU_IMAGE;
import static com.example.adak4.ui.home.menu.supermenu.SuperKatalogAdapter.SUPER_MENU_KEY;
import static com.example.adak4.ui.home.menu.supermenu.SuperKatalogAdapter.SUPER_MENU_NAME;
import static com.example.adak4.ui.home.menu.supermenu.SuperKatalogAdapter.SUPER_MENU_TITLE_IMAGE;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import com.example.adak4.R;

public class SuperMenuDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_super_menu_detail);
        getSupportActionBar().hide();

        ImageView supermenuBack = findViewById(R.id.supermenuBack);
        supermenuBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String SsupermenuDescription = intent.getStringExtra(SUPER_MENU_DESCRIPTION);
        String SsupermenuDescriptionTitle = intent.getStringExtra(SUPER_MENU_DESCRIPTION_TITLE);
        String SsupermenuTitleImage = intent.getStringExtra(SUPER_MENU_TITLE_IMAGE);

        String SsupermenuName = intent.getStringExtra(SUPER_MENU_NAME);
        String SsupermenuImage = intent.getStringExtra(SUPER_MENU_IMAGE);
        String SsupermenuKey = intent.getStringExtra(SUPER_MENU_KEY);

        TextView superMenuTitle = findViewById(R.id.superMenuTitle);
        TextView superMenuDescription = findViewById(R.id.superMenuDescription);
        TextView supermenuName = findViewById(R.id.supermenuName);
        ImageView supermenuImage = findViewById(R.id.supermenuImage);

       // Picasso.get().load(SsupermenuTitleImage).fit().centerInside().into(supermenuImage);
        Glide.with(this).load(SsupermenuTitleImage).into(supermenuImage);
        superMenuTitle.setText(SsupermenuDescriptionTitle);
        superMenuDescription.setText(SsupermenuDescription);
        supermenuName.setText(SsupermenuName);

    }
}