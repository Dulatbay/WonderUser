package kz.wonder.wonderuser.controller;

import kz.wonder.wonderuser.openfeign.proxy.CategoriesProxy;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final CategoriesProxy categoriesProxy;


    public TestController(CategoriesProxy categoriesProxy) {
        this.categoriesProxy = categoriesProxy;
    }

    @GetMapping("/getCategories")
    public ResponseEntity<Object> getCat(){
        System.out.println("**********");
        return ResponseEntity.ok(categoriesProxy.getCategories("D3Et6B7dntkwj2e9DwrM/QZ8O77JTsoqxHw2IlOcPgI=", "application/json"));
    }
}
