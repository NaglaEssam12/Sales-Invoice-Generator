package controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class ReadFile {
    public ArrayList<ArrayList<String>>  readInvoiceLine(String invoiceLinePath)
    {
        String line = "";
        String splitBy = ",";
        int index1 = 0;
        ArrayList<ArrayList<String>> fileRows= new ArrayList<ArrayList<String>>();
        ArrayList<String> lineItems;
        try
        {
            //parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader(invoiceLinePath));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                String[] invoiceLine = line.split(splitBy);    // use comma as separator
                lineItems = new ArrayList<>();
                for(int i = 0; i < invoiceLine.length; i++)
                    lineItems.add(invoiceLine[i]);
                fileRows.add(lineItems);
                System.out.println("Invoice Line [Invoice Number= " + invoiceLine[0] + ", Invoice Item= " + invoiceLine[1] + ", Invoice Price= " + invoiceLine[2] + ", Invoice Count= " + invoiceLine[3]  +"]");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return fileRows;
    }
    public ArrayList<ArrayList<String>>  readHeaderLine(String invoiceHeaderPath)
    {
        String line = "";
        String splitBy = ",";
        ArrayList<ArrayList<String>> fileRows= new ArrayList<ArrayList<String>>();
        ArrayList<String> headerItems;
        try
        {
//parsing a CSV file into BufferedReader class constructor
            BufferedReader br = new BufferedReader(new FileReader("InvoiceHeader.csv"));
            while ((line = br.readLine()) != null)   //returns a Boolean value
            {
                line=line.substring(1,line.length()-2);
                headerItems = new ArrayList<>();
                String[] invoiceHeader = line.split(splitBy);    // use comma as separator
                System.out.println(line);
                System.out.println(invoiceHeader[0]);
                for(int i = 0; i < invoiceHeader.length; i++)
                    headerItems.add(invoiceHeader[i]);
                fileRows.add(headerItems);
                //System.out.println(fileRows);
                //headerItems.clear();
                System.out.println("Invoice Header [Invoice Number= " + invoiceHeader[0] + ", Invoice Item= " + invoiceHeader[1] + ", Invoice Price= " + invoiceHeader[2] +"]");
            }

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return fileRows;
    }
}
