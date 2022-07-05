package controller;

import model.InvoiceLine;

import java.util.ArrayList;

public class InvoiceLineCalculation {
    private ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();

    public ArrayList<InvoiceLine> getInvoiceLines() {
        return this.invoiceLines;
    }

    public void setInvoiceLines(String invoiceLinePath)
    {
        ArrayList<ArrayList<String>> fileRows= new ArrayList<ArrayList<String>>();
        ReadFile readInvoiceLineFile = new ReadFile();
        fileRows = readInvoiceLineFile.readInvoiceLine(invoiceLinePath);

        InvoiceLine invoiceLine;
        for(int i = 0; i < fileRows.size(); i++)
        {
                invoiceLine = new InvoiceLine();
              // System.out.println(fileRows.get(i));
                invoiceLine.setInvoiceNumber(Integer.valueOf(fileRows.get(i).get(0)));
                invoiceLine.setItemName(fileRows.get(i).get(1));
                invoiceLine.setItemPrice(Double.valueOf(fileRows.get(i).get(2)));
                invoiceLine.setItemCount(Integer.valueOf(fileRows.get(i).get(3)));
                invoiceLine.setItemTotalPrice(invoiceLine.getItemPrice() * invoiceLine.getItemCount());
                invoiceLines.add(invoiceLine);

        }

    }
}
