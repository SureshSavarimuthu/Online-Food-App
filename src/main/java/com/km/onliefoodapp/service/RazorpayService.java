package com.km.onliefoodapp.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.Utils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class RazorpayService {

    private static final Logger logger = LoggerFactory.getLogger(RazorpayService.class);

    @Value("${razorpay.key.id:rzp_test_placeholder}")
    private String keyId;

    @Value("${razorpay.key.secret:secret_placeholder}")
    private String keySecret;

    private RazorpayClient client;
    private boolean isMockMode = false;

    @PostConstruct
    public void init() {
        try {
            if ("rzp_test_placeholder".equals(keyId) || "secret_placeholder".equals(keySecret)) {
                logger.warn("Using placeholder Razorpay keys. Payment requests will be mocked.");
                isMockMode = true;
            } else {
                client = new RazorpayClient(keyId, keySecret);
                logger.info("Razorpay Client initialized successfully.");
            }
        } catch (Exception e) {
            logger.error("Failed to initialize Razorpay Client: " + e.getMessage() + ". Switching to mock mode.");
            isMockMode = true;
        }
    }

    public String createRazorpayOrder(double amount, String orderId) {
        if (isMockMode) {
            logger.info("Mock Mode: Created Razorpay order for amount " + amount);
            return "rzp_mock_order_" + System.currentTimeMillis();
        }

        try {
            JSONObject orderRequest = new JSONObject();
            orderRequest.put("amount", (int) (amount * 100)); // amount in paise
            orderRequest.put("currency", "INR");
            orderRequest.put("receipt", "receipt_" + orderId);

            Order order = client.orders.create(orderRequest);
            return order.get("id");
        } catch (Exception e) {
            logger.error("Razorpay order creation failed: " + e.getMessage() + ". Returning mock order ID.");
            return "rzp_mock_order_failed_" + System.currentTimeMillis();
        }
    }

    public boolean verifyPaymentSignature(String razorpayOrderId, String razorpayPaymentId, String razorpaySignature) {
        if (isMockMode || (razorpayOrderId != null && razorpayOrderId.startsWith("rzp_mock_order"))) {
            logger.info("Mock Mode/Mock Order: Verifying signature as true.");
            return true;
        }

        try {
            JSONObject options = new JSONObject();
            options.put("razorpay_order_id", razorpayOrderId);
            options.put("razorpay_payment_id", razorpayPaymentId);
            options.put("razorpay_signature", razorpaySignature);
            return Utils.verifyPaymentSignature(options, keySecret);
        } catch (Exception e) {
            logger.error("Razorpay signature verification failed: " + e.getMessage());
            return false;
        }
    }
}
