package com.example.adak4.ui.home.menu;

import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_BRAND;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_DESCRIPTION;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_IMAGE;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_NAME;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_PRICE;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_QUANTITY;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_SHIP_DESCROPTION;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_SHIP_IMAGE;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_SIZEL;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_SIZEM;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_SIZEXL;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_TYPE;
import static com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter.PRODUCT_KEY;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.example.adak4.MainActivity;
import com.example.adak4.R;
import com.example.adak4.login.LoginActivity;
import com.example.adak4.register.PhoneAuthActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UrunDetayActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    private int year, month, dayOfMonth, mHour, mMinute;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    TextView txtSizeM, txtSizeL, txtSizeXL;
    Button btnKayitOl, btnGirisYap;
    ImageView checkM,checkL,checkXL;
    CardView sizeM,sizeL,sizeXL;
    TextView txtMKilos,txtLKilos,txtXLKilos,txtTotalPrice;

    boolean checkMMarked,checkLMarked,checkXLMarked = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_urun_detay);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        String productName = intent.getStringExtra(PRODUCT_NAME);
        String productType = intent.getStringExtra(PRODUCT_TYPE);
        String productQuantity = intent.getStringExtra(PRODUCT_QUANTITY);
        String productImage = intent.getStringExtra(PRODUCT_IMAGE);
        String productDescription = intent.getStringExtra(PRODUCT_DESCRIPTION);
        String productBrand = intent.getStringExtra(PRODUCT_BRAND);
        String productPrice = intent.getStringExtra(PRODUCT_PRICE);
        String productShipDescription = intent.getStringExtra(PRODUCT_SHIP_DESCROPTION);
        String productShipImage = intent.getStringExtra(PRODUCT_SHIP_IMAGE);
        String productKey =  intent.getStringExtra(PRODUCT_KEY);
        String productSizeM  =  intent.getStringExtra(PRODUCT_SIZEM);
        String productSizeL =  intent.getStringExtra(PRODUCT_SIZEL);
        String productSizeXL =  intent.getStringExtra(PRODUCT_SIZEXL);

        checkM = findViewById(R.id.checkM);
        checkL = findViewById(R.id.checkL);
        checkXL = findViewById(R.id.checkXL);

        sizeM = findViewById(R.id.sizeM);
        sizeL = findViewById(R.id.sizeL);
        sizeXL = findViewById(R.id.sizeXL);

        txtSizeM = findViewById(R.id.txtSizeM);
        txtSizeL = findViewById(R.id.txtSizeL);
        txtSizeXL = findViewById(R.id.txtSizeXL);

        txtMKilos = findViewById(R.id.txtMKilos);
        txtLKilos = findViewById(R.id.txtLKilos);
        txtXLKilos = findViewById(R.id.txtXLKilos);

        txtTotalPrice = findViewById(R.id.txtTotalPrice);

        ImageView close_guest = findViewById(R.id.close_guest);

        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnKayitOl = findViewById(R.id.btnKayitOl);
        btnKayitOl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), PhoneAuthActivity.class));
                finish();
            }
        });

        btnGirisYap = findViewById(R.id.btnGirisYap);
        btnGirisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        float floatKGPrice = Float.parseFloat(productPrice);
        float PriceSizeM = Float.parseFloat(productSizeM);
        float PriceSizeL = Float.parseFloat(productSizeL);
        float PriceSizeXL = Float.parseFloat(productSizeXL);

        TextView textView40 = findViewById(R.id.textView40);
        TextView textView41 = findViewById(R.id.textView41);
        TextView textView45 = findViewById(R.id.textView45);
        ImageView imageView23 = findViewById(R.id.imageView23);
        TextView txtMKilos = findViewById(R.id.txtMKilos);
        TextView txtLKilos = findViewById(R.id.txtLKilos);
        TextView txtXLKilos = findViewById(R.id.txtXLKilos);

        Picasso.get().load(productImage).fit().centerInside().into(imageView23);

        textView40.setText(productName);
        textView45.setText(productDescription);

        textView41.setText(new StringBuilder().append(productPrice).append(" TL/KG"));
        txtMKilos.setText(new StringBuilder().append(productSizeM).append(" KG"));
        txtLKilos.setText(new StringBuilder().append(productSizeL).append(" KG"));
        txtXLKilos.setText(new StringBuilder().append(productSizeXL).append(" KG"));

        sizeM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkM.setVisibility(View.VISIBLE);
                checkMMarked = true;
                float subTotal = PriceSizeM * floatKGPrice;
                String totalPrice = Float.toString(subTotal);
                txtTotalPrice.setText(new StringBuilder().append(totalPrice).append(" TL"));
                txtSizeM.setTextColor(Color.BLACK);

                txtMKilos.setTextColor(Color.BLACK);
                txtLKilos.setTextColor(getResources().getColor(R.color.teal_700));
                txtXLKilos.setTextColor(getResources().getColor(R.color.teal_700));

                txtSizeL.setTextColor(getResources().getColor(R.color.teal_700));
                txtSizeXL.setTextColor(getResources().getColor(R.color.teal_700));
                checkL.setVisibility(View.INVISIBLE);
                checkLMarked = false;
                checkXL.setVisibility(View.INVISIBLE);
                checkXLMarked = false;
            }
        });

        sizeL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkL.setVisibility(View.VISIBLE);
                checkLMarked = true;
                float subTotal = PriceSizeL * floatKGPrice;
                String totalPrice = Float.toString(subTotal);
                txtTotalPrice.setText(new StringBuilder().append(totalPrice).append(" TL"));

                txtSizeL.setTextColor(Color.BLACK);
                txtLKilos.setTextColor(Color.BLACK);
                txtMKilos.setTextColor(getResources().getColor(R.color.teal_700));
                txtXLKilos.setTextColor(getResources().getColor(R.color.teal_700));
                txtSizeM.setTextColor(getResources().getColor(R.color.teal_700));
                txtSizeXL.setTextColor(getResources().getColor(R.color.teal_700));
                checkM.setVisibility(View.INVISIBLE);
                checkLMarked = false;
                checkXL.setVisibility(View.INVISIBLE);
                checkXLMarked = false;
            }
        });

        sizeXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkXL.setVisibility(View.VISIBLE);
                checkXLMarked = true;

                float subTotal = PriceSizeXL * floatKGPrice;
                String totalPrice = Float.toString(subTotal);
                txtTotalPrice.setText(new StringBuilder().append(totalPrice).append(" TL"));

                txtSizeXL.setTextColor(Color.BLACK);
                txtLKilos.setTextColor(getResources().getColor(R.color.teal_700));
                txtMKilos.setTextColor(getResources().getColor(R.color.teal_700));
                txtSizeM.setTextColor(getResources().getColor(R.color.teal_700));
                txtSizeL.setTextColor(getResources().getColor(R.color.teal_700));
                txtSizeXL.setTextColor(Color.BLACK);
                checkM.setVisibility(View.INVISIBLE);
                checkMMarked = false;
                checkL.setVisibility(View.INVISIBLE);
                checkLMarked = false;

            }
        });

        Button btnAdakSatinAl = findViewById(R.id.btnAdakSatinAl);
        btnAdakSatinAl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkMMarked == true || checkLMarked == true || checkXLMarked == true)
                {
                    firebaseAuth = FirebaseAuth.getInstance();
                    firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                    firebaseUser = firebaseAuth.getCurrentUser();
                    if (firebaseUser != null) {
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        firebaseUser = firebaseAuth.getCurrentUser();
                        String userid = firebaseUser.getUid();
                        databaseReference = FirebaseDatabase.getInstance()
                                .getReference("Orders")
                                .push();
                        Map<String, Object> hashMap = new HashMap<>();
                        hashMap.put("UID",userid);
                        hashMap.put("userMailAdress",firebaseUser.getEmail());
                        databaseReference.updateChildren(hashMap);
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    } else {
                        ConstraintLayout layoutGirisYap = findViewById(R.id.layoutGirisYap1);
                        layoutGirisYap.setVisibility(View.VISIBLE);
                        close_guest.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                layoutGirisYap.setVisibility(View.GONE);
                            }
                        });
                    }
                }
                else {
                    Toast.makeText(UrunDetayActivity.this, "Lütfen boyut seçiniz.", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });

     //  selectTime.setOnClickListener(new View.OnClickListener() {
     //      @Override
     //      public void onClick(View view) {
     //          TimePicker(timeText, UrunDetayActivity.this);
     //      }
     //  });

     //  selectDate.setOnClickListener(new View.OnClickListener() {
     //      @Override
     //      public void onClick(View view) {

     //          DatePickler(dateTxt, UrunDetayActivity.this);

     //      }
     //  });

    }

    public void TimePicker(TextView timeText, Context context) {

        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(context,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        timeText.setText(hourOfDay + ":" + minute);

                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();

    }

    public void DatePickler(TextView dateTxt, Context context) {


        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        dateTxt.setText(day + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.show();

    }
}
