package com.example.adminfunitureshopapp.model.Order;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminfunitureshopapp.R;
import com.example.adminfunitureshopapp.model.Categories.Categories;
import com.example.adminfunitureshopapp.model.Item.Item;
import com.example.adminfunitureshopapp.model.Product.Product;
import com.example.adminfunitureshopapp.model.Product.productsAdapter;
import com.example.adminfunitureshopapp.ui.order.OrderDetailFragment;
import com.example.adminfunitureshopapp.ui.product.UpdateProductFragment;
import com.example.adminfunitureshopapp.viewmodel.CategoriesAPIService;
import com.example.adminfunitureshopapp.viewmodel.OrdersAPIService;
import com.example.adminfunitureshopapp.viewmodel.productsAPIService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.Viewholder>{

    private List<Order> Orders;
    private OrdersAPIService APIService;
    private FragmentManager fragmentManager;

    public OrdersAdapter(List<Order> OrderList, FragmentManager fragmentManager) {
        this.Orders = OrderList;
        this.fragmentManager = fragmentManager;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.order_item, parent, false);
        return new Viewholder(view);
    }

    @Override
    public int getItemCount() {
        return Orders.size();
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.Viewholder holder, int position) {

        Order order = Orders.get(holder.getAdapterPosition());
        holder.tvId.setText(String.valueOf(order.getId()));
        holder.tvPrice.setText(String.valueOf(order.getTotalPrice()) + "$");
        
        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    int id = order.getId();
                    CancelOrder(id);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Orders.get(holder.getAdapterPosition()).getId();
                int idUser = Orders.get(holder.getAdapterPosition()).getIdUser();
                String address = Orders.get(holder.getAdapterPosition()).getAddress();
                String phone = Orders.get(holder.getAdapterPosition()).getPhone();
                long totalPrice = Orders.get(holder.getAdapterPosition()).getTotalPrice();
                ArrayList<Item> item = Orders.get(holder.getAdapterPosition()).getItem();
                String email = Orders.get(holder.getAdapterPosition()).getEmail();
                String fullname = Orders.get(holder.getAdapterPosition()).getFullname();
                OrderDetailFragment fragment_detail = new OrderDetailFragment();
                Bundle args = new Bundle();
                Log.d("id", String.valueOf(id));
                args.putInt("id", id);
                Log.d("idUser", String.valueOf(idUser));
                args.putInt("idUser", idUser);
                Log.d("address", String.valueOf(address));
                args.putString("address", address);
                Log.d("phone", String.valueOf(phone));
                args.putString("phone", phone);
                args.putString("fullname", fullname);
                args.putLong("totalPrice", totalPrice);
                args.putString("email", email);
                args.putSerializable("itemList", item);
                fragment_detail.setArguments(args);
                fragmentManager.beginTransaction()
                        .replace(R.id.nav_host_fragment_content_main, fragment_detail)
                        .addToBackStack(null)
                        .commit();
            }
        });

    }

    public void CancelOrder(int id) {
        APIService = new OrdersAPIService();
        Disposable disposable = APIService.CancelOrder(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<Integer>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull Integer integer) {
                        Log.d("Delete", "SUCCESS");
                        List<Order> newData = new ArrayList<>(Orders);
                        for (Order order : Orders) {
                            if (order.getId() == id) {
                                newData.remove(order);
                                break;
                            }
                        }
                        updateData(newData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        // TODO: Xử lý khi xóa danh muc thất bại
                        Log.d("Fail", e.getMessage());
                        List<Order> newData = new ArrayList<>(Orders);
                        for (Order order : Orders) {
                            if (order.getId() == id) {
                                newData.remove(order);
                                break;
                            }
                        }
                        updateData(newData);
                    }
                });
    }

    public void updateData(List<Order> newData) {
        Orders = newData;
        notifyDataSetChanged();
    }
    public static class Viewholder extends RecyclerView.ViewHolder {
        public ImageView imageUrl;
        public TextView tvId;
        public TextView tvPrice;
        public Button btnCancel;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvPrice = itemView.findViewById(R.id.tv_price);
            btnCancel = itemView.findViewById(R.id.btn_cancel);
        }
    }
}
