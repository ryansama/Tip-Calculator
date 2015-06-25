import java.io.*;
import java.util.*;
public class TipCalculator {
	//main() to instantiate Scanner and call displayPrices()
	public static void main(String[]args){
		Scanner userInput = new Scanner(System.in);
		double tipPercent = getTipPercent(userInput);
		int bill = getBill(userInput);
		displayPrices(bill, tipPercent);
	}

	/**
	*Get user input of tip (in %) and attempt
	*parsing to an int. The user will be prompted
	*to specify again if the input was invalid.
	*@param Scanner object to scan user's input
	*@return tip percent as a double
	*/
	private static double getTipPercent(Scanner sc){
		int tipPercent;
		double tip;

		while(true){
			System.out.print("Please specify tip (%): ");
			String tipStr = sc.next();
			try{
				tipPercent = Integer.parseInt(tipStr);
				break;
			}catch(NumberFormatException e){
				System.out.print("Please specift a valid tip (%) amount: ");
			}
		}

		tip = (double) tipPercent/100;
		return tip;
	}

	/**
	*Get user input of bill and attempt
	*parsing to an int. The user will be prompted
	*to specify again if the input was invalid.
	*@param Scanner object to scan user's input
	*@return bill
	*/
	private static int getBill(Scanner sc){
		int bill = 0;

		while(true){
			System.out.print("Please specify bill total: $");
			String billStr = sc.next();
			try{
				bill = Integer.parseInt(billStr);
				break;
			}catch(NumberFormatException e){
				System.out.print("Please specify a valid bill amount: $");
			}

		}

		return bill;
	}

	/**
	*Calculate total by multiplying bill
	*with (1+tip).
	*/
	private static void displayPrices(int bill, double tip){
		System.out.println("--------------------------------------------");
		System.out.println("Total: $" + (bill * (1+tip)));
	}
}
