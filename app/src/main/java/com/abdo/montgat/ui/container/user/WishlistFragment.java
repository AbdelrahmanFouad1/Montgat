package com.abdo.montgat.ui.container.user;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.montgat.R;
import com.abdo.montgat.helper.FavoriteDB;
import com.abdo.montgat.model.FavoriteModel;
import com.abdo.montgat.ui.container.IOnBackPressed;
import com.abdo.montgat.ui.container.home.HomeFragment;
import com.abdo.montgat.utlis.Constant;

import java.util.ArrayList;
import java.util.List;

public class WishlistFragment extends Fragment implements IOnBackPressed {
    private View view;
    private ImageView back;
    private Button shopping;
    private RecyclerView wishlist_RV;
    private RelativeLayout emptyItem;
    private FavoriteDB favoriteDB;
    private int profile_counts;
    private List<FavoriteModel> favoriteModelList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_wishlist, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();

    }

    private void initView() {
        back = view.findViewById(R.id.back_iv_wishlist);
        shopping = view.findViewById(R.id.shopping_btn_wishlist);
        emptyItem = view.findViewById(R.id.emptyItemContainer);
        wishlist_RV = view.findViewById(R.id.wishlist_RV);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(WishlistFragment.this, new UserFragment());
            }
        });

        shopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(WishlistFragment.this, new HomeFragment());
            }
        });

        loadData();


    }

    private void loadData() {
        if (favoriteModelList != null) {
            favoriteModelList.clear();
        }
        favoriteDB = new FavoriteDB(requireActivity());
        SQLiteDatabase db = favoriteDB.getReadableDatabase();

        Cursor cursor = favoriteDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndex(FavoriteDB.ITEM_TITLE));
                String price = cursor.getString(cursor.getColumnIndex(FavoriteDB.ITEM_PRICE));
                String id = cursor.getString(cursor.getColumnIndex(FavoriteDB.KEY_ID));
                int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(FavoriteDB.ITEM_IMAGE)));
                FavoriteModel favItem = new FavoriteModel(id, price, title, image);
                favoriteModelList.add(favItem);
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

        WishlistAdapter favAdapter = new WishlistAdapter(favoriteModelList);

         profile_counts = favoriteDB.getProfilesCount();
        favoriteDB.close();

        if (profile_counts == 0){
            wishlist_RV.setVisibility(View.GONE);
            emptyItem.setVisibility(View.VISIBLE);
        }else {
            wishlist_RV.setVisibility(View.VISIBLE);
            emptyItem.setVisibility(View.GONE);
        }

        wishlist_RV.setAdapter(favAdapter);
    }


    private class WishlistAdapter extends RecyclerView.Adapter<WishlistAdapter.WishlistAdapterVH> {

        private List<FavoriteModel> favoriteModels;

        public WishlistAdapter(List<FavoriteModel> favoriteModels) {
            this.favoriteModels = favoriteModels;
        }

        @NonNull
        @Override
        public WishlistAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.wishlist_item, parent, false);

            return new WishlistAdapterVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WishlistAdapterVH holder, int position) {
            holder.favImageView.setImageResource(favoriteModels.get(position).getItem_image());
            holder.title.setText(favoriteModels.get(position).getItem_title());
            holder.price.setText(favoriteModels.get(position).getItem_price());
        }

        @Override
        public int getItemCount() {
            return favoriteModels.size();
        }

        class WishlistAdapterVH extends RecyclerView.ViewHolder {
            private ImageView favImageView;
            private ImageView favBtn;
            private TextView title, price;

            public WishlistAdapterVH(@NonNull View itemView) {
                super(itemView);
                favImageView = itemView.findViewById(R.id.favImageView);
                favBtn = itemView.findViewById(R.id.favBtn);
                title = itemView.findViewById(R.id.favTextView);
                price = itemView.findViewById(R.id.favTextViewPrice);

                favBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        final FavoriteModel favoriteModel = favoriteModels.get(position);
                        favoriteDB.remove_fav(favoriteModel.getKey_id());
                        removeItem(position);
                        notifyDataSetChanged();
                    }
                });
            }

            private void removeItem(int position) {
                favoriteModels.remove(position);

                if (favoriteModels.size() == 0){
                    wishlist_RV.setVisibility(View.GONE);
                    emptyItem.setVisibility(View.VISIBLE);
                }else {
                    wishlist_RV.setVisibility(View.VISIBLE);
                    emptyItem.setVisibility(View.GONE);
                }

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, favoriteModels.size());
            }
        }
    }

    @Override
    public boolean onBackPressed() {
        Constant.replaceFragment(WishlistFragment.this, new UserFragment());
        return true;
    }
}
