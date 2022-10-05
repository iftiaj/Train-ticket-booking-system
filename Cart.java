/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.time.*;
/**
 *
 * @author ahmadsaifullah
 */
public class Cart 
{
    String destination;
    int ticketIndex;
    int custIndex;
    int qty;
    
    public Cart(int custIndex,int tickIndex,String destination, int qty)
    {
        this.destination=destination;
        this.custIndex=custIndex;
        this.ticketIndex=tickIndex;
        this.qty=qty;
    }
    int getTIndex()
    {
        return ticketIndex;
    }
    int getCIndex()
    {
        return custIndex;
    }
    int getqty()
    {
        return qty;
    }
    void setDestination(String destination)
    {
        this.destination = destination;
    }

    @Override
    public String toString()
    {
        return destination;
    }
    
    //String viewCart()
    //String 
}
