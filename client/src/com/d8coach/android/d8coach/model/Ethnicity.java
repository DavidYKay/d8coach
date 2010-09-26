package com.d8coach.android.d8coach.model;

public enum Ethnicity {
	WHITE  ( "white"  , 1 )  , 
	LATINO ( "latino" , 2 )  , 
	BLACK  ( "black"  , 3 )  , 
	ASIAN  ( "asian"  , 4 ) ;

    private final String name;   // in kilograms
    private final int index; // in meters
    Ethnicity(String name, int index) {
        this.name = name;
        this.index = index;
    }
    public double index()  { return index; }

    public String toString() {
        return this.name;
    }
}
