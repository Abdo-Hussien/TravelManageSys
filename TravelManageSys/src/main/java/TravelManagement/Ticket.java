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
public class Ticket {
    Scanner in = new Scanner(System.in);
    protected int No_Of_Tickets, added_tickets, removed_tickets, count_silver, count_gold, count_platinum, total;
    public String Answer;
    public char choice;
    public boolean a = true;

    public double Ticket_Price() {
        return 0;
    }

    public void ticket_type() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("Choose Your Ticket Type:");
        System.out.println("1. Silver Ticket(Regular Ticket) ");
        System.out.println("2. Gold Ticket ");
        System.out.println("3. Platinum Ticket ");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice == '1') {
            Silver silver = new Silver();
            silver.silver_welcome();
            setNo_Of_Tickets();
            add_more_tickets();
            remove_tickets();
            this.count_silver = this.No_Of_Tickets;
        } else if (choice == '2') {
            Gold gold = new Gold();
            gold.gold_welcome();
            setNo_Of_Tickets();
            add_more_tickets();
            remove_tickets();
            this.count_gold = this.No_Of_Tickets;
        } else if (choice == '3') {
            Platinum platinum = new Platinum();
            platinum.platinum_welcome();
            setNo_Of_Tickets();
            add_more_tickets();
            remove_tickets();
            this.count_platinum = this.No_Of_Tickets;
        } else {
            System.out.println("Invalid Number, Please Try Again.");
            ticket_type();
        }
        edit_tickets();
    }

    public void setNo_Of_Tickets() {
        System.out.println("How Many Tickets Do You Want To Book?");
        this.No_Of_Tickets = in.nextInt();
        System.out.println("Successfully Booked " + this.No_Of_Tickets + " Ticket(s)!");
        total += this.No_Of_Tickets;
    }

    public void add_more_tickets() {
        System.out.println("Want To Book More Tickets?(yes or no)");
        Answer = in.next();
        if (Answer.toLowerCase().equals("yes")) {
            System.out.println("How Many Tickets Do You Want To Add?");
            this.added_tickets = in.nextInt();
            this.No_Of_Tickets += this.added_tickets;
            this.total += this.added_tickets;
            System.out.println("Successfully Added " + this.added_tickets + " Ticket(s)!");
        } else if (Answer.toLowerCase().equals("no")) {

        } else {
            System.out.println("Invalid Input!");
            add_more_tickets();
        }
    }

    public void remove_tickets() {
        System.out.println("Want To Remove Tickets?(yes or no)");
        Answer = in.next();
        if (Answer.toLowerCase().equals("yes")) {
            do {
                System.out.println("How Many Tickets Do You Want To Remove?");
                this.removed_tickets = in.nextInt();
                if (this.removed_tickets <= this.No_Of_Tickets) {
                    this.No_Of_Tickets -= this.removed_tickets;
                    this.total -= this.removed_tickets;
                    System.out.println("Successfully Removed " + this.removed_tickets + " Ticket(s)!");
                    a = true;
                } else {
                    System.out.println("Cant Remove, Tickets Are Higher Than Expected, Please Try Again.");
                    a = false;
                }
            } while (a == false);
        } else if (Answer.toLowerCase().equals("no")) {

        } else {
            System.out.println("Invalid Input!");
            remove_tickets();
        }
    }

    public void confirm_tickets() {
        System.out.println("~~~~~~~~~~~");
        System.out.println("1. Confirm");
        System.out.println("2. Go Back");
        System.out.println("~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice == '1') {
            System.out.println("Your Ticket(s) Has Been Confirmed Sucussfully!");
            System.out.println("You Booked:");
            System.out.println(this.count_silver + " Silver Tickets(Regular)");
            System.out.println(this.count_gold + " Gold Tickets");
            System.out.println(this.count_platinum + " Platinum Tickets");
            System.out.println("Total Tickets: " + total);
        } else if (choice == '2') {
            edit_tickets();
        } else {
            System.out.println("Invalid Number, Please Try Again.");
            confirm_tickets();
        }
    }

    public void edit_tickets() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("1. Book Another Ticket Type");
        System.out.println("2. Confirm Your Tickets");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~");
        choice = in.next().charAt(0);
        if (choice == '1') {
            ticket_type();
        } else if (choice == '2') {
            confirm_tickets();
        } else {
            System.out.println("Invalid Number, Please Try Again.");
            edit_tickets();
        }
    }

}
