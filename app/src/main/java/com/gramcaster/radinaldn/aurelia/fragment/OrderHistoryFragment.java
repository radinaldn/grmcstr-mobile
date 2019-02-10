package com.gramcaster.radinaldn.aurelia.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gramcaster.radinaldn.aurelia.R;
import com.gramcaster.radinaldn.aurelia.adapter.OrderAdapter;
import com.gramcaster.radinaldn.aurelia.model.Order;
import com.gramcaster.radinaldn.aurelia.response.ResponseGetOrders;
import com.gramcaster.radinaldn.aurelia.rest.ApiClient;
import com.gramcaster.radinaldn.aurelia.rest.ApiInterface;
import com.gramcaster.radinaldn.aurelia.util.SessionManager;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by radinaldn on 30/01/19.
 */

public class OrderHistoryFragment extends Fragment {

    private static final String TAG = OrderHistoryFragment.class.getSimpleName();
    private ApiInterface apiService;
    SwipeRefreshLayout swipeRefreshLayout;
    private static final String ARG_STATUS= "status";
    private String status;
    private ArrayList<Order> orders = new ArrayList<>();
    private RecyclerView rvOrder;
    private OrderAdapter adapter;

    private SessionManager sessionManager;
//    private ConnectionDetector cd;

    public OrderHistoryFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getArguments();
        if( extras != null){
            status = extras.getString(ARG_STATUS);
        }

        apiService = ApiClient.getClient().create(ApiInterface.class);
        sessionManager = new SessionManager(getContext());
//        cd = new ConnectionDetector(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_order_history, container, false);

        rvOrder = view.findViewById(R.id.recyclerView);
        swipeRefreshLayout = view.findViewById(R.id.swipe);
        swipeRefreshLayout.setColorSchemeResources(R.color.refresh, R.color.refresh1, R.color.refresh2);
        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshUI();
            }
        });

        refreshUI();

        return view;
    }

    private void refreshUI() {
        orders.clear();
        apiService.ordersFindAllByIdUser(sessionManager.getUserDetails().get(SessionManager.ID)).enqueue(new Callback<ResponseGetOrders>() {
            @Override
            public void onResponse(Call<ResponseGetOrders> call, Response<ResponseGetOrders> response) {
                Log.d(TAG, "onResponse: "+response.toString());
                if (response.isSuccessful()){
                    Log.d(TAG, "onResponse: "+response.body().toString());
                    if (response.body().getOrder().size() > 0){
                        orders.addAll(response.body().getOrder());
                        Log.i(TAG, "onResponse: ordeers.size() : "+orders.size());
                        adapter = new OrderAdapter(getContext(), orders);

                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
                        rvOrder.setLayoutManager(layoutManager);
                        rvOrder.setAdapter(adapter);
                    } else {
                        Log.i(TAG, "onResponse: no data");
                    }
                } else {
                    Toast.makeText(getContext(), getContext().getString(R.string.error_occured), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseGetOrders> call, Throwable t) {
                Toast.makeText(getContext(), getContext().getString(R.string.failed_to_connect_to_server), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
        swipeRefreshLayout.setRefreshing(false);
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
