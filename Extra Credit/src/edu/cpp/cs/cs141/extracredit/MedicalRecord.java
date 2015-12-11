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
 * This class represents a medical record. Each medical record has a field for
 * info, and whether or not the disease is still current.
 * 
 * @author Anthony Nguyen
 *
 */
public class MedicalRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5974930004576293497L;
	private String info;
	private boolean current;

	public MedicalRecord(String info, boolean current) {
		super();
		this.info = info;
		this.current = current;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isCurrent() {
		return current;
	}

	public void setCurrent(boolean current) {
		this.current = current;
	}

}
