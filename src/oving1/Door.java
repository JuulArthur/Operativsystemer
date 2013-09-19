package oving1;

import java.util.ArrayList;
import java.util.Random;

public class Door implements Runnable{
	private int id = 1;
	private int time = 0;
	private Random rand;
	private ArrayList<Customer> customers = new ArrayList<Customer>();
	private Boolean customersIsEmpty = true;
	
	public Boolean getBool(){
		return this.customersIsEmpty;
	}
	
	public Customer getFirstCustomer(){
		if(customersIsEmpty){
			return null;
		}
		return customers.get(0);
	}
	
	private void setBool(Boolean customerIsEmpty){
		this.customersIsEmpty=customerIsEmpty;
	}
	
	public synchronized Customer handleCustomer(Customer customer, Boolean bool){
		if(bool){
			customers.add(customer);
			if(customersIsEmpty){
				setBool(false);
			}
			return null;
		}
		else if (customers.size()!=0){
			customer = customers.get(0);
			customers.remove(0);
			setBool(customers.size()==0);
			return customer;
		}
		else {
			return null;
		}
	}
	
	public void run(){
		while(SushiBar.isOpen){
			SushiBar.write(Thread.currentThread().getName()+ ": Customer "+id+" is now created");
			Customer customer = new Customer(id);
			handleCustomer(customer, true);
			new Thread(customer).start();
			id++;
			try {
				Thread.sleep(SushiBar.doorWait);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		SushiBar.write(Thread.currentThread().getName()+ ": The door has closed");
	}
}
