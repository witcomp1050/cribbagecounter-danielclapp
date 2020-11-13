package edu.wit.comp1050;

public class JavaMain {

    public static void main(String[] args) {
        Card starter = new Card(12, Suit.H),
                card1 = new Card(13, Suit.S),
                card2 = new Card(5, Suit.S),
                card3 = new Card(11, Suit.H),
                card4 = new Card(10, Suit.S);

        Hand h = new Hand(starter, card1, card2, card3, card4);

        System.out.println(h.getScore());

        System.out.println(starter.toString());

        for(String s : h._scores)
            if(s != null)
                System.out.println(s);

    }
}