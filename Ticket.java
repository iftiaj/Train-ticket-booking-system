   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.*;
/**
 *
 * @author ahmadsaifullah
 */
// * = methods to be amended if GUI implementation
public class Ticket 
{
    int time;
    String date;
    String destination;
    int vacancy;
    Queue<String> seats = new LinkedList<>();
    LinkedList<String> seatView = new LinkedList<>();
    boolean flag;
    String tempString="";
    public Ticket(int time, String date, String destination)
    {
        this.time = time;
        this.date = date;
        this.destination = destination;
        //initializing seats upon creation of ticket object
        for(int r=1;r<16;r++)       //r = seat row (int)
        {
            for (char c=65;c<69;c++) //c = seat char 
            {
                seats.add(String.valueOf(r)+Character.toString(c)); //seat number = 1A,1B...
                seatView.add(String.valueOf(r)+Character.toString(c));
            }
        }
        vacancy = seats.size();
    }
    String getDestination()
    {
        return destination;
    }
    int getTime()
    {
        return time;
    }
    String getDate()
    {
        return date;
    }   
    void getAll()   //*
    {
        System.out.println("====ticket details====");
        System.out.println("ticket time= "+time);
        System.out.println("ticket date= " +date);
        System.out.println("ticket destination= "+destination);
        System.out.println("Seats avail= "+vacancy);
        System.out.println("=====================");
    }
    int getAvail()
    {
        return vacancy;
    }
    
    String viewCurrent(int qty)
    {
        if(checkBuy(qty))
        {
            for (int i=0;i<qty;i++)
            {
                tempString=tempString+" "+seatView.get(i);
            }
            return tempString;
        }
        else
        {
            return "";
        }
    }
    
    void clearHistory()//must be implemented to avoid carryforward for future purchase
    {
        tempString="";
    }
    String checkOut(int num)
    {
        flag=checkBuy(num);
        if (flag)
        {
            for (int i=0;i<num;i++)
            {
                tempString=tempString+" "+seats.poll();
                seatView.removeFirst();                 //remove seat from view after bought out
            }
            System.out.print("Tickets bought: "+tempString+"\n");//*
            vacancy = seats.size();
            return tempString;
        }
        else
        {
            return "";
        }
    }
    
    private boolean checkBuy(int qty) //checkavailability     
    {
        if (qty>vacancy) //check for available seats before commit checkout
        {
            System.out.println("Unable to comply, insufficient available seats");  //*
            return false;
        }
        else
        {
            return true;
        }
    }
    //override to return name of city (display in GUI)
    @Override
    public String toString()
    {
        return destination;
    }
    
}
