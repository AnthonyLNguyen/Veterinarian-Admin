/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.io.Serializable;

/**
 * @author antho
 *
 */
public class Appointment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8470228822133976832L;
	private String date;
	private String time;
	private Animal pet;
	private Owner owner;
	
	public Appointment(String date, String time, Animal pet, Owner owner) {
		super();
		this.date = date;
		this.time = time;
		this.pet = pet;
		this.owner = owner;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Animal getPet() {
		return pet;
	}

	public void setPet(Animal pet) {
		this.pet = pet;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
}
