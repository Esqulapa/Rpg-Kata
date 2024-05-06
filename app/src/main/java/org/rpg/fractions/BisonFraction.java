package org.rpg.fractions;

public class BisonFraction extends Fraction {
    private static final String fractionName = "Bison fraction";


    protected BisonFraction(String name) {
        super(name);
    }

    public static BisonFraction createBisonFraction(){
        return new BisonFraction(fractionName);
    }
}
