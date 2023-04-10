package com.example.adminfunitureshopapp.ui.product;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.adminfunitureshopapp.databinding.FragmentProductBinding;

public class ProductFragment extends Fragment {

    private FragmentProductBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentProductBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.textProduct.setText("This is product fragment");
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}