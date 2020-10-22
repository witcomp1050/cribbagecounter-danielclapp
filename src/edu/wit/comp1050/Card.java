package edu.wit.comp1050;

public class Card {

    final int MAX_RANK = 13, MIN_RANK = 1;

    Card(int r, Suit s){

        if(r > MAX_RANK || r < MIN_RANK)
            throw new IllegalArgumentException("Invalid Rank");
        _rank = r;
        _suit = s;
    }

    //checks if the cards' rank AND suit are both equal
    public boolean equals(Card c) {
        return _rank == c.getRank() && _suit == c.getSuit();
    }

    //prints the Card in the format "RANK of SUIT"
    @Override
    public String toString() {
        if(_rank == 1)
            return "A" + " of " + _suit;
        else if(_rank > 1 && _rank < 11)
            return _rank + " of " + _suit;
        else if(_rank == 11)
            return "J" + " of "  + _suit;
        else if(_rank == 12)
            return "Q" + " of " + _suit;
        else if(_rank == 13)
            return "K" + " of " + _suit;
        return "This is not a card I know ... interesting";
    }

    //allows the user to access _suit without directly calling it
    Suit getSuit() {
        return _suit;
    }

    //allows the user to access _rank without directly calling it
    int getRank() {
        return _rank;
    }

    //returns the value of the card used when checking if the cards add up to fifteen
    //aka any card greater than ten is worth ten and everything else is worth its rank
    int getValue() {
        int value = 0;

        if(_rank < 10)
            value = _rank;
        else if(_rank >= 10)
            value = 10;
        return value;
    }

    //instance
    private int _rank;
    private Suit _suit;
}
