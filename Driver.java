/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author ahmadsaifullah
 */
public class Driver 
{
    public static void main(String[] args)
    {
        
        int currentCust;
        new GUI();
        
        
        //LinkedList<Customer> cList = new LinkedList<>();
        //LinkedList<Ticket> tList = new LinkedList<>();
        //LinkedList<Purchase> cart = new LinkedList<>();
        
        //initialize ticket objects
        //tList.add(new Ticket (1700,"17March2020","Selangor"));
        //tList.add(new Ticket (1730,"18March2020","KualaLumpur"));
        //tList.add(new Ticket (1700,"19March2020","Perak"));
        //cList.add(new Customer("Ahmad","Selangor"));
        
        
        
        
        /*
        System.out.println("Print all available tickets");
        printTickets(tList);
        System.out.println("****************************");
        
        System.out.println();
        
        System.out.println("Print all available tickets for Kuala Lumpur");
        tList.get(1).getAll();  //view available tickets for Kuala lumpur (index 1)
        System.out.println("****************************");
        System.out.println();
        System.out.println("Print all available tickets for Selangor");
        tList.get(0).getAll();
        System.out.println("****************************");
        
        cart.add(new Purchase(custID,0,3));//add 5 tickets from ticketIndex 0 to cart
        cart.add(new Purchase(custID,1,6));
        cart.add(new Purchase(custID,0,5));
        //cList.get(custID).assignSeats(tList.get(1).getDestination(),tList.get(1).checkOut(5));//customer(index 0) buys 5 tickets to Kuala Lumpur
        System.out.println("Print preview of tickets to be purchased");
        viewCart(cart,tList);
        System.out.println("========================================");
        System.out.println();
        
        //checkOut(custID,0,3,cList,tList);
        System.out.println("Checkout for all items in cart for customer");
        for (int i=0;i<cart.size();i++)
        {
            checkOut(custID,cart.get(i).getTIndex(),cart.get(i).getqty(),cList,tList);// checkOut method (custindex,ticketindex,qtypurchase,cust list,ticket list) 
        }
        System.out.println("=============================================");
        
        System.out.println();
        
        System.out.println("***all tickets bought by customer***");
        cList.get(custID).printAll();//print tickets bought
        System.out.println("*******************************");
        System.out.println();
        System.out.println("&&&Currently available tickets for all destinations&&&");
        printTickets(tList);
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        /*
        printTickets(tList);    // printTickets method (ticketlist)
                                //printCust method (custlist) will print all cust
        
        //cList.get(custID).assignSeats(tList.get(2).getDestination(),tList.get(2).checkOut(10));//customer(index 0) buys 10 tickets to Perak
        
        //checkOut(custID,2,20,cList,tList); 
        //checkOut(custID,1,5,cList,tList);
        
        cList.get(custID).printAll();//print tickets bought
        
        printTickets(tList);
        /*
        //remove unavailable ticket
        removeTicket(tList);
        
        //ticket after removal
        printTickets(tList);
        
        createDummyCust100(cList);
        custID=50;
        cart.clear();
        cart.add(new Purchase (custID,0,10));
        cart.add(new Purchase (custID,2,7));
        for (int i=0;i<cart.size();i++)
        {
            checkOut(custID,cart.get(i).getTIndex(),cart.get(i).getqty(),cList,tList);// checkOut method (custindex,ticketindex,qtypurchase,cust list,ticket list) 
        }
        printCust(cList);
        System.out.println(custID);
        */
    }
    static void printMenu()
    {
        System.out.println("Kindly select from the choices below:");
        System.out.println("1.Purchase tickets");
        System.out.println("2.Register new user");
        System.out.println("3.View tickets available for booking");
        System.out.println("4.View cart");
        System.out.println("5.Checkout");
        System.out.println("6.View purchased");
        System.out.println("7.View menu");
        System.out.println("0.exit");
    }
    /*
    static int resetCounter()
    {
        return 0;
    }
    static void printTickets(List<Ticket> list)
    {
        for (int i=0;i<list.size();i++)
        {
            list.get(i).getAll();
        }
    }
    static void removeTicket(List<Ticket> list)
    {
        for (int i=0;i<list.size();i++)
        {
            if (list.get(i).getAvail()==0)
            {
                list.remove(i);
            }
        }
    }
    
    static void createDummyCust100(List<Customer> clist)
    {
        for (int i=0;i<100;i++)
        {
            clist.add(new Customer("Customer"+i,"city"+i));
        }
    }
    static void createDummyCust200(List<Customer> clist)
    {
        for (int i=0;i<200;i++)
        {
            clist.add(new Customer("Customer"+i,"city"+i));
        }
    }
    static void createDummyCust300(List<Customer> clist)
    {
        for (int i=0;i<300;i++)
        {
            clist.add(new Customer("Customer"+i,"city"+i));
        }
    }
    static void printCust(List<Customer> clist)
    {
        for (int i=0;i<clist.size();i++)
        {
            clist.get(i).printAll();
        }
    }
    static void viewCart(List<Purchase> cart,List<Ticket> ticket)
    {
        int qty;
        for (int i=0;i<cart.size();i++)
        {
            qty=cart.get(i).getqty();
            System.out.print("Ticket to be purchased= ");
            System.out.print(ticket.get(cart.get(i).getTIndex()).getDestination()+" => "+ticket.get(cart.get(i).getTIndex()).viewCurrent(cart.get(i).getqty())+"\n");
            ticket.get(cart.get(i).ticketIndex).clearHistory();
        }
    }
    static void addCust(List<Customer> clist,String input1,String input2)
    {
        clist.add(new Customer (input1,input2));
    }
    
    static void checkOut(int custID,int ticketID,int qty,List<Customer> cust,List<Ticket> ticket)
    {
        
        cust.get(custID).assignSeats(ticket.get(ticketID).getDestination(),ticket.get(ticketID).checkOut(qty));
        ticket.get(ticketID).clearHistory();
    
    }
        
    /*
    static void buyAll(List<Customer> cust,List<Ticket> ticket,List<Purchase> cart)
    {
        for (int i =0;i<cart.size();i++)
        {
            cust.get(cart.get(i).getCIndex()).assignSeats(ticket.get(cart.get(i).getTIndex()).getDestination(),ticket.get(cart.get(i).getTIndex()).checkOut(cart.get(i).getqty()));
            ticket.get(cart.get(i).getTIndex()).clearHistory(); //clear history for ticket
        }
    }
    /*
    
    static void buyAll(List<Purchase> cart,Customer cust,Ticket ticket)
    {
        for (int i=0;i<cart.size();i++)
        {
            cust.assignSeats(ticket.getDestination(), ticket.checkOut(cart.get(i).qty));
            ticket.clearHistory();
        }
    }
    */
}
