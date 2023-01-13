package com.example.adak4.ui.home.listeners;

import com.example.adak4.ui.home.menu.AnaSayfaKategoriModel;

import java.util.List;

public interface IKategoriLoadListener {

    void onKategoriLoadSucces(List<AnaSayfaKategoriModel> anaSayfaKategoriModelList);

    void onKategoriLoadFailed(String message);

}

