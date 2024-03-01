package kz.wonder.wonderuser.openfeign.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "categories", url = "https://kaspi.kz/shop/api/products/classification/categories")
public interface CategoriesProxy {

    @GetMapping
    Object getCategories(
            @RequestHeader("X-Auth-Token") String token,
            @RequestHeader("Accept") String acceptHeader);

}
