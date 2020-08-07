package tictactoe;

import java.util.Random;

public class MediumAI implements Player {
    public int tryDefend(Field field) {
        return 0;
    }
    public int tryWin(Field field) {
        return 0;
    }
    @Override
    public int move(Field field) {
        int[] pom = field.check();
        System.out.println("Making move level \"medium\"");
        if(pom == null) {
            moveRandomly(field);
        } else {
            field.setField(pom[0], pom[1]);
        }
        return 0;
    }
}
