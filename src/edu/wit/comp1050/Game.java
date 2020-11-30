package edu.wit.comp1050;

import java.util.*;

public class Game {

    static Card[] deck = new Card[52], hand = new Card[5];
    static Suit[] suits = {Suit.H, Suit.D, Suit.C, Suit.S};
    static int[] cardsUsed = {-1, -1, -1, -1, -1};
    static Hand h;
    static int score;

    public static void main(String[] args) {

        Game g = new Game();

        g.createDeck();

        while(true) {
            g.createHand();

            Scanner reader = new Scanner(System.in);
            System.out.println("\nYour Hand is: \n\n" + h.toString());

            System.out.print("Enter Score: ");
            score = reader.nextInt();
            int hScore = h.getScore();

            if (score == hScore) {
                System.out.println("\nCorrect! You won. \nThe correct score is " + hScore + "\n");
                g.printScore();
            }else {
                System.out.println("Wrong! the correct score is: " + hScore);
                g.printScore();
            }

            Scanner strReader = new Scanner(System.in);

            System.out.print("Would you like to play again(Y/N): " );
            String playAgain = strReader.nextLine();


            if(playAgain.compareToIgnoreCase("Y") == 0) {
                System.out.println(" ");
                continue;
            } else if(playAgain.compareToIgnoreCase("N") == 0)
                break;
            else {
                System.out.println("Invalid, ending game.");
                break;
            }
        }






    }

    void createDeck() {
        int j = 0;
        for(Suit s : suits) {
            for (int i = 0; i < 13; i++)
                deck[i + (13 * j)] = new Card(i + 1, s);
            j++;
        }
    }

    void createHand() {
        int rNum = -1, index = 0;
        boolean used;

        for(int i = 0; i < 5; i++) {
            do {
                used = false;
                rNum = (int) (Math.random() * 51);
                for(int j = 0; j < cardsUsed.length; j++) {
                    if(rNum == cardsUsed[j]) {
                        used = true;
                        j = cardsUsed.length;
                    }
                }
            } while(used);
            cardsUsed[i] = rNum;
        }

        for(Card c : hand) {
            c = deck[cardsUsed[index]];
            hand[index] = c;
            index++;
        }

        h = new Hand(hand[0], hand[1], hand[2], hand[3], hand[4]);
//        try {
//            h = new Hand(hand[0], hand[1], hand[2], hand[3], hand[4]);
//        }catch(IllegalArgumentException illex) {
//            for(int card : cardsUsed)
//                System.out.println(card);
//            System.exit(0);
//        }
    }

    private void printScore() {
        for (String s : h._scores)
            if (s != null)
                System.out.println(s);
        System.out.println();
    }


}
