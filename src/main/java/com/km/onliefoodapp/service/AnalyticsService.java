package com.km.onliefoodapp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.dao.FoodOrderDao;
import com.km.onliefoodapp.dao.FoodProductDao;
import com.km.onliefoodapp.entity.FoodOrders;
import com.km.onliefoodapp.entity.FoodProduct;
import com.km.onliefoodapp.entity.Status;
import com.km.onliefoodapp.util.ResponseStructure;

@Service
public class AnalyticsService {

    @Autowired
    private FoodOrderDao foodOrderDao;

    @Autowired
    private FoodProductDao foodProductDao;

    /**
     * Returns total revenue collected from all PAID orders.
     */
    public ResponseEntity<ResponseStructure<Map<String, Object>>> getTotalRevenue() {
        Double totalRevenue = foodOrderDao.getTotalRevenue();

        Map<String, Object> data = new HashMap<>();
        data.put("totalRevenue", totalRevenue);
        data.put("currency", "INR");

        ResponseStructure<Map<String, Object>> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Total revenue fetched successfully");
        response.setData(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns order counts broken down by every status level.
     */
    public ResponseEntity<ResponseStructure<Map<String, Object>>> getOrdersByStatus() {
        Map<String, Object> data = new HashMap<>();

        for (Status status : Status.values()) {
            Long count = foodOrderDao.countByStatus(status);
            data.put(status.name(), count != null ? count : 0L);
        }

        // Also fetch total
        List<FoodOrders> allOrders = foodOrderDao.findAllFoodOrders();
        data.put("TOTAL", (long) allOrders.size());

        ResponseStructure<Map<String, Object>> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Order counts by status fetched successfully");
        response.setData(data);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns a list of FoodProducts where availability is below the threshold (default 5).
     */
    public ResponseEntity<ResponseStructure<List<FoodProduct>>> getLowStockProducts(int threshold) {
        List<FoodProduct> lowStock = foodProductDao.findLowStockProducts(threshold);

        ResponseStructure<List<FoodProduct>> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Low stock products fetched successfully (threshold: " + threshold + ")");
        response.setData(lowStock);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    /**
     * Returns a summary dashboard combining revenue, order counts, and low stock alert count.
     */
    public ResponseEntity<ResponseStructure<Map<String, Object>>> getDashboardSummary() {
        Map<String, Object> dashboard = new HashMap<>();

        // Revenue
        dashboard.put("totalRevenue", foodOrderDao.getTotalRevenue());

        // Order counts per status
        Map<String, Long> orderCounts = new HashMap<>();
        for (Status status : Status.values()) {
            Long count = foodOrderDao.countByStatus(status);
            orderCounts.put(status.name(), count != null ? count : 0L);
        }
        dashboard.put("ordersByStatus", orderCounts);

        // Total orders
        dashboard.put("totalOrders", (long) foodOrderDao.findAllFoodOrders().size());

        // Low stock count (< 5 units)
        dashboard.put("lowStockItemCount", foodProductDao.findLowStockProducts(5).size());

        ResponseStructure<Map<String, Object>> response = new ResponseStructure<>();
        response.setStatus(HttpStatus.OK.value());
        response.setMessage("Dashboard summary fetched successfully");
        response.setData(dashboard);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
