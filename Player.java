package tictactoe;

import java.util.Random;

interface Player {
    int move(Field field);
    default void moveRandomly(Field field) {
        Random r = new Random();
        int x = 0;
        int y = 0;
        do {
            x = r.nextInt(3);
            y = r.nextInt(3);
        } while (!field.isFree(x, y));
        field.setField(x, y);
    }
}
