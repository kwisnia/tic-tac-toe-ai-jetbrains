package tictactoe;

import javafx.util.Pair;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HardAI implements Player {
    private final Symbol symbol;
    private int bestMoveX;
    private int bestMoveY;

    public HardAI(Symbol symbol) {
        this.symbol = symbol;
    }

    @Override
    public int move(Field field) {
        System.out.println("Making move level \"hard\"");
        minmax(field, false);
//        if (field.xIsNext() && symbol == Symbol.X) {
//            minmax(field, false);
//        } else {
//            minmax(field, true);
//        }
        field.setField(bestMoveX, bestMoveY);
        return 0;
    }
    private int minmax(Field field, boolean opponentMove) {
        Field newField = new Field(field);
        List<Pair<Integer, Integer>> emptySpots = field.getAllEmptySpots(field);
        List<Integer> moves = new ArrayList<>();
        if (field.winCheck(symbol.getOppositeSymbol())) {
            return -10;
        } else if (field.winCheck(symbol)) {
            return 10;
        } else if (emptySpots.isEmpty()) {
            return 0;
        }
        for (Pair<Integer, Integer> i : emptySpots) {
            newField.setField(i.getKey(), i.getValue());
            if (newField.xIsNext() && symbol == Symbol.X) {
                int moveScore = minmax(newField, false);
                moves.add(moveScore);
            } else if (!newField.xIsNext() && symbol == Symbol.O) {
                int moveScore = minmax(newField, false);
                moves.add(moveScore);
            }
            else {
                int moveScore = minmax(newField, true);
                moves.add(moveScore);
            }
            newField.unsetField(i.getKey(), i.getValue());
        }
        int bestMoveIndex;
        if (!opponentMove) {
            bestMoveIndex = moves.indexOf(Collections.max(moves));
        } else {
            bestMoveIndex = moves.indexOf(Collections.min(moves));
        }
        bestMoveX = emptySpots.get(bestMoveIndex).getKey();
        bestMoveY = emptySpots.get(bestMoveIndex).getValue();
        return moves.get(bestMoveIndex);
    }
}
