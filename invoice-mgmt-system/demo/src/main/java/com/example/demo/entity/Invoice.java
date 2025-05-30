package com.example.demo.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String customerName;
	private Double amount;
	private LocalDate date;

	// Getters and setters
	public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }
	public String getCustomerName() { return customerName; }
	public void setCustomerName(String customerName) { this.customerName = customerName; }
	public Double getAmount() { return amount; }
	public void setAmount(Double amount) { this.amount = amount; }
	public LocalDate getDate() { return date; }
	public void setDate(LocalDate date) { this.date = date; }
}