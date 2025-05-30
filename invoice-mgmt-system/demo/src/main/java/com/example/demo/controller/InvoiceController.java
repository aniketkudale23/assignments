package com.example.demo.controller;

import com.example.demo.entity.Invoice;
import com.example.demo.service.InvoiceService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.time.LocalDate;

@Controller
@RequestMapping("/invoices")
class InvoiceController {

    @Autowired
    InvoiceService service;

    public InvoiceController(InvoiceService service) {
        this.service = service;
    }

    @GetMapping
    public String listInvoices(Model model) {
        model.addAttribute("invoices", service.findAll());
        return "invoices";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        model.addAttribute("invoice", new Invoice());
        return "invoice_form";
    }

    @PostMapping
    public String saveInvoice(@ModelAttribute Invoice invoice) {
        invoice.setDate(LocalDate.now());
        service.save(invoice);
        return "redirect:/invoices";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteById(id);
        return "redirect:/invoices";
    }

    @GetMapping("/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=invoices.pdf");

        List<Invoice> invoices = service.findAll();

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            document.add(new Paragraph("Invoice List"));
            document.add(new Paragraph("\n"));
            for (Invoice invoice : invoices) {
                document.add(new Paragraph("Invoice: " + invoice.getId() + " - " +
                        invoice.getCustomerName() + " - Rs." + invoice.getAmount()));
            }
            document.close();
        } catch (DocumentException e) {
            throw new IOException(e.getMessage());
        }
    }


}