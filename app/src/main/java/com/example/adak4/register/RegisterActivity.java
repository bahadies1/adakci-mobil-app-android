package com.example.adak4.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.DhcpInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.format.Formatter;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import com.example.adak4.Encryption;
import com.example.adak4.MainActivity;
import com.example.adak4.R;
import com.example.adak4.login.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class RegisterActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    DatabaseReference databaseReference;

    @SuppressLint("SimpleDateFormat")
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

    CheckBox checkbox_allowMail, checkbox_allowSMS;
    Button btnKayitOlRegister;
    ImageView btnBack;
    EditText userMailAdress, userPassword, userPasswordConf, userName, userSurname;
    Spinner spinner_il, spinner_ilce;
    Boolean spinnerIlCheck,spinnerIlceCheck;

    private int year, month, dayOfMonth, mHour, mMinute;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private long pressedTime;

    @Override
    public void onBackPressed() {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:

                        firebaseAuth = FirebaseAuth.getInstance();
                        firebaseUser = firebaseAuth.getCurrentUser();
                        String userid = firebaseUser.getUid();
                        databaseReference = FirebaseDatabase.getInstance()
                                .getReference("Users")
                                .child(userid);
                        databaseReference.removeValue();
                        firebaseUser.delete();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder
                .setMessage("Eğer çıkış yaparsanız kayıt işleminiz iptal olacak. Onaylıyor musunuz?")
                .setPositiveButton("Çıkış yap", dialogClickListener)
                .setNegativeButton("Vazgeç", dialogClickListener).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        Timestamp userRegisterDate = new Timestamp(System.currentTimeMillis());
        Context context = RegisterActivity.this.getApplicationContext();
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        DhcpInfo dhcp = wm.getDhcpInfo();

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        btnBack = findViewById(R.id.btnBack);
        userMailAdress = findViewById(R.id.userMailAdress);
        userPassword = findViewById(R.id.userPassword);
        userPasswordConf = findViewById(R.id.userPasswordConf);
        userName = findViewById(R.id.userName);
        userSurname = findViewById(R.id.userSurname);
        checkbox_allowMail = findViewById(R.id.checkbox_allowMail);
        checkbox_allowSMS = findViewById(R.id.checkbox_allowSMS);
        btnKayitOlRegister = findViewById(R.id.btnKayitOlRegister);
        spinner_il = findViewById(R.id.spinner_il);
        spinner_ilce = findViewById(R.id.spinner_ilce);

        loadIlSpinner();
        loadIlceSpinner();

        spinnerIlCheck = false;

        spinner_il.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){
                    spinnerIlCheck = true;
                }
                else { spinnerIlCheck = false; }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerIlCheck = false;
            }
        });

        spinnerIlceCheck = false;

        spinner_ilce.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0){

                    spinnerIlceCheck = true;
                }
                else { spinnerIlceCheck = false; }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                spinnerIlceCheck = false;
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:

                                firebaseAuth = FirebaseAuth.getInstance();
                                firebaseUser = firebaseAuth.getCurrentUser();
                                String userid = firebaseUser.getUid();
                                databaseReference = FirebaseDatabase.getInstance()
                                        .getReference("Users")
                                        .child(userid);
                                databaseReference.removeValue();
                                firebaseUser.delete();
                                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                builder
                        .setMessage("Eğer çıkış yaparsanız kayıt işleminiz iptal olacak. Onaylıyor musunuz?")
                        .setPositiveButton("Çıkış yap", dialogClickListener)
                        .setNegativeButton("Vazgeç", dialogClickListener).show();

            }
        });

        btnKayitOlRegister.setOnClickListener(v -> {

            Encryption encryption = Encryption.getDefault("Key", "Salt", new byte[16]);

            String SuserName = userName.getText().toString();
            String SuserSurname = userSurname.getText().toString();
            String SuserMailAdress = userMailAdress.getText().toString();
            String SuserPassword = userPassword.getText().toString();
            String SuserPasswordConf = userPasswordConf.getText().toString();
            String sCity = spinner_il.getSelectedItem().toString();
            String sState = spinner_ilce.getSelectedItem().toString();

            String ipAdress = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
            String dhcpInfo = String.valueOf(dhcp);
            String SuserRegisterDate = sdf.format(userRegisterDate);
            String encrypteduserSurname = encryption.encryptOrNull(SuserSurname);
            String encryptedUsername = encryption.encryptOrNull(SuserName);
            String encryptedregisterPassword = encryption.encryptOrNull(SuserPassword);

            if (SuserMailAdress.isEmpty()) {
                userMailAdress.setError("Lütfen bir mail adresi girin");
                return;
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(userMailAdress.getText().toString()).matches()) {
                userMailAdress.setError("Lütfen geçerli bir mail adresi giriniz.");
                return;
            }
            if (SuserPassword.isEmpty()) {
                userPassword.setError("Lütfen bir şifre giriniz.");
                return;
            }
            if (!SuserPasswordConf.equals(SuserPassword)) {
                userPasswordConf.setError("Şifreler birbiri ile uyuşmuyor.");
                return;
            }
            if (SuserPassword.length() < 6 && SuserPassword.contains(" ")) {
                userPassword.setError("Şifreniz en az 6 karekterden oluşmalıdır");
                return;
            }
            if (SuserName.isEmpty()) {
                userName.setError("Lütfen bir kullanıcı adı giriniz.");
                return;
            }
            if (SuserName.length() < 2 && SuserName.contains(" ")) {
                userName.setError("Lütfen bir isim giriniz.");
                return;
            }
            if (SuserSurname.isEmpty()) {
                userName.setError("Lütfen bir soyadı giriniz.");
                return;
            }

            if (SuserSurname.length() < 2 && SuserName.contains(" ")) {
                userSurname.setError("Lütfen bir soyadı giriniz.");
                return;
            }

            if(!spinnerIlCheck){
                Toast.makeText(RegisterActivity.this, "Lütfen il seçiniz.", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!spinnerIlceCheck){
                Toast.makeText(RegisterActivity.this, "Lütfen ilçe seçiniz.", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth = FirebaseAuth.getInstance();
            firebaseUser = firebaseAuth.getCurrentUser();
            String userid = firebaseUser.getUid();

            HashMap<String, Object> hashMap = new HashMap<>();

            databaseReference = FirebaseDatabase.getInstance()
                    .getReference("Users")
                    .child(userid);

            hashMap.put("userMailAdress", SuserMailAdress);
            hashMap.put("userPassword", SuserPassword);
            hashMap.put("userName", SuserName);
            hashMap.put("userID", userid);
            hashMap.put("cityName",sCity);
            hashMap.put("stateName",sState);
            hashMap.put("userSurname", SuserSurname);
            hashMap.put("userRegisterDate", SuserRegisterDate);
            hashMap.put("ipAdress", ipAdress);

            boolean checked = ((CheckBox) checkbox_allowSMS).isChecked();
            if (checkbox_allowSMS.getId() == R.id.checkbox_allowSMS) {
                if (checked) {
                    hashMap.put("AdsSMSYes", firebaseUser.getPhoneNumber());
                }
            }
            if (checkbox_allowMail.getId() == R.id.checkbox_allowMail) {
                if (checked) {
                    hashMap.put("AdsMailYes", SuserMailAdress);
                }
            }

            databaseReference.updateChildren(hashMap);
            Toast.makeText(RegisterActivity.this, "Kayıt başarı ile oluşturuldu! Hoşgeldiniz.", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            finish();

        });

    }

    public void loadIlSpinner () {

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference CityRef = rootRef.collection("İller");
        List<String> cities = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, cities);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_il.setAdapter(adapter);
        CityRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("nameCity");
                        cities.add(0,"Lütfen il seçiniz");
                        cities.add(subject);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void loadIlceSpinner () {

        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        CollectionReference CityRef = rootRef.collection("İlçeler");
        List<String> cities = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, cities);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_ilce.setAdapter(adapter);
        CityRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        String subject = document.getString("nameState");
                        cities.add(0,"Lütfen ilçe seçiniz");
                        cities.add(subject);
                    }
                    adapter.notifyDataSetChanged();
                }
            }
        });
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


