package com.example.adak4.ui.home.menu;

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
import com.example.adak4.R;
import com.example.adak4.ui.home.listeners.IKategoriLoadListener;
import com.example.adak4.ui.home.listeners.IRecyclerViewClickListener;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class AnaSayfaKategoriAdapter extends RecyclerView.Adapter<AnaSayfaKategoriAdapter.MyKategoriViewHolder>  {

    public static final String PRODUCT_NAME = "productName";
    public static final String PRODUCT_TYPE = "productType";
    public static final String PRODUCT_QUANTITY = "productQuantity";
    public static final String PRODUCT_IMAGE = "productImage";
    public static final String PRODUCT_DESCRIPTION = "productDescription";
    public static final String PRODUCT_BRAND = "productBrand";
    public static final String PRODUCT_PRICE = "productPrice";
    public static final String PRODUCT_SHIP_DESCROPTION = "productShipDescription";
    public static final String PRODUCT_SHIP_IMAGE = "productShipImage";
    public static final String PRODUCT_KEY = "productKey";
    public static final String PRODUCT_SIZEM = "productSizeM";
    public static final String PRODUCT_SIZEL = "productSizeL";
    public static final String PRODUCT_SIZEXL = "productSizeXL";


    private Context context;
    private List<AnaSayfaKategoriModel> anaSayfaKategoriModelList;

    private IKategoriLoadListener iKategoriLoadListener;

    public AnaSayfaKategoriAdapter(Context context, List<AnaSayfaKategoriModel> anaSayfaKategoriModelList, IKategoriLoadListener iKategoriLoadListener) {
        this.context = context;
        this.anaSayfaKategoriModelList = anaSayfaKategoriModelList;
        this.iKategoriLoadListener = iKategoriLoadListener;
    }


    @NonNull
    @Override
    public MyKategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AnaSayfaKategoriAdapter.MyKategoriViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_kategori_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyKategoriViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context)
                .load(anaSayfaKategoriModelList.get(position).getProductImage())
                .into(holder.imageView);

        Glide.with(context)
                .load(anaSayfaKategoriModelList.get(position).getProductShipImage())
                .into(holder.simgeHizliKargo);

        holder.txtHizliKargo.setText(anaSayfaKategoriModelList.get(position).getProductShipDescription());

        holder.txtName.setText(new StringBuilder().append(anaSayfaKategoriModelList.get(position).getProductName()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent;
                intent =  new Intent(context, UrunDetayActivity.class);
                intent.putExtra(PRODUCT_NAME, anaSayfaKategoriModelList.get(position).getProductName());
                intent.putExtra(PRODUCT_TYPE, anaSayfaKategoriModelList.get(position).getProductType());
                intent.putExtra(PRODUCT_QUANTITY, anaSayfaKategoriModelList.get(position).getProductQuantity());
                intent.putExtra(PRODUCT_IMAGE, anaSayfaKategoriModelList.get(position).getProductImage());
                intent.putExtra(PRODUCT_DESCRIPTION, anaSayfaKategoriModelList.get(position).getProductDescription());
                intent.putExtra(PRODUCT_BRAND, anaSayfaKategoriModelList.get(position).getProductBrand());
                intent.putExtra(PRODUCT_PRICE, anaSayfaKategoriModelList.get(position).getProductPrice());
                intent.putExtra(PRODUCT_SHIP_DESCROPTION, anaSayfaKategoriModelList.get(position).getProductShipDescription());
                intent.putExtra(PRODUCT_SHIP_IMAGE, anaSayfaKategoriModelList.get(position).getProductShipImage());
                intent.putExtra(PRODUCT_KEY, anaSayfaKategoriModelList.get(position).getProductKey());
                intent.putExtra(PRODUCT_SIZEM, anaSayfaKategoriModelList.get(position).getProductSizeM());
                intent.putExtra(PRODUCT_SIZEL, anaSayfaKategoriModelList.get(position).getProductSizeL());
                intent.putExtra(PRODUCT_SIZEXL, anaSayfaKategoriModelList.get(position).getProductSizeXL());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return anaSayfaKategoriModelList.size();
    }

    public class MyKategoriViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        @BindView(R.id.imageView83)
        ImageView imageView;

        @BindView(R.id.simgeHizliKargo)
        ImageView simgeHizliKargo;

        @BindView(R.id.txtName3)
        TextView txtName;

        @BindView(R.id.txtHizliKargo)
        TextView txtHizliKargo;

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
