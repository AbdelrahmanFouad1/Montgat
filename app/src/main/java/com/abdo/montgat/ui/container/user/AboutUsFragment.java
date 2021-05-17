package com.abdo.montgat.ui.container.user;

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
import com.abdo.montgat.model.AboutUsModel;
import com.abdo.montgat.ui.container.IOnBackPressed;
import com.abdo.montgat.utlis.Constant;

import java.util.List;

public class AboutUsFragment extends Fragment implements IOnBackPressed {
    private View view;
    private RecyclerView aboutUsRV;
    private ImageView back;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about_us, container, false);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//
//            Window window = requireActivity().getWindow();
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.WHITE);
//
//        }
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        aboutUsRV = view.findViewById(R.id.recyclerview_aboutUs);
        back = view.findViewById(R.id.back_iv_aboutUs);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Constant.replaceFragment(AboutUsFragment.this, new UserFragment());
            }
        });

        List<AboutUsModel> list = new Data().getDataFromAboutUs();
        aboutUsRV.setAdapter(new AboutUsAdapter(list));
    }


    private class AboutUsAdapter extends RecyclerView.Adapter<AboutUsAdapter.AboutUsVH>{

        List<AboutUsModel> aboutUsModelList ;

        public AboutUsAdapter(List<AboutUsModel> aboutUsModelList) {
            this.aboutUsModelList = aboutUsModelList;
        }

        @NonNull
        @Override
        public AboutUsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(requireActivity()).inflate(R.layout.about_us_item, parent, false);
            return new AboutUsVH(v);
        }

        @Override
        public void onBindViewHolder(@NonNull AboutUsVH holder, int position) {

           holder.title.setText( aboutUsModelList.get(position).getTitle());
           holder.description.setText( aboutUsModelList.get(position).getDescription());
        }

        @Override
        public int getItemCount() {
            return aboutUsModelList.size();
        }

        private class AboutUsVH extends RecyclerView.ViewHolder{

            private TextView title, description;
            public AboutUsVH(@NonNull View itemView) {
                super(itemView);
                title = itemView.findViewById(R.id.title_tv_aboutUs);
                description = itemView.findViewById(R.id.description_tv_aboutUs);
            }
        }
    }


    @Override
    public boolean onBackPressed() {
        Constant.replaceFragment(AboutUsFragment.this, new UserFragment());
        return true;
    }
}
