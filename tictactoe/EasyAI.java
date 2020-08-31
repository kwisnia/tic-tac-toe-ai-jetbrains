package tictactoe;

import java.util.Random;

public class EasyAI implements Player {
    public int move(Field field) {
            System.out.println("Making move level \"easy\"");
            moveRandomly(field);
            return 0;
    }
}
