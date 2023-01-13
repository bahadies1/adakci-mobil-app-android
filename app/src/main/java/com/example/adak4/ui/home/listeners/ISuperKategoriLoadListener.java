package com.example.adak4.ui.home.listeners;

import com.example.adak4.ui.home.menu.supermenu.SuperMenuModel;

import java.util.List;

public interface ISuperKategoriLoadListener {

    void onSuperKategoriLoadSucces(List<SuperMenuModel> superMenuModelList);

    void onSuperKategoriLoadFailed(String message);
}
