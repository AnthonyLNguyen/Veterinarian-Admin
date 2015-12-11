/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author antho
 *
 */
public class Owner implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6244171774309846146L;
	private String name;
	private String address;
	private String phone;
	private ArrayList<Animal> pets = new ArrayList<Animal>();

	public Owner(String name, String address, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public ArrayList<Animal> getPets() {
		return pets;
	}

	public void setPets(ArrayList<Animal> pets) {
		this.pets = pets;
	}

	public void addPet(Animal p) {
		pets.add(p);
	}

	public void removePet(Animal p) {
		pets.remove(p);
	}

	public boolean hasPet() {
		if (pets.size() > 0)
			return true;
		else
			return false;
	}

	public String petsToString() {
		String listString = "| ";
		for (Animal p : pets)
		{
		    listString += p.getName() + " | ";
		}

		return listString;
	}

}
