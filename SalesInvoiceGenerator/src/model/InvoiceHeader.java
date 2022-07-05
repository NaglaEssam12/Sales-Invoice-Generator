package model;

import controller.InvoiceLineCalculation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {
    private int invoiceNumber;
    private String invoiceDate;
    private String customerName;
    private double total;
    private ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
    //InvoiceLineCalculation invoiceLineCalculation = new InvoiceLineCalculation();

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return invoiceLines;
    }

    public void setInvoiceLines(ArrayList<InvoiceLine> invoiceLines) {
        for(int i = 0; i < invoiceLines.size(); i++)
            this.invoiceLines.add(invoiceLines.get(i));
    }

    public InvoiceHeader() {
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
