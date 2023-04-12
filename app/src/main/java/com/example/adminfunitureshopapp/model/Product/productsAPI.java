package com.example.adminfunitureshopapp.model.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface productsAPI {
    @GET("getProducts.php")
    Single<List<Product>> getProducts();

    @GET("getProductsByCategories.php")
    Single<List<Product>> getProductsByCategories(
            @Query("categoryId") int categoryId
    );
}
