package ro.msg.learning.shop.myodata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.msg.learning.shop.entities.OrderDetails;
import ro.msg.learning.shop.entities.Product;
import ro.msg.learning.shop.entities.ProductCategory;
import ro.msg.learning.shop.repositories.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */
@Component
public class MyODataStorage {

    private final EmployeeRepository employeeRepository;
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;

    @Autowired
    public MyODataStorage(EmployeeRepository employeeRepository, OrderDetailsRepository orderDetailsRepository, OrderRepository orderRepository, ProductRepository productRepository, ProductCategoryRepository productCategoryRepository) {
        this.employeeRepository = employeeRepository;
        this.orderRepository = orderRepository;
        this.orderDetailsRepository = orderDetailsRepository;
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
    }

    public Map<String, Object> getEmployee(long id) {
        return MyODataMapper.map(employeeRepository.getOne(id));
    }

    public Map<String, Object> getOrder(long id) {
        return MyODataMapper.map(orderRepository.getOne(id));
    }

    public Map<String, Object> getOrderDetails(long id) {
        return MyODataMapper.map(orderDetailsRepository.getOne(id));
    }

    public Map<String, Object> getProduct(long id) {
        return MyODataMapper.map(productRepository.getOne(id));
    }

    public Map<String, Object> getProductCategory(long id) {
        return MyODataMapper.map(productCategoryRepository.getOne(id));
    }

    public List<Map<String, Object>> getEmployeeSet() {
        return employeeRepository.findAll().stream().map(MyODataMapper::map).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getOrderSet() {
        return orderRepository.findAll().stream().map(MyODataMapper::map).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getOrderDetailsSet() {
        return orderDetailsRepository.findAll().stream().map(MyODataMapper::map).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getProductSet() {
        return productRepository.findAll().stream().map(MyODataMapper::map).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getProductCategorySet() {
        return productCategoryRepository.findAll().stream().map(MyODataMapper::map).collect(Collectors.toList());
    }

    public Map<String, Object> getEmployeeForOrder(long id) {
        return MyODataMapper.map(employeeRepository.findByOrdersId(id));
    }

    public Map<String, Object> getOrderForOrderDetails(long id) {
        return MyODataMapper.map(orderRepository.findByOrdersDetailsId(id));
    }

    public Map<String, Object> getProductForOrderDetails(long id) {
        OrderDetails orderDetails = orderDetailsRepository.findOne(id);
        if (orderDetails != null) {
            return MyODataMapper.map(orderDetails.getProduct());
        }
        return null;
    }

    public Map<String, Object> getProductCategoryForProduct(long id) {
        Product product = productRepository.findOne(id);
        if (product != null) {
            return MyODataMapper.map(product.getCategory());
        }
        return null;
    }

    public Map<String, Object> getProductCategoryForProductCategory(long id) {
        ProductCategory productCategory = productCategoryRepository.findOne(id);
        if (productCategory != null) {
            if (productCategory.getMainCategory() != null) {
                return MyODataMapper.map(productCategory.getMainCategory());
            }
            return new HashMap<>();
        }
        return null;
    }

    public List<Map<String, Object>> getOrderSetForEmployee(long id) {
        return orderRepository.findAllByEmployeeId(id).stream().map(MyODataMapper::map).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getOrderDetailsSetForOrder(long id) {
        return orderDetailsRepository.findAllByOrderId(id).stream().map(MyODataMapper::map).collect(Collectors.toList());
    }

    public List<Map<String, Object>> getProductSetForProductCategory(long id) {
        return productRepository.findAllByCategoryId(id).stream().map(MyODataMapper::map).collect(Collectors.toList());
    }
}
