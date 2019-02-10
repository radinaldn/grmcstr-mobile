package com.gramcaster.radinaldn.aurelia.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gramcaster.radinaldn.aurelia.R;
import com.gramcaster.radinaldn.aurelia.model.Order;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by radinaldn on 10/02/19.
 */

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private static final String TAG_ID_PEMESANAN = "id_pemesanan";
    private Context context;

    private ArrayList<Order> dataList;
    private static final String TAG = OrderAdapter.class.getSimpleName();

    private static String NIK;
//    private OrderankuFragment fragment;
    private String proses;

    public OrderAdapter(Context context, ArrayList<Order> dataList) {
        this.context = context;
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.item_order, parent, false);


        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final OrderViewHolder holder, final int position) {

        // convert saldo
        // convert format saldo into rupiah
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        String biayaInRP = formatRupiah.format(Double.parseDouble(String.valueOf(dataList.get(position).getBiaya())));

        // convert date format

        holder.tvCreatedAt.setText(dataList.get(position).getCreatedAt());

        switch (dataList.get(position).getStatus()){
            case "1":
                holder.ivStatus.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_check_circle_green));
                break;
            case "0":
                holder.ivStatus.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_sync_orange));
                break;
        }

        holder.tvUsernameIg.setText("@"+dataList.get(position).getUsernameIg());
        holder.tvBiaya.setText(biayaInRP);

        holder.tvTanggalExpired.setText("Exp at : "+dataList.get(position).getTanggalExpired());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(context, DetailOrderActivity.class);
//                i.putExtra(Order.ID, dataList.get(position).getId());
//
//                // Aktifkan untuk mode debugging
//                //Toast.makeText(itemView.getContext(), "ID_MENGAJAR : "+tv_idmengajar.getText(), Toast.LENGTH_SHORT).show();
//
//                context.startActivity(i);
            }
        });
    }


    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {

        TextView tvCreatedAt, tvUsernameIg, tvBiaya, tvTanggalExpired;
        ImageView ivStatus;
        CardView cardView;

        public OrderViewHolder(final View itemView) {
            super(itemView);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvUsernameIg = itemView.findViewById(R.id.tvUsernameIg);
            tvBiaya = itemView.findViewById(R.id.tvBiaya);
            tvTanggalExpired = itemView.findViewById(R.id.tvTanggalExpired);
            ivStatus = itemView.findViewById(R.id.ivStatus);
            cardView = itemView.findViewById(R.id.cvOrder);

        }
    }
}
