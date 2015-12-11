/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.io.*;

/**
 * @author antho
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
		setGameState(g);
	}
	
	public void setGameState (Engine g) {
		engine = g;
	}
	
	public void saveGame(String gameStateName){
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
	
	public Engine loadGame(String gameStateName) {
		FileInputStream fis;
		try {
			fis = new FileInputStream(gameStateName + ".dat");
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
