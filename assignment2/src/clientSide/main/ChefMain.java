package clientSide.main;

import clientSide.entities.Chef;
import clientSide.entities.ChefState;
import clientSide.stubs.KitchenStub;
import clientSide.stubs.BarStub;

/**
 *    Client side of the Assignment 2 - Chef.
 *    Static solution Attempt (number of threads controlled by global constants - ExecConst)
 *    Implementation of a client-server model of type 2 (server replication).
 *    Communication is based on a communication channel under the TCP protocol.
 */

public class ChefMain 
{
    /**
     *    Main method.
     *
     *    @param args runtime arguments
     */
    
    public static void main(String[] args)
    {
        Chef chef;

        KitchenStub kitchen;
        BarStub bar;

        kitchen = new KitchenStub("l040101-ws02.ua.pt", 22342);
        bar = new BarStub("l040101-ws01.ua.pt", 22341);

        chef = new Chef(0, ChefState.WAITING_FOR_AN_ORDER, kitchen, bar);

        /* start thread */
        chef.start();

        /* wait for the end */
        try
        {
            chef.join();
        }catch(InterruptedException e){}

        System.out.println("The Chef thread has been terminated");

        
    }
}
