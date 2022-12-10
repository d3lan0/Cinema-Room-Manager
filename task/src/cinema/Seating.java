package cinema;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Seating {
    final int smallRoomTicketCost = 10;
    private final int bigRoomTicketCost = 8;
    private int rows;
    private int seats;
    private int totalSeatCount;
    private int purchasedTickets;
    private int currentIncome;
    private int totalProfit;
    private String[][] cinemaSeatingChart;
    private String[] header;


    public Seating(int rows, int seats) {
        this.purchasedTickets = 0;
        this.currentIncome = 0;
        this.rows = rows;
        this.seats = seats;
        this.totalSeatCount = rows * seats;
        this.cinemaSeatingChart = new String[rows][seats + 1];
        this.header = new String[seats + 1];
        setTotalProfit();
        createSeatingChart();
    }

    public void sellSeat(int rowNumber, int seatNumber) {
        cinemaSeatingChart[rowNumber - 1][seatNumber] = " B";

        if (this.totalSeatCount > 60 &&
                //not sure if int division on odd number rounds in the right direction. (should test)
                rowNumber > (int) Math.floor(this.rows / 2)) {
            this.purchasedTickets++;
            this.currentIncome += this.bigRoomTicketCost;
            System.out.println("Ticket price: $8");
        } else {
            this.purchasedTickets++;
            this.currentIncome += this.smallRoomTicketCost;
            System.out.println("Ticket price: $10");
        }
    }

    public void printCinemaSeatingChart() {
        System.out.println("Cinema:");
        //print header
        for (int k = 0; k < this.header.length; k++) {
            System.out.print(header[k]);
        }
        System.out.println("");

        //print 2d array
        for (int i = 0; i < cinemaSeatingChart.length; i++) {
            System.out.print(i + 1);
            for (int j = 1; j < cinemaSeatingChart[i].length; j++) {
                System.out.print(cinemaSeatingChart[i][j]);
            }
            System.out.print("\n");
        }
    }

    private void createSeatingChart() {
        //fill header
        header[0] = " ";
        for (int i = 1; i < header.length; i++) {
            header[i] = " " + i;
        }
        //fill 2d array
        for (String[] strings : this.cinemaSeatingChart) {
            Arrays.fill(strings, " S");
        }
    }

    private void setTotalProfit() {
        if (this.totalSeatCount <= 60) {
            this.totalProfit = this.totalSeatCount * smallRoomTicketCost;
        } else {
            int half = (int) Math.floor(this.rows / 2);
            int firstHalfProfits = (half * this.seats) * smallRoomTicketCost;
            int secondHalfProfits = ((rows - half) * this.seats) * bigRoomTicketCost;
            this.totalProfit = firstHalfProfits + secondHalfProfits;
        }
    }

    public void buyTicket(Scanner scanner, Seating seating) {

        int rowNumber;
        int seatNumber;

        while (true) {
            System.out.println("Enter a row number: ");
            rowNumber = scanner.nextInt();

            System.out.println("Enter a seat number in that row: ");
            seatNumber = scanner.nextInt();

            if (rowNumber < 1 || rowNumber > this.rows
                    || seatNumber < 1 || seatNumber > this.seats) {
                System.out.println("Wrong input!");
                continue;
            }

            String seat = cinemaSeatingChart[rowNumber - 1][seatNumber];
            if (seat.equals(" B")) {
                System.out.println("That ticket has already been purchased!");
                continue;
            }

            break;
        }
        seating.sellSeat(rowNumber, seatNumber);
    }

    public String toString() {
        final DecimalFormat df = new DecimalFormat("0.00");
        double percentage = (1.00 * this.purchasedTickets) / this.totalSeatCount * 100.00;
        System.out.println(percentage);
        return "Number of purchased tickets: " + this.purchasedTickets +
                "\nPercentage: " + df.format(percentage) + "%" +
                "\nCurrent income: $" + this.currentIncome +
                "\nTotal income: $" + this.totalProfit;
    }
}
