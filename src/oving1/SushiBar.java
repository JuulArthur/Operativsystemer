package oving1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SushiBar {
	//SushiBar settings:
    	//These parameters have to be changed to check that the program works in all situations.
	public static int capacity =20; //capacity of Sushi Bar
	private static int duration = 3; // Simulation time
	public static int maxOrder = 10; // Maximum number of orders for each customer
	public static int customerWait= 100; // coefficient of eating time for customers
	public static int doorWait = 10; // coefficient of waiting time for door for creating next customer


	public static boolean isOpen=true;
	public static Door door;

	//Creating the log file
	private static File log;
	private static String path = "./";
        
	public static void main(String[] args) { //It only opens the log file, creates the door and clock.
		log= new File(path + "log.txt"); 
		door = new Door();
		new Thread(door).start();
		Clock clock = new Clock(SushiBar.duration);
	}

	
	//Writes actions in the log file and console
	public static void write(String str){
		try {
			FileWriter fw = new FileWriter(log.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(Clock.getTime() + ", " + str +"\n");
			bw.close();
			System.out.println(Clock.getTime() + ", " + str);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
