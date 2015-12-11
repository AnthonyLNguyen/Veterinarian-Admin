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
public abstract class Animal implements Serializable, Comparable<Animal> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 337611831044850386L;
	private String species;
	private Owner owner;
	private String name;
	private int age;
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

	public String medToString() {
		if (medicalHistory.isEmpty()){
			return "";
		}
		ArrayList<MedicalRecord> list = medicalHistory;

		String listString = "| ";

		for (MedicalRecord s : list) {
			listString += s.getInfo() + " | ";
		}

		return listString;
	}

	public ArrayList<String> getVaccinations() {
		return vaccinations;
	}

	public void setVaccinations(ArrayList<String> vaccinations) {
		this.vaccinations = vaccinations;
	}
	
	public String vacToString() {
		if (vaccinations.isEmpty()){
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

}
