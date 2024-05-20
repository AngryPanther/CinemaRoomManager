package cinema;
import java.util.Scanner;

public class Cinema {
    static Scanner scanner = new Scanner(System.in);
    static final int LOW_PRICE = 8;
    static final int HIGH_PRICE = 10;
    static final int SEATING_THRESHOLD = 60;
    static int customerRow;
    static int customerSeat;
    static int profit = 0;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:"); // Acquire total rows and seats per row
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfSeats = scanner.nextInt();
        char[][] cinema = new char[numberOfRows][numberOfSeats];

        boolean exit = false;
        do {
            System.out.printf("%n");
            System.out.printf("1. Show the seats%n" +
                    "2. Buy a ticket%n" +
                    "3. Statistics%n" +
                    "0. Exit%n");
            int selection = scanner.nextInt();
            switch (selection) {
                case 1:
                    printSeats(cinema, numberOfSeats);
                    break;
                case 2:
                    buyTicket(cinema, numberOfRows, numberOfSeats);
                    break;
                case 3:
                    showStats(cinema, numberOfRows, numberOfSeats);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Please select 0, 1, 2, or 3.");
            }
        }
        while (!exit);
    }

    // Print Cinema seating arrangement
    private static void printSeats(char[][] cinema, int numberOfSeats) {
        char seatLabel = 'S';
        System.out.println();
        System.out.println("Cinema:");
        System.out.print("  ");

        for (int i = 0; i < numberOfSeats; i++) {
            System.out.printf(i + 1 + " ");
        }
        System.out.printf("%n");

        for (int j = 0; j < cinema.length; j++) {
            if (j > 0) {
                System.out.printf("%n");
            }
            System.out.print(j + 1 + " ");

            for (int k = 0; k < cinema[j].length; k++) {
                if (customerRow == 0 || customerSeat == 0) {
                    seatLabel = 'S';
                } else if (cinema[j][k] == cinema[customerRow][customerSeat]) {
                    seatLabel = 'B';
                }
                System.out.printf(seatLabel + " ");
                seatLabel = 'S';
            }
        }
        System.out.printf("%n");
    }

    // Buy Tickets
    private static void buyTicket(char[][] cinema, int numberOfRows, int numberOfSeats) {
        int totalSeats = numberOfRows * numberOfSeats;
        int ticketPrice;
        boolean validSeat = false;

        // Assert for invalid inputs such as seats that do not exist in the room or if the ticket has already been purchased
        // Keeping in mind customer input are off by 1 compared to array indexing
        while (!validSeat) {
            System.out.printf("%nEnter a row number:");
            customerRow = scanner.nextInt();
            System.out.printf("Enter a seat number in that row:%n");
            customerSeat = scanner.nextInt();

            if (customerRow-1 < 0 || customerSeat-1 < 0 || customerRow-1 > cinema.length-1 || customerSeat-1 > cinema[customerRow-1].length-1) {
                System.out.println("Wrong input!");

        } else if (cinema[customerRow-1][customerSeat-1] == 'B') {
                System.out.println("That ticket has already been purchased!");

            } else {
                validSeat = true;
                // Determine ticket price based on selection
                if (totalSeats <= SEATING_THRESHOLD || (double) customerRow / numberOfRows <= 0.5) {
                    ticketPrice = HIGH_PRICE;
                } else {
                    ticketPrice = LOW_PRICE;
                }
                System.out.println("Ticket price: $" + ticketPrice);
                customerRow--;
                customerSeat--;
                cinema[customerRow][customerSeat] = 'B';
                profit = profit + ticketPrice;
            }
        }
    }

    // Show statistics
    private static void showStats(char[][] cinema, int numberOfRows, int numberOfSeats) {
        int totalSeats = numberOfRows * numberOfSeats;
        int counter = 0;
        // Iterate over array to count how many seat are bought
        for (int j = 0; j < cinema.length; j++) {
            for (int k = 0; k < cinema[j].length; k++) {
                if (cinema[j][k] == 'B') {
                    counter++;
                }
            }
        }

        System.out.println("Number of purchased tickets: " + counter);
        double percentage = (double) counter / totalSeats * 100;
        System.out.printf("Percentage: %.2f", percentage);
        System.out.print("%");
        System.out.println();
        System.out.println("Current income: $" + profit);
        int income = 0;

        // Calculate total possible income earned at full capacity
        if (totalSeats <= 60) {
            income = 10 * totalSeats;
        } else {
            for (double a = 1; a <= numberOfRows; a++) {
                if (a / numberOfRows <= 0.5) {
                    income += 10 * numberOfSeats;
                } else {
                    income += 8 * numberOfSeats;
                }
            }
        }
        System.out.println("Total income: $" + income);
        
        
    }
}

