package tictactoe;
import javafx.util.Pair;

import java.util.List;
import java.util.ArrayList;

public class Field {
    private static final Symbol[][] field = new Symbol[3][3];
    private static int xCount = 0;
    private static int oCount = 0;

    public Field() {
        for (Symbol[] row : field) {
            java.util.Arrays.fill(row, Symbol.EMPTY);
        }
    }

    boolean isFree(int x, int y) {
        return field[x][y] == Symbol.EMPTY;
    }

    public int getXCount() {
        return xCount;
    }

    public int getYCount() {
        return oCount;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("---------");
        stringBuilder.append(System.lineSeparator());
        for (int i = 0; i < 3; i++) {
            stringBuilder.append("| ");
            for (int j = 0; j < 3; j++) {
                stringBuilder.append(field[i][j].getSymbol()).append(" ");
            }
            stringBuilder.append('|');
            stringBuilder.append(System.lineSeparator());
        }
        stringBuilder.append("---------");
        return stringBuilder.toString();
    }

    public void setField(int x, int y) { // x = y, y = x - 1
        if (xCount == oCount) {
            field[x][y] = Symbol.X;
            xCount++;
        } else {
            field[x][y] = Symbol.O;
            oCount++;
        }
    }

    // =====================================
    // CHECKING FOR WINNING COMBINATIONS
    // =====================================
    private boolean rowCheck(Symbol symbol) {
        for (int i = 0; i < 3; i++) {
            if (field[i][0] == symbol && field[i][1] == symbol && field[i][2] == symbol) {
                return true;
            }
        }
        return false;
    }

    private boolean columnCheck(Symbol symbol) {
        for (int i = 0; i < 3; i++) {
            if (field[0][i] == symbol && field[1][i] == symbol && field[2][i] == symbol) {
                return true;
            }
        }
        return false;
    }

    private boolean diagonalCheck(Symbol symbol) {
        return field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol
                || field[2][0] == symbol && field[1][1] == symbol && field[0][2] == symbol;
    }

    public boolean winCheck(Symbol symbol) {
        if (rowCheck(symbol) || columnCheck(symbol) || diagonalCheck(symbol)) {
            System.out.println(symbol + " wins");
            return true;
        }
        return false;
    }

    // =====================================
    // CHECKING FOR MOVES THAT CAN END THE GAME (2 in a row)
    // =====================================
    public boolean endingMoveCheck(boolean attack) {
        Symbol targetSign;
        //
        if (attack) {
            if (xCount == oCount) {
                targetSign = Symbol.X;
            } else {
                targetSign = Symbol.O;
            }
        } else {
            if (xCount == oCount) {
                targetSign = Symbol.O;
            } else {
                targetSign = Symbol.X;
            }
        }
        int[] endingMoveCoordinates; // this array is going to hold coordinates
        // where to defend / attack (depending on attack value)


        // If a spot that could end the game was found, the function
        // makes the move in that place to defend / attack and returns true to signal
        // the AI that it
        // should end the move.
        // If the checked row didn't have such a place,
        // it moves on to another loop iteration. This process is repeated for every
        // row, column and diagonal
        // until it finds that place. If the place isn't found, the function returns
        // false.

        for (int i = 0; i < 3; i++) {
            endingMoveCoordinates = getEndingMovePosition(new int[] { i, 0 }, new int[] { i, 1 }, new int[] { i, 2 },
                    targetSign);

            if (endingMoveCoordinates != null) {
                setField(endingMoveCoordinates[0], endingMoveCoordinates[1]);
                return true;
            }
        }
        // column check
        for (int i = 0; i < 3; i++) {
            endingMoveCoordinates = getEndingMovePosition(new int[] { 0, i }, new int[] { 1, i }, new int[] { 2, i },
                    targetSign);
            if (endingMoveCoordinates != null) {
                setField(endingMoveCoordinates[0], endingMoveCoordinates[1]);
                return true;
            }
        }

        // diagonal check
        endingMoveCoordinates = getEndingMovePosition(new int[] { 0, 0 }, new int[] { 1, 1 }, new int[] { 2, 2 },
                targetSign);
        if (endingMoveCoordinates != null) {
            setField(endingMoveCoordinates[0], endingMoveCoordinates[1]);
            return true;
        }
        endingMoveCoordinates = getEndingMovePosition(new int[] { 2, 0 }, new int[] { 1, 1 }, new int[] { 0, 2 },
                targetSign);
        if (endingMoveCoordinates != null) {
            setField(endingMoveCoordinates[0], endingMoveCoordinates[1]);
            return true;
        }
        return false;
    }

    private int[] getEndingMovePosition(int[] first, int[] second, int[] third, Symbol targetSign) {

        // This function takes 4 arguments, 3 arrays of coordinates in a row / column /
        // diagonal and
        // symbol that the function is supposed to look for. If it finds that symbols
        // under given coordinates line up
        // in a desired fashion, for example X ' ' X, X X ' ' or ' ' X X, it returns
        // coordinates of the empty spot
        // as an array. If the desired combination isn't found, it returns null, telling
        // the endingMoveCheck
        // function to keep searching.

        if (field[first[0]][first[1]] == Symbol.EMPTY && field[second[0]][second[1]] == targetSign
                && field[third[0]][third[1]] == targetSign) {
            return first;
        }
        if (field[first[0]][first[1]] == targetSign && field[second[0]][second[1]] == Symbol.EMPTY
                && field[third[0]][third[1]] == targetSign) {
            return second;
        }
        if (field[first[0]][first[1]] == targetSign && field[second[0]][second[1]] == targetSign
                && field[third[0]][third[1]] == Symbol.EMPTY) {
            return third;
        }
        return null;
    }

    public void clear() { // function used to clear the field after the game has finished and reset
        for (Symbol[] row : field) {
            java.util.Arrays.fill(row, Symbol.EMPTY);
        }
        xCount = 0;
        oCount = 0;
    }
    public List<Pair<Integer, Integer>> getAllEmptySpots(Field field) {
        List<Pair<Integer, Integer>> emptySpots = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field.isFree(i, j)) {
                    emptySpots.add(new Pair<>(i, j));
                }
            }
        }
    }
}
