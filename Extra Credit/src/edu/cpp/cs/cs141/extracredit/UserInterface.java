/**
 * 
 */
package edu.cpp.cs.cs141.extracredit;

import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Scanner;

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
 * Takes input from the user and prints our information.
 * 
 * @author Anthony Nguyen
 *
 */
public class UserInterface {

	private Engine engine;
	private Scanner input;
	/**
	 * The owner that was last selected by the user using {@link #chooseOwner()}
	 * .
	 */
	private Owner currentOwner;
	/**
	 * The current pet number that is chosen according to the currentOwner using
	 * {@link #choosePet()}.
	 */
	private int currentPetNumber;
	private Save save;

	public UserInterface(Engine engine) {
		this.engine = engine;
		input = new Scanner(System.in);
	}

	/**
	 * Runs the interface of the program
	 */
	public void start() {
		welcome();
		while (true)
			run();
	}

	/**
	 * @return The user's choice from 1. Appointments 2. Owners 3. Pets 4. Save
	 *         5. Load 6. Help Used in {@link #run()}
	 */
	public int mainMenu() {
		System.out.println("Choose: \n\t" + "1. Appointments\n\t" + "2. Owners\n\t" + "3. Pets\n\t" + "4. Save\n\t"
				+ "5. Load\n\t" + "6. Help\n\t");
		return getInt();
	}

	/**
	 * A menu with 6 options 1. Appointments 2. Owners 3. Pets 4. Save 5. Load
	 * 6. Help Takes input from {@link #mainMenu()}
	 */
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

	/**
	 * Prints out help
	 */
	public void help() {
		System.out.println("=======HELP========\n" + "This program was developed by Anthony Nguyen.\n"
				+ "Enter 'stop' any time to stop the program.\n"
				+ "There is a save called 'test.dat' that has some preloaded content to test.\n");

	}

	/**
	 * Switch with options to 1. Add owner 2.List owner pets 3. List owners 4.
	 * Owner Options 5. Return
	 */
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
				chooseOwner();
				listPets();
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

	/**
	 * Switch with options to 1. Add appointment 2. Resolve appointment 3. List
	 * appointments 4. Return
	 */
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
				sortAppointmentsBy();
				System.out.println("Enter Appointment Number");
				engine.getAppList().get(getInt()).resolveAppointment();
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

	/**
	 * Prints out the options 1. Create Appointment for Pet 2. Show Pet Info 3.
	 * Add Pet Info 4. Show Pet List 5. Add Pet 6. Resolve Appointment 7. Return
	 * Uses a switch to activate the chosen option
	 */
	public void petOptions() {
		System.out.println("Choose one: \n\t" + "1. Create Appointment for Pet\n\t" + "2. Show Pet Info\n\t"
				+ "3. Add Pet Info\n\t" + "4. Show Pet List\n\t" + "5. Add Pet\n\t" + "6. Resolve Appointment\n\t"
				+ "7. Return\n\t");
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
				System.out.println("Choose one: \n\t" + "1. Change Pet Age\n\t" + "2. Add One Disease\n\t"
						+ "3. Add One Vaccination\n\t");
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
				chooseOwner();
				Animal p = choosePet();
				p.appToString();
				System.out.println("Enter Appointment Number");
				p.getAppointments().get(getInt()).resolveAppointment();
			case 7:
				selected = false;
				break;
			default:
				System.out.println("Invalid Entry");
				break;
			}
	}

	/**
	 * Prints a welcome message
	 */
	public void welcome() {
		System.out.println("Welcome to Vet Admin Pro v1.0");
	}

	/**
	 * Saves the program into a data file named by the user.
	 */
	public void save() {
		System.out.println("Enter a name for the save file");
		String saveName = input.next();
		save = new Save(engine);
		save.save(saveName);
		System.out.println("Game has been saved! \nThe save state is called " + saveName);
	}

	/**
	 * Loads the program from a data file named by the user.
	 */
	public void load() {
		System.out.println("What is the name of the save you would like to load? (Don't include extention)");
		String saveName = input.next();
		save = new Save();
		engine = save.load(saveName);
		start();

	}

	/**
	 * @param option
	 *            There are 2 different options to print out. One for owner and
	 *            one for appointment. The one selected will determine which
	 *            ones are printed out.
	 * @return The option that is chosen for either appointments or owner option
	 *         menu.
	 */
	public int menu(int option) {
		switch (option) {
		case 1:
			System.out.println("Choose one: \n\t" + "1. Add appointment\n\t" + "2. Resolve appointment\n\t"
					+ "3. List appointments\n\t" + "4. Return\n\t");
			break;
		case 2:
			System.out.println("Choose one: \n\t" + "1. Add owner\n\t" + "2. Remove owner\n\t" + "3. List owners\n\t"
					+ "4. Owner Options\n\t" + "5. Return\n\t");
			break;
		}
		return getInt();
	}

	/**
	 * Owner options are printed and then a switch is used from the input
	 */
	public void ownerOptions() {
		System.out.println(
				"Choose one: \n\t" + "1. Show Owner Info\n\t" + "2. Show Pet Info\n\t" + "3. Edit Owner Name\n\t"
						+ "4. Edit Address\n\t" + "5. Edit Phone Number\n\t" + "6. Add Pet\n\t" + "7. Return\n\t");
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

	/**
	 * Allows you to edit 1. Change Pet Age 2. Add One Disease 3. Add One
	 * Vaccination
	 * 
	 * @param choice
	 *            Input that will choose which case to use in the switch
	 */
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

	/**
	 * Sorts the pet list according to which case is chosen.
	 */
	public void sortPetsBy() {
		System.out.println("Sort by:\n");
		System.out.println("Choose: \n\t" + "1. Species\n\t" + "2. Owner\n\t" + "3. Name\n\t");
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

	/**
	 * Sorts the appointment list according to which case is chosen.
	 */
	public void sortAppointmentsBy() {
		System.out.println("Sort by:\n");
		System.out.println("Choose: \n\t" + "1. Owner\n\t" + "2. Date\n\t");
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
			return (a2.getDate().compareTo(a1.getDate()) * 100) + a2.getTime().compareTo(a1.getTime());
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

	/**
	 * 2 Options whether to list all the owners and then input the user ID or
	 * just input right away.
	 */
	public void addApp() {
		System.out.println("Choose one: \n\t" + "1. List Owners\n\t" + "2. Enter Owner ID\n\t");
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

	/**
	 * Asks for input for owner information and uses the info to create an
	 * owner.
	 */
	public void createOwner() {
		System.out.println("Enter Name: ");
		String name = input.nextLine();
		System.out.println("Enter Address: ");
		String address = input.nextLine();
		System.out.println("Enter Phone Number: ");
		String number = input.nextLine();
		engine.createOwner(name, address, number);

	}

	/**
	 * Prompts the user to enter a user ID and changes {@link #currentOwner} to
	 * the owner that is chosen. Catches the {@link IndexOutOfBoundsException}.
	 * 
	 * @return The owner that is chosen
	 */
	public Owner chooseOwner() {

		System.out.println("Please Enter User ID");
		while (true) {

			int number = getInt();

			try {
				currentOwner = engine.getOwnerList().get(number);
				return engine.getOwnerList().get(number);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("ID Doesn't Exist");
			}
		}
	}

	/**
	 * Prompts the user to create an appointment for a new animal or an existing
	 * one. Used in {@link #appointments()}.
	 * 
	 * @return The appointment that is created
	 */
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

	/**
	 * Prompts the user to create an appointment for an existing pet. Used in
	 * {@link #petOptions()}
	 * 
	 * @param p
	 *            A pet that will have an appointment added to it
	 * @return The appointment that is created.
	 */
	public Appointment createAppointment(Animal p) {
		String date = enterDate();
		System.out.println("Enter Time: ");
		String time = input.nextLine();
		return engine.createAppointment(date, time, p, currentOwner);
	}

	/**
	 * Makes sure the user inputs an 8 character string. Recommends to use
	 * YYYYMMDD format for best sorting possible.
	 * 
	 * @return A string that represents the date.
	 */
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

	/**
	 * Creates a pet according to certain owner.
	 * 
	 * @param o
	 *            The owner of the new pet.
	 * @return The created pet.
	 */
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

	/**
	 * Creates a pet using the {@link #currentOwner}.
	 * 
	 * @return The created pet.
	 */
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

	/**
	 * {@link #createPet() Creates a pet} then changes {@link #currentPetNumber
	 * the currently selected pet.}
	 * 
	 * @return the pet that is created.
	 */
	public Animal newPet() {
		Animal p;
		p = createPet();
		currentPetNumber = currentOwner.getPets().indexOf(p);
		return p;
	}

	/**
	 * Changes {@link #currentPetNumber the currently selected pet.}
	 * 
	 * @return
	 */
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

	/**
	 * Lists all the pets of the {@link #currentOwner current owner.}
	 */
	public void listPets() {
		for (Animal pet : currentOwner.getPets())
			System.out.println("Pet Number: " + currentOwner.getPets().indexOf(pet) + ". " + "Species: "
					+ pet.getSpecies() + "  Name : " + pet.getName() + "  Owner: " + pet.getOwner().getName()
					+ "  Age : " + pet.getAge() + "\nMedical History: " + pet.medToString() + "\nVaccinations: "
					+ pet.vacToString());
	}

	/**
	 * Lists all the pets in the {@link #engine}
	 */
	public void listAllPets() {
		for (Animal pet : engine.getPetList())
			System.out.println(engine.getPetList().indexOf(pet) + ". " + "Species: " + pet.getSpecies() + "  Name : "
					+ pet.getName() + "  Owner: " + pet.getOwner().getName() + "  Age : " + pet.getAge());
	}

	/**
	 * Returns what type of animal the pet is. Used in {@link #createPet()}
	 * 
	 * @param d
	 *            The species ID
	 * @return What integer was inputed by the user.
	 */
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

	/**
	 * Asks the user a yes or no question.
	 * 
	 * @return true if yes false if no
	 */
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

	/**
	 * Prints the info of a certain arrayList. {@link Appointment},
	 * {@link Owner}, or {@link Animal}
	 * 
	 * @param option
	 *            What type of list
	 */
	public void printList(int option) {
		switch (option) {
		case 1:
			for (Appointment a : engine.getAppList())
				System.out.println(engine.getAppList().indexOf(a) + ". " + a.getCompletion() + "Time: " + a.getTime()
						+ "  Date: " + a.getDate() + "  Pet: " + a.getPet().getName() + "  Owner: "
						+ a.getOwner().getName());
			break;
		case 2:
			for (Owner o : engine.getOwnerList())
				System.out.println("ID: " + engine.getOwnerList().indexOf(o) + "  Name: " + o.getName());
			break;
		case 3:
			for (Animal a : engine.getPetList())
				System.out.println(engine.getPetList().indexOf(a) + ". " + "Species: " + a.getSpecies() + "  Type: "
						+ a.getTypes()[a.getType()] + "  Pet Number: " + currentOwner.getPets().indexOf(a) + "  Name: "
						+ a.getName() + "  Age : " + a.getAge() + "\nMedical History: " + a.medToString()
						+ "\nVaccinations: " + a.vacToString());
		}
	}

	/**
	 * Prints out the single info of a {@link Appointment}, {@link Owner}, or
	 * {@link Animal}
	 * 
	 * @param o
	 *            {@link Appointment}, {@link Owner}, or {@link Animal}
	 */
	public void printInfo(Object o) {
		if (o instanceof Appointment) {
			Appointment a = (Appointment) o;
			System.out.println(engine.getAppList().indexOf(a) + ". " + a.getCompletion() + "Time: " + a.getTime()
					+ "  Date: " + a.getDate() + "  Pet: " + a.getPet().getName());
		} else if (o instanceof Owner) {
			Owner a = (Owner) o;
			System.out.println("ID: " + engine.getOwnerList().indexOf(a) + "  Name: " + a.getName() + "  Adress: "
					+ a.getAddress() + "  Phone: " + a.getPhone() + "  Pets: " + a.petsToString());
		} else if (o instanceof Animal) {
			Animal a = (Animal) o;
			System.out.println(engine.getPetList().indexOf(a) + ". " + "Species: " + a.getSpecies() + "  Type: "
					+ a.getTypes()[a.getType()] + "  Pet Number: " + currentOwner.getPets().indexOf(a) + "  Name: "
					+ a.getName() + "  Age : " + a.getAge() + "\nMedical History: " + a.medToString()
					+ "\nVaccinations: " + a.vacToString());
		}
	}

	/**
	 * My way of avoiding when the user inputs something other than an integer.
	 * Catches the {@link InputMismatchException}
	 * 
	 * @return A user inputted integer
	 */
	public int getInt() {
		if (input.hasNext("stop")) {
			System.out.println("***Terminated***");
			System.exit(0);
		}
		while (true) {
			try {
				int temp = input.nextInt();
				return temp;
			} catch (InputMismatchException e) {
				System.out.println("Input is not a valid integer!");
				input.next();
			}
		}
	}

}
