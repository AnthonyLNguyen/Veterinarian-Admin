/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

/**
 * @author antho
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
