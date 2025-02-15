package lastpencil;

import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //initial values
        int pencils;
        int removePencils = 0;

        //beginning of game
        System.out.println("How many pencils would you like to use:");
        pencils = checkStartPencils();
        System.out.println("Who will be the first (John, Jack):");
        String Player = checkPLayer();

        //loop do-while
        do {
            printPencils(pencils);
            //playerTurn(Player);
            if (Player.equals("John")) {
                System.out.print(Player + "`s turn!" + '\n');
                removePencils = userMove(pencils);
            } else if (Player.equals("Jack")){
                System.out.print(Player + "`s turn:" + '\n');
                removePencils = botMove(pencils);
                System.out.println(removePencils);
            }
            pencils -= removePencils;
            Player = changePlayerTurn(Player);
        } while (pencils > 0);
        printWinner(Player);

        scanner.close();
    }

    private static int userMove(int pencils) {
        int num;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                String userIn = scanner.next();
                num = Integer.parseInt(userIn);
                if (num <=0 || num > 3 ) {
                    System.out.println("Possible values: '1', '2' or '3'");
                } else if (num > pencils) {
                    System.out.println("Too many pencils were taken");
                } else {
                    return num;
                }
            } catch (NumberFormatException e) {
                System.out.println("Possible values: '1', '2' or '3'");
            }
        } while(true);
    }

    private static int botMove(int pencils){
        //winning bot position
        if ((pencils + 2) % 4 == 0) {
            return 1;
        } else if ((pencils + 1) % 4 == 0) {
            return 2;
        } else if (pencils % 4 == 0) {
            return 3;
        }
        //losing bot position
        int randomNum;
        if (pencils == 1){
            return 1;
        } else if ((pencils + 3) % 4 == 0) {
            randomNum = randomNum();
            return randomNum;
        }
        return 0;
    }

    private static int randomNum() {
        Random num = new Random();
        return num.nextInt(3)+1;
    }

    private static String changePlayerTurn(String name) {
        if (name.equals("John")) {
            name = "Jack";
        } else if (name.equals("Jack")) {
            name = "John";
        }
        return name;
    }

    private static void printWinner(String player) {
        String name1 = "John";
        String name2 = "Jack";
        String winner = name1.equals(player) ? name1 : name2;
        System.out.printf("%s won!",winner);
    }

    private static int checkStartPencils() {
        int startPen = 0;
        boolean key = true;
        do {
            try {
                Scanner scanner = new Scanner(System.in);
                String userIn = scanner.nextLine();
                if(userIn.isBlank()) {throw new NumberFormatException();}
                startPen = Integer.parseInt(userIn);
                if (startPen <= 0) {
                    System.out.println("The number of pencils should be positive");
                    continue;
                }
                key = false;
            } catch (NumberFormatException e) {
                System.out.println("The number of pencils should be numeric");
            }
        } while(key);
        return startPen;
    }

    private static String checkPLayer() {
        Scanner scanner = new Scanner(System.in);
        String name1 = "John";
        String name2 = "Jack";
        do {
            String userIn = scanner.next();
            if (!(name1.equals(userIn) || name2.equals(userIn))) {
                System.out.print("Choose between" + name1 + " and " + name2 + '\n');
                continue;
            }
            return userIn;
        } while (true);
    }

    private static void printPencils(int pencils) {
        for (int p = 1; p <= pencils; p++) {
            System.out.print('|');
        }
        System.out.println();
    }
}
