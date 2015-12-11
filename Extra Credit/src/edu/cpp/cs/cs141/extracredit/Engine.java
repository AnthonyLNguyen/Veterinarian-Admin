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
public class Engine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4776646930816181984L;
	private ArrayList<Appointment> appList = new ArrayList<Appointment>();
	private ArrayList<Owner> ownerList = new ArrayList<Owner>();
	private ArrayList<Animal> petList = new ArrayList<Animal>();
	
	public Appointment createAppointment(String date, String time, Animal pet, Owner owner) {
		Appointment a =new Appointment(date, time, pet, owner);
		addAppointment(a);
		return a;
	}
	
	public void addAppointment(Appointment a){
		appList.add(a);
	}
	
	public void removeAppointment(Appointment a){
		appList.remove(a);
	}
	
	public void createOwner(String name, String address, String number) {
		addOwner(new Owner(name, address, number));
	}
	
	public void addOwner(Owner o){
		ownerList.add(o);
	}
	
	public void removeOwner(Owner o){
		ownerList.remove(o);
	}
	
	public Animal createPet(int species, Owner owner, String name, int age, int type) {
		Animal p = null;
		switch(species){
			case 1:
				p = new Bird(owner, name, age, type);
				break;
			case 2:
				p = new Dog(owner, name, age, type);
				break;
			case 3:
				p = new Fish(owner, name, age, type);
				break;
		}
		owner.addPet(p);
		petList.add(p);
		return p;
	}
	
	public void addPet(Owner o , Animal p){
		o.addPet(p);
	}
	
	public void removePet(Owner o , Animal p){
		o.removePet(p);
	}
	
	public ArrayList<Appointment> getAppList() {
		return appList;
	}

	public ArrayList<Owner> getOwnerList() {
		return ownerList;
	}

	public ArrayList<Animal> getPetList() {
		return petList;
	}

	public void setPetList(ArrayList<Animal> petList) {
		this.petList = petList;
	}

	public Appointment getFromList(int a){
		return appList.get(a);
	}
	
}
