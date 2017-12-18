package ro.msg.learning.shop.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.msg.learning.shop.entities.Location;
import ro.msg.learning.shop.entities.Order;
import ro.msg.learning.shop.entities.OrderDetails;
import ro.msg.learning.shop.entities.Revenue;
import ro.msg.learning.shop.repositories.OrderRepository;
import ro.msg.learning.shop.repositories.RevenueRepository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zbiera Alexandru-Robert <Robert.Zbiera@msg.group>
 */

@Component
public class CalculateRevenue {

    private final OrderRepository orderRepository;
    private final RevenueRepository revenueRepository;
    private final ZoneId zoneId;

    @Autowired
    public CalculateRevenue(OrderRepository orderRepository, RevenueRepository revenueRepository, ZoneId zoneId) {
        this.orderRepository = orderRepository;
        this.revenueRepository = revenueRepository;
        this.zoneId = zoneId;
    }

    @Transactional
    @Scheduled(cron = "${onlineShop.util.cronString:00 00 22 * * ?}")
    public void calculate() {
        List<Revenue> revenues = new ArrayList<>();
        Date date = Date.valueOf(LocalDate.now(zoneId));

        Map<Location, Double> locationAndSum = new HashMap<>();
        for (Order order : orderRepository.findAllByOrderDate(date)) {
            for (OrderDetails orderDetails : order.getOrdersDetails()) {
                Location tempLocation = orderDetails.getLocation();
                double oldSum = locationAndSum.getOrDefault(tempLocation, 0D);
                locationAndSum.put(tempLocation, oldSum + orderDetails.getQuantity() * orderDetails.getUnitPrice());
            }
        }

        for (Map.Entry<Location, Double> locationSumEntry : locationAndSum.entrySet()) {
            Revenue tempRevenue = new Revenue();
            tempRevenue.setLocation(locationSumEntry.getKey());
            tempRevenue.setSum(locationSumEntry.getValue());
            tempRevenue.setDate(date);
            revenues.add(tempRevenue);
        }

        revenueRepository.save(revenues);
    }
}
