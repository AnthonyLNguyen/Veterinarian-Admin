/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.io.*;

/**
 * 
 *
 * CS 141: Introduction to Programming and Problem Solving
 * Professor: Edwin Rodr&iacute;guez
 *
 * Programming Assignment EXTRA CREDIT
 *
 * Administration program for a veterinary office. Can be used to make appointments.
 *
 * Anthony Nguyen
 *
 *
 */

/**
 * @author Anthony Nguyen
 *
 */
public class Save implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7361935344923958807L;
	Engine engine;
	
	public Save (){
		engine = null;
	}
	public Save(Engine g) {
		setState(g);
	}
	
	public void setState (Engine g) {
		engine = g;
	}
	
	public void save(String gameStateName){
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(gameStateName + ".dat");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject((Object) engine);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Engine load(String stateName) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(stateName + ".dat");
			ObjectInputStream ois = new ObjectInputStream(fis);
			engine = (Engine) ois.readObject();
			fis.close();
		} catch (IOException e) {
			System.out.println("That file doesn't exist!");
		} catch (ClassNotFoundException e) {
			System.out.println("Something horribly went wrong.");
		}
		return engine;
	}
	
	
	
	

	
}
