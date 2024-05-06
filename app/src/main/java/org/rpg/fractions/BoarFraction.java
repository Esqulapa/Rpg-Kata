package org.rpg.fractions;

public final class BoarFraction extends Fraction  {
    private static final String fractionName = "Boar fraction";

    private BoarFraction(String name) {
        super(name);
    }

    public static BoarFraction createBoarFraction(){
        return new BoarFraction(fractionName);
    }
}
