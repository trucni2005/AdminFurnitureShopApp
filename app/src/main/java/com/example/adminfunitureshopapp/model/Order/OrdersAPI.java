package com.example.adminfunitureshopapp.model.Order;

import com.example.adminfunitureshopapp.model.Product.Product;
import com.example.adminfunitureshopapp.model.Product.productsAPI;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface OrdersAPI {
    @GET("getOrders.php")
    Single<List<Order>> getOrders();
}
