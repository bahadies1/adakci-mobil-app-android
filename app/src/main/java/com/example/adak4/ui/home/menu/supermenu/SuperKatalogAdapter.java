package com.example.adak4.ui.home.menu.supermenu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.adak4.R;
import com.example.adak4.ui.home.listeners.IRecyclerViewClickListener;
import com.example.adak4.ui.home.listeners.ISuperKategoriLoadListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SuperKatalogAdapter extends RecyclerView.Adapter<SuperKatalogAdapter.MyKategoriViewHolder>  {

    public static final String SUPER_MENU_NAME = "supermenuName";
    public static final String SUPER_MENU_DESCRIPTION = "supermenuDescription";
    public static final String SUPER_MENU_DESCRIPTION_TITLE = "supermenuDescriptionTitle";
    public static final String SUPER_MENU_IMAGE = "supermenuImage";
    public static final String SUPER_MENU_TITLE_IMAGE = "supermenuTitleImage";
    public static final String SUPER_MENU_KEY = "supermenuKey";

    private Context context;
private List<SuperMenuModel> superMenuModelList;

private ISuperKategoriLoadListener iSuperKategoriLoadListener;

public SuperKatalogAdapter(Context context, List<SuperMenuModel> superMenuModelList, ISuperKategoriLoadListener iSuperKategoriLoadListener) {
        this.context = context;
        this.superMenuModelList = superMenuModelList;
        this.iSuperKategoriLoadListener = iSuperKategoriLoadListener;
        }

        @NonNull
        @Override
        public MyKategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new SuperKatalogAdapter.MyKategoriViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_superkategori_item,parent,false));
                }

        @Override
        public void onBindViewHolder(@NonNull MyKategoriViewHolder holder, @SuppressLint("RecyclerView") int position) {

                Glide.with(context).load(superMenuModelList.get(position).getSupermenuImage()).apply(RequestOptions.circleCropTransform()).into(holder.imageView);

                holder.txtName.setText(new StringBuilder().append(superMenuModelList.get(position).getSupermenuName()));

                holder.itemView.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View view) {
        final Intent intent;
        intent =  new Intent(context, SuperMenuDetailActivity.class);
            intent.putExtra(SUPER_MENU_NAME, superMenuModelList.get(position).getSupermenuName());
            intent.putExtra(SUPER_MENU_DESCRIPTION, superMenuModelList.get(position).getSupermenuDescription());
            intent.putExtra(SUPER_MENU_DESCRIPTION_TITLE, superMenuModelList.get(position).getSupermenuDescriptionTitle());
            intent.putExtra(SUPER_MENU_IMAGE, superMenuModelList.get(position).getSupermenuImage());
            intent.putExtra(SUPER_MENU_TITLE_IMAGE, superMenuModelList.get(position).getSupermenuTitleImage());
            context.startActivity(intent);
            }
        });

}

        @Override
        public int getItemCount() {
                return superMenuModelList.size();
                }

    public class MyKategoriViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        @BindView(R.id.roundedlogo)
        ImageView imageView;

        @BindView(R.id.textView10)
        TextView txtName;

        IRecyclerViewClickListener listener;

        public void setListener(IRecyclerViewClickListener listener) {
            this.listener = listener;
        }

        private Unbinder unbinder;

        public MyKategoriViewHolder(@NonNull View itemView) {
            super(itemView);
            unbinder = ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v){
            listener.onRecyclerClick(v,getAdapterPosition());
        }
    }
}

