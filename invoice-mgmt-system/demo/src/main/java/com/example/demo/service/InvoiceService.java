package com.example.demo.service;

import com.example.demo.entity.Invoice;
import com.example.demo.entity.InvoiceRepository;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    InvoiceRepository repo;

    public List<Invoice> findAll() {
        return repo.findAll();
    }

    public Invoice save(Invoice invoice) {
        return repo.save(invoice);
    }

    public Invoice findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

	
}