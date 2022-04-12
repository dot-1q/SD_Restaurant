package entities;

import sharedRegions.Bar;
import sharedRegions.Table;
import genclass.*;

public class Student extends Thread
{
    private StudentState state;
    private boolean salutedByWaiter = false;
    private boolean servedByWaiter = false;
    private int sID;
    private int tableSeat;
    private Bar bar;
    private Table table;

    public Student(int id, StudentState state, Bar bar, Table table)
    {
        this.sID=id;
        this.state = state;
        this.bar = bar;
        this.table = table;
    }

    public int getTableSeat() {
        return tableSeat;
    }

    public void setTableSeat(int tableSeat) {
        this.tableSeat = tableSeat;
    }

    public boolean servedByWaiter() 
    {
        return servedByWaiter;
    }

    public void setServedByWaiter() 
    {
        this.servedByWaiter = true;
    }

    public boolean getSalutedByWaiter() 
    {
        return salutedByWaiter;
    }

    public void setSalutedByWaiter() 
    {
        this.salutedByWaiter = true;
    }

    public void setState(StudentState state)
    {
        this.state = state;
    }

    public StudentState getStudentState()
    {
        return this.state; 
    }

    public int getID()
    {
        return this.sID;
    }
    
    @Override
    public String toString() 
    {
        return this.state.toString();
    }

    @Override
    public void run()
    {
        walkABit();
        bar.enter();

        table.readTheMenu();

        if(bar.FirstStudent(this.sID))
        {
            while(!table.hasEverybodyChosen())
            {
                table.prepareTheOrder();
                table.addUpOnesChoice();
            }
            // Means the first student has registered everybody choice
            System.out.println("Arrived here");
            bar.callTheWaiter();
            table.describeTheOrder();
            table.joinTheTalk();
        }
        else
        {
            table.informCompanion();
        }

        table.startEating();
        table.endEating();

        while(!table.hasEverbodyFinished()); // blocking
        bar.signalTheWaiter();

        if(bar.shouldHaveArrivedEarlier(this.sID))
        {
            table.honourTheBill();
        }
        bar.exit();
        String s = "\033[41m Student[ " + this.sID + "] End Of Life \033[0m";
        GenericIO.writelnString(s);
    }    
    
    public synchronized void walkABit() 
    {
        try {
            sleep((long) (3 + 1000 * Math.random()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}