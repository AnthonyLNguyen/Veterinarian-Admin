/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.io.Serializable;

/**
 * @author antho
 *
 */
public class Bird extends Animal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4127624152654775177L;
	private static String[] types = {"Parrot", "Canary", "Parakeet", "Macaw", "Cockatoo"};
	
	public Bird(Owner owner, String name, int age, int type) {
		super(owner, name, age, type);
		setSpecies("Bird");
		setTypes(types);
	}

	public static String typeList(){
		String s = "";
		for (int i = 0 ; i < 5 ; i++)
			s += i + ". " + types[i] + " ";
		return s;
	}
	
}
