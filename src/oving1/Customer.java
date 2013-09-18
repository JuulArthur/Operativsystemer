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
	
	public void notifyMe(){
		this.notify();
	}
	
	public void waitMe(){
		try {
			this.wait();
		} catch (InterruptedException e) {
		}
	}

	public void run() {
		if(ServingArea.isSpace()){
			ServingArea.handleCustomer(this, true);
		}
		else{
			waitMe();
		}
		ServingArea.handleCustomer(this, true);
		SushiBar.write(Thread.currentThread().getName()+ ": Customer "+id+" entering serving area");
		try {
			SushiBar.write(Thread.currentThread().getName()+ ": Customer "+id+" eating");
			Thread.sleep(SushiBar.customerWait);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ServingArea.handleCustomer(this, false);
		SushiBar.write(Thread.currentThread().getName()+ ": Customer "+id+" is leaving");
	}

}
