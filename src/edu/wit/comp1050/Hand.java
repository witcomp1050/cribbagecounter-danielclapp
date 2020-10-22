package edu.wit.comp1050;

import java.util.Arrays;

public class Hand {

    public Hand(Card starter, Card c1, Card c2, Card c3, Card c4) {

        _cards[0] = starter;
        _cards[1] = c1;
        _cards[2] = c2;
        _cards[3] = c3;
        _cards[4] = c4;

        createRankArray();
        createComboArrays();

        if (checkValidity() == false)
            throw new IllegalArgumentException("Invalid hand of cards");

    }

    //makes sure all the cards in hand are different, mostly used for debugging
    private boolean checkValidity() {
        for(int i = 0; i < _cards.length; i++)
            for(int j = 0; j < _cards.length; j++)
                if(_cards[i].equals(_cards[j]) && i != j)
                    return false;

        return true;
    }

    //prints out each card in the hand
    @Override
    public String toString() {
        return "Starter: " + _cards[0].toString() + "\n" +
                "Card 1: " + _cards[1].toString() + "\n" +
                "Card 2: " + _cards[2].toString() + "\n" +
                "Card 3: " + _cards[3].toString() + "\n" +
                "Card 4: " + _cards[4].toString() + "\n";
    }

    //creates an array of the cards' ranks
    private void createRankArray() {
        int i = 0;
        for(Card c : _cards) {
            _ranks[i] = c.getRank();
            i++;
        }
    }

    //creates arrays of combinations of different numbers of cards used later in calculations of score
    private void createComboArrays() {
        int arri = 0;

        for(int i = 0; i < _cards.length; i++)
            for (int j = i + 1; j < _cards.length; j++) {
                _twoCards[arri][0] = _cards[i];
                _twoCards[arri][1] = _cards[j];
                arri++;
            }
        for(int i = 0; i < _twoCards.length; i++)
            _twoCards[i] = sort(_twoCards[i]);

        arri = 0;
        for(int i = 0; i < _cards.length; i++)
            for(int j = i + 1; j < _cards.length; j++)
                for(int k = j + 1; k < _cards.length; k++) {
                    _threeCards[arri][0] = _cards[i];
                    _threeCards[arri][1] = _cards[j];
                    _threeCards[arri][2] = _cards[k];
                    arri++;
                }
        for(int i = 0; i < _threeCards.length; i++)
            _threeCards[i] = sort(_threeCards[i]);

        arri = 0;
        for(int i = 0; i < _cards.length; i++)
            for(int j = i + 1; j < _cards.length; j++)
                for(int k = j + 1; k < _cards.length; k++)
                    for(int l = k + 1; l < _cards.length; l++) {
                        _fourCards[arri][0] = _cards[i];
                        _fourCards[arri][1] = _cards[j];
                        _fourCards[arri][2] = _cards[k];
                        _fourCards[arri][3] = _cards[l];
                        arri++;
                    }
        for(int i = 0; i < _fourCards.length; i++)
            _fourCards[i] = sort(_fourCards[i]);

        _sortedRanks = _ranks;
        Arrays.sort(_sortedRanks);


    }

    //adds up all ways to get points in cribbage to get the total score
    int getScore() {
        return get15Score() + getPairScore() + getRunScore() + getNobScore() + getFlushScore() ;
    }

    //adds up all the times card add up to 15 and stores them in _scores
    int get15Score(){
        int score = 0;
        boolean isNull = false;

        for(Card[] c : _twoCards) {
            isNull = false;
            if (c[0].getValue() + c[1].getValue() == 15) {
                score += 2;
                for (int i = 0; i < _scores.length && isNull == false; i++)
                    if (_scores[i] == null) {
                        _scores[i] = "15: " + c[0].toString() + ", " + c[1].toString();
                        isNull = true;
                    }
            }
        }

        for(Card[] c : _threeCards) {
            isNull = false;
            if (c[0].getValue() + c[1].getValue() + c[2].getValue() == 15) {
                score += 2;
                for (int i = 0; i < _scores.length && isNull == false; i++)
                    if (_scores[i] == null) {
                        _scores[i] = "15: " + c[0].toString() + ", " + c[1].toString() + ", " + c[2].toString();
                        isNull = true;
                    }
            }
        }

        for(Card[] c : _fourCards) {
            isNull = false;
            if (c[0].getValue() + c[1].getValue() + c[2].getValue() + c[3].getValue() == 15) {
                score += 2;
                for (int i = 0; i < _scores.length && isNull == false; i++)
                    if (_scores[i] == null) {
                        _scores[i] = "15: " + c[0].toString() + ", " + c[1].toString() + ", " + c[2].toString() + ", " + c[3].toString();
                        isNull = true;
                    }
            }
        }

        isNull = false;
        if(_cards[0].getValue() + _cards[1].getValue() + _cards[2].getValue() + _cards[3].getValue() + _cards[4].getValue() == 15) {
            score += 2;
            for (int i = 0; i < _scores.length && isNull == false; i++)
                if (_scores[i] == null) {
                    _scores[i] = "15: " + _cards[0].toString() + ", " + _cards[1].toString() + ", " + _cards[2].toString() + ", " + _cards[3].toString() + ", " + _cards[4].toString();
                    isNull = true;
                }
        }

        return score;
    }

    //adds up all the times two cards have the same rank and stores them in _scores
    int getPairScore(){
        int pairScore = 0;
        boolean isNull;

        for(Card[] c : _twoCards) {
            isNull = false;
            if (c[0].getRank() == c[1].getRank()) {
                pairScore += 2;
                for (int i = 0; i < _scores.length && isNull == false; i++)
                    if (_scores[i] == null) {
                        _scores[i] = "Pair: " + c[0].toString() + ", " + c[1].toString();
                        isNull = true;
                    }
            }
        }

        return pairScore;
    }

    //adds up all the times 3, 4, or 5 cards are in a row(Ace is low) and stores it in _scores
    int getRunScore() {
        int runScore = 0;
        boolean isNull;

        for(int i = 0; i < _threeCards.length; i++) {
            isNull = false;
            if(_threeCards[i][0].getRank() + 1 == _threeCards[i][1].getRank() && _threeCards[i][1].getRank() + 1 == _threeCards[i][2].getRank()) {
                runScore += 3;
                for (int j = 0; j < _scores.length && isNull == false; j++)
                    if (_scores[j] == null) {
                        _scores[j] = "Run: " + _threeCards[i][0] + ", " + _threeCards[i][1] + ", " + _threeCards[i][2];
                        isNull = true;
                    }
            }
        }

        for(int j = 0; j < _fourCards.length; j++) {
            isNull = false;
            if(_fourCards[j][0].getRank() + 1 == _fourCards[j][1].getRank() && _fourCards[j][1].getRank() + 1 == _fourCards[j][2].getRank() && _fourCards[j][2].getRank() + 1 == _fourCards[j][3].getRank()) {
                runScore += 4;
                for (int i = 0; i < _scores.length && isNull == false; i++)
                    if (_scores[i] == null) {
                        _scores[i] = "Run: " + _fourCards[j][0] + ", " + _fourCards[j][1] + ", " + _fourCards[j][2] + _fourCards[j][3];
                        isNull = true;
                    }
            }
        }

        isNull = false;
        if(_sortedRanks[0] + 1 == _sortedRanks[1] && _sortedRanks[1] + 1 == _sortedRanks[2] && _sortedRanks[2] + 1 == _sortedRanks[3] && _sortedRanks[3] + 1 == _sortedRanks[4]) {
            runScore += 5;
            for (int i = 0; i < _scores.length && isNull == false; i++)
                if (_scores[i] == null) {
                    _scores[i] = "Run: " + _sortedRanks[0] + ", " + _sortedRanks[1] + ", " + _sortedRanks[2] + _sortedRanks[3] + ", " + _sortedRanks[4] ;
                    isNull = true;
                }
        }

        return runScore;
    }

    //adds one point if a jack in your hand is the same suit as the starter card and stores it in _scores
    int getNobScore() {
        boolean isNull;

        for(int i = 1; i < _cards.length; i++) {
            isNull = false;
            if (_cards[i].getRank() == 11 && _cards[i].getSuit() == _cards[0].getSuit()) {
                for (int j = 0; j < _scores.length && isNull == false; j++)
                    if (_scores[j] == null) {
                        _scores[j] = "Nob: " + _cards[i].toString() + ", " + _cards[0].toString();
                        isNull = true;
                    }
                return 1;
            }
        }
        return 0;
    }

    //adds four points if all cards in hand are the same suit, 5 points if the starter cards is also the same suit and stores it in _scores
    int getFlushScore() {
        boolean isNull = false;

        for(int i = 1; i < _cards.length; i++)
            if(_cards[1].getSuit() != _cards[i].getSuit())
                return 0;
        if(_cards[0].getSuit() == _cards[1].getSuit()) {
            for (int j = 0; j < _scores.length && isNull == false; j++)
                if (_scores[j] == null) {
                    _scores[j] = "Flush: " + _cards[0].toString() + ", " + _cards[1].toString() + ", " + _cards[2].toString() + ", " + _cards[3].toString() + ", " + _cards[4].toString();
                    isNull = true;
                }
            return 5;
        } else {
            for (int j = 0; j < _scores.length && isNull == false; j++)
                if (_scores[j] == null) {
                    _scores[j] = "Flush: " + _cards[2].toString() + ", " + _cards[3].toString() + ", " + _cards[4].toString();
                    isNull = true;
                }
            return 4;
        }
    }

    private Card[] sort(Card[] c) {
        Card holder;

        for(int i = 0; i < 15; i++) {
            for(int j = 0; j < c.length - 1; j++) {
                if(c[j].getRank() > c[j + 1].getRank()) {
                    holder = c[j];
                    c[j] = c[j + 1];
                    c[j + 1] = holder;
                }
            }
        }


        return c;
    }

    //instance
    Card[] _cards = new Card[5];
    Card[][] _twoCards = new Card[10][2], _threeCards = new Card[10][3], _fourCards = new Card[5][4];
    String[] _scores = new String[15];
    int[] _ranks = new int[5], _sortedRanks = new int [5];
}

