/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author ahmadsaifullah
 */
public class Purchased 
{
    String destination;
    String date;
    int time;
    String seats;
    
    public Purchased(String destination, String date, int time, String seats)
    {
        this.destination = destination;
        this.date = date;
        this.time = time;
        this.seats = seats;
    }
    String getDestination()
    {
        return destination;
    }
    String getDate()
    {
        return date;
    }
    int getTime()
    {
        return time;
    }
    String getSeats()
    {
        return seats;
    }
    @Override
    public String toString()
    {
        return destination;
    }
}
