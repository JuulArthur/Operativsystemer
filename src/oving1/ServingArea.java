package oving1;

import java.util.ArrayList;

public class ServingArea {
	private static int capacity = SushiBar.capacity;
	private static ArrayList<Customer> customers = new ArrayList<Customer>();
	private static ArrayList<Customer> queCustomers = new ArrayList<Customer>();
	private static int totalOrders = 0;
	private static int totalEatenOrders = 0;
	private static int totalTakeAwayOrders = 0;
	private static int id = 1;
	
	public static int getID(){
		return id;
	}
	
	public synchronized static void addOrders(int orders){
		totalOrders+=orders;
	}
	
	public synchronized static void addEatenOrders(int orders){
		totalEatenOrders+=orders;
	}
	
	public synchronized static void addTakeAwayOrders(int orders){
		totalTakeAwayOrders+=orders;
	}
	
	public static int getTotalOrders(){
		return totalOrders;
	}
	
	public static int getTotalEatenOrders(){
		return totalEatenOrders;
	}
	
	public static int getTotalTakeAwayOrders(){
		return totalTakeAwayOrders;
	}
	
	public synchronized static Customer handleQueCustomer(Customer customer, Boolean bool){
		if(bool){
			queCustomers.add(customer);
			return null;
		}
		else{
			if(queCustomers.size()>0){
				return queCustomers.remove(0);
			}
			return null;
		}
	}
	
	public synchronized static Customer getQueCustomer(int index){
		return queCustomers.get(index);
	}
	
	public synchronized static Boolean enter(Customer customer){
		if (customers.size() < capacity && customer.getId()==id){
			id++;
			customers.add(customer);
			SushiBar.write(Thread.currentThread().getName()+ "Customer "+customer.getId()+": Has a seat now");
			queCustomers.remove(customer);
			return true;
		}
		else{
			return false;
		}
	}
	
	public synchronized static void leave(Customer customer){
		totalOrders+=customer.getOrders();
		totalEatenOrders+=customer.getEatenOrders();
		totalTakeAwayOrders+=customer.getTakeAwayOrders();
		if(customers.indexOf(customer)>0){
			Customer oldCustomer = customers.remove(0);
			synchronized (oldCustomer){
				oldCustomer.notify(); //For some reason some customers gets stuck in the servingArea, and then we need to notify them again
			}
		}
		customers.remove(customer);
		if(customers.size()+1==capacity){
			SushiBar.write(Thread.currentThread().getName()+ ": Now there is a free seat in the shop");
		}
		if(queCustomers.size()>0){
			id++;
			Customer newCustomer = queCustomers.remove(0);
			SushiBar.write(Thread.currentThread().getName()+ "Customer "+newCustomer.getId()+": Has a seat now");
			synchronized (newCustomer){
				newCustomer.notify();
			}
			customers.add(newCustomer);
			queCustomers.remove(newCustomer);
		}
		if(customers.size()==0 && queCustomers.size()==0){
			SushiBar.write(Thread.currentThread().getName()+": Total number of orders are "+totalOrders);
			SushiBar.write(Thread.currentThread().getName()+": Total number of eaten orders are "+totalEatenOrders);
			SushiBar.write(Thread.currentThread().getName()+": Total number of takeaway orders are "+totalTakeAwayOrders);
		}
	}
}
