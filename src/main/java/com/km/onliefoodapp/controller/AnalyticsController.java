package com.km.onliefoodapp.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.km.onliefoodapp.entity.FoodProduct;
import com.km.onliefoodapp.service.AnalyticsService;
import com.km.onliefoodapp.util.ResponseStructure;

@RestController
@RequestMapping("reports")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("revenue")
    public ResponseEntity<ResponseStructure<Map<String, Object>>> getTotalRevenue() {
        return analyticsService.getTotalRevenue();
    }

    @GetMapping("orders-by-status")
    public ResponseEntity<ResponseStructure<Map<String, Object>>> getOrdersByStatus() {
        return analyticsService.getOrdersByStatus();
    }

    @GetMapping("low-stock")
    public ResponseEntity<ResponseStructure<List<FoodProduct>>> getLowStockProducts(
            @RequestParam(defaultValue = "5") int threshold) {
        return analyticsService.getLowStockProducts(threshold);
    }

    @GetMapping("dashboard")
    public ResponseEntity<ResponseStructure<Map<String, Object>>> getDashboardSummary() {
        return analyticsService.getDashboardSummary();
    }
}
