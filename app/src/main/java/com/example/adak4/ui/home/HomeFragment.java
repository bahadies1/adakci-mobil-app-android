package com.example.adak4.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adak4.ui.home.listeners.IKategoriLoadListener;

import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.adak4.databinding.FragmentHomeBinding;
import com.example.adak4.ui.home.Slider.SliderAdapter;
import com.example.adak4.ui.home.Slider.SliderData;
import com.example.adak4.ui.home.listeners.ISuperKategoriLoadListener;
import com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter;
import com.example.adak4.ui.home.menu.AnaSayfaKategoriModel;
import com.example.adak4.ui.home.menu.supermenu.SuperKatalogAdapter;
import com.example.adak4.ui.home.menu.supermenu.SuperMenuModel;
import com.example.adak4.ui.home.utils.SpaceItemDecoration;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class HomeFragment extends Fragment implements IKategoriLoadListener, ISuperKategoriLoadListener {

    private FragmentHomeBinding binding;

    IKategoriLoadListener kategoriLoadListener;
    ISuperKategoriLoadListener superKategoriLoadListener;


    private SliderAdapter adapter;
    private ArrayList<SliderData> sliderDataArrayList;
    private SliderView sliderView;
    FirebaseFirestore  firebaseFirestore;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        sliderDataArrayList = new ArrayList<>();
        sliderView = binding.newsSlider;
        firebaseFirestore = FirebaseFirestore.getInstance();

        loadImages();

        init();
        loadDrinkFromFirebase();

        init2();
        loadDrinkFromFirebase2();
        return root;

    }

    private void loadImages() {

        FirebaseFirestore  firebaseFirestore;
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseFirestore.collection("Slider").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    SliderData sliderData = documentSnapshot.toObject(SliderData.class);
                    SliderData model = new SliderData();
                    model.setImgUrl(sliderData.getImgUrl());
                    sliderDataArrayList.add(model);
                    adapter = new SliderAdapter(getActivity(), sliderDataArrayList);
                    sliderView.setSliderAdapter(adapter);
                    sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
                    sliderView.setScrollTimeInSec(3);
                    sliderView.setAutoCycle(true);
                    sliderView.startAutoCycle();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "Fail to load slider data..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDrinkFromFirebase2() {
        List<SuperMenuModel> drinkModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Kategoriler").child("SuperMenu")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            for (DataSnapshot drinkSnapShot:snapshot.getChildren())
                            {
                                SuperMenuModel drinkModel = drinkSnapShot.getValue(SuperMenuModel.class);
                                drinkModel.setSupermenuKey(drinkSnapShot.getKey());
                                drinkModels.add(drinkModel);
                            }
                            superKategoriLoadListener.onSuperKategoriLoadSucces(drinkModels);
                        } else {
                            superKategoriLoadListener.onSuperKategoriLoadFailed("Ürün Bulunamadı!");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
    }

    private void loadDrinkFromFirebase() {
        List<AnaSayfaKategoriModel> drinkModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Kategoriler").child("Başlıklar")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            for (DataSnapshot drinkSnapShot:snapshot.getChildren())
                            {
                                AnaSayfaKategoriModel drinkModel = drinkSnapShot.getValue(AnaSayfaKategoriModel.class);
                                drinkModel.setProductKey(drinkSnapShot.getKey());
                                drinkModels.add(drinkModel);
                            }
                            kategoriLoadListener.onKategoriLoadSucces(drinkModels);
                        } else {
                            kategoriLoadListener.onKategoriLoadFailed("Ürün Bulunamadı!");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
    }

    private void init() {
        ButterKnife.bind(getActivity());
        kategoriLoadListener = this;

        RecyclerView recyclerView = binding.homeRec;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration());

    }

    private void init2() {
        ButterKnife.bind(getActivity());
        superKategoriLoadListener = this;

        RecyclerView superRec = binding.superKategoriHome;

        GridLayoutManager superGridLayoutManager = new GridLayoutManager(getActivity(),1);
        superRec.setLayoutManager(superGridLayoutManager);



        superRec.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        // superRec.addItemDecoration(new SpaceItemDecoration());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onKategoriLoadSucces(List<AnaSayfaKategoriModel> anaSayfaKategoriModelList) {

        RecyclerView recyclerView = binding.homeRec;
        AnaSayfaKategoriAdapter adapter = new AnaSayfaKategoriAdapter(getActivity(), anaSayfaKategoriModelList,kategoriLoadListener);
        recyclerView.setAdapter(adapter);


    }

    @Override
    public void onKategoriLoadFailed(String message) {

    }

    @Override
    public void onSuperKategoriLoadSucces(List<SuperMenuModel> superMenuModelList) {

        RecyclerView superRec = binding.superKategoriHome;
        SuperKatalogAdapter superKatalogAdapter = new SuperKatalogAdapter(getActivity(), superMenuModelList,superKategoriLoadListener);
        superRec.setAdapter(superKatalogAdapter);


    }

    @Override
    public void onSuperKategoriLoadFailed(String message) {

    }
}