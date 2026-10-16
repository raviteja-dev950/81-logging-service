package com.logging.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.logging.model.Order;
import com.logging.model.Payment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class LogConsumer {
    
    private List<String> logs = new ArrayList<>();
    private ObjectMapper mapper = new ObjectMapper();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    @KafkaListener(topics = "orders", groupId = "logging-group-RAVI-FINAL")
    public void consumeOrder(String message) {
        try {
            System.out.println("========================================");
            System.out.println(">>> [81-LOGGING] RAW MESSAGE (orders): " + message);
            Order order = mapper.readValue(message, Order.class);
            System.out.println(">>> [81-LOGGING] CONSUMED Order Event: Order{id='" + order.getId() + "', product='" + order.getProduct() + "', qty=" + order.getQty() + "}");
            System.out.println("========================================");
            System.out.println("********** LOG SAVED - ORDER **********");
            String logEntry = String.format("[%s] LOG - Order ID: %s | Product: %s | Qty: %d | Source: 78-order-service | Topic: orders | Group: logging-group-RAVI-FINAL",
                    LocalDateTime.now().format(formatter), order.getId(), order.getProduct(), order.getQty());
            System.out.println(logEntry);
            System.out.println("***************************************");
            logs.add(logEntry);
        } catch (Exception e) {
            System.out.println(">>> [81-LOGGING] Error parsing order: " + e.getMessage());
        }
    }
    
    @KafkaListener(topics = "payments", groupId = "logging-group-RAVI-FINAL")
    public void consumePayment(String message) {
        try {
            System.out.println("========================================");
            System.out.println(">>> [81-LOGGING] RAW MESSAGE (payments): " + message);
            Payment payment = mapper.readValue(message, Payment.class);
            System.out.println(">>> [81-LOGGING] CONSUMED Payment Event: PaymentId=" + payment.getPaymentId() + " OrderId=" + payment.getOrderId() + " Status=" + payment.getStatus() + " Amount=$" + payment.getAmount());
            System.out.println("========================================");
            System.out.println("********** LOG SAVED - PAYMENT **********");
            String logEntry = String.format("[%s] LOG - Payment ID: %s | Order ID: %s | Status: %s | Amount: $%.1f | Source: 80-payment-service | Topic: payments | Group: logging-group-RAVI-FINAL",
                    LocalDateTime.now().format(formatter), payment.getPaymentId(), payment.getOrderId(), payment.getStatus(), payment.getAmount());
            System.out.println(logEntry);
            System.out.println("***************************************");
            logs.add(logEntry);
        } catch (Exception e) {
            System.out.println(">>> [81-LOGGING] Error parsing payment: " + e.getMessage());
            // Try as plain string log
            String logEntry = String.format("[%s] LOG - RAW: %s | Topic: payments", LocalDateTime.now().format(formatter), message);
            System.out.println(logEntry);
            logs.add(logEntry);
        }
    }
    
    public List<String> getLogs() {
        return logs;
    }
}