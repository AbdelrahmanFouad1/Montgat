package com.abdo.montgat.ui.container.cart;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.montgat.R;
import com.abdo.montgat.helper.CartDB;
import com.abdo.montgat.model.CartModel;
import com.abdo.montgat.ui.container.home.HomeFragment;
import com.abdo.montgat.utlis.Constant;

import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment {
    private static final String TAG = "cart";
    private View view;
    private Button goShopping;
    private RecyclerView cart_RV;
    private CartDB cartDB;
    private TextView countItem, clearAll, totalPrice;
    private RelativeLayout empty;
    private NestedScrollView fullScroll;
    private int profile_counts;
    private List<CartModel> cartModelList = new ArrayList<>();
//    private List<String> total;
//    String a = "250";
//    String b = "250";
//    String result;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        goShopping = view.findViewById(R.id.shopping_btn_cart);
        cart_RV = view.findViewById(R.id.cart_RV);
        countItem = view.findViewById(R.id.itemCount_tv_cart);
        empty = view.findViewById(R.id.empty_rl_cart);
        fullScroll = view.findViewById(R.id.full_nsv_cart);
        clearAll = view.findViewById(R.id.clearAll_tv_cart);
        totalPrice = view.findViewById(R.id.totalPrice_tv_cart);

        goShopping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(CartFragment.this, new HomeFragment());

//                try{
//                    int value = Integer.parseInt(a)+Integer.parseInt(b);
//                    result = String. valueOf(value) ;
//                }catch(NumberFormatException ex){
//                    //either a or b is not a number
//                    result = "Invalid input";
//                }
//
//                Log.d(TAG, "onClick: "+result);
            }
        });

        clearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartDB.remove_all_cart();
                fullScroll.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
            }
        });

        loadData();
    }

    private void loadData() {
        if (cartModelList != null) {
            cartModelList.clear();
        }
        cartDB = new CartDB(requireActivity());
        SQLiteDatabase db = cartDB.getReadableDatabase();

        Cursor cursor = cartDB.select_all_cart_list();
        try {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(CartDB.KEY_ID));
                int image = Integer.parseInt(cursor.getString(cursor.getColumnIndex(CartDB.ITEM_IMAGE)));
                String title = cursor.getString(cursor.getColumnIndex(CartDB.ITEM_TITLE));
                String price = cursor.getString(cursor.getColumnIndex(CartDB.ITEM_PRICE));
                String new_price = cursor.getString(cursor.getColumnIndex(CartDB.ITEM_NEW_PRICE));
                String size = cursor.getString(cursor.getColumnIndex(CartDB.ITEM_SIZE));
                String quantity = cursor.getString(cursor.getColumnIndex(CartDB.ITEM_QUANTITY));
                CartModel cartModel = new CartModel(id, price, new_price, title, size, quantity, image);
                cartModelList.add(cartModel);
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

        CartAdapter favAdapter = new CartAdapter(cartModelList);

        profile_counts = cartDB.getCartCount();
        countItem.setText(profile_counts+"");
        cartDB.close();

        if (profile_counts == 0){
            fullScroll.setVisibility(View.GONE);
            empty.setVisibility(View.VISIBLE);
        }else {
            fullScroll.setVisibility(View.VISIBLE);
            empty.setVisibility(View.GONE);
        }

        cart_RV.setAdapter(favAdapter);
    }

    private class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartAdapterVH> {

        List<CartModel> cartModels;

        public CartAdapter(List<CartModel> cartModels) {
            this.cartModels = cartModels;
        }

        @NonNull
        @Override
        public CartAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.cart_item, parent, false);
            return new CartAdapterVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CartFragment.CartAdapter.CartAdapterVH holder, int position) {

            holder.image.setImageResource(cartModels.get(position).getItem_image());
            holder.title.setText(cartModels.get(position).getItem_title());
            holder.price.setText(cartModels.get(position).getItem_price());
            holder.size.setText(cartModels.get(position).getSize());
            holder.quantity.setText(cartModels.get(position).getQuantity());

            if (cartModels.get(position).getQuantity().equals("1")){
                holder.quantity.setVisibility(View.GONE);
                holder.multi.setVisibility(View.GONE);
            }

            int total = 0;
            for(int counter = 0; counter <= cartModels.size()-1; counter++) {
                total += Integer.parseInt(cartModels.get(counter).getItem_new_price()) ;
                totalPrice.setText(String.valueOf(total));
            }
//            totalPrice.setText(total+"");

        }

        @Override
        public int getItemCount() {
            return cartModels.size();
        }

        private class CartAdapterVH extends RecyclerView.ViewHolder {
            private ImageView remove, image;
            private TextView title, price, size, quantity, multi;

            public CartAdapterVH(@NonNull View itemView) {
                super(itemView);
                remove = itemView.findViewById(R.id.remove_btn_cart);
                image = itemView.findViewById(R.id.cartImage_iv_cart);
                title = itemView.findViewById(R.id.titleCart_tv_cart);
                price = itemView.findViewById(R.id.price_tv_cart);
                size = itemView.findViewById(R.id.sizeCart_tv_cart);
                quantity = itemView.findViewById(R.id.quantityCart_tv_cart);
                multi = itemView.findViewById(R.id.quantity_tv_item);


                remove.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = getAdapterPosition();
                        final CartModel cartModel = cartModels.get(position);
                        cartDB.remove_cart(cartModel.getKey_id());
                        removeItem(position);
                        notifyDataSetChanged();
                    }
                });
            }
            private void removeItem(int position) {
                cartModels.remove(position);
                countItem.setText(cartModels.size()+"");
                if (cartModels.size() == 0){
                    fullScroll.setVisibility(View.GONE);
                    empty.setVisibility(View.VISIBLE);
                }else {
                    fullScroll.setVisibility(View.VISIBLE);
                    empty.setVisibility(View.GONE);
                }

                notifyItemRemoved(position);
                notifyItemRangeChanged(position, cartModels.size());
            }
        }
    }
}
