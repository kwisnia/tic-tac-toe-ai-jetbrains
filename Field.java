package tictactoe;

import java.util.Arrays;

public class Field {
    private static final char[][] field = new char[3][3];
    private static int xCount = 0;
    private static int yCount = 0;

    public Field() {
        for (char[] row : field) {
            Arrays.fill(row, ' ');
        }
    }

    boolean isFree(int x, int y) {
        if (field[x][y] != ' ') {
            return false;
        } else {
            return true;
        }
    }

    public int getXCount() {
        return xCount;
    }

    public int getYCount() {
        return yCount;
    }

    public void display() {
        System.out.println("---------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.print('|');
            System.out.println();
        }
        System.out.println("---------");
    }

    public void setField(int x, int y) { // x = y, y = x - 1
        if (xCount == yCount) {
            field[x][y] = 'X';
            xCount++;
        } else {
            field[x][y] = 'O';
            yCount++;
        }
    }

    public boolean winCheck() {
        char ch = field[0][0];
        int chcount = 0;
        for (int i = 0; i < 3; i++) {
            ch = field[i][0];
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ch) {
                    chcount++;
                }
            }
            if (chcount == 3 && ch != ' ') {
                System.out.println(ch + " wins");
                return true;
            }
            chcount = 0;
        }
        for (int j = 0; j < 3; j++) {
            ch = field[0][j];
            for (int i = 0; i < 3; i++) {
                if (field[i][j] == ch) {
                    chcount++;
                }
            }
            if (chcount == 3 && ch != ' ') {
                System.out.println(ch + " wins");
                return true;
            }
            chcount = 0;
        }
        ch = field[0][0];
        if (field[0][0] == field[1][1] && field[0][0] != ' ') {
            if (field[0][0] == field[2][2]) {
                System.out.println(ch + " wins");
                return true;
            }
        }
        ch = field[0][2];
        if (field[0][2] == field[1][1] && field[0][2] != ' ') {
            if (field[0][2] == field[2][0]) {
                System.out.println(ch + " wins");
                return true;
            }
        }
        return false;
    }

    public void state() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (field[i][j] == ' ') {
                    System.out.println("Game not finished");
                    return;
                }
            }
        }
        System.out.println("Draw");
    }

    public int[] check() {
        int[] pom;
//wiersze
        for (int i = 0; i < 3; i++) {
            pom = tryCheck(new int[]{i, 0}, new int[]{i, 1}, new int[]{i, 2});
            if (pom != null) {
                return pom;
            }
        }
//kolumny
        for (int i = 0; i < 3; i++) {
            pom = tryCheck(new int[]{0, i}, new int[]{1, i}, new int[]{2, i});
            if (pom != null) {
                return pom;
            }
        }

//przekatne
        pom =  tryCheck(new int[]{0, 0}, new int[]{1, 1}, new int[]{2, 2});
        if (pom != null) {
            return pom;
        }
        pom =  tryCheck(new int[]{2, 0}, new int[]{1, 1}, new int[]{0, 2});
        if (pom != null) {
            return pom;
        }
        return null;
    }
    public int[] tryCheck(int[] first, int[] second, int[] third, boolean def){
        char targetSign;
        if (def) {
            if (xCount == yCount) {
                targetSign = 'O';
            } else {
                targetSign = 'X';
            }
        } else {
            if (xCount == yCount) {
                targetSign = 'X';
            } else {
                targetSign = 'O';
            }
        }


        if ( field[first[0]][first[1]] == ' ' && field[second[0]][second[1]] == targetSign &&
                field[third[0]][third[1]] == targetSign) {
            return first;
        }
        if ( field[first[0]][first[1]] == targetSign && field[second[0]][second[1]] == ' ' &&
                field[third[0]][third[1]] == targetSign) {
            return second;
        }
        if ( field[first[0]][first[1]] == targetSign && field[second[0]][second[1]] == targetSign &&
                field[third[0]][third[1]] == ' ') {
            return third;
        }
        return null;
    }
}

