package carRentalSystem;

public class Customer {
	private String name;
	private String customerId ;
	
	Customer(String name,String customerId){
		this.name = name;
		this.customerId = customerId;
	}
	
	//creating the getters to access the name and customerId
	public String getName() {
		return name;
	}
	public String getCustomerId(){
		return customerId;
	}
}
