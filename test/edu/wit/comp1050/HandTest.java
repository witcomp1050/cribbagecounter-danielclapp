package edu.wit.comp1050;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {

    Card starter = new Card(2, Suit.HEARTS),
            card1 = new Card(2, Suit.SPADES),
            card2 = new Card(4, Suit.SPADES),
            card3 = new Card(3, Suit.SPADES),
            card4 = new Card(10, Suit.SPADES);
    Hand h = new Hand(starter, card1, card2, card3, card4);


    Card starter1 = new Card(5, Suit.HEARTS),
            card11 = new Card(5, Suit.SPADES),
            card21 = new Card(5, Suit.DIAMONDS),
            card31 = new Card(5, Suit.CLUBS),
            card41 = new Card(11, Suit.HEARTS);
    Hand h1 = new Hand(starter1, card11, card21, card31, card41);

    Card starter2 = new Card(10, Suit.HEARTS),
            card12 = new Card(13, Suit.SPADES),
            card22 = new Card(5, Suit.SPADES),
            card32 = new Card(12, Suit.CLUBS),
            card42 = new Card(9, Suit.SPADES);


    Hand h2 = new Hand(starter2, card12, card22, card32, card42);


    @Test
    void getScoreTest() {

        int n = h.getScore();

        int n1 = h1.getScore();

        assertEquals(16, n);
        assertEquals(29, n1);



    }

    @Test
    void get15ScoreTest() {

        int n = h.get15Score();
        int n1 = h1.get15Score();

        assertEquals(4, n);
        assertEquals(16, n1);

    }

    @Test
    void getPairScoreTest() {
        int n = h.getPairScore();
        int n1 = h1.getPairScore();

        assertEquals(2, n);
        assertEquals(12, n1);
    }

    @Test
    void getRunScoreTest() {
       int n = h.getRunScore();
       int n1 = h1.getRunScore();

       assertEquals(6, n);
       assertEquals(0, n1);
    }

    @Test
    void getNobScoreTest() {
        int n = h.getNobScore();
        int n1 = h1.getNobScore();

        assertEquals(0, n);
        assertEquals(1, n1);
    }

    @Test
    void getFlushScoreTest() {
        int n = h.getFlushScore();
        int n1 = h1.getFlushScore();

        assertEquals(4, n);
        assertEquals(0, n1);
    }
}