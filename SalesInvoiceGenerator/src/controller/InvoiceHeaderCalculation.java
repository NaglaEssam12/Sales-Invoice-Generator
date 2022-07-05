package controller;

import model.InvoiceHeader;
import model.InvoiceLine;

import java.util.ArrayList;

public class InvoiceHeaderCalculation {
    private ArrayList<InvoiceHeader> invoiceHeaderLines = new ArrayList<>();

    public ArrayList<InvoiceHeader> getInvoiceHeaderLines() {
        return invoiceHeaderLines;
    }

    public void setInvoiceHeaderLines(String invoiceHeaderPath, String invoiceLinePath) {
        InvoiceLineCalculation invoiceLineCalculation = new InvoiceLineCalculation();
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        ArrayList<InvoiceLine> invoiceLineList = new ArrayList<>();
        invoiceLineCalculation.setInvoiceLines(invoiceLinePath);
        invoiceLines = invoiceLineCalculation.getInvoiceLines();
        ReadFile readInvoiceLineFile = new ReadFile();
        ArrayList<ArrayList<String>> invoiceHeaderRows = new ArrayList<>();
        ArrayList<ArrayList<InvoiceLine>>invoiceLineItems = new ArrayList<ArrayList<InvoiceLine>>();
        InvoiceLine invoiceLine;
        ArrayList<Double> totals = new ArrayList<>();
        invoiceHeaderRows = readInvoiceLineFile.readHeaderLine(invoiceHeaderPath); //"path"
        InvoiceHeader invoiceHeader;
        double total;
        for(int i = 0; i < invoiceHeaderRows.size();i++)
        {
                total = 0;
                invoiceLineList = new ArrayList<>();
                for(int j = 0; j < invoiceLines.size(); j++)
                {
                   System.out.println(invoiceLines.get(j).getInvoiceNumber());
                    if(Integer.valueOf(invoiceHeaderRows.get(i).get(0)) == (invoiceLines.get(j).getInvoiceNumber()))
                    {
                        invoiceLine = new InvoiceLine();
                       // invoiceLineList = new ArrayList<>();
                        //  System.out.println(fileRows.get(i));
                        invoiceLine.setInvoiceNumber(invoiceLines.get(j).getInvoiceNumber());
                        invoiceLine.setItemName(invoiceLines.get(j).getItemName());
                        invoiceLine.setItemPrice(invoiceLines.get(j).getItemPrice());
                        invoiceLine.setItemCount(invoiceLines.get(j).getItemCount());
                        invoiceLine.setItemTotalPrice(invoiceLine.getItemPrice() * invoiceLine.getItemCount());


                        System.out.println(invoiceLine.getItemName());
                        invoiceLineList.add(invoiceLine);
                        
                        total += invoiceLines.get(j).getItemTotalPrice();
                       // System.out.print(total);
                    }
                    /*else
                        break;*/
                }
                System.out.println(invoiceLineList.size());
                totals.add(total);
                invoiceLineItems.add(invoiceLineList);

        }
        for(int i = 0; i < invoiceHeaderRows.size();i++)
        {
                    invoiceHeader = new InvoiceHeader();
                    invoiceLineCalculation = new InvoiceLineCalculation();
                    invoiceLineCalculation.setInvoiceLines(invoiceLinePath);
                    invoiceLines = invoiceLineCalculation.getInvoiceLines();

                    invoiceHeader.setInvoiceNumber(Integer.valueOf(invoiceHeaderRows.get(i).get(0)));
                    invoiceHeader.setInvoiceDate(invoiceHeaderRows.get(i).get(1));
                    invoiceHeader.setCustomerName(invoiceHeaderRows.get(i).get(2));
                    invoiceHeader.setInvoiceLines(invoiceLineItems.get(i));
                    invoiceHeader.setTotal(totals.get(i));
                    invoiceHeaderLines.add(invoiceHeader);

        }
    }

}
