package test;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private List<Customer> customers;
	private int defaultNumberOfCustomers = 20;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	public Customer(String firstname, String lastname, String email, String password) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
	}
	
public Customer() {
	
}
public Customer(int numberOfCustomers) {
	defaultNumberOfCustomers= numberOfCustomers;
}

public String getFirstname() {
	return firstname;
}

public String getLastname() {
	return lastname;
}

public String getEmail() {
	return email;
}

public String getPassword() {
	return password;
}
public List<Customer> getCustomers(){
	if(this.customers==null) {
		customers = new ArrayList<>();
		for(int i=1; i<=defaultNumberOfCustomers; i++){
		customers.add(new Customer("Test","Test","test"+i+"@gmail.com","temp11!!"));
		}
	}
	return customers;
}


}
