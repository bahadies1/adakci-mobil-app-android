package com.example.adak4.ui.dashboard;

import com.example.adak4.ui.home.menu.AnaSayfaKategoriModel;

import java.util.List;

public interface IKampanyalarLoadListener {

    void onKampanyaLoadSucces(List<KampanyaModel> ikampanyaModelList);

    void onKampanyaLoadFailed(String message);


}
