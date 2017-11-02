package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.model.ShelfProduct;
import ro.msg.learning.shop.services.ProductPresenter;

import java.util.List;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@RestController
public class ProductPresenterController {

    private final ProductPresenter productPresenter;

    @Autowired
    public ProductPresenterController(ProductPresenter productPresenter) {
        this.productPresenter = productPresenter;
    }

    @GetMapping(value = "/rest/seeProducts")
    public List<ShelfProduct> seeProducts() {
        return productPresenter.getProductList();
    }
}
