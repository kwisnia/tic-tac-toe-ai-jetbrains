package tictactoe;

import java.util.Scanner;

public class Main {
    static public final Scanner scanner = new Scanner(System.in);
    static Player player1 = null;
    static Player player2 = null;
    static Field field = new Field();
    public static void main(String[] args) {
        int h;
        int end = 0;
        if (menu() == -1) {
            return;
        }
        field.toString();
        game(field);

    }
    public static int menu() {
        player1 = null;
        player2 = null;
        String[] parameters;
        String command;
        String p1;
        String p2;
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
                        case "user":
                            player1 = new Human();
                            break;
                        case "easy":
                            player1 = new EasyAI();
                            break;
                        case "medium":
                            player1 = new MediumAI();
                            break;
                        default:
                            System.out.println("Bad parameters!");
                            break;
                    }
                    switch (parameters[2]) {
                        case "user":
                            player2 = new Human();
                            break;
                        case "easy":
                            player2 = new EasyAI();
                            break;
                        case "medium":
                            player2 = new MediumAI();
                            break;
                        default:
                            System.out.println("Bad parameters!");
                            break;
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
        int h = 0;
        do {
        while (true) {
            do {
                h = player1.move(field);
            } while (h == -1);
            System.out.println(field.toString());
            if (field.winCheck(Symbol.X)) {
                break;
            } else if (field.getXCount() + field.getYCount() == 9) {
                field.state();
                break;
            }
            do {
                h = player2.move(field);
            } while (h == -1);
            System.out.println(field.toString());
            if (field.winCheck(Symbol.O)) {
                break;
            }
        } } while (menu() != -1);
    }
}
