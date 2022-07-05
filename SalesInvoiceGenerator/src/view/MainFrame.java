package view;

import model.FileOperations;
import model.InvoiceHeader;
import model.InvoiceLine;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class MainFrame extends JFrame implements ActionListener {
    private JMenuBar mb;
    private JButton cacnelNewInvoice;
    private ArrayList<InvoiceHeader> invoiceHeaders;
    private String path, path2;
    private JMenu file;
    private JMenuItem load;
    private JMenuItem save;
    private JTextArea ta;
    private JLabel addCustomer;
    private JLabel addDate;
    private JTextField addCustomerField;
    private JTextField addDateField;
    private JButton saveButton;
    private JPanel customerPanel, datePanel;
    private JLabel addItemName;
    private JLabel addItemPrice;
    private JLabel addItemCount;

    private JTextField addItemNameField;
    private JTextField addItemPriceField;
    private JTextField addItemCountField;
    private JButton saveItemButton;
    private JPanel itemNamePanel, itemPricePanel, itemCountPanel;
    private JPanel panel3;
    private JTable jt;
    private JTable jt1;
    private JTextField invoiceNumber;
    private JTextField invoiceDate;
    private JTextField customerName;
    private JTextField invoiceTotal;
    private ArrayList<Integer>maxID = new ArrayList<>();
    private ArrayList<Integer> maxItemIDs = new ArrayList<>();

   public MainFrame(JTable jt)
   {
        jt1 = jt;
   }
    public MainFrame() {
         super("Sales Invoice Generator");
         //frame = new JFrame();
        // setting grid layout of 3 rows and 3 columns
        setLayout(new GridLayout(1, 2));
        panel3 = new JPanel();
        panel3.setBounds(0, 80, 20, 20);
        mb = new JMenuBar();
        file = new JMenu("File");
        load = new JMenuItem("Load File");
        load.addActionListener(this);
        load.setActionCommand("L");
        save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setActionCommand("S");
        setJMenuBar(mb);
        mb.add(file);
        file.add(load);
        file.add(save);

        mb.setBounds(20, 0, 20, 20);

       /* ta=new JTextArea(800,800);
        ta.setBounds(0,20,800,800);*/
        panel3.add(mb);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        //panel.setBounds(0,80,20,20);


        JButton addNewInvoice = new JButton("Add New Invoice");
        addNewInvoice.setBounds(150, 200, 200, 30);
        addNewInvoice.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                     addNewInvoiceFrame();
            }
            });


        cacnelNewInvoice = new JButton("Delete");
        cacnelNewInvoice.setBounds(180, 200, 100, 30);


        JLabel tableInvoiceLabel = new JLabel("Invoices Table ");
        tableInvoiceLabel.setBounds(30, 200, 250, 200);

        String invoiceData[][] = {
        };
        String invoiceColumns[] = {"No.", "Date", "Customer", "Total"};


        jt1 = new JTable(new DefaultTableModel(invoiceData, invoiceColumns));
        jt1.setBounds(30, 40, 300, 300);

        JScrollPane sp1 = new JScrollPane(jt1);
        panel3.add(tableInvoiceLabel);
        panel3.add(sp1);
        panel3.add(addNewInvoice);
        panel3.add(cacnelNewInvoice);
        add(panel3);

        JLabel invoiceNumberLabel = new JLabel("Invoice Number ");

        JLabel invoiceDateLabel = new JLabel("Invoice Date ");
        JLabel customerNameLabel = new JLabel("Customer Name ");

        JLabel invoiceTotalLabel = new JLabel("Invoice Total ");
        invoiceNumberLabel.setBounds(30, 50, 200, 20);

        invoiceDateLabel.setBounds(20, 100, 200, 20);
        customerNameLabel.setBounds(20, 150, 200, 20);
        invoiceTotalLabel.setBounds(20, 200, 200, 20);

        //Create two text fields for name and password

        invoiceNumber = new JTextField();
        invoiceNumber.setEditable(false);
        invoiceDate = new JTextField();
        customerName = new JTextField();
        invoiceTotal = new JTextField();
        invoiceTotal.setEditable(false);

        invoiceNumber.setBounds(150, 50, 250, 20);

        invoiceDate.setBounds(150, 100, 300, 20);
        customerName.setBounds(150, 150, 300, 20);
        invoiceTotal.setBounds(150, 200, 250, 20);

        jt1.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(jt1.getValueAt(jt1.getSelectedRow(), 0).toString());
                invoiceNumber.setText(jt1.getValueAt(jt1.getSelectedRow(),0).toString());
                invoiceDate.setText(jt1.getValueAt(jt1.getSelectedRow(),1).toString());
                customerName.setText(jt1.getValueAt(jt1.getSelectedRow(),2).toString());
                invoiceTotal.setText(jt1.getValueAt(jt1.getSelectedRow(),3).toString());

                int rowID = jt1.getSelectedRow();
                if(invoiceHeaders.get(rowID).getInvoiceLines().size() > 0 ) {
                    loadInvoiceItemTable(invoiceHeaders.get(rowID).getInvoiceLines());

                }
            }

            });
        ///Save File?
        cacnelNewInvoice.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowID = jt1.getSelectedRow();
                System.out.println("////////////////////////");
                System.out.println(rowID);
                int sizee = invoiceHeaders.get(rowID).getInvoiceLines().size();
                if(rowID > 0) {
                    invoiceNumber.setText("");
                    customerName.setText("");
                    invoiceDate.setText("");
                    invoiceTotal.setText("");
                    for(int i = 0; i < sizee; i++)
                        invoiceHeaders.get(rowID).getInvoiceLines().remove(0);
                    loadInvoiceItemTable(invoiceHeaders.get(rowID).getInvoiceLines());
                    invoiceHeaders.remove(rowID);
                    render(invoiceHeaders);


                }

            }
        });




        panel.add(invoiceNumberLabel);
        panel.add(invoiceNumber);
        panel.add(invoiceDateLabel);
        panel.add(invoiceDate);
        panel.add(customerNameLabel);
        panel.add(customerName);
        panel.add(invoiceTotalLabel);
        panel.add(invoiceTotal);
        //add(panel);
        JLabel lab1 = new JLabel("Invoice Items", JLabel.RIGHT);

        //add(p);
        JLabel tableLabel = new JLabel("Invoice Items ");
        tableLabel.setBounds(30, 200, 250, 200);
        String itemData[][] = {};
        String invoiceItemColumns[] = {"No.", "Item Name", "Item Price", "Count", "Item Total"};


        jt = new JTable(new DefaultTableModel(itemData, invoiceItemColumns));
        jt.setBounds(30, 40, 300, 300);
        jt.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowID = jt.getSelectedRow();

            }});
        JScrollPane sp = new JScrollPane(jt);


        JButton addNewItem = new JButton("Add New Item");
        addNewItem.setBounds(150, 200, 200, 30);

         addNewItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                     addNewItemFrame();

                                                                        
             }
             });

         JButton cacnelNewItem = new JButton("Delete");
         cacnelNewItem.setBounds(180, 200, 100, 30);
         cacnelNewItem.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 int rowID1 = jt1.getSelectedRow();
                 int rowID2 = jt.getSelectedRow();
                 //System.out.println(invoiceHeaders.get(rowID1).getInvoiceLines().size());
                 //System.out.println("////////////////////////");
                 //System.out.println(rowID2);
                 double total = invoiceHeaders.get(rowID1).getTotal() -
                         invoiceHeaders.get(rowID1).getInvoiceLines().get(rowID2).getItemTotalPrice();
                 invoiceHeaders.get(rowID1).getInvoiceLines().remove(rowID2);
                 invoiceTotal.setText(total + "");
                 invoiceHeaders.get(rowID1).setTotal(total);
                 render(invoiceHeaders);
                 loadInvoiceItemTable(invoiceHeaders.get(rowID1).getInvoiceLines());



                 }
                 });

        JPanel panel2 = new JPanel();
        panel2.setBounds(20, 80, 20, 20);

        panel2.add(panel);
        panel2.add(sp);
        panel2.add(addNewItem);
        panel2.add(cacnelNewItem);
        add(panel2);
        setSize(1000, 600);
        setLocation(200, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "L":
                loadFile();
                break;
            case "S":
                writeFile();
                break;
        }

    }

    private void loadFile() {
        FileOperations fileOperations = new FileOperations();
        invoiceHeaders = new ArrayList<>();//= fileOperations.readFile();
        JFileChooser fc = new JFileChooser();

        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
             path = fc.getSelectedFile().getPath();
             //setPath(path);
             result = fc.showOpenDialog(this);
             if(result == JFileChooser.APPROVE_OPTION)
             {
                 path2 = fc.getSelectedFile().getPath();
                // System.out.println(path2);
                 invoiceHeaders = fileOperations.readFile(path, path2);
                 System.out.println(invoiceHeaders.size());
                 for(int i = 0; i < invoiceHeaders.size(); i++) {
                     for (int j = 0; j < invoiceHeaders.get(i).getInvoiceLines().size(); j++) {
                         System.out.println(invoiceHeaders.get(i).getInvoiceLines().get(j).getInvoiceNumber());
                         System.out.println(invoiceHeaders.get(i).getInvoiceLines().get(j).getItemName());
                         System.out.println(invoiceHeaders.get(i).getInvoiceLines().get(j).getItemPrice());
                         System.out.println(invoiceHeaders.get(i).getInvoiceLines().get(j).getItemCount());
                         System.out.println(invoiceHeaders.get(i).getInvoiceLines().get(j).getItemTotalPrice());
                     }
                 }

             }
           // System.out.print(path);
             render(invoiceHeaders);

        }
    }
    private void writeFile()
    {
        FileOperations fileOperations = new FileOperations();
        fileOperations.writeFile(invoiceHeaders,path,path2);
    }

   public void render(ArrayList<InvoiceHeader> invoiceHeaders)
    {
        //FileOperations fileOperations = new FileOperations();
        //fileOperations.print();
        DefaultTableModel defaultTableModel = (DefaultTableModel) jt1.getModel();
        System.out.println(defaultTableModel.getRowCount());
        int rows =  defaultTableModel.getRowCount();
        for(int i = rows - 1; i >= 0; i --)
        {
            defaultTableModel.removeRow(i);
        }
        String []arr =new String[4];
        int index = 0;
        for(int i = 0; i < invoiceHeaders.size(); i++) {
            maxID.add(invoiceHeaders.get(i).getInvoiceNumber());
            arr[index++] = String.valueOf(invoiceHeaders.get(i).getInvoiceNumber());
            arr[index++] = invoiceHeaders.get(i).getInvoiceDate() + "";
            arr[index++] = invoiceHeaders.get(i).getCustomerName();
            arr[index++] = invoiceHeaders.get(i).getTotal() + "";

           /* System.out.println(invoiceHeaders.get(i).getInvoiceNumber());
            System.out.println(invoiceHeaders.get(i).getInvoiceDate());
            System.out.println(invoiceHeaders.get(i).getCustomerName());
            System.out.println(invoiceHeaders.get(i).getTotal());*/
            defaultTableModel.addRow(arr);
            index = 0;
        }
    }
    private int getMaxID(ArrayList<Integer>iDs)
    {
        return Collections.max(iDs);

    }


    private void loadInvoiceItemTable(ArrayList<InvoiceLine>invoiceLines)
    {
       // System.out.println(invoiceLines.size());
        DefaultTableModel defaultTableModel = (DefaultTableModel) jt.getModel();
        int rows =  defaultTableModel.getRowCount();
        for(int i = rows - 1; i >= 0; i --)
        {
            defaultTableModel.removeRow(i);
        }
        String []arr =new String[5];
        int index = 0, id = 1;

        for(int i = 0; i < invoiceLines.size(); i++) {

            //maxItemIDs.add(id);
            System.out.println(invoiceLines.get(i).getItemName() + "");
            arr[index++] = invoiceLines.get(i).getInvoiceNumber() + "" ;
            arr[index++] = invoiceLines.get(i).getItemName() + "";
            arr[index++] = invoiceLines.get(i).getItemPrice() + "";
            arr[index++] = invoiceLines.get(i).getItemCount() + "";
            arr[index] = invoiceLines.get(i).getItemTotalPrice() + "";

            defaultTableModel.addRow(arr);
            index = 0;
        }
    }
/*
    public void setPath(String path) {
        this.path = path;
    }

    public void setPath2(String path2) {
        this.path2 = path2;
    }

    public String getInvoiceHeaderPath()
    {
        return path;
    }
    public String getInvoiceItemPath()
    {
        return path2;
    }*
    public ArrayList<InvoiceHeader>getInvoiceHeaders(String path1, String path2)
    {
        ArrayList<InvoiceHeader> invoiceHeaders = new ArrayList<>();
        FileOperations fileOperations = new FileOperations();
        invoiceHeaders = fileOperations.readFile("InvoiceHeader.csv", "InvoiceLine.csv");
        return invoiceHeaders;
    }*/
    private void addNewInvoiceFrame()
    {
        JFrame frame = new JFrame("Add New Invoice ");
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
        saveButton = new JButton("Save");
        saveButton.setBounds(150, 200, 30, 30);
        saveButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String[] arr = new String[3];

                if(addCustomerField.getText().equals("") && addDateField.getText().equals(""))
                    JOptionPane.showMessageDialog(frame, "Enter a valid Data",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                else {
                    if (addCustomerField.getText().equals(""))
                        validateNames();
                    else if(addDateField.getText().equals(""))
                        validateDate();
                    else if(addDateField.getText().length() > 0 && !addCustomerField.getText().equals("")) {
                        arr = addDateField.getText().split("-");
                        //System.out.println("++++++++++++++++++++");
                        //System.out.println(arr[0]);
                        if(Integer.valueOf(arr[0]) > 31 || (Integer.valueOf(arr[0]) > 30) && Integer.valueOf(arr[1]) == 2
                                || Integer.valueOf(arr[1]) > 12 || arr[2].length() > 4 || arr[2].length() < 4 )
                            validateDate();
                        else
                            addNewInvoice();
                    }
                    else
                        addNewInvoice();
                }
                frame.setVisible(false);

            }
        });
        frame.add(customerPanel);
        frame.add(datePanel);
        frame.add(saveButton);
        frame.setLayout(new FlowLayout());
        frame.setSize(400,100);
        frame.setLocation(400,400);
        frame.setVisible(true);

    }
    private void addNewInvoice()
    {
        InvoiceHeader invoiceHeader = new InvoiceHeader();
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        String customerName, date;
        int id;
        double total;
        customerName = addCustomerField.getText();
        date = addDateField.getText();
        id = getMaxID(maxID) + 1;

        System.out.println(id);
        total = 0.0;
        invoiceHeader.setInvoiceNumber(id);
        invoiceHeader.setInvoiceDate(date);
        invoiceHeader.setCustomerName(customerName);
        invoiceHeader.setTotal(total);
        invoiceHeader.setInvoiceLines(invoiceLines);
        invoiceHeaders.add(invoiceHeader);
        render(invoiceHeaders);
        loadInvoiceItemTable(invoiceLines);
    }
    private void addNewItemFrame()
    {
        JFrame frame = new JFrame("Add New Invoice Item");
        addItemName = new JLabel("Enter Item Name");
        addItemName.setBounds(0,20,40,20);
        addItemNameField = new JTextField();
        addItemNameField.setBounds(60,20,50,20);
        itemNamePanel = new JPanel(new GridLayout(1,2));
        itemNamePanel.add(addItemName);
        itemNamePanel.add(addItemNameField);

        addItemPrice = new JLabel("Enter Item Price");
        addItemPrice.setBounds(150,20,30,20);
        addItemPriceField = new JTextField();
        addItemPriceField.setBounds(40,20,50,20);
        itemPricePanel = new JPanel(new GridLayout(1,2));
        itemPricePanel.add(addItemPrice);
        itemPricePanel.add(addItemPriceField);

        addItemCount = new JLabel("Enter Item Count");
        addItemCount.setBounds(150,20,30,20);
        addItemCountField = new JTextField();
        addItemCountField.setBounds(40,20,50,20);
        itemCountPanel = new JPanel(new GridLayout(1,2));
        itemCountPanel.add(addItemCount);
        itemCountPanel.add(addItemCountField);

        saveItemButton = new JButton("Save");
        saveItemButton.setBounds(150, 200, 30, 30);
        saveItemButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(addItemCountField.getText().equals("") && addItemPriceField.getText().equals("")&&addItemNameField.getText().equals(""))
                    JOptionPane.showMessageDialog(frame, "Enter a valid Data",
                            "ERROR", JOptionPane.ERROR_MESSAGE);
                else {
                    if (Integer.valueOf(addItemCountField.getText()) <= 0 || Integer.valueOf(addItemPriceField.getText()) <= 0 ||
                            addItemCountField.getText().equals("") || addItemPriceField.getText().equals("")) {

                        validateNumbers();
                        if (addItemNameField.getText().equals(""))
                            validateNames();

                    } else
                        addNewInvoiceItem();

                }
                frame.setVisible(false);
            }

        });


        frame.add(itemNamePanel);
        frame.add(itemPricePanel);
        frame.add(itemCountPanel);
        frame.add(saveItemButton);
        frame.setLayout(new FlowLayout());
        frame.setSize(400,120);
        frame.setLocation(400,400);
        frame.setVisible(true);


    }
    private void addNewInvoiceItem()
    {
        int rowId = jt1.getSelectedRow();
        InvoiceLine invoiceLine = new InvoiceLine();
        ArrayList<InvoiceLine> invoiceLines = new ArrayList<>();
        String itemName;
        double itemPrice;
        int itemcount = 0;
        double itemTotalPrice = 0.0;
        itemName = addItemNameField.getText();
        itemPrice = Double.parseDouble(addItemPriceField.getText());
        itemcount = Integer.valueOf(addItemCountField.getText());
        itemTotalPrice = itemPrice * itemcount;

        invoiceLine.setInvoiceNumber(invoiceHeaders.get(rowId).getInvoiceNumber());
        invoiceLine.setItemName(itemName);
        invoiceLine.setItemPrice(itemPrice);
        invoiceLine.setItemCount(itemcount);
        invoiceLine.setItemTotalPrice(itemTotalPrice);

        invoiceHeaders.get(rowId).getInvoiceLines().add(invoiceLine);
        invoiceLines.add(invoiceLine);

        double invoiceTotalPrice;
        invoiceTotalPrice = invoiceHeaders.get(rowId).getTotal() + itemTotalPrice;
        invoiceHeaders.get(rowId).setTotal(invoiceTotalPrice);

        invoiceTotal.setText(String.valueOf(invoiceTotalPrice));
        loadInvoiceItemTable(invoiceHeaders.get(rowId).getInvoiceLines());
        render(invoiceHeaders);


    }
    private void validateNumbers()
    {
            JOptionPane.showMessageDialog(this, "Enter a valid Number",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    private void validateNames()
    {
        JOptionPane.showMessageDialog(this, "Enter a valid Name",
                "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    private void validateDate()
    {
            JOptionPane.showMessageDialog(this, "Enter a valid Date",
                    "ERROR", JOptionPane.ERROR_MESSAGE);
    }



}
