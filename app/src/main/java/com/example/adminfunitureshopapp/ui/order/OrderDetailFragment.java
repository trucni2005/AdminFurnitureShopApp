package com.example.adminfunitureshopapp.ui.order;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.adminfunitureshopapp.databinding.FragmentOrderDetailBinding;
import com.example.adminfunitureshopapp.model.Item.Item;
import com.example.adminfunitureshopapp.model.Item.ItemAdapter;
import com.example.adminfunitureshopapp.model.Order.Order;

import java.util.ArrayList;

public class OrderDetailFragment extends Fragment {
    private FragmentOrderDetailBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOrderDetailBinding.inflate(inflater, container, false);
        Bundle args = getArguments();
        if (args != null) {
            int id = args.getInt("id");
            int idUser = args.getInt("idUser");
            String address = args.getString("address");
            String phone = args.getString("phone");
            String email = args.getString("email");
            String fullname = args.getString("fullname");
            long totalPrice = args.getLong("totalPrice");
            ArrayList<Item> itemList = (ArrayList<Item>) args.getSerializable("itemList");
            binding.tvId.setText(String.valueOf(id));
            binding.tvAddress.setText(address);
            binding.tvPhone.setText(phone);
            binding.tvEmail.setText(email);
            binding.tvTotalprice.setText(String.valueOf(totalPrice));
            binding.tvName.setText(fullname);
            RecyclerView recyclerView = binding.rvProducts;
            ItemAdapter adapter = new ItemAdapter(itemList);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        }
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

