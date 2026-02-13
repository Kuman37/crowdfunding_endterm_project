package com.crowdfunding.controller;

import com.crowdfunding.model.Payment;
import com.crowdfunding.service.ProjectService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final ProjectService service;
    public PaymentController(ProjectService service) { this.service = service; }

    @PostMapping
    public Payment process(@RequestBody Map<String, Object> body) {
        return service.processPayment(
                (Integer) body.get("pledgeId"),
                (String) body.get("method")
        );
    }
}