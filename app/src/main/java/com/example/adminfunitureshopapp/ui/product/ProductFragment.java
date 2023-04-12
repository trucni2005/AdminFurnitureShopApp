package com.example.adminfunitureshopapp.ui.product;

import android.graphics.Outline;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;
import com.example.adminfunitureshopapp.databinding.FragmentProductBinding;
import com.example.adminfunitureshopapp.model.Product.Product;
import com.example.adminfunitureshopapp.model.Product.productsAdapter;
import com.example.adminfunitureshopapp.viewmodel.productsAPIService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;
    private productsAPIService apiServices;
    private RecyclerView rvDogs;
    private ArrayList<Product> products;
    private ArrayList<Product> productsCopy;
    private ArrayList<Product> newProducts;
    private com.example.adminfunitureshopapp.model.Product.productsAdapter productsAdapter;

    public ProductFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = FragmentProductBinding.inflate(getLayoutInflater());
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View viewRoot = binding.getRoot();
        return viewRoot;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        products = new ArrayList<Product>();
        newProducts = new ArrayList<Product>();
        productsAdapter = new productsAdapter(newProducts);
        binding.rvProducts.setAdapter(productsAdapter);
        binding.rvProducts.setLayoutManager(new LinearLayoutManager(getContext()));


        apiServices = new productsAPIService();
        apiServices.getProducts()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Product>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Product> products) {
                        Log.d("DEBUG", "Success");
                        for (Product dog : products) {
                            newProducts.add(dog);
                        }
                        productsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("DEBUG", "Fail" + e.getMessage());
                    }
                });

    }
}