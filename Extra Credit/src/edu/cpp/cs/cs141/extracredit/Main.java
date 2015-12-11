/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

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
 * Main class creates an {@link Engine} and a {@link UserInterface}
 * 
 * @author Anthony Nguyen
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Engine e = new Engine();
		UserInterface u = new UserInterface(e);
		u.start();
	}

}
