package tictactoe;

public class Field {
    private static final Symbol[][] field = new Symbol[3][3];
    private static int xCount = 0;
    private static int yCount = 0;

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
        return yCount;
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
        if (xCount == yCount) {
            field[x][y] = Symbol.X;
            xCount++;
        } else {
            field[x][y] = Symbol.O;
            yCount++;
        }
    }
    public void setSymbol(Symbol symbol) {
        if (xCount == yCount) {
            symbol = Symbol.X;
            xCount++;
        } else {
            symbol = Symbol.O;
            yCount++;
        }
    }
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
        return field[0][0] == symbol && field[1][1] == symbol && field[2][2] == symbol ||
                field[2][0] == symbol && field[1][1] == symbol && field[0][2] == symbol;
    }

    public boolean winCheck(Symbol symbol) {
            if (rowCheck(symbol) || columnCheck(symbol) || diagonalCheck(symbol)) {
                System.out.println(symbol + " wins");
                return true;
            }
        return false;
    }

    public void state() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == Symbol.EMPTY) {
                    System.out.println("Game not finished");
                    return;
                }
            }
        }
        System.out.println("Draw");
    }

    public Symbol check(boolean attack) {
        Symbol pom;
//wiersze
        for (int i = 0; i < 3; i++) {
            pom = tryCheck(field[i][0], field[i][1], field[i][2], attack);
            if (pom != null) {
                return pom;
            }
        }
//kolumny
        for (int i = 0; i < 3; i++) {
            pom = tryCheck(field[0][i], field[1][i], field[2][i], attack);
            if (pom != null) {
                return pom;
            }
        }

//przekatne
        pom =  tryCheck(field[0][0], field[1][1], field[2][2], attack);
        if (pom != null) {
            return pom;
        }
        pom =  tryCheck(field[2][0], field[1][1], field[0][2], attack);
        if (pom != null) {
            return pom;
        }
        return null;
    }
    private Symbol tryCheck(Symbol first, Symbol second, Symbol third, boolean attack){
        Symbol targetSign;
        if (attack) {
            if (xCount == yCount) {
                targetSign = Symbol.X;
            } else {
                targetSign = Symbol.O;
            }
        } else {
            if (xCount == yCount) {
                targetSign = Symbol.O;
            } else {
                targetSign = Symbol.X;
            }
        }
            if (first == Symbol.EMPTY && second == targetSign &&  third == targetSign) {
                return first;
            }
            if (first == targetSign && second == Symbol.EMPTY && third == targetSign) {
                return second;
            }
            if (first == targetSign && second == targetSign && third == Symbol.EMPTY) {
                return third;
            }
        return null;
    }
}

