package model;

import controller.InvoiceHeaderCalculation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import com.opencsv.CSVWriter;
import view.MainFrame;

public class FileOperations {
    InvoiceHeaderCalculation invoiceHeaderCalculation = new InvoiceHeaderCalculation();
    public ArrayList<InvoiceHeader> readFile(String invoiceHeaderPath, String incvoiceLinePath)
    {
        invoiceHeaderCalculation.setInvoiceHeaderLines(invoiceHeaderPath, incvoiceLinePath);
        return invoiceHeaderCalculation.getInvoiceHeaderLines();
    }

    public void writeFile(ArrayList<InvoiceHeader> invoiceHeaders, String invoiceHeaderPath, String invoiceItemPath)
    {
        File file = new File(invoiceHeaderPath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile,',',CSVWriter.NO_QUOTE_CHARACTER);

            // adding header to csv
           // String[] header = { "Name", "Class", "Marks" };
            //writer.writeNext(header);

            // add data to csv
            String[] data = new String[3];
            int index = 0;
           // writer.writeNext(data1);
            for(int i = 0; i < invoiceHeaders.size();i++)
            {
                data[index++] = invoiceHeaders.get(i).getInvoiceNumber() + "";
                data[index++] = invoiceHeaders.get(i).getInvoiceDate()+ "";
                data[index] = invoiceHeaders.get(i).getCustomerName();
                writer.writeNext(data);
                index = 0;


            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        file = new File(invoiceItemPath);
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile,',',CSVWriter.NO_QUOTE_CHARACTER);

            // adding header to csv
            // String[] header = { "Name", "Class", "Marks" };
            //writer.writeNext(header);

            // add data to csv
            String[] data = new String[4];
            int index = 0;
            // writer.writeNext(data1);
            for(int i = 0; i < invoiceHeaders.size();i++)
            {
//                writeInvoiceItemFile(,invoiceItemPath);
//                String[] data2 = { "Suraj", "10", "630" };
                for(int j= 0; j < invoiceHeaders.get(i).getInvoiceLines().size();j++)
                {
                    data[index++] = invoiceHeaders.get(i).getInvoiceLines().get(j).getInvoiceNumber() + "";
                    data[index++] = invoiceHeaders.get(i).getInvoiceLines().get(j).getItemName()+ "";
                    data[index++] = (int)invoiceHeaders.get(i).getInvoiceLines().get(j).getItemPrice() + "";
                    data[index++] = invoiceHeaders.get(i).getInvoiceLines().get(j).getItemCount() + "";

                    index = 0;
                    writer.writeNext(data);

                }



            }

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /*
    * loadInvoiceItemsOfSelectedRow()
    * {
    *   getSelectedRowInvoiceID;
    *   getInvoiceHeaderOfSelectedRowFromArrayListofInvoiceHeaders;
    *  for loop on ArrayList of Invoice Line
    *       show its elements on the table
    * }
    * */
    /*
    * Add new Invoice()
    * {
    *   get maximum number of Invoice then InvoiceNumber = ++max;
    *   make object of Invoice Header
    *   append invoice object to invoice header
    *   show it to table
    * }
    * */

    /*
    * AddNewItem()
    * {
    *   getArrayListofInvoiceLine of this specific invoice
    *   ID of item = Arraylist<InvoiceLine>.size() + 1;
    *   calculate total price
    *   make object of the invoice line
    *   append invoice line to array list
    *   show it to the table
    * }
    * */
}
