package carRentalSystem;

// in this class we will store the the data of the customers, cars and the the customer rented which car
import java.util.*;
public class carRentalSystem {
	private List<Car> cars;
	private List<Customer> customers ;
	private List<Rental> rental;
	
	public carRentalSystem() {
		cars = new ArrayList<>();
		customers = new ArrayList<>();
		rental = new ArrayList<>();
	}
	
	//creating  a method to add car objects
	public void addCar(Car car) {
		cars.add(car); // cars is the name of the object and we are adding it in the arrayList
	}
	
	public void addCustomer(Customer customer) {
		customers.add(customer);
	}
	
	//creating a rent car method where the details of the customer and which car he has rented all that details will be present
	public void rentCar(Car car, Customer customer,int days) {
		if(car.isAvailable()) {
			car.rent();
			rental.add(new Rental(car, customer, days));
		}else {
			System.out.println("The car is not available for rent");
		}
	}
	
	public void returnCar(Car car){
		car.returnCar();
		Rental rentalToRemove = null;
		for(Rental rentedCar : rental) {
			if(rentedCar.getCar() == car) {
				rentalToRemove = rentedCar;
				break;
			}
		}
		
		if(rentalToRemove!=null) {
			rental.remove(rentalToRemove);
			System.out.println("Car returned Successfully");
		}else {
			System.out.println("Car was not rented");
		}
	}
	
	public void menu() {
		Scanner sc = new Scanner(System.in);
		
		while(true) { 
			System.out.println("===== Car rental System =====");
			System.out.println("1. Rent a car");
			System.out.println("2. Return a car");
			System.out.println("3. Exit");
			System.out.println("4. Enter your choice");
			
			int choice = sc.nextInt();
			sc.nextLine();
			
			if(choice == 1) {
				System.out.println("Rent a car");
				System.out.println("Enter your name");
				String customerName = sc.nextLine();
				
				System.out.println("===== The list of available cars =====");
				for(Car car : cars) {
					if(car.isAvailable()) {
						System.out.println(car.getCarId() + " - " + car.getBrand() + " - " + car.getModel());
					}
				}
				System.out.println("\nEnter the car ID that you want to rent");
				String carId = sc.nextLine();
				
				System.out.println("Enter the number of days for the rental: ");;
				int rentDays = sc.nextInt();
				
				Customer newCustomer = new Customer("CUS" + (customers.size() + 1),customerName);
				addCustomer(newCustomer);
				
				//checking if selected car is present or not
				Car selectedCar = null;
				
				for(Car car :cars) {
					if(car.getCarId().equals(carId) && car.isAvailable()) {
						selectedCar = car;
						break;
					}
				}
				
				if(selectedCar!=null) { // means car is present in our list
					double totalPrice = selectedCar.calculatePrice(rentDays);
					System.out.println("\n== Rental Information ==\n");
                    System.out.println("Customer ID: " + newCustomer.getCustomerId());
                    System.out.println("Customer Name: " + newCustomer.getName());
                    System.out.println("Car: " + selectedCar.getBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days: " + rentDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);
					
					System.out.println("\nConfirm Rental (Y/N)\n ");
					//String confirm = sc.nextLine();
					String confirm = sc.next();
					
					if(confirm.equalsIgnoreCase("Y")) {
						rentCar(selectedCar,newCustomer,rentDays);
						System.out.println("\nCar rented successfully\n");
					}else {
						System.out.println("Rental cancelled");
					}
					
				}
				else {
					System.out.println("\nInvalid car selected or the car is not availbale for rent\n");
					//System.out.println("Check the database manually "); 
				}
			}else if(choice == 2) {
				System.out.println("\n===Return a car===\n");
				System.out.println("Enter the id you want to return: ");
				
				String carId = sc.nextLine();
				
				Car carToReturn = null;
				for(Car car : cars) {
					if(car.getCarId().equals(carId) &&!car.isAvailable()) {
						carToReturn = car;
						break;
					}
				}
				if (carToReturn != null) {
                    Customer customer = null;
                    for (Rental rental : rental) {
                        if (rental.getCar() == carToReturn) {
                            customer = rental.getCustomer();
                            break;
                        }
                    }
                    if (customer != null) {
                        returnCar(carToReturn);
                        System.out.println("Car returned successfully by " + customer.getName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing.");
                    }
                } else {
                    System.out.println("Invalid car ID or car is not rented.");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option.");
            }
        }

        System.out.println("\nThank you for using the Car Rental System!");
    }		
		
}
	

