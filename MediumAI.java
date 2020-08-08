package tictactoe;

public class MediumAI implements Player {
    private boolean canLose(Field field) {
        return field.check(false) != null;
    }
    private boolean canWin(Field field) {
        return field.check(true) != null;
    }
    private void tryWin(Field field) {
        Symbol nextMove = field.check(true);
        field.setSymbol(nextMove);
    }
    private void tryDefend(Field field) {
        Symbol nextMove = field.check(false);
        field.setSymbol(nextMove);
    }
    @Override
    public int move(Field field) {
        System.out.println("Making move level \"medium\"");
        if (canWin(field)) {
            tryWin(field);
        } else if (canLose(field)) {
            tryDefend(field);
        } else {
            moveRandomly(field);
        }
        return 0;
    }
}
