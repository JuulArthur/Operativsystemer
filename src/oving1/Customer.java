package oving1;
import java.util.Random;


public class Customer implements Runnable{
	private int id;
	private int orders;
	private int eatenOrders;
	private int takeAwayOrders;
	private Random rand = new Random();
	
	public Customer(int id){
		this.id = id;
		orders = rand.nextInt(SushiBar.maxOrder-1) + 1;
		eatenOrders = rand.nextInt(orders)+1;
		takeAwayOrders = orders - eatenOrders;
	}
	
	public int getOrders() {
		return orders;
	}

	public int getEatenOrders() {
		return eatenOrders;
	}

	public int getTakeAwayOrders() {
		return takeAwayOrders;
	}
	
	public int getId(){
		return id;
	}
	
	public synchronized void waitMe(){
		try {
			this.wait();
		} catch (InterruptedException e) {
		}
	}

	public void run() {
		if(!ServingArea.enter(this)){ //If it is able to enter the serving area it will not wait but just continue to eat
			SushiBar.write(Thread.currentThread().getName()+ ": Customer "+id+" is waiting for a free seat");
			waitMe(); //The customer waits to be notified
		}
		SushiBar.write(Thread.currentThread().getName()+ ": Customer "+id+" entering serving area");
		try {
			SushiBar.write(Thread.currentThread().getName()+ ": Customer "+id+" is eating sushi");
			Thread.sleep(SushiBar.customerWait); //The customer is eating
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServingArea.leave(this);
		SushiBar.write(Thread.currentThread().getName()+ ": Customer "+id+" is leaving");
	}
	
	public String toString(){ //only used when I want to print information about the program, not used in the exercise
		return (""+id);
	}

}
