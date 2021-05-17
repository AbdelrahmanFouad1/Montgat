package com.abdo.montgat.ui.container.categories;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.montgat.R;
import com.abdo.montgat.data.Data;
import com.abdo.montgat.helper.FavoriteDB;
import com.abdo.montgat.model.ProductModel;
import com.abdo.montgat.ui.container.productShow.ProductFragment;
import com.abdo.montgat.utlis.Constant;

import java.util.List;

public class SunglassesFragment extends Fragment {
    private View view;
    private RecyclerView productRV;
    private FavoriteDB favoriteDB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sunglasses, container, false);

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {

        productRV = view.findViewById(R.id.product_rv_sunglasses);

        List<ProductModel> list1 = new Data().getDataFromSunglasses();
        productRV.setAdapter(new ProductAdapter(list1));

    }

    private class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductVH> {

        List<ProductModel> productList;

        public ProductAdapter(List<ProductModel> productList) {
            this.productList = productList;
        }

        @NonNull
        @Override
        public ProductVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            favoriteDB = new FavoriteDB(requireActivity());
            //create table on first
            SharedPreferences prefs = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
            boolean firstStart = prefs.getBoolean("firstStart", true);
            if (firstStart) {
                createTableOnFirstStart();
            }
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.t_shirt_item, parent, false);
            return new ProductVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductVH holder, int position) {
            ProductModel trending = productList.get(position);

            readCursorData(trending, holder);

            int image = trending.getImage();
            String label = trending.getLabel();
            String price = trending.getPrice();

            holder.imageView.setImageResource(image);
            holder.label.setText(label);
            holder.price.setText(price);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    ProductFragment productFragment = new ProductFragment();
                    bundle.putSerializable("description", trending);
                    bundle.putString("FROM_HOME","sunglasses");
                    productFragment.setArguments(bundle);
                    Constant.replaceFragment(SunglassesFragment.this, productFragment);
                }
            });
        }

        @Override
        public int getItemCount() {
            return productList.size();
        }

        private class ProductVH extends RecyclerView.ViewHolder {

            private ImageView imageView, favorite;
            private TextView label, price;

            public ProductVH(View itemView) {
                super(itemView);

                imageView = itemView.findViewById(R.id.product_iv_item);
                favorite = itemView.findViewById(R.id.favorite_iv_item);
                label = itemView.findViewById(R.id.tile_tv_item);
                price = itemView.findViewById(R.id.price_tv_item);

                //add to fav btn
                favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int position = getAdapterPosition();
                        ProductModel trending = productList.get(position);

                        if (trending.getFavStatus().equals("0")){
                            trending.setFavStatus("1");
                            favoriteDB.insertIntoTheDatabase(trending.getImage(), trending.getPrice(), trending.getLabel(),
                                    trending.getKey_id(), trending.getFavStatus());
                            favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                        }else {
                            trending.setFavStatus("0");
                            favoriteDB.remove_fav(trending.getKey_id());
                            favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                        }
                    }
                });
            }
        }
    }

    private void createTableOnFirstStart() {
        favoriteDB.insertEmpty();

        SharedPreferences prefs = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStart", false);
        editor.apply();
    }

    private void readCursorData(ProductModel trending, ProductAdapter.ProductVH holder) {

        Cursor cursor = favoriteDB.read_all_data(trending.getKey_id());
        SQLiteDatabase db = favoriteDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(FavoriteDB.FAVORITE_STATUS));
                trending.setFavStatus(item_fav_status);

                //check fav status
                if (item_fav_status != null && item_fav_status.equals("1")) {
                    holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_24);
                } else if (item_fav_status != null && item_fav_status.equals("0")) {
                    holder.favorite.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }


    }

}
