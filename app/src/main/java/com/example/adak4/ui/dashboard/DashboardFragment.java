package com.example.adak4.ui.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adak4.databinding.FragmentDashboardBinding;
import com.example.adak4.ui.home.utils.SpaceItemDecoration;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class DashboardFragment extends Fragment implements IKampanyalarLoadListener {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    IKampanyalarLoadListener iKampanyalarLoadListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        init();
        loadDrinkFromFirebase();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }

    private void loadDrinkFromFirebase() {
        List<KampanyaModel> drinkModels = new ArrayList<>();
        FirebaseDatabase.getInstance()
                .getReference("Kampanyalar")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                        if (snapshot.exists())
                        {
                            for (DataSnapshot drinkSnapShot:snapshot.getChildren())
                            {
                                KampanyaModel drinkModel = drinkSnapShot.getValue(KampanyaModel.class);
                                drinkModel.setKampanyaKey(drinkSnapShot.getKey());
                                drinkModels.add(drinkModel);
                            }
                            iKampanyalarLoadListener.onKampanyaLoadSucces(drinkModels);
                        } else {
                            iKampanyalarLoadListener.onKampanyaLoadFailed("Ürün Bulunamadı!");
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull @NotNull DatabaseError error) {

                    }
                });
    }

    private void init() {
        ButterKnife.bind(getActivity());
        iKampanyalarLoadListener = this;

        RecyclerView recyclerView = binding.recKampanya;

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),1);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration());

    }


    @Override
    public void onKampanyaLoadSucces(List<KampanyaModel> kampanyaModelList) {
        RecyclerView recyclerView = binding.recKampanya;
        KampanyalarAdapter adapter = new KampanyalarAdapter(getActivity(), kampanyaModelList, iKampanyalarLoadListener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onKampanyaLoadFailed(String message) {

    }
}