
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
public class Trial {
    public static void main(String args[]) {
         final int month_in_year = 12;
         final int percent = 100;
         
         Scanner input = new Scanner(System.in);
         int principal;
         System.out.print("Principal: ");
         while(true)
         {
         principal = input.nextInt();
             if(principal>= 1000 && principal <= 1000000)
            {
             break;
            }
            System.out.print("Enter a principal between 1,000 and 1,000,000: ");
         }
         
         double annual_interest;
         double monthly_interest;
         System.out.print("Annual Interest Rate(Number as a Percent without percent symbol): ");
         
         while(true)
         {
          annual_interest = input.nextDouble();
         if(annual_interest > 0 && annual_interest <= 30)
         {
             break;
         }
         System.out.print("Enter a annual interest rate between 0 and 30: ");
         }
         monthly_interest = annual_interest/percent/month_in_year;
         
         
         System.out.print("Period(Years): ");
         double years;
         while(true)
         {
             years = input.nextInt();
             if(years > 0 && years <= 30)
             {
                 break;
             }
             System.out.print("Enter a period of years between 0 and 30: ");
         }
         double amount_of_monthly_payments = years * month_in_year;
         
         double mortgage = principal * (monthly_interest * Math.pow(1 + monthly_interest, amount_of_monthly_payments)/(Math.pow(1 + monthly_interest, amount_of_monthly_payments) - 1));
         System.out.println("Mortgage: " + mortgage);
         //needs to be monthly interest in this specific formula to get out monthly payment. 
    }
}


//Scanner format
//type identifier = new type();

//compare strings use name.equals()

//compare primitives use name == name

//Comparision operators: used for numbers (primitaves)
// == equality (equals)
// != inequality (not equals)
// <= less than or equal to
// >= greater than or equal to

//Logical operators: used for string 
// && and (both need to be true)
// || or (either can be true)
// ! not (inverse the boolean)

//  icu: (loops) while loop, do while loop, for loop (most people use integer i)
// initialization
// comparison
// update

