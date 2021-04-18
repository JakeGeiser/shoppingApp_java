package CartApp;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {

		// create ArrayList to store available products - class defined below
		ArrayList<product> productsList = new ArrayList<product>();
		
		// Add products to productsList
		productsList.add(new product("Apple",0.60));
		productsList.add(new product("Coke",1.25));
		productsList.add(new product("Ice",2.00));
		productsList.add(new product("Carrot",0.30));
		productsList.add(new product("Chicken",6.95));
		productsList.add(new product("Steak",15.50));
		productsList.add(new product("Jello",1.00));
		productsList.add(new product("Rice",8.99));
		productsList.add(new product("Lettuce",2.70));
		productsList.add(new product("Poptart",1.00));
		
		// order the products by name
		productsList.sort((o1,o2) -> o1.name.compareTo(o2.name));
		
		// print the current inventory
		printInv(productsList);
		
		// Start Scanner
		Scanner myScan = new Scanner(System.in);
		
		
		// initialize shopping variables
		int productID = 0;
		int productQty = 0;
		
		
		// Start shopping! While loop keeps asking for input
		System.out.println("If done, type (-1) for product id.");
		do {
			// get product selection
			System.out.println("Product to purchase (id): ");
			productID = myScan.nextInt();
			myScan.nextLine();
			
			if (productID == -1) { // if done shopping
				break;
			}
			
			// get quantity input
			System.out.println("How many would you like: ");
			try {
				productQty = myScan.nextInt();
				myScan.nextLine();
				if(productQty >= 0) {
					productsList.get(productID).qty += productQty; // add quantity to object
				}
				else {
					System.out.println("Invalid Quantity... Current product selection canceled.");
					continue;
				}
			} catch (Exception e){
				System.out.println("Be sure you used an appropriate productID");
				throw e;
			}
		} while(true);
		
		
		System.out.println(""); // create readability buffer
		
		
		// initialize delivery carriers
		ArrayList<carrier> carrierList = new ArrayList<carrier>();
		carrierList.add(new carrier("FedEx"));
		carrierList.add(new carrier("UPS"));
		
		// print out all carriers options
		Iterator<carrier> itrCarrier = carrierList.iterator();
		System.out.println("Availabel delivery partners:");
		int cCounter = 0;
		while(itrCarrier.hasNext()) {
			System.out.println(itrCarrier.next().name+"("+cCounter+")");
			cCounter++;
		}
		// get delivery carrier choice
		System.out.println("Enter delivery choice (ID): ");
		int carrierChoice = myScan.nextInt();
		myScan.nextLine();
		
		
		// Add readability buffer
		System.out.println();
		

		// Ask for payment method
		System.out.println("Choose Payment Method: VISA or Mastercard");
		String paymentChoice = myScan.nextLine();
		
		// close out Scanner
		myScan.close();
		
		
		// Add readability buffer
		System.out.println();
		
		
		// get cart total and print out cart
		double total = printCart(productsList);
		System.out.println("Your total comes out to: $"+total);
		
		if (total >= 500.0) {
			if (paymentChoice.equals("Mastermind") || paymentChoice.equals("mastermind")) {
				System.out.println("Thank you for shopping with us, here is a $20 gift card!");
			}
			else if (paymentChoice.equals("VISA") || paymentChoice.equals("visa")) {
				System.out.println("Thank you for shopping with us, here is a $10 gift card!");
			}
			else {
				System.out.println("Thank you for shopping with us!");
			}
		}
		else {
			System.out.println("Thank you for shopping with us!");
		}

		// Add readability buffer
		System.out.println();
		System.out.println();
		System.out.println();
				
				
		// delivery status checks (can imagine tracking the delivery and checking status)
		carrierList.get(carrierChoice).status();
		carrierList.get(carrierChoice).status();
		carrierList.get(carrierChoice).status();
		carrierList.get(carrierChoice).status();
		carrierList.get(carrierChoice).status();
		carrierList.get(carrierChoice).status();
		carrierList.get(carrierChoice).status();
		carrierList.get(carrierChoice).status();
		carrierList.get(carrierChoice).status();
		

		
	}
	
	// create method for printing out products and their prices
	static void printInv(ArrayList<product> input) {		
		Iterator<product> itr = input.iterator();
		System.out.println("Availabel Products:");
		System.out.println("<Name>(<ID>) for <Price>");
		int counter = 0;
		while(itr.hasNext()) {
			product tempProd = itr.next();
			System.out.println(tempProd.name+"("+counter+") for $"+tempProd.price);
			counter++;
		}
	}
	
	// create method for printing out cart and finding total
	static double printCart(ArrayList<product> input) {		
		Iterator<product> itr = input.iterator();
		System.out.println("Chosen Products:");
		System.out.println("<Quantity> of <Name>(<ID>) for <Price>");
		int counterCart = 0;
		double totalCost = 0;
		while(itr.hasNext()) {
			product tempProd = itr.next();
			if (tempProd.qty > 0) { // only print if some of product has been chosen
				System.out.println(tempProd.qty+" of "+tempProd.name+" for $"+tempProd.price*tempProd.qty);
				totalCost += tempProd.price*tempProd.qty;
				counterCart++;
			}
		}
		return totalCost;
	}
	
}


//create product class for creating which products are stocked
class product {
	
	String name;
	double price;
	int qty = 0;
	
	product(String name, double price) {
		this.name = name;
		this.price = price;
	}
}

class carrier implements SafetyStandards {
	String name;
	int statusCheck = 0;
	
	// add constructors
	carrier(){
		
	}
	carrier(String name) {
		this.name = name;
	}
	
	// add status method
	void status() {
		this.statusCheck++;
		if (this.statusCheck == 1) {
			System.out.println("Waiting for pickup by "+this.name);
		}
		else if (this.statusCheck <= 4) {
			System.out.println(this.name+" is out delivery");
		}
		else if (this.statusCheck == 5) {
			carrier temp = new carrier();
			temp.applySafetyMeasures();
		}
		else {
			System.out.println(this.name+" has delivered your order");
		}
		
	}
	
	@Override
	public void applySafetyMeasures() {
		System.out.println("Applying appropriate safety measures...");
	}
}
