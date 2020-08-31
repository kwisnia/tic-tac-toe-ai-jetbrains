package tictactoe;


import java.util.Scanner;

public class Human implements Player {
    public int move(Field field) {
        Scanner scanner = new Scanner(System.in);
        String xs;
        String ys;
        int x;
        int y;
        System.out.print("Enter the coordinates: ");
        try {
            xs = scanner.next();
            x = Integer.parseInt(xs);
            ys = scanner.next();
            y = Integer.parseInt(ys);
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers!");
            return -1;
        }
        if (x > 3 || x < 1 || y > 3 || y < 1) {
            System.out.println("Coordinates should be from 1 to 3!");
            return -1;
        }
        if (!field.isFree(x, y)) {
            System.out.println("This cell is occupied! Choose another one!");
            return -1;
        }
        field.setField(x, y);
        return 0;
    }
}
