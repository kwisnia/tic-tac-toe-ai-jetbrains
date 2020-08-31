package tictactoe;

enum Symbol {
    EMPTY(" ") {
        @Override
        public Symbol getOppositeSymbol() {
            return EMPTY;
        }
    },
    O("O") {
        @Override
        public Symbol getOppositeSymbol() {
            return X;
        }
    },
    X("X") {
        @Override
        public Symbol getOppositeSymbol() {
            return O;
        }
    };

    private final String symbol;

    Symbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
    public abstract Symbol getOppositeSymbol();
}
