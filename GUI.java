/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import javax.swing.*;
import java.awt.Dimension;

/**
 *
 * @author ahmadsaifullah
 */
public class GUI extends JFrame implements ActionListener
{
    JButton button1;
    JButton button2;
    JButton button3;
    JButton button4;
    JButton button5;
    JButton button6;
    
    JPanel display;
    //logo and icons
    ImageIcon logo;
    ImageIcon title;
    //list object declaration
    LinkedList<Customer> cList = new LinkedList<>();
    
    JList<Ticket> tList = new JList<>();
    DefaultListModel<Ticket> tListmod = new DefaultListModel<>();
    JList<Cart> cart = new JList<>();
    DefaultListModel<Cart> cartMod = new DefaultListModel<>();
    JList<Purchased> purchase = new JList<>();
    DefaultListModel<Purchased> purchaseMod= new DefaultListModel<>();
    //default list model implemented to automatically update changes to displayed items
    int custID=0;
    //form for registration
    public GUI()
    {
        //list instantiation
        tListmod.addElement(new Ticket (1700,"17March2020","Shah Alam"));
        tListmod.addElement(new Ticket (1730,"18March2020","KualaLumpur"));
        tListmod.addElement(new Ticket (1700,"19March2020","Perak"));
        
        //default cust instantiation
        cList.add(new Customer("defaultcust","defaultAddress",0));
        
        //panels
        JPanel tickSel = new JPanel();
        JPanel cartSel = new JPanel();
        JPanel purcSel = new JPanel();
        
        //tabs
        JSplitPane tSPane = new JSplitPane();
        JSplitPane cartSPane = new JSplitPane();
        JSplitPane purcSPane = new JSplitPane();
        JTabbedPane tabs = new JTabbedPane();
        JPanel tab1 = new JPanel();
        JPanel tab2 = new JPanel();
        JPanel tab3 = new JPanel();
        
        tabs.add("Tickets",tab1);
        tabs.add("Cart",tab2);
        tabs.add("Purchased",tab3);
        
        //tab1 (ticketSelect) layout
        tab1.add(tSPane);
        button2 = new JButton("Add to cart");
        tab1.add(button2);
        
        //tab2 (cart) layout
        tab2.add(cartSPane);
        button3 = new JButton("Remove");
        button4 = new JButton("Clear");
        button5 = new JButton("Checkout");
        tab2.add(button3);
        tab2.add(button4);
        tab2.add(button5);
        
        //tab3 (purchased) layout
        tab3.add(purcSPane);
        
        //Assign appropriate models to appropriate list
        tList.setModel(tListmod);   
        cart.setModel(cartMod);
        purchase.setModel(purchaseMod);
        
        //ticket selection tab
        JLabel tdesc = new JLabel();
        JLabel tdesc2 = new JLabel();
        JLabel tdesc3 = new JLabel();
        JLabel tdesc4 = new JLabel();
        tickSel.add(tdesc);
        tickSel.add(tdesc2);
        tickSel.add(tdesc3);
        tickSel.add(tdesc4);
        tickSel.setLayout(new BoxLayout(tickSel, BoxLayout.Y_AXIS));
        tSPane.setLeftComponent(new JScrollPane(tList));
        tSPane.setRightComponent(tickSel);
        //listener assigned to check selected list and display appropriate information
        tList.getSelectionModel().addListSelectionListener
            (e -> 
                {
                    Ticket t = tList.getSelectedValue();
                    tdesc.setText("Time :"+ t.getTime());
                    tdesc2.setText("Date: "+ t.getDate());
                    tdesc3.setText("Destination: "+ t.getDestination());
                    tdesc4.setText("Vacancy: "+ t.getAvail());
                }
            );
        //listener assigned to designate action for add to cart
        button2.addActionListener
            (e ->
                {
                    if (custID==0)
                    {
                        JPanel errorPanel = new JPanel();
                        errorPanel.setLayout(new BoxLayout(errorPanel, BoxLayout.Y_AXIS));
                        errorPanel.add(new JLabel ("No customers registered"));
                        errorPanel.add(new JLabel ("Proceeding to customer registration dialog"));
                        JOptionPane.showMessageDialog(null,errorPanel,"Error", JOptionPane.ERROR_MESSAGE);
                        registerCust();
                    }
                    else
                    {
                        JSpinner qtySel;
                        SpinnerModel qtyModel = new SpinnerNumberModel(1,1,60,1);
                        JPanel pPanel = new JPanel();
                        
                        qtySel = new JSpinner(qtyModel);
                        pPanel.add(new JLabel("Please select ticket amount"));
                        pPanel.add(qtySel);
                        int tIndex = tList.getSelectedIndex();

                        int selection = JOptionPane.showConfirmDialog(null, pPanel, 
                        "Ticket amount", JOptionPane.OK_CANCEL_OPTION);                
                        if(selection==JOptionPane.OK_OPTION)
                        {
                            int qty = (Integer) qtySel.getValue();
                            cartMod.addElement
                            (new Cart(custID,tIndex,tListmod.get(tIndex).getDestination(),qty));
                        }
                    }
                }
            );
        
        //cart item selection
        JLabel cdesc = new JLabel();
        JLabel cdesc2 = new JLabel();
        JLabel cdesc3 = new JLabel();
        cartSel.add(cdesc);
        cartSel.add(cdesc2);
        cartSel.add(cdesc3);
        cartSel.setLayout(new BoxLayout(cartSel, BoxLayout.Y_AXIS));
        cartSPane.setLeftComponent(new JScrollPane(cart));
        cartSPane.setRightComponent(cartSel);
        //display relevant info during selection
        
        cart.getSelectionModel().addListSelectionListener
            (e -> 
                {
                    Cart c = cart.getSelectedValue();
                    if (c!=null)
                    {
                        cdesc.setText("Date: " + tListmod.get(c.getTIndex()).getDate());
                        cdesc2.setText("Time: " + tListmod.get(c.getTIndex()).getTime());
                        cdesc3.setText("Quantity: " + c.getqty());
                    }
                }
            );
        //remove from cart
        button3.addActionListener
            (e ->
                {
                    Cart c = cart.getSelectedValue();
                    cartMod.removeElement(c);
                    
                    //remove description for cart item post deletion
                    //see below for method
                    refreshDesc(cdesc,cdesc2,cdesc3);      
                }
            );
        
        //clear cart
        button4.addActionListener
            (e ->
                {
                    cartMod.clear();
                    refreshDesc(cdesc,cdesc2,cdesc3);
                }
            );
        //Checkout
        button5.addActionListener
            (e ->
                {
                    JPanel checkOutPanel = new JPanel();
                    checkOutPanel.setLayout(new BoxLayout(checkOutPanel, BoxLayout.Y_AXIS));
                    checkOutPanel.add(new JLabel ("Confirm purchase for all cart items?"));
                    int selection = JOptionPane.showConfirmDialog(null, checkOutPanel, 
                    "Confirm Purchase", JOptionPane.OK_CANCEL_OPTION);                
                    if(selection==JOptionPane.OK_OPTION)
                    {
                        checkOut(custID,cList,tListmod,cartMod);
                        cartMod.clear();
                        refreshDesc(cdesc,cdesc2,cdesc3);
                        //transfer item into purchased JList(current JList in runtime)
                        purchaseMod.clear();
                        //clear cache for purchase before viewing purchases for current cust
                        for (int i=0;i<cList.get(custID).getTicket().size();i++)
                        {
                            purchaseMod.addElement
                            (new Purchased
                                (
                                    cList.get(custID).purchasedItem.get(i).getDestination(),
                                    cList.get(custID).purchasedItem.get(i).getDate(),
                                    cList.get(custID).purchasedItem.get(i).getTime(),
                                    cList.get(custID).purchasedItem.get(i).getSeats()
                            ));
                        }
                    }
                }
            );
 
        
        //purchased item view()
        JLabel pDesc = new JLabel();
        JLabel pDesc2 = new JLabel();
        JLabel pDesc3 = new JLabel();
        JLabel pDesc4 = new JLabel();
        purcSel.add(pDesc);
        purcSel.add(pDesc2);
        purcSel.add(pDesc3);
        purcSel.add(pDesc4);
        
        purcSel.setLayout(new BoxLayout(purcSel, BoxLayout.Y_AXIS));
        purcSPane.setLeftComponent(new JScrollPane(purchase));
        purcSPane.setRightComponent(purcSel);
        
        purchase.getSelectionModel().addListSelectionListener
            (e -> 
                {
                    Purchased p = purchase.getSelectedValue();
                    if (p!=null)
                    {
                        pDesc.setText("Destination: " + p.getDestination());
                        pDesc2.setText("Date: " + p.getDate());
                        pDesc3.setText("Time: " + p.getTime());
                        pDesc4.setText("Seats: " + p.getSeats());
                        
                    }
                }
            );
        
        
        
        //main menu buttons
        button1 = new JButton("New User");
        button6 = new JButton("Quit");
        
        //Logo instantiation
        logo = new ImageIcon("logoEdited.png");
        JLabel imageContainer = new JLabel(logo);
        //object layout
        button1.setBounds(25,275,250,50);
        button6.setBounds(25,325,250,50);
        tabs.setBounds(300,50,500,500);
        imageContainer.setBounds(50,70,200,200);
        tSPane.setPreferredSize(new Dimension(400,400));
        cartSPane.setPreferredSize(new Dimension(400,400));
        purcSPane.setPreferredSize(new Dimension(400,400));
        
        //main menu button function assignment
        button1.addActionListener(this);
        button6.addActionListener(this);
        
        //frame instantiation
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setSize(new Dimension(800,850));
        this.setVisible(true);
        this.add(button1);
        this.add(button6);
        this.add(tabs);
        this.add(imageContainer);
        
    }
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        //register new user
        if (e.getSource()==button1)
        {
            registerCust();
            //see below for registerCust() method
        }
        
        //quit
        if (e.getSource()==button6)
        {
            System.exit(0);
        }
//To change body of generated methods, choose Tools | Templates.
    }
    public void refreshDesc(JLabel a,JLabel b, JLabel c)
    {
        a.setText("");
        b.setText("");
        c.setText("");
    }
    public void refreshDesc(JLabel a,JLabel b,JLabel c,JLabel d)
    {
        a.setText("");
        b.setText("");
        c.setText("");
        d.setText("");
    }
    public void checkOut
        (
            int custID,
            LinkedList<Customer> cust,
            DefaultListModel<Ticket> ticket,
            DefaultListModel<Cart> cart
        )
    {
        for (int i=0;i<cart.size();i++)
        {
            cust.get(custID).assignSeats
            (
                ticket.get(cart.get(i).getTIndex()).getDestination(),
                ticket.get(cart.get(i).getTIndex()).getDate(),
                ticket.get(cart.get(i).getTIndex()).getTime(),
                ticket.get(cart.get(i).getTIndex()).checkOut(cart.get(i).getqty())
            );
            
            ticket.get(cart.get(i).getTIndex()).clearHistory();
        }
    }
    public void registerCust()
    {
        JTextField nameIn = new JTextField(20);
        JTextField addressIn = new JTextField(20);
        
        JPanel rPanel = new JPanel();
        rPanel.add(new JLabel("Name:"));
        rPanel.add(nameIn);
        //rPanel.add(Box.createHorizontalStrut(5)); // a spacer
        rPanel.add(new JLabel("City:"));
        rPanel.add(addressIn);

        int selection = JOptionPane.showConfirmDialog(null, rPanel, 
        "Please enter name and address", JOptionPane.OK_CANCEL_OPTION);
        if (selection == JOptionPane.OK_OPTION) 
        {
            custID++;
            purchaseMod.clear();
            cartMod.clear();
            //clear purchases n cart to log for new customer
            cList.add(new Customer(nameIn.getText(),addressIn.getText(),custID));
            JOptionPane.showMessageDialog(null,"Current transactions logged for\n"+cList.get(custID).getAll());
        }
    }
    
}
