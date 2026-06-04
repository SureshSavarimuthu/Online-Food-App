package com.km.onliefoodapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.km.onliefoodapp.entity.Customer;
import com.km.onliefoodapp.entity.FoodOrders;

@Service
public class NotificationService {

    private static final Logger logger = LoggerFactory.getLogger(NotificationService.class);

    public void sendOrderStatusNotification(FoodOrders order) {
        Customer customer = order.getCustomer();
        if (customer == null) return;

        String phone = String.valueOf(customer.getPhoneNumber());
        String name = customer.getName();
        String status = String.valueOf(order.getStatus());

        logger.info("---------------- MOCK SMS NOTIFICATION ----------------");
        logger.info("To: +91 " + phone);
        logger.info("Message: Hello " + name + ", your Food Order #" + order.getId() + " status has been updated to: [" + status + "].");
        logger.info("-------------------------------------------------------");
    }

    public void sendPaymentSuccessNotification(FoodOrders order) {
        Customer customer = order.getCustomer();
        if (customer == null) return;

        String phone = String.valueOf(customer.getPhoneNumber());
        String name = customer.getName();
        double amount = order.getTotalcost();

        logger.info("---------------- MOCK EMAIL NOTIFICATION ----------------");
        logger.info("To: (Customer Email linked to user account)");
        logger.info("Subject: Payment Successful - Order #" + order.getId());
        logger.info("Body: Dear " + name + ",\n\nWe have received your payment of Rs. " + amount + 
                    " for Order #" + order.getId() + ".\nOur chef has started preparing your fresh food.\n\nThank you for choosing us!");
        logger.info("---------------------------------------------------------");
    }
}
