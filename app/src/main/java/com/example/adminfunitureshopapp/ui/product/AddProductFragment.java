package com.example.adminfunitureshopapp.ui.product;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

import com.example.adminfunitureshopapp.databinding.FragmentAddProductBinding;
import com.example.adminfunitureshopapp.model.Categories.Categories;
import com.example.adminfunitureshopapp.viewmodel.CategoriesAPIService;
import com.example.adminfunitureshopapp.viewmodel.productsAPIService;

import java.util.ArrayList;
import java.util.List;

public class AddProductFragment extends Fragment {
    private FragmentAddProductBinding binding;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private CategoriesAPIService categoriesAPIService;
    private List<String> newCategories = new ArrayList<>();
    private ArrayAdapter<String> categoryAdapter;
    private Spinner spinnerCategory;
    private productsAPIService productAPIService;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.inflate(inflater, container, false);
        spinnerCategory = binding.spinnerCategory;
        categoryAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, newCategories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(categoryAdapter);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        categoriesAPIService = new CategoriesAPIService();
        categoriesAPIService.getCategories()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<Categories>>() {
                    @Override
                    public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull List<Categories> categories) {
                        for (Categories category : categories) {
                            String categoryName = category.getName();
                            newCategories.add(categoryName);
                        }
                        categoryAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                        Log.d("DEBUG", "Fail" + e.getMessage());
                    }
                });

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy giá trị của trường edittextName
                String name = binding.edittextName.getText().toString();
                int quantity = Integer.parseInt(binding.edittextQuantity.getText().toString());
                String imageUrl = binding.edittextImageurl.getText().toString();
                int originalPrice = Integer.parseInt(binding.edittextOriginalprice.getText().toString());
                int discount = Integer.parseInt(binding.edittextDiscount.getText().toString());
                String detail = binding.edittextDetail.getText().toString();
                String type = binding.edittextType.getText().toString();
                int position = spinnerCategory.getSelectedItemPosition();
                int categoryId = position + 1; // tăng lên 1 để được id
//                addProduct(name, quantity, imageUrl, originalPrice, discount, detail, type, categoryId);
            }
        });
    }

//    private void addProduct(String name, int quantity, String imageUrl, int originalPrice, int discount, String detail, String type, int categoryId) {
//        productAPIService = new productsAPIService();
//        Log.d("DEBUG", "addProduct");
//        compositeDisposable.add(productAPIService.addProduct(name, quantity, imageUrl, originalPrice, discount, detail, type, categoryId)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        productModel -> {
//                            if (productModel.isSuccess()) {
//                                // Xử lý khi thêm sản phẩm thành công
//                                Toast.makeText(getApplicationContext(), "Add product success!", Toast.LENGTH_SHORT).show();
//                            } else {
//                                // Xử lý khi thêm sản phẩm thất bại
//                                Toast.makeText(getApplicationContext(), productModel.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        }
//                ));
//    }

}
