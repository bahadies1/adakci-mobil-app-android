package com.example.adak4.ui.profile;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.adak4.MainActivity;
import com.example.adak4.R;
import com.example.adak4.databinding.FragmentProfileBinding;
import com.example.adak4.login.LoginActivity;
import com.example.adak4.register.PhoneAuthActivity;
import com.example.adak4.register.RegisterActivity;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class NotificationsFragment extends Fragment {

    private FragmentProfileBinding binding;

    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    Button btnCikisYap;
    Button btnRegisterProile, btnLoginProfile;
    TextView txtUserName;
    LinearLayout guestLayout;
    CardView userLayout2;
    RelativeLayout userLayout;

    @Override
    public void onStart() {
        super.onStart();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        firebaseUser = firebaseAuth.getCurrentUser();

        userLayout2 = binding.userLayout2;
        guestLayout = binding.guestLayout;
        userLayout = binding.userLayout;
        btnRegisterProile = binding.btnRegisterProfile;
        btnLoginProfile = binding.btnLoginProfile;


        if (firebaseUser == null) {

            userLayout.setVisibility(View.INVISIBLE);
            userLayout2.setVisibility(View.INVISIBLE);
            guestLayout.setVisibility(View.VISIBLE);

            btnLoginProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().startActivity(new Intent(getActivity().getApplicationContext(), LoginActivity.class));
                }
            });

            btnRegisterProile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    getActivity().startActivity(new Intent(getActivity().getApplicationContext(), PhoneAuthActivity.class));
                }
            });

        }
        else {

            initializeUI();

        }

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private void initializeUI()
    {

        TextView nameUserTXT = binding.nameUserTXT;
        TextView userPhoneTXT = binding.userPhoneTXT;

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        String userid = firebaseUser.getUid();

        databaseReference = FirebaseDatabase.getInstance()
                .getReference("Users")
                .child(userid);
        databaseReference.addValueEventListener(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        UsersModel user = dataSnapshot.getValue(UsersModel.class);
                        nameUserTXT.setText(user.getUserName());
                        userPhoneTXT.setText(user.getUserPhone());
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // read query is cancelled.
                    }
                });

        userLayout.setVisibility(View.VISIBLE);
        guestLayout.setVisibility(View.GONE);


        TabLayout tabLayout = binding.tabLayout;
        ViewPager viewPager = binding.viewPager;

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager());
        viewPagerAdapter.addFragment(new ProfileFragment(), "Hesabım");
        viewPagerAdapter.addFragment(new OrdersFragment(), "Siparişlerim");
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);



    }

    public static class ViewPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        public ViewPagerAdapter(@NonNull @NotNull FragmentManager fm) {
            super(fm);
            this.titles = new ArrayList<>();
            this.fragments = new ArrayList<>();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }


    }
}