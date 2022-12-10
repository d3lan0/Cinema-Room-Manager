package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        Seating seating = new Seating(rows, seats);

        while (true) {
            System.out.println("1. Show the seats " +
                    "\n2. Buy a ticket" +
                    "\n3. Statistics" +
                    "\n0. Exit");
            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    seating.printCinemaSeatingChart();
                    break;
                case 2:
                    seating.buyTicket(scanner, seating);
                    break;
                case 3:
                    System.out.println(seating);
                    break;
                case 0:
                    return;
                default:
            }
        }

    }
}