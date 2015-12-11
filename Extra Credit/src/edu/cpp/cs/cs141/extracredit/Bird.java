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
 * This class represents a Bird which can be of 5 types of bird
 * 
 * @author Anthony Nguyen
 *
 */
public class Bird extends Animal implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4127624152654775177L;
	private static String[] types = { "Parrot", "Canary", "Parakeet", "Macaw", "Cockatoo" };

	public Bird(Owner owner, String name, int age, int type) {
		super(owner, name, age, type);
		setSpecies("Bird");
		setTypes(types);
	}

	/**
	 * @return A string that displays all of the different breed of bird.
	 */
	public static String typeList() {
		String s = "";
		for (int i = 0; i < 5; i++)
			s += i + ". " + types[i] + " ";
		return s;
	}

}
