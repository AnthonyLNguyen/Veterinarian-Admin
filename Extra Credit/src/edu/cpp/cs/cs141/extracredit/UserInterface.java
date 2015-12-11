/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * @author antho
 *
 */
public class UserInterface {

	private Engine engine;
	private Scanner input;
	private Owner currentOwner;
	private int currentPetNumber;
	private Save save;

	public UserInterface(Engine engine) {
		this.engine = engine;
		input = new Scanner(System.in);
	}

	public void start() {
		welcome();
		while (true)
			run();
	}

	public void run() {
		switch (mainMenu()) {
		case 1:
			appointments();
			break;
		case 2:
			owners();
			break;
		case 3:
			petOptions();
			break;
		case 4:
			save();
			break;
		case 5:
			load();
			break;
		case 6:
			help();
			break;
		default:
			System.out.println("Invalid Entry");
			break;
		}
	}

	public void help() {
		System.out.println("=======HELP========\n"
				+ "This program was developed by Anthony Nguyen.\n"
				+ "Enter 'stop' any time to stop the program.\n");
		
	}

	public void owners() {
		boolean selected = true;
		while (selected)
			switch (menu(2)) {
			case 1:
				input.nextLine();
				createOwner();
				selected = false;
				break;
			case 2:
				selected = false;
				break;
			case 3:
				printList(2);
				selected = false;
				break;
			case 4:
				chooseOwner();
				ownerOptions();
				selected = false;
				break;
			case 5:
				selected = false;
				break;
			default:
				System.out.println("Invalid Entry");
				break;
			}

	}

	public void appointments() {
		boolean selected = true;
		while (selected)
			switch (menu(1)) {
			case 1:
				input.nextLine();
				addApp();
				selected = false;
				break;
			case 2:
				selected = false;
				break;
			case 3:
				sortAppointmentsBy();
				selected = false;
				break;
			case 4:
				selected = false;
				break;
			default:
				System.out.println("Invalid Entry");
				break;
			}

	}

	public void welcome() {
		System.out.println("Welcome to Vet Admin Pro");
	}

	public int mainMenu() {
		System.out.println("Choose: \t\n" + "1. Appointments\t\n" + "2. Owners\t\n" + "3. Pets\t\n" + "4. Save\t\n"
				+ "5. Load\t\n");
		return getInt();
	}

	public void save() {
		System.out.println("Enter a name for the save file");
		String saveName = input.next();
		save = new Save(engine);
		save.saveGame(saveName);
		System.out.println("Game has been saved! \nThe save state is called " + saveName);
	}

	public void load() {
		System.out.println("What is the name of the save you would like to load? (Don't include extention)");
		String saveName = input.next();
		save = new Save();
		engine = save.loadGame(saveName);
		start();

	}

	public int menu(int option) {
		switch (option) {
		case 1:
			System.out.println("Choose one: \t\n" + "1. Add appointment\t\n" + "2. Remove appointment\t\n"
					+ "3. List appointments\t\n" + "4. Return\t\n");
			break;
		case 2:
			System.out.println("Choose one: \t\n" + "1. Add owner\t\n" + "2. Remove owner\t\n" + "3. List owners\t\n"
					+ "4. Owner Options" + "5. Return\t\n");
			break;
		}
		return getInt();
	}

	public void ownerOptions() {
		System.out.println("Choose one: \t\n" + "1. Show Owner Info\t\n" + "2. Show Pet Info\t\n"
				+ "3. Edit Owner Name\t\n" + "4. Edit Address\t\n" + "5. Edit Phone Number\t\n" + "6. Add Pet\t\n"
				+ "7. Return\n\t");
		boolean selected = true;
		while (selected)
			switch (getInt()) {
			case 1:
				printInfo(currentOwner);
				selected = false;
				break;
			case 2:
				choosePet();
				printInfo(currentOwner.getPets().get(currentPetNumber));
				selected = false;
				break;
			case 3:
				currentOwner.setName(input.nextLine());
				selected = false;
				break;
			case 4:
				currentOwner.setAddress(input.nextLine());
				selected = false;
				break;
			case 5:
				currentOwner.setPhone(input.nextLine());
				selected = false;
				break;
			case 6:
				createPet();
				selected = false;
				break;
			case 7:
				selected = false;
				break;
			default:
				System.out.println("Invalid Entry");
				break;
			}
	}

	public void petOptions() {
		System.out.println("Choose one: \t\n" + "1. Create Appointment for Pet\t\n" + "2. Show Pet Info\t\n"
				+ "3. Add Pet Info\t\n" + "4. Show Pet List\t\n" + "5. Add Pet\t\n" + "6. Return\t\n");
		boolean selected = true;
		while (selected)
			switch (getInt()) {
			case 1:
				chooseOwner();
				createAppointment(choosePet());
				selected = false;
				break;
			case 2:
				chooseOwner();
				printInfo(choosePet());
				selected = false;
				break;
			case 3:
				System.out.println("Choose one: \t\n" + "1. Change Pet Age\t\n" + "2. Add One Disease\t\n"
						+ "3. Add One Vaccination\t\n");
				addPetInfo(getInt());
				selected = false;
				break;
			case 4:
				sortPetsBy();
				selected = false;
				break;
			case 5:
				chooseOwner();
				createPet(currentOwner);
				selected = false;
				break;
			case 6:
				selected = false;
				break;
			default:
				System.out.println("Invalid Entry");
				break;
			}
	}

	public void addPetInfo(int choice) {
		chooseOwner();
		Animal p = choosePet();
		input.nextLine();
		boolean selected = true;
		while (selected)
			switch (choice) {
			case 1:
				p.setAge(getInt());
				selected = false;
				break;
			case 2:
				boolean current = false;
				System.out.println("Enter Disease: ");
				String info = input.nextLine();
				System.out.println("Is the disease still current?: ");
				if (yesOrNo())
					current = true;
				p.getMedicalHistory().add(new MedicalRecord(info, current));
				selected = false;
				break;
			case 3:
				System.out.println("Enter Vaccination: ");
				p.getVaccinations().add(input.nextLine());
				selected = false;
				break;
			default:
				System.out.println("Invalid Entry");
				break;
			}
	}

	public void sortPetsBy() {
		System.out.println("Sort by:\n");
		System.out.println("Choose: \t\n" + "1. Species\t\n" + "2. Owner\t\n" + "3. Name\t\n");
		boolean selected = true;
		while (selected)
			switch (getInt()) {
			case 1:
				Collections.sort(engine.getPetList(), new AnimalSpeciesComparator());
				listAllPets();
				selected = false;
				break;
			case 2:
				Collections.sort(engine.getPetList(), new AnimalOwnerComparator());
				listAllPets();
				selected = false;
				break;
			case 3:
				Collections.sort(engine.getPetList(), new AnimalNameComparator());
				listAllPets();
				selected = false;
				break;
			case 4:
				System.out.println(engine.getPetList().isEmpty());
				break;
			default:
				System.out.println("Invalid Entry");
				break;
			}
	}

	public void sortAppointmentsBy() {
		System.out.println("Sort by:\n");
		System.out.println("Choose: \t\n" + "1. Owner\t\n" + "2. Date\t\n");
		boolean selected = true;
		while (selected)
			switch (getInt()) {
			case 1:
				Collections.sort(engine.getAppList(), new AppointmentOwnerNameComparator());
				printList(1);
				selected = false;
				break;
			case 2:
				Collections.sort(engine.getAppList(), new AppointmentDateComparator());
				printList(1);
				selected = false;
				break;
			default:
				System.out.println("Invalid Entry");
				break;
			}
	}

	public class AppointmentOwnerNameComparator implements Comparator<Appointment> {
		public int compare(Appointment a1, Appointment a2) {
			return a1.getOwner().getName().compareTo(a2.getOwner().getName());
		}
	}

	public class AppointmentDateComparator implements Comparator<Appointment> {
		public int compare(Appointment a1, Appointment a2) {
			return (a2.getDate().compareTo(a1.getDate())*100) + a2.getTime().compareTo(a1.getTime());
		}
	}

	public class AnimalSpeciesComparator implements Comparator<Animal> {
		public int compare(Animal a1, Animal a2) {
			return a1.getSpecies().compareTo(a2.getSpecies());
		}
	}

	public class AnimalOwnerComparator implements Comparator<Animal> {
		public int compare(Animal a1, Animal a2) {
			return a1.getOwner().getName().compareTo(a2.getOwner().getName());
		}
	}

	public class AnimalNameComparator implements Comparator<Animal> {
		public int compare(Animal a1, Animal a2) {
			return a1.getName().compareTo(a2.getName());
		}
	}

	public void addApp() {
		System.out.println("Choose one: \t\n" + "1. List Owners\t\n" + "2. Enter Owner ID\t\n");
		boolean selected = true;
		while (selected)
			switch (getInt()) {
			case 1:
				printList(2);
				chooseOwner();
				printInfo(createAppointment());
				selected = false;
				break;
			case 2:
				chooseOwner();
				printInfo(createAppointment());
				selected = false;
				break;
			default:
				System.out.println("Invalid Entry");
				break;
			}

	}

	public void createOwner() {
		System.out.println("Enter Name: ");
		String name = input.nextLine();
		System.out.println("Enter Address: ");
		String address = input.nextLine();
		System.out.println("Enter Phone Number: ");
		String number = input.nextLine();
		engine.createOwner(name, address, number);

	}

	public Owner chooseOwner() {

		System.out.println("Please Enter User ID");
		while (true) {

			int number = getInt();

			try {
				currentOwner = engine.getOwnerList().get(number);
				return engine.getOwnerList().get(number);
			} catch (Exception e) {
				System.out.println("ID Doesn't Exist");
			}
		}
	}

	public Appointment createAppointment() {
		String date = enterDate();
		System.out.println("Enter Time: ");
		String time = input.nextLine();
		System.out.println("Choose:\n\t" + "1. New Pet\n\t" + "2. Existing Pet\n\t");

		Animal p = null;

		boolean selected = true;
		while (selected)
			switch (getInt()) {
			case 1:
				p = newPet();
				selected = false;
			case 2:
				p = choosePet();
				selected = false;
			default:
				if (selected)
				System.out.println("Invalid Choice");
				break;
			}
		Animal pet = p;
		input.nextLine();
		return engine.createAppointment(date, time, pet, currentOwner);
	}

	public Appointment createAppointment(Animal p) {
		String date = enterDate();
		System.out.println("Enter Time: ");
		String time = input.nextLine();
		return engine.createAppointment(date, time, p, currentOwner);
	}

	public String enterDate() {
		System.out.println("Enter Date (YYYYMMDD): ");
		boolean correct = true;
		String date = "";
		while (correct) {
			date = input.nextLine();
			if (date.length() != 8)
				System.out.println("Enter the correct format YYYYMMDD");
			if (date.length() == 8)
				correct = false;
		}
		return date;
	}

	public Animal createPet(Owner o) {
		System.out.println("Enter Species:\n\t" + "1. Bird\n\t" + "2. Dog\n\t" + "3. Fish\n\t");
		int species = getInt();
		System.out.println("Enter Name: ");
		input.nextLine();
		String name = input.nextLine();
		System.out.println("Enter Age: ");
		int age = getInt();
		int type = chooseType(species);
		Animal pet = engine.createPet(species, o, name, age, type);
		return pet;
	}

	public Animal createPet() {
		System.out.println("Enter Species:\n\t" + "1. Bird\n\t" + "2. Dog\n\t" + "3. Fish\n\t");
		int species = getInt();
		System.out.println("Enter Name: ");
		input.nextLine();
		String name = input.nextLine();
		System.out.println("Enter Age: ");
		int age = getInt();
		int type = chooseType(species);
		Animal pet = engine.createPet(species, currentOwner, name, age, type);
		return pet;
	}

	public Animal newPet() {
		Animal p;
		p = createPet();
		currentPetNumber = currentOwner.getPets().indexOf(p);
		return p;
	}

	public Animal choosePet() {
		Animal p;
		listPets();
		System.out.println("ENTER PET NUMBER:");
		while (true) {

			int number = getInt();

			try {
				p = currentOwner.getPets().get(number);
				currentPetNumber = currentOwner.getPets().indexOf(p);
				return p;
			} catch (Exception e) {
				System.out.println("ID Doesn't Exist");
			}
		}
	}

	public void listPets() {
		for (Animal p : currentOwner.getPets())
			System.out.println("Pet Number: " + currentOwner.getPets().indexOf(p) + "  Name: " + p.getName());
	}

	public void listAllPets() {
		for (Animal pet : engine.getPetList())
			System.out.println("Species: " + pet.getSpecies() + "  Name : " + pet.getName() + "  Owner: "
					+ pet.getOwner().getName() + "  Age : " + pet.getAge() + "\nMedical History: "
					+ pet.medToString() + "\nVaccinations: " + pet.vacToString());
	}

	public int chooseType(int d) {
		String s = "";
		boolean selected = true;
		while (selected)
			switch (d) {
			case 1:
				s += Bird.typeList();
				selected = false;
				break;
			case 2:
				s += Dog.typeList();
				selected = false;
				break;
			case 3:
				s += Fish.typeList();
				selected = false;
				break;
			default:
				System.out.println("Invalid Type");
				break;
			}
		System.out.println("Enter Type:\n\t" + s);
		return getInt();

	}

	public boolean yesOrNo() {
		String a = input.nextLine();
		if (a.equalsIgnoreCase("yes") || a.equalsIgnoreCase("y"))
			return true;
		if (a.equalsIgnoreCase("no") || a.equalsIgnoreCase("n"))
			return false;
		else {
			System.out.println("Please enter yes or no!");
			return yesOrNo();
		}
	}

	public void printList(int option) {
		switch (option) {
		case 1:
			for (Appointment a : engine.getAppList())
				System.out.println("Time: " + a.getTime() + "  Date: " + a.getDate() + "  Pet: " + a.getPet().getName()
						+ "  Owner: " + a.getOwner().getName());
			break;
		case 2:
			for (Owner o : engine.getOwnerList())
				System.out.println("ID: " + engine.getOwnerList().indexOf(o) + "  Name: " + o.getName());
			break;
		case 3:
			for (Animal a : engine.getPetList())
				System.out.println("Species: " + a.getSpecies() + "  Type: " + a.getTypes()[a.getType()]
						+ "  Pet Number: " + currentOwner.getPets().indexOf(a) + "  Name: " + a.getName() + "  Age : "
						+ a.getAge() + "\nMedical History: " + a.medToString() + "\nVaccinations: "
						+ a.vacToString());
		}
	}

	public void printInfo(Object o) {
		if (o instanceof Appointment) {
			Appointment a = (Appointment) o;
			System.out.println("Time: " + a.getTime() + "  Date: " + a.getDate() + "  Pet: " + a.getPet().getName());
		} else if (o instanceof Owner) {
			Owner a = (Owner) o;
			System.out.println("ID: " + engine.getOwnerList().indexOf(a) + "  Name: " + a.getName() + "  Adress: "
					+ a.getAddress() + "  Phone: " + a.getPhone() + "  Pets: " + a.petsToString());
		} else if (o instanceof Animal) {
			Animal a = (Animal) o;
			System.out.println("Species: " + a.getSpecies() + "  Type: " + a.getTypes()[a.getType()] + "  Pet Number: "
					+ currentOwner.getPets().indexOf(a) + "  Name: " + a.getName() + "  Age : " + a.getAge()
					+ "\nMedical History: " + a.medToString() + "\nVaccinations: " + a.vacToString());
		}
	}

	public int getInt() {
		if (input.hasNext("stop")) {
			System.out.println("***Terminated***");
			System.exit(0);
		}
		while (!input.hasNextInt()) {
			System.out.print("Input is not a valid integer!");
			input.next();
		}
		int temp = input.nextInt();
		while (temp < 0 && temp > 100) {
			System.out.println("invalid input");
			temp = input.nextInt();
		}
		return temp;
	}
	
	
}
