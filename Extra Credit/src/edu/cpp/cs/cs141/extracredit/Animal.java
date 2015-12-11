/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.io.Serializable;
import java.util.ArrayList;

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
 * 
 * This class is an abstract class that represents all animals. It has a field
 * called species which determines the type of animal. There are also fields for
 * the owner of the animal, the name, the age, the appointments, history,
 * vaccinations, and the breed of animal.
 * 
 * @author Anthony Nguyen
 *
 */
public abstract class Animal implements Serializable, Comparable<Animal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 337611831044850386L;
	private String species;
	private Owner owner;
	private String name;
	private int age;
	private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
	private ArrayList<MedicalRecord> medicalHistory = new ArrayList<MedicalRecord>();
	private ArrayList<String> vaccinations = new ArrayList<String>();
	private int type;
	private String[] types = new String[5];

	public Animal(Owner owner, String name, int age, int type) {
		super();
		this.owner = owner;
		this.name = name;
		this.age = age;
		this.type = type;
	}

	public int compareTo(Animal p) {
		return this.age - p.getAge();
	}

	public String typeToString() {
		return types[type];
	}

	public String printStuff() {
		return "Species: " + species + "\n" + "Owner: " + owner.getName() + "\n" + "Name: " + name + "\n" + "Age: "
				+ age + "\n" + "Type: " + types[type];
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public ArrayList<MedicalRecord> getMedicalHistory() {
		return medicalHistory;
	}

	public void setMedicalHistory(ArrayList<MedicalRecord> medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	/**
	 * @return A string that shows all of the medical history.
	 */
	public String medToString() {
		if (medicalHistory.isEmpty()) {
			return "NONE";
		}
		ArrayList<MedicalRecord> list = medicalHistory;

		String listString = "| ";

		for (MedicalRecord s : list) {
			if (s.isCurrent())
				listString += "[CURRENT]";
			listString += s.getInfo() + " | ";
		}

		return listString;
	}

	/**
	 * @return A string that shows all of the vaccinations.
	 */
	public ArrayList<String> getVaccinations() {
		return vaccinations;
	}

	public void setVaccinations(ArrayList<String> vaccinations) {
		this.vaccinations = vaccinations;
	}

	public String vacToString() {
		if (vaccinations.isEmpty()) {
			return "NONE";
		}
		ArrayList<String> list = vaccinations;

		String listString = "| ";

		for (String s : list) {
			listString += s + " | ";
		}

		return listString;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String[] getTypes() {
		return types;
	}

	public void setTypes(String[] types) {
		this.types = types;
	}

	public ArrayList<Appointment> getAppointments() {
		return appointments;
	}

	/**
	 * @return A string that shows all of the appointments.
	 */
	public String appToString() {
		if (appointments.isEmpty()) {
			return "NONE";
		}
		ArrayList<Appointment> list = appointments;

		String listString = "| ";
		for (Appointment s : list) {
			listString += list.indexOf(s) + s.getTime() + " " + s.getDate() + " | ";
		}

		return listString;
	}

	public void setAppointments(ArrayList<Appointment> appointments) {
		this.appointments = appointments;
	}

}
