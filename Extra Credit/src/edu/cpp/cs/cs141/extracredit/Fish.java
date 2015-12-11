/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.io.Serializable;

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
public class Fish extends Animal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3031023217833471205L;
	private static String[] types = {"Gold", "Koi", "Clown", "Puffer", "Angel"};
	
	public Fish(Owner owner, String name, int age, int type) {
		super(owner, name, age, type);
		setSpecies("Fish");
		setTypes(types);
	}

	/**
	 * @return A string that displays all of the different breed of fish.
	 */
	public static String typeList(){
		String s = "";
		for (int i = 0 ; i < 5 ; i++)
			s += i + ". " + types[i] + " ";
		return s;
	}
	
}
