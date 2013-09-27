package oving1;

import java.util.ArrayList;
import java.util.Random;

public class Door implements Runnable{
	private int id = 1;
	
	public void run(){
		while(SushiBar.isOpen){
			SushiBar.write(Thread.currentThread().getName()+ ": Customer "+id+" is now created");
			Customer customer = new Customer(id); //creates customers
			ServingArea.handleQueCustomer(customer, true);
			new Thread(customer).start(); //start the customer in a new thread
			id++;
			try {
				Thread.sleep(SushiBar.doorWait); //wait the given time before creating a new customer
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SushiBar.write(Thread.currentThread().getName()+ ": ***** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
		ServingArea.printStatistics(); //This method checks if all the customers has been handled and we can print the statistics now, else we wait for the last customer to leave
	}
}
