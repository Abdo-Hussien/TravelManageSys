/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TravelManagement;

import java.util.Scanner;

/**
 *
 * @author bmood
 */
public abstract class Ticket {
    Scanner in = new Scanner(System.in);
    protected int No_Of_Tickets =0, added_tickets, removed_tickets;
    public static int total_tickets;
    public String Answer;
    public char choice;
    public boolean a= true;
    public float Ticket_Price(){
        return 0;
    }
    
    public void ticket_type(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Choose Your Ticket Type:");
        System.out.println("1. Silver Ticket(Regular Ticket) ");
        System.out.println("2. Gold Ticket ");
        System.out.println("3. Platinum Ticket ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice =='1') {
            Silver silver = new Silver();
            silver.silver_welcome();
            silver.setNo_Of_Tickets();
            silver.ticket_detalis();
        }   
            else if (choice =='2') {
               Gold gold = new Gold();
               gold.gold_welcome();
               gold.setNo_Of_Tickets();
               gold.ticket_detalis();  
            }
            else if (choice =='3') {
                Platinum platinum = new Platinum();
                platinum.platinum_welcome();
                platinum.setNo_Of_Tickets();
                platinum.ticket_detalis();
            }
            else{
                System.out.println("Invalid Number, Please Try Again.");
                ticket_type();   
            }    
    }       

    public void setNo_Of_Tickets() {
        System.out.println("How Many Tickets Do You Want To Book?");
        this.No_Of_Tickets =in.nextInt();
        Ticket.total_tickets +=this.No_Of_Tickets;
        System.out.println("Successfully Booked " + this.No_Of_Tickets + " Ticket(s)!" );
        edit_tickets();
    }

    public void add_more_tickets(){
        System.out.println("Want To Book More Tickets?(yes or no)");
        Answer = in.next();
        if (Answer.toLowerCase().equals("yes")) {
            System.out.println("How Many Tickets Do You Want To Add?");
            this.added_tickets +=in.nextInt();
            this.No_Of_Tickets += this.added_tickets;
            System.out.println("Successfully Added " + this.added_tickets + " Ticket(s)!" );
        }
        else if (Answer.toLowerCase().equals("no")) {
            edit_tickets();
        }
        else{
        System.out.println("Invalid Input!");
        add_more_tickets();
        }
        edit_tickets();
    }

    public void remove_tickets(){
        System.out.println("Want To Remove Tickets?(yes or no)");
        Answer = in.next();
        if (Answer.toLowerCase().equals("yes")) {
           do{
                System.out.println("How Many Tickets Do You Want To Remove?");
                this.removed_tickets=in.nextInt();
                if (this.removed_tickets <= this.No_Of_Tickets) {
                    this.No_Of_Tickets -= this.removed_tickets;
                    System.out.println("Successfully Removed " + this.removed_tickets + " Ticket(s)!" );
                    a=true;
                }
                else{
                    System.out.println("Cant Remove, Tickets Are Higher Than Expected, Please Try Again.");
                    a=false;  
                }
            }while(a==false);
        } 
        else if (Answer.equals("no")||Answer.equals("NO")||Answer.equals("No")) {
            edit_tickets();
        }
        else{
        System.out.println("Invalid Input!");
        remove_tickets();
        }
        edit_tickets();
    }

    public void confirm_tickets(){
        System.out.println("~~~~~~~~~~~");
        System.out.println("1. Confirm");   
        System.out.println("2. Go Back");
        System.out.println("~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice =='1') {
            System.out.println("Your Ticket(s) Has Been Confirmed Sucussfully!");
            System.out.println("Total Tickets: "+ Ticket.total_tickets);
        }
            else if (choice =='2') {
                edit_tickets();
            }
            else{
                System.out.println("Invalid Number, Please Try Again.");
                confirm_tickets();
            }
    }

    public void edit_tickets(){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Book More Tickets");
        System.out.println("2. Remove Booked Tickets");
        System.out.println("3. Book Another Ticket Type");
        System.out.println("4. Confirm Your Tickets");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice =='1') {
            add_more_tickets();
        }
            else if (choice =='2') {
                remove_tickets();
            }
            else if (choice =='3') {
                ticket_type();
            }
            else if (choice == '4') {
                confirm_tickets();
            }
        else{
            System.out.println("Invalid Number, Please Try Again.");
            edit_tickets();
        }
    }
}


