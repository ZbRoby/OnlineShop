package ro.msg.learning.shop.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ro.msg.learning.shop.model.PLQList;
import ro.msg.learning.shop.services.StockExporter;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 * Export stock will be a GET mapping, CSV response.
 */
@RestController
public class StockExporterController {

    private final StockExporter stockExporter;

    @Autowired
    public StockExporterController(StockExporter stockExporter) {
        this.stockExporter = stockExporter;
    }

    @GetMapping(value = "/rest/stock", produces = "text/csv")
    public PLQList getStock() {
        return new PLQList(stockExporter.getStock());
    }

    @GetMapping(value = "/rest/stock/{id}", produces = "text/csv")
    public PLQList getStock(@PathVariable("id") long id) {
        return new PLQList(stockExporter.getStockOfLocation(id));
    }

}
