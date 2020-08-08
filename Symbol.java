package tictactoe;

enum Symbol {
    EMPTY(" "),
    O("O"),
    X("X");

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
