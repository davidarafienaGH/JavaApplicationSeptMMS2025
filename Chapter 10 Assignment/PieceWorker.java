public class PieceWorker extends Employee {
    private double wage;
    private double pieces;

    public PieceWorker(String firstName, String lastName, String ssn, Date birthDate, double wage, double pieces) {
        super(firstName, lastName, ssn, birthDate);
        setWage(wage);
        setPieces(pieces);
    }

    public void setWage(double wage) {
        if (wage < 0.0) throw new IllegalArgumentException("Wage per piece must be >= 0.0");
        this.wage = wage;
    }

    public void setPieces(double pieces) {
        if (pieces < 0.0) throw new IllegalArgumentException("Pieces produced must be >= 0.0");
        this.pieces = pieces;
    }

    public double getWage() { return wage; }
    public double getPieces() { return pieces; }

    @Override
    public double earnings() {
        return getWage() * getPieces();
    }

    @Override
    public String toString() {
        return String.format("Piece Worker: %s%nWage per Piece: $%,.2f%nPieces Produced: %,.2f", 
            super.toString(), getWage(), getPieces());
    }
}
