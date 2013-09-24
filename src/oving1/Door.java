package oving1;

import java.util.ArrayList;
import java.util.Random;

public class Door implements Runnable{
	private int id = 1;
	
	public void run(){
		while(SushiBar.isOpen){
			SushiBar.write(Thread.currentThread().getName()+ ": Customer "+id+" is now created");
			Customer customer = new Customer(id);
			ServingArea.handleQueCustomer(customer, true);
			new Thread(customer).start();
			id++;
			try {
				Thread.sleep(SushiBar.doorWait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SushiBar.write(Thread.currentThread().getName()+ ": ***** NO MORE CUSTOMERS - THE SHOP IS CLOSED NOW. *****");
	}
}
