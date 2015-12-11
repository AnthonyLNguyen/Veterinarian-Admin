/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.io.Serializable;

/**
 * @author antho
 *
 */
public class Dog extends Animal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6016966910091596835L;
	private static String[] types = {"German Shepard", "Poodle", "Labrador", "Bulldog", "Golden"};
	
	public Dog(Owner owner, String name, int age, int type) {
		super(owner, name, age, type);
		setSpecies("Dog");
		setTypes(types);
	}
	
	public static String typeList(){
		String s = "";
		for (int i = 0 ; i < 5 ; i++)
			s += i + ". " + types[i] + " ";
		return s;
	}
	
}
