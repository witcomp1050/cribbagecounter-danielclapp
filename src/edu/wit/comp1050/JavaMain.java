package edu.wit.comp1050;

public class JavaMain {

    public static void main(String[] args) {
        Card starter = new Card(10, Suit.HEARTS),
                card1 = new Card(3, Suit.SPADES),
                card2 = new Card(5, Suit.SPADES),
                card3 = new Card(4, Suit.CLUBS),
                card4 = new Card(9, Suit.SPADES);


        Hand h = new Hand(starter, card1, card2, card3, card4);

        System.out.println(h.getScore());

        for(String s : h._scores)
            if(s != null)
                System.out.println(s);

    }
}