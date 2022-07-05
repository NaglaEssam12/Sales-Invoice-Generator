package view;

import model.FileOperations;
import model.InvoiceHeader;
import model.InvoiceLine;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class InvoiceHeaderFrames extends JFrame {
    private JLabel addCustomer;
    private JLabel addDate;
    private JTextField addCustomerField;
    private JTextField addDateField;
    private JButton saveButton;
    private JPanel customerPanel, datePanel;
    private JTable jt1;
    private int maxID;
    private ArrayList<InvoiceHeader>invoiceHeaders;
    private MainFrame mainFrame;
    public InvoiceHeaderFrames(int ID)
    {

        super("Add New Invoice");
        maxID = ID;
        addCustomer = new JLabel("Enter Customer Name");
        addCustomer.setBounds(0,20,40,20);
        addCustomerField = new JTextField();
        addCustomerField.setBounds(60,20,50,20);
        customerPanel = new JPanel(new GridLayout(1,2));
        customerPanel.add(addCustomer);
        customerPanel.add(addCustomerField);

        addDate = new JLabel("Enter Date");
        addDate.setBounds(150,20,30,20);
        addDateField = new JTextField();
        addDateField.setBounds(40,20,50,20);
        datePanel = new JPanel(new GridLayout(1,2));
        datePanel.add(addDate);
        datePanel.add(addDateField);

        String invoiceData[][] = {
        };
        String invoiceColumns[] = {"No.", "Date", "Customer", "Total"};


        jt1 = new JTable(new DefaultTableModel(invoiceData, invoiceColumns));

        saveButton = new JButton("Save");
        saveButton.setBounds(150, 200, 30, 30);
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                mainFrame = new MainFrame(jt1);
                AddNewInvoice();
                mainFrame.render(invoiceHeaders);

            }
        });

        add(customerPanel);
        add(datePanel);
        add(saveButton);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(400,100);
        setLocation(400,400);
        setVisible(true);

    }
    private void AddNewInvoice()
    {

        FileOperations fileOperation = new FileOperations();
        String customerName, date;
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        InvoiceHeader invoiceHeader = new InvoiceHeader();
        invoiceHeaders = new ArrayList<>();
        int id;
        double total;
        customerName = addCustomerField.getText();
        date = addDateField.getText();
        id = ++maxID;

        System.out.println(id);
        total = 0.0;
        invoiceHeader.setInvoiceNumber(id);
        invoiceHeader.setInvoiceDate(date);
        invoiceHeader.setCustomerName(customerName);
        invoiceHeader.setTotal(total);
        invoiceHeader.setInvoiceLines(invoiceLines);
        //invoiceHeaders = fileOperation.readFile();
        //System.out.println(mainFrame.getInvoiceHeaderPath());
        //invoiceHeaders = mainFrame.getInvoiceHeaders(mainFrame.getInvoiceHeaderPath(), mainFrame.getInvoiceItemPath());
        System.out.println("**************************");
        System.out.println(invoiceHeaders.size());
        System.out.println("**************************");
        invoiceHeaders.add(invoiceHeader);
        //mainFrame.render(invoiceHeaders);


    }
}

