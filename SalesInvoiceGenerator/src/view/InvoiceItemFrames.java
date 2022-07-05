package view;

import javax.swing.*;
import java.awt.*;

public class InvoiceItemFrames extends JFrame {
    private JLabel addItemName;
    private JLabel addItemPrice;
    private JLabel addItemCount;

    private JTextField addItemNameField;
    private JTextField addItemPriceField;
    private JTextField addItemCountField;
    private JButton saveButton;
    private JPanel itemNamePanel, itemPricePanel, itemCountPanel;
    public InvoiceItemFrames()
    {
        super("Add New Item");
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

        saveButton = new JButton("Save");
        saveButton.setBounds(150, 200, 30, 30);

        add(itemNamePanel);
        add(itemPricePanel);
        add(itemCountPanel);
        add(saveButton);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(HIDE_ON_CLOSE);
        setSize(400,100);
        setLocation(400,400);
        setVisible(true);

    }
}
