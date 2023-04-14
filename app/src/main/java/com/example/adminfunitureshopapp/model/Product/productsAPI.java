package com.example.adminfunitureshopapp.model.Product;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface productsAPI {
    @GET("getProducts.php")
    Single<List<Product>> getProducts();

    @POST("addProduct.php")
    @FormUrlEncoded
    Single<Integer> addProduct(
            @Field("name") String name,
            @Field("quantity") Integer quantity,
            @Field("imageUrl") String imageUrl,
            @Field("originalPrice") Integer originalPrice,
            @Field("discount") Integer discount,
            @Field("detail") String detail,
            @Field("type") String type,
            @Field("categoryId") String categoryId
    );

    @POST("deleteProduct.php")
    @FormUrlEncoded
    Single<Integer> deleteProduct(@Field("productId") int productId);

}
