package com.abdo.montgat.ui.container.productShow;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.abdo.montgat.R;
import com.abdo.montgat.helper.CartDB;
import com.abdo.montgat.helper.FavoriteDB;
import com.abdo.montgat.model.ProductModel;
import com.abdo.montgat.ui.container.categories.CategoryFragment;
import com.abdo.montgat.ui.container.home.HomeFragment;
import com.abdo.montgat.utlis.Constant;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProductFragment extends Fragment {
    private static final String TAG = "product";
    private View view;
    private TextView deprecatedPrice, counterText, price, title, description, inTheCart;
    private ImageView min, plus, imageProduct, back, love, addCart;
    private int counter = 1;
    private String fromHome, fromCategory, realSize;
    private RecyclerView colorRV, sizeRV;
    private FavoriteDB favoriteDB;
    private CartDB cartDB;
    private ProductModel productModel;
    private AppCompatButton addToCard;
    private int newPrice, cart_counts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        cartDB = new CartDB(requireActivity());
        //create table on first
        SharedPreferences prefs = requireActivity().getSharedPreferences("prefsCart", Context.MODE_PRIVATE);
        boolean firstStart = prefs.getBoolean("firstStartCart", true);
        if (firstStart) {
            createTableOnFirstStart();
        }
        view = inflater.inflate(R.layout.fragment_product, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        deprecatedPrice = view.findViewById(R.id.deprecatedPrice_tv_product);
        min = view.findViewById(R.id.quantity_iv_product_min);
        plus = view.findViewById(R.id.quantity_iv_product_plus);
        counterText = view.findViewById(R.id.quantity_tv_product_num);
        price = view.findViewById(R.id.Price_tv_product);
        title = view.findViewById(R.id.title_tv_product);
        description = view.findViewById(R.id.description_tv_product);
        imageProduct = view.findViewById(R.id.image_iv_product);
        colorRV = view.findViewById(R.id.color_rv_product);
        sizeRV = view.findViewById(R.id.size_rv_product);
        back = view.findViewById(R.id.back_iv_product);
        love = view.findViewById(R.id.favorite_iv_product);
        addToCard = view.findViewById(R.id.addToCart_acbtn_login);
        addCart = view.findViewById(R.id.cart_iv_product);
        inTheCart = view.findViewById(R.id.counter_tv_product);

        fromHome = getArguments().getString("FROM_HOME");
        fromCategory = getArguments().getString("FROM_CATEGORY");

        counterText.setText(counter + "");

        Log.d(TAG, "initialCounter: "+ counter );

        deprecatedPrice.setPaintFlags(deprecatedPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fromHome.equals("trending") || fromHome.equals("newArrival")){
                    Constant.replaceFragment(ProductFragment.this, new HomeFragment());
                }else{
                    Constant.replaceFragment(ProductFragment.this, new CategoryFragment());
                }
            }
        });

        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrement();
            }
        });

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increment();
            }
        });

        //item inside the cart
        counterOfItem();


        productModel = (ProductModel) requireArguments().getSerializable("description");
        deprecatedPrice.setText(productModel.getDeprecatedPrice());
        price.setText(productModel.getPrice());
        title.setText(productModel.getLabel());
        description.setText(productModel.getDescription());
        imageProduct.setImageResource(productModel.getImage());
        colorRV.setAdapter(new ColorAdapter(productModel.getColor()));
        sizeRV.setAdapter(new SizeAdapter(productModel.getSize()));

        //only chek
        if (productModel.getFavStatus().equals("0")){
            love.setImageResource(R.drawable.ic_baseline_favorite_border_24);
        }else if (productModel.getFavStatus().equals("1")) {
            love.setImageResource(R.drawable.ic_baseline_favorite_24);
        }

        favoriteDB = new FavoriteDB(requireActivity());
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (productModel.getFavStatus().equals("0")){
                    productModel.setFavStatus("1");
                    favoriteDB.insertIntoTheDatabase(productModel.getImage(), productModel.getPrice(), productModel.getLabel(),
                            productModel.getKey_id(), productModel.getFavStatus());
                    love.setImageResource(R.drawable.ic_baseline_favorite_24);
                }else {
                    productModel.setFavStatus("0");
                    favoriteDB.remove_fav(productModel.getKey_id());
                    love.setImageResource(R.drawable.ic_baseline_favorite_border_24);
                }
            }
        });

        //only chek
        if (productModel.getIntoCart().equals("0")){

        }else if (productModel.getIntoCart().equals("1")) {
            Toast.makeText(requireActivity(), "Already in the cart!", Toast.LENGTH_SHORT).show();
        }

        addToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCursorData();
                if (productModel.getIntoCart().equals("0")) {
                    productModel.setIntoCart("1");

                    newPrice = Integer.parseInt(productModel.getPrice()) * Integer.parseInt(counterText.getText().toString());

                    Log.d(TAG, "newPrice: " + newPrice);

                    cartDB.insertIntoTheDatabaseCart(productModel.getImage(), productModel.getPrice(), String.valueOf(newPrice), productModel.getLabel(),
                            realSize, counterText.getText().toString(), productModel.getKey_id(), productModel.getIntoCart());

                    counterOfItem();

                } else if (productModel.getIntoCart().equals("1")) {

                    Toast.makeText(requireActivity(), "Already in the cart!", Toast.LENGTH_SHORT).show();
//                    productModel.setIntoCart("0");
//                    cartDB.remove_cart(productModel.getKey_id());
                }
                Log.d(TAG, "onClick add to cart: " + counterText.getText().toString());

            }
        });
        addCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                readCursorData();
                if (productModel.getIntoCart().equals("0")) {
                    productModel.setIntoCart("1");

                    newPrice = Integer.parseInt(productModel.getPrice()) * Integer.parseInt(counterText.getText().toString());

                    Log.d(TAG, "newPrice: " + newPrice);

                    cartDB.insertIntoTheDatabaseCart(productModel.getImage(), productModel.getPrice(), String.valueOf(newPrice), productModel.getLabel(),
                            realSize, counterText.getText().toString(), productModel.getKey_id(), productModel.getIntoCart());

                    counterOfItem();

                } else if (productModel.getIntoCart().equals("1")) {

                    Toast.makeText(requireActivity(), "Already in the cart!", Toast.LENGTH_SHORT).show();
//                    productModel.setIntoCart("0");
//                    cartDB.remove_cart(productModel.getKey_id());
                }
                Log.d(TAG, "onClick add to cart: " + counterText.getText().toString());

            }
        });


    }

    private void counterOfItem() {
        cartDB = new CartDB(requireActivity());

        cart_counts = cartDB.getCartCount();
        Log.d("counter2", String.valueOf(cart_counts));
        inTheCart.setText(String.valueOf(cart_counts));
        cartDB.close();

        if (cart_counts == 0) {
            inTheCart.setVisibility(View.GONE);
        }else {
            inTheCart.setVisibility(View.VISIBLE);
        }
    }

    private void increment() {
        counter++;
        counterText.setText(String.valueOf(counter));
//        finalCounter = counter;
        Log.d(TAG, "increment: "+ counter);
    }

    private void decrement() {

        if (counter <= 1) {
            counterText.setText(String.valueOf(counter));
        } else {
            counter--;
            counterText.setText(counter + "");
//            finalCounter = counter;
            Log.d(TAG, "decrement: "+ counter);
        }
    }

    public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.ColorAdapterVH>{

        List<Integer> colorList;
        int selectColor = 0;

        public ColorAdapter(List<Integer> colorList) {
            this.colorList = colorList;
        }

        @NonNull
        @Override
        public ColorAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.color_list_item, parent, false);
            return new ColorAdapterVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ColorAdapterVH holder, int position) {
            int colors = colorList.get(position);

            holder.circleColor.setImageResource(colors);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    selectColor = position;
                    notifyDataSetChanged();
                }
            });

            if (selectColor == position){
                holder.stroke.setVisibility(View.VISIBLE);

                int intColor = colorList.get(selectColor);
                String hexCode = "#" + Integer.toHexString(intColor).substring(4);
                Log.d(TAG, "initialColor: "+  hexCode);
                Log.d(TAG, "initialColor2: "+  colorList.get(selectColor));

            }else {
                holder.stroke.setVisibility(View.GONE);

            }
        }

        @Override
        public int getItemCount() {
            return colorList.size();
        }

        public class ColorAdapterVH extends RecyclerView.ViewHolder{

            CircleImageView circleColor;
            ImageView stroke;

            public ColorAdapterVH(@NonNull View itemView) {
                super(itemView);
                circleColor = itemView.findViewById(R.id.color_civ_listItem);
                stroke = itemView.findViewById(R.id.shape_circle_iv_item);
            }
        }
    }

    public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.SizeAdapterVH>{

        List<String> sizes;
        int sizePosition = 2 ;

        public SizeAdapter(List<String> sizes) {
            this.sizes = sizes;
        }

        @NonNull
        @Override
        public SizeAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(requireActivity()).inflate(R.layout.size_product_item, parent, false);
            return new SizeAdapterVH(view);
        }

        @Override
        public void onBindViewHolder(@NonNull SizeAdapterVH holder, int position) {

            String size = sizes.get(position);



            holder.sizeNum.setText(size);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sizePosition = position;
                    notifyDataSetChanged();

                }
            });

            if (sizePosition == position){
                holder.stroke.setVisibility(View.VISIBLE);
                realSize = sizes.get(sizePosition);
                Log.d(TAG, "initial: "+  sizes.get(sizePosition));
            }else {
                holder.stroke.setVisibility(View.GONE);
            }

        }

        @Override
        public int getItemCount() {
            return sizes.size();
        }

        public class SizeAdapterVH extends RecyclerView.ViewHolder{

            TextView sizeNum;
            ImageView stroke;
            public SizeAdapterVH(@NonNull View itemView) {
                super(itemView);
                sizeNum = itemView.findViewById(R.id.size_size_item);
                stroke = itemView.findViewById(R.id.stroke_size_item);
            }
        }
    }

    private void createTableOnFirstStart() {
        cartDB.insertEmptyCart();

        SharedPreferences prefs = requireActivity().getSharedPreferences("prefsCart", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firstStartCart", false);
        editor.apply();
    }

    private void readCursorData() {

        Cursor cursor = cartDB.read_all_dataCart(productModel.getKey_id());
        SQLiteDatabase db = cartDB.getReadableDatabase();
        try {
            while (cursor.moveToNext()) {
                String item_fav_status = cursor.getString(cursor.getColumnIndex(CartDB.CART_STATUS));
                productModel.setIntoCart(item_fav_status);

//                check fav status if you need
//                 if (item_fav_status != null && item_fav_status.equals("0")) {
//
//                }else {item_fav_status != null && item_fav_status.equals("0")}
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }


    }

}
