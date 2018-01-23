package ro.msg.learning.shop.myodata;

import ro.msg.learning.shop.entities.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
public class MyODataMapper {

    private MyODataMapper() {

    }

    public static Map<String, Object> map(Employee employee) {
        Map<String, Object> data = new HashMap<>();
        data.put("Id", employee.getId());
        data.put("FirstName", employee.getFirstName());
        data.put("LastName", employee.getLastName());
        data.put("HomePhone", employee.getHomePhone());
        data.put("Title", employee.getTitle());
        return data;
    }

    public static Map<String, Object> map(Order order) {
        Map<String, Object> data = new HashMap<>();
        data.put("Id", order.getId());
        data.put("ShippedDate", order.getShippedDate());
        data.put("OrderDate", order.getOrderDate());
        return data;
    }

    public static Map<String, Object> map(OrderDetails orderDetails) {
        Map<String, Object> data = new HashMap<>();
        data.put("Id", orderDetails.getId());
        data.put("Quantity", orderDetails.getQuantity());
        data.put("Discount", orderDetails.getDiscount());
        data.put("UnitPrice", orderDetails.getUnitPrice());
        return data;
    }

    public static Map<String, Object> map(Product product) {
        Map<String, Object> data = new HashMap<>();
        data.put("Id", product.getId());
        data.put("Name", product.getName());
        data.put("SupplierName", product.getSupplierName());
        data.put("Description", product.getDescription());
        data.put("CurrencyCode", product.getCurrencyCode());
        data.put("Price", product.getPrice());
        return data;
    }

    public static Map<String, Object> map(ProductCategory productCategory) {
        Map<String, Object> data = new HashMap<>();
        data.put("Id", productCategory.getId());
        data.put("Name", productCategory.getName());
        return data;
    }

}

