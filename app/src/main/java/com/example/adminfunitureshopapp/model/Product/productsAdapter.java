package com.example.adminfunitureshopapp.model.Product;

import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.AdminFunitureShopApp.ProductDetail;
import com.example.adminfunitureshopapp.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class productsAdapter extends RecyclerView.Adapter<productsAdapter.Viewholder> implements Filterable {
    private static List<Product> products;
    private static List<Product> productsCopy;

    public productsAdapter(List<Product> userList) {
        this.products = userList;
        this.productsCopy = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item, parent, false);
        return new Viewholder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        holder.tvName.setText(products.get(position).getName());

        holder.tvAmount.setText(String.valueOf(products.get(position).getQuantity()));

        holder.tvPrice.setText(String.valueOf(products.get(position).getPrice()) + "$");

        Picasso.get().load(products.get(position).getImageUrl()).into(holder.imageUrl);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        public ImageView imageUrl;
        public TextView tvName;
        public TextView tvDetail;
        public TextView tvAmount;
        public TextView tvPrice;
        public Button btnAddCart;


        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageUrl = (ImageView) itemView.findViewById(R.id.image);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
            //tvDetail = (TextView) itemView.findViewById(R.id.tv_detail);
            tvPrice = (TextView) itemView.findViewById(R.id.tv_price);
            tvAmount = (TextView) itemView.findViewById(R.id.tv_amount);
        }
    }
}
