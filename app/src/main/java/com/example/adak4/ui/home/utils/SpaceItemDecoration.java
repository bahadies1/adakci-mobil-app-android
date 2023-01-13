package com.example.adak4.ui.home.utils;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adak4.R;

import org.jetbrains.annotations.NotNull;

public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

    @Override
    public void getItemOffsets(@NonNull @NotNull Rect outRect, @NonNull @NotNull View view, @NonNull @NotNull RecyclerView parent, @NonNull @NotNull RecyclerView.State state) {
        if(parent.getChildLayoutPosition(view)%2 != 0)
        {
            //outRect.top = 50;
            //outRect.bottom = -50;
           //outRect.right = 50;
           //outRect.left = 50;
        } else {

        }
    }
}
