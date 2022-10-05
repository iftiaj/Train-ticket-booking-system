/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author ahmadsaifullah
 */
public class Customer 
{
    int id;
    String name;
    String address;
    LinkedList<Purchased> purchasedItem;
    public Customer(String name,String address,int id)
    {
        purchasedItem = new LinkedList<>();
        this.id = id;
        this.name=name;
        this.address=address;
    }
    void assignSeats(String destination,String date,int time,String seats)
    {
        purchasedItem.add(new Purchased(destination,date,time,seats));
    }
    //getter methods
    int getID()
    {
        return id;
    }
    String getName()
    {
        return name;
    }
    String getAddress()
    {
        return address;
    }
    String getAll()
    {
        return ("Name: "+ name + "\nAddress: " + address);
    }
    LinkedList getTicket()
    {
        return purchasedItem;
    }
    
/*
    void printAll()
    {
        System.out.println("custName= "+name+" "+ "CustAddress: "+address);
        System.out.println("++++purchased seats++++");
        for(int i=0;i<tickets.size();i++)
        {
            System.out.println(tickets.get(i));
        }
        System.out.println("+++++++++++++++++++++++");
    }
 */   
}
