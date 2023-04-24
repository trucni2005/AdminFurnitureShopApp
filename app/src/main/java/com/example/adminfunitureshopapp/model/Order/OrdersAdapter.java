package com.example.adminfunitureshopapp.model.Order;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminfunitureshopapp.R;
import com.example.adminfunitureshopapp.model.Product.Product;
import com.example.adminfunitureshopapp.model.Product.productsAdapter;
import com.example.adminfunitureshopapp.ui.product.UpdateProductFragment;
import com.example.adminfunitureshopapp.viewmodel.OrdersAPIService;
import com.example.adminfunitureshopapp.viewmodel.productsAPIService;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.Viewholder>{

    private List<Order> Orders;
    private OrdersAPIService APIService;
    private FragmentActivity fragment;

    public OrdersAdapter(List<Order> OrderList, FragmentActivity fragment) {
        this.Orders = OrderList;
        this.fragment = fragment;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(fragment)
                .inflate(R.layout.order_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public int getItemCount() {
        return Orders.size();
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.Viewholder holder, int position) {

        Order order = Orders.get(position);
        holder.tvId.setText(String.valueOf(order.getId()));
        holder.tvPrice.setText(String.valueOf(order.getTotalPrice()) + "$");

    }

    public static class Viewholder extends RecyclerView.ViewHolder {
        public ImageView imageUrl;
        public TextView tvId;
        public TextView tvPrice;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvPrice = itemView.findViewById(R.id.tv_price);
        }
    }
}
