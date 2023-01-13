package com.example.adak4.ui.profile;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adak4.MainActivity;
import com.example.adak4.R;
import com.example.adak4.databinding.FragmentProfileBinding;
import com.example.adak4.databinding.FragmentUserProfileBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileFragment extends Fragment {

    private FragmentUserProfileBinding binding;

    LinearLayout btnCikisYap;

    public ProfileFragment() {
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        btnCikisYap = binding.btnCikisYap;
        btnCikisYap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:

                                FirebaseAuth.getInstance().signOut();
                                getActivity().finish();
                                startActivity(new Intent(getContext().getApplicationContext(), MainActivity.class));

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder
                        .setMessage("Oturumu kapatmak istediğinizden emin misiniz?")
                        .setPositiveButton("Oturumu Kapat", dialogClickListener)
                        .setNegativeButton("Vazgeç", dialogClickListener).show();


            }
        });

        return root;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

}