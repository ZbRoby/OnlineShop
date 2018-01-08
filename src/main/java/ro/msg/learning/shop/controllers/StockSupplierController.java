package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.models.PLQList;
import ro.msg.learning.shop.services.StockSupplier;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 * Import stock will be a POST mapping, CSV body, empty response.
 */
@RestController
public class StockSupplierController {

    private final StockSupplier stockSupplier;

    @Autowired
    public StockSupplierController(StockSupplier stockSupplier) {
        this.stockSupplier = stockSupplier;
    }

    @PostMapping(value = "/rest/resupply", consumes = "text/CSV")
    @ResponseStatus(value = HttpStatus.CREATED)
    public void resupply(@RequestBody PLQList plqList) {
        stockSupplier.addStock(plqList.getMap());
    }
}
