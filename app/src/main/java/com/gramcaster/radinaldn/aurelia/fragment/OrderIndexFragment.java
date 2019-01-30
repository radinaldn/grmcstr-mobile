package com.gramcaster.radinaldn.aurelia.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gramcaster.radinaldn.aurelia.R;

/**
 * Created by radinaldn on 30/01/19.
 */

public class OrderIndexFragment extends Fragment {

//    ApiInterface apiService;
    SwipeRefreshLayout swipeRefreshLayout;
    private static final String ARG_STATUS= "status";
    private String status;

//    private ConnectionDetector cd;

    public OrderIndexFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getArguments();
        if( extras != null){
            status = extras.getString(ARG_STATUS);
        }

//        apiService = ApiClient.getClient().create(ApiInterface.class);
//        cd = new ConnectionDetector(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_order_index, container, false);


        swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh, R.color.refresh1, R.color.refresh2);
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });

        return view;
    }

    private void showSnackbarConnection(View v) {
        final Snackbar snackbar = Snackbar.make(getView(), "Koneksi internet anda mati", Snackbar.LENGTH_LONG);
        snackbar.setAction("Coba Lagi", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }

}
