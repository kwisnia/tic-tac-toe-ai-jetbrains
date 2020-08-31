package tictactoe;

public class MediumAI implements Player {
    @Override
    public int move(Field field) {
        System.out.println("Making move level \"medium\"");
        if (!field.endingMoveCheck(true)) {
            if (!field.endingMoveCheck(false)) {
                moveRandomly(field);
            }
        }
       return 0;
    }
}
