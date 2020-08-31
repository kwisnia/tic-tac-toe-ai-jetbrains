package tictactoe;

import java.util.Scanner;

public class Main {
    static public final Scanner scanner = new Scanner(System.in);
    static Player player1 = null;
    static Player player2 = null;
    static Field field = new Field();
    public static void main(String[] args) {
        if (menu() == -1) {
            return;
        }
        System.out.println(field.toString());
        game(field);

    }
    public static int menu() {
        player1 = null;
        player2 = null;
        String[] parameters;
        String command;
        do {
            System.out.print("Input command: ");
            command = scanner.nextLine();
            command = command.toLowerCase();
            parameters = command.split(" ");
            switch (parameters[0]) {
                case "start":
                    if (parameters.length < 3) {
                        System.out.println("Bad parameters!");
                        break;
                    }
                    switch (parameters[1]) {
                        case "user" -> player1 = new Human();
                        case "easy" -> player1 = new EasyAI();
                        case "medium" -> player1 = new MediumAI();
                        case "hard" -> player1 = new HardAI(Symbol.X);
                        default -> System.out.println("Bad parameters!");
                    }
                    switch (parameters[2]) {
                        case "user" -> player2 = new Human();
                        case "easy" -> player2 = new EasyAI();
                        case "medium" -> player2 = new MediumAI();
                        case "hard" -> player2 = new HardAI(Symbol.O);
                        default -> System.out.println("Bad parameters!");
                    }
                    break;
                case "exit":
                    return -1;
                default:
                    System.out.println("Bad parameters!");
                    break;
            }
        } while (player1 == null || player2 == null);
        return 0;
    }
    public static void game(Field field) {
        int h;
        do {
        while (true) {
            do {
                h = player1.move(field);
            } while (h == -1);
            System.out.println(field.toString());
            if (field.winCheck(Symbol.X)) {
                field.printWinMessage(Symbol.X);
                field.clear();
                break;
            } else if (field.getXCount() + field.getYCount() == 9) {
                System.out.println("Draw");
                field.clear();
                break;
            }
            do {
                h = player2.move(field);
            } while (h == -1);
            System.out.println(field.toString());
            if (field.winCheck(Symbol.O)) {
                field.printWinMessage(Symbol.O);
                field.clear();
                break;
            }
        } } while (menu() != -1);
    }
}
