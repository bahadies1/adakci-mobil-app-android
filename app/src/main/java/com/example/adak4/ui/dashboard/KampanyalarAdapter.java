package com.example.adak4.ui.dashboard;

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
import com.example.adak4.ui.home.menu.AnaSayfaKategoriAdapter;
import com.example.adak4.ui.home.menu.AnaSayfaKategoriModel;
import com.example.adak4.ui.home.menu.UrunDetayActivity;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class KampanyalarAdapter extends RecyclerView.Adapter<KampanyalarAdapter.MyKategoriViewHolder>  {

    public static final String KAMPANYA_NAME = "kampanyaName";
    public static final String KAMPANYA_CODE = "kampanyaCode";
    public static final String KAMPANYA_IMAGE = "kampanyaImage";
    public static final String KAMPANYA_KEY = "kampanyaKey";
    public static final String KAMPANYA_DESCRIPTION = "kampanyaDescription";
    public static final String KAMPANYA_TITLE = "kampanyaTitle";
    public static final String KAMPANYA_TITLE_IMAGE = "kampanyaTitleImage";

    private Context context;
    private List<KampanyaModel> kampanyaModelList;

    private IKampanyalarLoadListener iKampanyalarLoadListener;

    public KampanyalarAdapter(Context context, List<KampanyaModel> kampanyaModelList, IKampanyalarLoadListener iKampanyalarLoadListener) {
        this.context = context;
        this.kampanyaModelList = kampanyaModelList;
        this.iKampanyalarLoadListener = iKampanyalarLoadListener;
    }

    @NonNull
    @Override
    public KampanyalarAdapter.MyKategoriViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KampanyalarAdapter.MyKategoriViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.layout_kampanya_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull KampanyalarAdapter.MyKategoriViewHolder holder, @SuppressLint("RecyclerView") int position) {

        Glide.with(context)
                .load(kampanyaModelList.get(position).getKampanyaImage())
                .into(holder.kampanyaImage);

        holder.txtName.setText(new StringBuilder().append(kampanyaModelList.get(position).getKampanyaName()));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Intent intent;
                intent =  new Intent(context, KampanyaDetayActivity.class);
                intent.putExtra(KAMPANYA_NAME, kampanyaModelList.get(position).getKampanyaName());
                intent.putExtra(KAMPANYA_CODE, kampanyaModelList.get(position).getKampanyaCode());
                intent.putExtra(KAMPANYA_IMAGE, kampanyaModelList.get(position).getKampanyaImage());
                intent.putExtra(KAMPANYA_KEY, kampanyaModelList.get(position).getKampanyaKey());
                intent.putExtra(KAMPANYA_DESCRIPTION, kampanyaModelList.get(position).getKampanyaDescription());
                intent.putExtra(KAMPANYA_TITLE, kampanyaModelList.get(position).getKampanyaTitle());
                intent.putExtra(KAMPANYA_TITLE_IMAGE, kampanyaModelList.get(position).getKampanyaTitleImage());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return kampanyaModelList.size();
    }

    public class MyKategoriViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener  {

        @BindView(R.id.kampanyaImage)
        ImageView kampanyaImage;

        @BindView(R.id.kampanyaName)
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
