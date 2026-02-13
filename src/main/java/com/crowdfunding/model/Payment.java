package com.crowdfunding.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "payment")
public class Payment extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "pledge_id", unique = true, nullable = false)
    private Pledge pledge;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate = LocalDateTime.now();

    public Payment() {}

    public Pledge getPledge() { return pledge; }
    public void setPledge(Pledge pledge) { this.pledge = pledge; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
}