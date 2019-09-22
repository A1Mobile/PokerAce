package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

public class GetResults extends DrawCards {
    protected Integer[] cardsInPlayerHand = new Integer[7];
    protected Integer[] cardsInComputerHand = new Integer[7];
    protected String[] suitsInPlayerHand = new String[7];
    protected String[] suitsInComputerHand = new String[7];
    protected String[] suitsInPlayerHandSF = new String[7];
    protected String[] suitsInComputerHandSF = new String[7];
    protected Integer[] cardsInPlayerHandHolder = new Integer[7];
    protected Integer[] cardsInComputerHandHolder = new Integer[7];
    protected int playerHandStrength;
    protected int computerHandStrength;
    protected String whoseCheck;
    protected int playerPairCard;
    protected int playerTwoPairCard1;
    protected int playerTwoPairCard2;
    protected int playerThreeOfAKindCard;
    protected int playerStraightCardHigh;
    protected int playerStraightCardLow;
    protected int playerFlushCard1;
    protected int playerFlushCard2;
    protected int playerFlushCard3;
    protected int playerFlushCard4;
    protected int playerFlushCard5;
    protected int playerFullHouseThreeOfAKind;
    protected int playerFullHousePair;
    protected int playerFourOfAKindCard;
    protected int playerStraightFlushCardHigh;
    protected int playerStraightFlushCardLow;

    protected int computerPairCard;
    protected int computerTwoPairCard1;
    protected int computerTwoPairCard2;
    protected int computerThreeOfAKindCard;
    protected int computerStraightCardHigh;
    protected int computerStraightCardLow;
    protected int computerFlushCard1;
    protected int computerFlushCard2;
    protected int computerFlushCard3;
    protected int computerFlushCard4;
    protected int computerFlushCard5;
    protected int computerFullHouseThreeOfAKind;
    protected int computerFullHousePair;
    protected int computerFourOfAKindCard;
    protected int computerStraightFlushCardHigh;
    protected int computerStraightFlushCardLow;

    protected void getHandStrengthComputer() {
        cardsInComputerHand[0] = card3.getFaceValue();
        cardsInComputerHand[1] = card4.getFaceValue();
        cardsInComputerHand[2] = card5.getFaceValue();
        cardsInComputerHand[3] = card6.getFaceValue();
        cardsInComputerHand[4] = card7.getFaceValue();
        cardsInComputerHand[5] = card8.getFaceValue();
        cardsInComputerHand[6] = card9.getFaceValue();
        Arrays.sort(cardsInComputerHand, Collections.reverseOrder());
        whoseCheck = "Computer";
        cardsInComputerHandHolder[0] = card3.getFaceValue();
        cardsInComputerHandHolder[1] = card4.getFaceValue();
        cardsInComputerHandHolder[2] = card5.getFaceValue();
        cardsInComputerHandHolder[3] = card6.getFaceValue();
        cardsInComputerHandHolder[4] = card7.getFaceValue();
        cardsInComputerHandHolder[5] = card8.getFaceValue();
        cardsInComputerHandHolder[6] = card9.getFaceValue();
        Arrays.sort(cardsInComputerHandHolder, Collections.reverseOrder());

        //cardsInComputerHandHolder = cardsInComputerHand;

        suitsInComputerHand[0] = card3.getSuit();
        suitsInComputerHand[1] = card4.getSuit();
        suitsInComputerHand[2] = card5.getSuit();
        suitsInComputerHand[3] = card6.getSuit();
        suitsInComputerHand[4] = card7.getSuit();
        suitsInComputerHand[5] = card8.getSuit();
        suitsInComputerHand[6] = card9.getSuit();
        suitsInComputerHandSF = suitsInComputerHand;

        checkHighCard();
        checkPair();
        checkTwoPair();
        checkThreeOfAKind();
        checkStraight();
        checkFlush();
        checkFullHouse();
        checkFourOfAKind();
        checkStraightFlush();
        checkRoyalFlush();
    }

    protected void getHandStrengthComputerFlop() {
        cardsInComputerHand[0] = card3.getFaceValue();
        cardsInComputerHand[1] = card4.getFaceValue();
        cardsInComputerHand[2] = card5.getFaceValue();
        cardsInComputerHand[3] = card6.getFaceValue();
        cardsInComputerHand[4] = card7.getFaceValue();
        cardsInComputerHand[5] = -8;
        cardsInComputerHand[6] = -20;
        Arrays.sort(cardsInComputerHand, Collections.reverseOrder());
        whoseCheck = "Computer";
        cardsInComputerHandHolder[0] = card3.getFaceValue();
        cardsInComputerHandHolder[1] = card4.getFaceValue();
        cardsInComputerHandHolder[2] = card5.getFaceValue();
        cardsInComputerHandHolder[3] = card6.getFaceValue();
        cardsInComputerHandHolder[4] = card7.getFaceValue();
        cardsInComputerHandHolder[5] = -8;
        cardsInComputerHandHolder[6] = -20;
        Arrays.sort(cardsInComputerHandHolder, Collections.reverseOrder());

        suitsInComputerHand[0] = card3.getSuit();
        suitsInComputerHand[1] = card4.getSuit();
        suitsInComputerHand[2] = card5.getSuit();
        suitsInComputerHand[3] = card6.getSuit();
        suitsInComputerHand[4] = card7.getSuit();
        suitsInComputerHand[5] = "n";
        suitsInComputerHand[6] = "n";
        suitsInComputerHandSF = suitsInComputerHand;

        checkHighCard();
        checkPair();
        checkTwoPair();
        checkThreeOfAKind();
        checkStraight();
        checkFlush();
        checkFullHouse();
        checkFourOfAKind();
        checkStraightFlush();
        checkRoyalFlush();
    }

    protected void getHandStrengthComputerTurn() {
        cardsInComputerHand[0] = card3.getFaceValue();
        cardsInComputerHand[1] = card4.getFaceValue();
        cardsInComputerHand[2] = card5.getFaceValue();
        cardsInComputerHand[3] = card6.getFaceValue();
        cardsInComputerHand[4] = card7.getFaceValue();
        cardsInComputerHand[5] = card8.getFaceValue();
        cardsInComputerHand[6] = -20;
        Arrays.sort(cardsInComputerHand, Collections.reverseOrder());
        whoseCheck = "Computer";
        cardsInComputerHandHolder[0] = card3.getFaceValue();
        cardsInComputerHandHolder[1] = card4.getFaceValue();
        cardsInComputerHandHolder[2] = card5.getFaceValue();
        cardsInComputerHandHolder[3] = card6.getFaceValue();
        cardsInComputerHandHolder[4] = card7.getFaceValue();
        cardsInComputerHandHolder[5] = card8.getFaceValue();
        cardsInComputerHandHolder[6] = -20;
        Arrays.sort(cardsInComputerHandHolder, Collections.reverseOrder());

        suitsInComputerHand[0] = card3.getSuit();
        suitsInComputerHand[1] = card4.getSuit();
        suitsInComputerHand[2] = card5.getSuit();
        suitsInComputerHand[3] = card6.getSuit();
        suitsInComputerHand[4] = card7.getSuit();
        suitsInComputerHand[5] = card8.getSuit();
        suitsInComputerHand[6] = "n";
        suitsInComputerHandSF = suitsInComputerHand;

        checkHighCard();
        checkPair();
        checkTwoPair();
        checkThreeOfAKind();
        checkStraight();
        checkFlush();
        checkFullHouse();
        checkFourOfAKind();
        checkStraightFlush();
        checkRoyalFlush();
    }

    protected void getHandStrengthPlayer() {
        cardsInPlayerHand[0] = card1.getFaceValue();
        cardsInPlayerHand[1] = card2.getFaceValue();
        cardsInPlayerHand[2] = card5.getFaceValue();
        cardsInPlayerHand[3] = card6.getFaceValue();
        cardsInPlayerHand[4] = card7.getFaceValue();
        cardsInPlayerHand[5] = card8.getFaceValue();
        cardsInPlayerHand[6] = card9.getFaceValue();
        Arrays.sort(cardsInPlayerHand, Collections.reverseOrder());
        whoseCheck = "Player";
        cardsInPlayerHandHolder[0] = card1.getFaceValue();
        cardsInPlayerHandHolder[1] = card2.getFaceValue();
        cardsInPlayerHandHolder[2] = card5.getFaceValue();
        cardsInPlayerHandHolder[3] = card6.getFaceValue();
        cardsInPlayerHandHolder[4] = card7.getFaceValue();
        cardsInPlayerHandHolder[5] = card8.getFaceValue();
        cardsInPlayerHandHolder[6] = card9.getFaceValue();
        Arrays.sort(cardsInPlayerHandHolder, Collections.reverseOrder());

        suitsInPlayerHand[0] = card1.getSuit();
        suitsInPlayerHand[1] = card2.getSuit();
        suitsInPlayerHand[2] = card5.getSuit();
        suitsInPlayerHand[3] = card6.getSuit();
        suitsInPlayerHand[4] = card7.getSuit();
        suitsInPlayerHand[5] = card8.getSuit();
        suitsInPlayerHand[6] = card9.getSuit();
        suitsInPlayerHandSF = suitsInPlayerHand;

        checkHighCard();
        checkPair();
        checkTwoPair();
        checkThreeOfAKind();
        checkStraight();
        checkFlush();
        checkFullHouse();
        checkFourOfAKind();
        checkStraightFlush();
        checkRoyalFlush();
    }

    protected void checkHighCard() {
        if (whoseCheck == "Player") {
            playerHandStrength = 1;
        }
        else {
            computerHandStrength = 1;
        }
    }

    protected void checkPair() {
        String pair = "False";
        int numOccurrences = 0;

        if (whoseCheck == "Player") {
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=0; p < 7; p++) {
                    if (cardsInPlayerHand[p] == cardsInPlayerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 2) {
                    pair = "True";
                    playerPairCard = cardsInPlayerHand[i];
                }
            }

            if (pair == "True") {
                playerHandStrength = 2;
            }
        }

        if (whoseCheck == "Computer") {
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=0; p < 7; p++) {
                    if (cardsInComputerHand[p] == cardsInComputerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 2) {
                    pair = "True";
                    computerPairCard = cardsInComputerHand[i];
                }
            }

            if (pair == "True") {
                computerHandStrength = 2;
            }
        }
    }


    protected void checkTwoPair() {
        String twoPair = "False";
        int numOccurrences = 0;
        int twoPairCount = 0;
        int highCardHolder;
        int cardCount = 0;

        if (whoseCheck == "Player") {
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=i; p < 7; p++) {
                    if (cardsInPlayerHand[p] == cardsInPlayerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 2) {
                    twoPairCount = twoPairCount + 1;
                    cardCount = cardCount + 1;
                    highCardHolder = cardsInPlayerHand[i];
                    if (cardCount == 1) {
                        playerTwoPairCard1 = highCardHolder;
                    }
                    if (cardCount == 2) {
                        playerTwoPairCard2 = highCardHolder;
                    }

                    if (twoPairCount == 2) {
                        twoPair = "True";
                    }
                }
            }

            if (twoPair == "True") {
                playerHandStrength = 3;
            }
        }

        if (whoseCheck == "Computer") {
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=i; p < 7; p++) {
                    if (cardsInComputerHand[p] == cardsInComputerHand[i])  {
                            numOccurrences++;
                    }
                }

                if (numOccurrences == 2) {
                    twoPairCount = twoPairCount + 1;
                    cardCount = cardCount + 1;
                    highCardHolder = cardsInComputerHand[i];
                    if (cardCount == 1) {
                        computerTwoPairCard1 = highCardHolder;
                    }
                    if (cardCount == 2) {
                        computerTwoPairCard2 = highCardHolder;
                    }

                    if (twoPairCount == 2) {
                        twoPair = "True";
                    }
                }
            }

            if (twoPair == "True") {
                computerHandStrength = 3;
            }
        }
    }

    protected void checkThreeOfAKind() {
        String threeOfAKind = "False";
        int numOccurrences = 0;
        int cardCount = 0;

        if (whoseCheck == "Player") {
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=0; p < 7; p++) {
                    if (cardsInPlayerHand[p] == cardsInPlayerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 3) {
                    threeOfAKind = "True";
                    cardCount = cardCount + 1;
                    if (cardCount == 1){
                        playerThreeOfAKindCard = cardsInPlayerHand[i];
                    }
                }
            }

            if (threeOfAKind == "True") {
                playerHandStrength = 4;
            }
        }

        if (whoseCheck == "Computer") {
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=0; p < 7; p++) {
                    if (cardsInComputerHand[p] == cardsInComputerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 3) {
                    threeOfAKind = "True";
                    cardCount = cardCount + 1;
                    if (cardCount == 1){
                        computerThreeOfAKindCard = cardsInComputerHand[i];
                    }
                }
            }

            if (threeOfAKind == "True") {
                computerHandStrength = 4;
            }
        }
    }

    protected void checkStraight() {
        String straight = "False";
        int counterHolder = 0;

        if (whoseCheck == "Player") {
            counterHolder = 0;
            for (int i = 0; i < 6; i++) {
                if ((cardsInPlayerHand[i] - 1) == cardsInPlayerHand[i + 1]) {
                    counterHolder = counterHolder + 1;
                    if (counterHolder == 1) {
                        playerStraightCardHigh = cardsInPlayerHand[i];
                    }
                    if (counterHolder == 4) {
                        playerStraightCardLow = cardsInPlayerHand[i + 1];
                        straight = "True";
                    }
                }
                else {
                    if (i != 0) {
                        if (cardsInPlayerHand[i + 1] != cardsInPlayerHand[i]) {
                            counterHolder = 0;
                        }
                    }
                }
            }

            if (straight != "True") {
                counterHolder = 0;
                if (cardsInPlayerHandHolder[0] == 14) {
                    cardsInPlayerHandHolder[0] = 1;
                }
                if (cardsInPlayerHandHolder[1] == 14) {
                    cardsInPlayerHandHolder[1] = 1;
                }
                if (cardsInPlayerHandHolder[2] == 14) {
                    cardsInPlayerHandHolder[2] = 1;
                }
                if (cardsInPlayerHandHolder[3] == 14) {
                    cardsInPlayerHandHolder[3] = 1;
                }
                if (cardsInPlayerHandHolder[4] == 14) {
                    cardsInPlayerHandHolder[4] = 1;
                }
                if (cardsInPlayerHandHolder[5] == 14) {
                    cardsInPlayerHandHolder[5] = 1;
                }
                if (cardsInPlayerHandHolder[6] == 14) {
                    cardsInPlayerHandHolder[6] = 1;
                }
                Arrays.sort(cardsInPlayerHandHolder, Collections.reverseOrder());

                for (int i = 0; i < 6; i++) {
                    if ((cardsInPlayerHandHolder[i] - 1) == cardsInPlayerHandHolder[i + 1]) {
                        counterHolder = counterHolder + 1;
                        if (counterHolder == 1) {
                            playerStraightCardHigh = cardsInPlayerHandHolder[i];
                        }
                        if (counterHolder == 4) {
                            playerStraightCardLow = cardsInPlayerHandHolder[i + 1];
                            straight = "True";
                        }
                    }
                    else {
                        if (i != 0) {
                            if (cardsInPlayerHandHolder[i + 1] != cardsInPlayerHandHolder[i]) {
                                counterHolder = 0;
                            }
                        }
                    }
                }
            }

            if (straight == "True") {
                playerHandStrength = 5;
            }
        }

        if (whoseCheck == "Computer") {
            counterHolder = 0;
            for (int i = 0; i < 6; i++) {
                if ((cardsInComputerHand[i] - 1) == cardsInComputerHand[i + 1]) {
                    counterHolder = counterHolder + 1;
                    if (counterHolder == 1) {
                        computerStraightCardHigh = cardsInComputerHand[i];
                    }
                    if (counterHolder == 4) {
                        computerStraightCardLow = cardsInComputerHand[i + 1];
                        straight = "True";
                    }
                }
                else {
                    if (i != 0) {
                        if (cardsInComputerHand[i + 1] != cardsInComputerHand[i]) {
                            counterHolder = 0;
                        }
                    }
                }
            }

            if (straight != "True") {
                counterHolder = 0;
                if (cardsInComputerHandHolder[0] == 14) {
                    cardsInComputerHandHolder[0] = 1;
                }
                if (cardsInComputerHandHolder[1] == 14) {
                    cardsInComputerHandHolder[1] = 1;
                }
                if (cardsInComputerHandHolder[2] == 14) {
                    cardsInComputerHandHolder[2] = 1;
                }
                if (cardsInComputerHandHolder[3] == 14) {
                    cardsInComputerHandHolder[3] = 1;
                }
                if (cardsInComputerHandHolder[4] == 14) {
                    cardsInComputerHandHolder[4] = 1;
                }
                if (cardsInComputerHandHolder[5] == 14) {
                    cardsInComputerHandHolder[5] = 1;
                }
                if (cardsInComputerHandHolder[6] == 14) {
                    cardsInComputerHandHolder[6] = 1;
                }
                Arrays.sort(cardsInComputerHandHolder, Collections.reverseOrder());

                for (int i = 0; i < 6; i++) {
                    if ((cardsInComputerHandHolder[i] - 1) == cardsInComputerHandHolder[i + 1]) {
                        counterHolder = counterHolder + 1;
                        if (counterHolder == 1) {
                            computerStraightCardHigh = cardsInComputerHandHolder[i];
                        }
                        if (counterHolder == 4) {
                            computerStraightCardLow = cardsInComputerHandHolder[i + 1];
                            straight = "True";
                        }
                    }
                    else {
                        if (i != 0) {
                            if (cardsInComputerHandHolder[i + 1] != cardsInComputerHandHolder[i]) {
                                counterHolder = 0;
                            }
                        }
                    }
                }
            }

            if (straight == "True") {
                computerHandStrength = 5;
            }
        }
    }

    protected void checkFlush() {
        int numOccurrencesForSpades = 0;
        int numOccurrencesForHearts = 0;
        int numOccurrencesForClubs = 0;
        int numOccurrencesForDiamonds = 0;
        String suitUsed = "";
        String flush = "False";
        Card x = card1;
        int faceValue1 = 0;
        int faceValue2 = 0;
        int faceValue3 = 0;
        int faceValue4 = 0;
        int faceValue5 = 0;

        if (whoseCheck == "Player") {
            numOccurrencesForSpades = Collections.frequency(Arrays.asList(suitsInPlayerHand), "s");
            numOccurrencesForHearts = Collections.frequency(Arrays.asList(suitsInPlayerHand), "h");
            numOccurrencesForClubs = Collections.frequency(Arrays.asList(suitsInPlayerHand), "c");
            numOccurrencesForDiamonds = Collections.frequency(Arrays.asList(suitsInPlayerHand), "d");

            if (numOccurrencesForSpades >= 5 | numOccurrencesForHearts >= 5 | numOccurrencesForClubs >= 5 | numOccurrencesForDiamonds >= 5) {
                flush = "True";

                if (numOccurrencesForSpades >= 5) {
                    suitUsed = "s";
                }
                if (numOccurrencesForHearts >= 5) {
                    suitUsed = "h";
                }
                if (numOccurrencesForClubs >= 5) {
                    suitUsed = "c";
                }
                if (numOccurrencesForDiamonds >= 5) {
                    suitUsed = "d";
                }

                for (int i = 0; i < 7; i++) {
                    if(i == 0) {
                        x = card1;
                    }
                    if(i == 1) {
                        x = card2;
                    }
                    if(i == 2) {
                        x = card5;
                    }
                    if(i == 3) {
                        x = card6;
                    }
                    if(i == 4) {
                        x = card7;
                    }
                    if(i == 5) {
                        x = card8;
                    }
                    if(i == 6) {
                        x = card9;
                    }

                    if (x.getSuit() == suitUsed) {
                        if (x.getFaceValue() > faceValue1) {
                            faceValue5 = faceValue4;
                            faceValue4 = faceValue3;
                            faceValue3 = faceValue2;
                            faceValue2 = faceValue1;
                            faceValue1 = x.getFaceValue();
                        }
                        else {
                            if (x.getFaceValue() > faceValue2) {
                                faceValue5 = faceValue4;
                                faceValue4 = faceValue3;
                                faceValue3 = faceValue2;
                                faceValue2 = x.getFaceValue();
                            }
                            else {
                                if (x.getFaceValue() > faceValue3) {
                                    faceValue5 = faceValue4;
                                    faceValue4 = faceValue3;
                                    faceValue3 = x.getFaceValue();
                                }
                                else {
                                    if (x.getFaceValue() > faceValue4) {
                                        faceValue5 = faceValue4;
                                        faceValue4 = x.getFaceValue();
                                    }
                                    else {
                                        if (x.getFaceValue() > faceValue5) {
                                            faceValue5 = x.getFaceValue();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    playerFlushCard1 = faceValue1;
                    playerFlushCard2 = faceValue2;
                    playerFlushCard3 = faceValue3;
                    playerFlushCard4 = faceValue4;
                    playerFlushCard5 = faceValue5;
                }
            }
            if (flush == "True") {
                playerHandStrength = 6;
            }
        }

        if (whoseCheck == "Computer") {
            numOccurrencesForSpades = Collections.frequency(Arrays.asList(suitsInComputerHand), "s");
            numOccurrencesForHearts = Collections.frequency(Arrays.asList(suitsInComputerHand), "h");
            numOccurrencesForClubs = Collections.frequency(Arrays.asList(suitsInComputerHand), "c");
            numOccurrencesForDiamonds = Collections.frequency(Arrays.asList(suitsInComputerHand), "d");

            if (numOccurrencesForSpades >= 5 | numOccurrencesForHearts >= 5 | numOccurrencesForClubs >= 5 | numOccurrencesForDiamonds >= 5) {
                flush = "True";

                if (numOccurrencesForSpades >= 5) {
                    suitUsed = "s";
                }
                if (numOccurrencesForHearts >= 5) {
                    suitUsed = "h";
                }
                if (numOccurrencesForClubs >= 5) {
                    suitUsed = "c";
                }
                if (numOccurrencesForDiamonds >= 5) {
                    suitUsed = "d";
                }

                for (int i = 0; i < 7; i++) {
                    if(i == 0) {
                        x = card3;
                    }
                    if(i == 1) {
                        x = card4;
                    }
                    if(i == 2) {
                        x = card5;
                    }
                    if(i == 3) {
                        x = card6;
                    }
                    if(i == 4) {
                        x = card7;
                    }
                    if(i == 5) {
                        x = card8;
                    }
                    if(i == 6) {
                        x = card9;
                    }

                    if (x.getSuit() == suitUsed) {
                        if (x.getFaceValue() > faceValue1) {
                            faceValue5 = faceValue4;
                            faceValue4 = faceValue3;
                            faceValue3 = faceValue2;
                            faceValue2 = faceValue1;
                            faceValue1 = x.getFaceValue();
                        }
                        else {
                            if (x.getFaceValue() > faceValue2) {
                                faceValue5 = faceValue4;
                                faceValue4 = faceValue3;
                                faceValue3 = faceValue2;
                                faceValue2 = x.getFaceValue();
                            }
                            else {
                                if (x.getFaceValue() > faceValue3) {
                                    faceValue5 = faceValue4;
                                    faceValue4 = faceValue3;
                                    faceValue3 = x.getFaceValue();
                                }
                                else {
                                    if (x.getFaceValue() > faceValue4) {
                                        faceValue5 = faceValue4;
                                        faceValue4 = x.getFaceValue();
                                    }
                                    else {
                                        if (x.getFaceValue() > faceValue5) {
                                            faceValue5 = x.getFaceValue();
                                        }
                                    }
                                }
                            }
                        }
                    }
                    computerFlushCard1 = faceValue1;
                    computerFlushCard2 = faceValue2;
                    computerFlushCard3 = faceValue3;
                    computerFlushCard4 = faceValue4;
                    computerFlushCard5 = faceValue5;
                }
            }
            if (flush == "True") {
                computerHandStrength = 6;
            }
         }
    }

    protected void checkFullHouse() {
        String threeOfAKind = "False";
        String pair = "False";
        String fullHouse = "False";
        int numOccurrences = 0;
        int threeOfAKindCard = 0;
        int pairCard = 0;
        int cardCount = 0;

        if (whoseCheck == "Player") {
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=0; p < 7; p++) {
                    if (cardsInPlayerHand[p] == cardsInPlayerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 3) {
                    threeOfAKind = "True";
                    cardCount = cardCount + 1;
                    if (cardCount == 1){
                        threeOfAKindCard = cardsInPlayerHand[i];
                    }
                }
            }
            cardCount = 0;
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=0; p < 7; p++) {
                    if (cardsInPlayerHand[p] == cardsInPlayerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 2) {
                    if (cardsInPlayerHand[i] != threeOfAKindCard) {
                        cardCount = cardCount + 1;
                        pair = "True";
                        if (cardCount == 1) {
                            pairCard = cardsInPlayerHand[i];
                        }
                    }
                }
            }

            if (threeOfAKind == "True" & pair == "True") {
                playerHandStrength = 7;
                playerFullHouseThreeOfAKind = threeOfAKindCard;
                playerFullHousePair = pairCard;
            }
        }

        if (whoseCheck == "Computer") {
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=0; p < 7; p++) {
                    if (cardsInComputerHand[p] == cardsInComputerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 3) {
                    threeOfAKind = "True";
                    cardCount = cardCount + 1;
                    if (cardCount == 1){
                        threeOfAKindCard = cardsInComputerHand[i];
                    }
                }
            }
            cardCount = 0;
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=0; p < 7; p++) {
                    if (cardsInComputerHand[p] == cardsInComputerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 2) {
                    if (cardsInComputerHand[i] != threeOfAKindCard) {
                        cardCount = cardCount + 1;
                        pair = "True";
                        if (cardCount == 1) {
                            pairCard = cardsInComputerHand[i];
                        }
                    }
                }
            }

            if (threeOfAKind == "True" & pair == "True") {
                computerHandStrength = 7;
                computerFullHouseThreeOfAKind = threeOfAKindCard;
                computerFullHousePair = pairCard;
            }
        }
    }

    protected void checkFourOfAKind() {
        String fourOfAKind = "False";
        int numOccurrences = 0;
        int cardCount = 0;

        if (whoseCheck == "Player") {
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=0; p < 7; p++) {
                    if (cardsInPlayerHand[p] == cardsInPlayerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 4) {
                    fourOfAKind = "True";
                    cardCount = cardCount + 1;
                    if (cardCount == 1){
                        playerFourOfAKindCard = cardsInPlayerHand[i];
                    }
                }
            }

            if (fourOfAKind == "True") {
                playerHandStrength = 8;
            }
        }

        if (whoseCheck == "Computer") {
            for (int i = 0; i < 7; i++) {
                numOccurrences = 0;
                for (int p=0; p < 7; p++) {
                    if (cardsInComputerHand[p] == cardsInComputerHand[i])  {
                        numOccurrences++;
                    }
                }

                if (numOccurrences == 4) {
                    fourOfAKind = "True";
                    cardCount = cardCount + 1;
                    if (cardCount == 1){
                        computerFourOfAKindCard = cardsInComputerHand[i];
                    }
                }
            }

            if (fourOfAKind == "True") {
                computerHandStrength = 8;
            }
        }
    }

    protected void checkStraightFlush() {
        int[] cardsInPlayerHandSF = new int[7];
        int[] cardsInComputerHandSF = new int[7];
        Integer[] cardsInPlayerHandSFSorted = {-1,-1,-1,-1,-1,-1,-1};
        Integer[] cardsInComputerHandSFSorted = {-1,-1,-1,-1,-1,-1,-1};
        int numOccurrencesSpades = 0;
        int numOccurrencesHearts = 0;
        int numOccurrencesClubs = 0;
        int numOccurrencesDiamonds = 0;
        String suitUsed = "";
        int counterHolder = 0;
        String straightFlush = "False";

        if (whoseCheck == "Player") {
            counterHolder = 0;
            cardsInPlayerHandSF[0] = card1.getFaceValue();
            cardsInPlayerHandSF[1] = card2.getFaceValue();
            cardsInPlayerHandSF[2] = card5.getFaceValue();
            cardsInPlayerHandSF[3] = card6.getFaceValue();
            cardsInPlayerHandSF[4] = card7.getFaceValue();
            cardsInPlayerHandSF[5] = card8.getFaceValue();
            cardsInPlayerHandSF[6] = card9.getFaceValue();

            numOccurrencesSpades = Collections.frequency(Arrays.asList(suitsInPlayerHandSF), "s");
            numOccurrencesHearts = Collections.frequency(Arrays.asList(suitsInPlayerHandSF), "h");
            numOccurrencesClubs = Collections.frequency(Arrays.asList(suitsInPlayerHandSF), "c");
            numOccurrencesDiamonds = Collections.frequency(Arrays.asList(suitsInPlayerHandSF), "d");

            if (numOccurrencesSpades >= 5) { suitUsed = "s"; }
            if (numOccurrencesHearts >= 5) { suitUsed = "h"; }
            if (numOccurrencesClubs >= 5) { suitUsed = "c"; }
            if (numOccurrencesDiamonds >= 5) { suitUsed = "d"; }



            for (int i = 0; i < 7; i++) {
                if (suitsInPlayerHandSF[i].equals(suitUsed)) {
                    counterHolder = counterHolder + 1;
                    if (counterHolder == 0) {
                        cardsInPlayerHandSFSorted[0] = cardsInPlayerHandSF[i];
                    }
                    if (counterHolder == 1) {
                        cardsInPlayerHandSFSorted[1] = cardsInPlayerHandSF[i];
                    }
                    if (counterHolder == 2) {
                        cardsInPlayerHandSFSorted[2] = cardsInPlayerHandSF[i];
                    }
                    if (counterHolder == 3) {
                        cardsInPlayerHandSFSorted[3] = cardsInPlayerHandSF[i];
                    }
                    if (counterHolder == 4) {
                        cardsInPlayerHandSFSorted[4] = cardsInPlayerHandSF[i];
                    }
                    if (counterHolder == 5) {
                        cardsInPlayerHandSFSorted[5] = cardsInPlayerHandSF[i];
                    }
                    if (counterHolder == 6) {
                        cardsInPlayerHandSFSorted[6] = cardsInPlayerHandSF[i];
                    }
                    Arrays.sort(cardsInPlayerHandSFSorted, Collections.reverseOrder());
                }
            }
            for (int x = 0; x < 6; x++) {
                if ((cardsInPlayerHandSFSorted[x] - 1) == cardsInPlayerHandSFSorted[x + 1]) {
                    counterHolder = counterHolder + 1;
                    if (counterHolder == 1) {
                        playerStraightFlushCardHigh = cardsInPlayerHandSFSorted[x];
                    }
                    if (counterHolder == 4) {
                        playerStraightFlushCardLow = cardsInPlayerHandSFSorted[x + 1];
                        straightFlush = "True";
                    }
                }
                else {
                    if (x != 0) {
                        if (cardsInPlayerHandSFSorted[x - 1] != cardsInPlayerHandSFSorted[x]) {
                            counterHolder = 0;
                        }
                    }
                }
            }

            if (straightFlush != "True") {
                counterHolder = 0;
                if (cardsInPlayerHandSFSorted[0] == 14) {
                    cardsInPlayerHandSFSorted[0] = 1;
                }
                if (cardsInPlayerHandSFSorted[1] == 14) {
                    cardsInPlayerHandSFSorted[1] = 1;
                }
                if (cardsInPlayerHandSFSorted[2] == 14) {
                    cardsInPlayerHandSFSorted[2] = 1;
                }
                if (cardsInPlayerHandSFSorted[3] == 14) {
                    cardsInPlayerHandSFSorted[3] = 1;
                }
                if (cardsInPlayerHandSFSorted[4] == 14) {
                    cardsInPlayerHandSFSorted[4] = 1;
                }
                if (cardsInPlayerHandSFSorted[5] == 14) {
                    cardsInPlayerHandSFSorted[5] = 1;
                }
                if (cardsInPlayerHandSFSorted[6] == 14) {
                    cardsInPlayerHandSFSorted[6] = 1;
                }
                Arrays.sort(cardsInPlayerHandSFSorted, Collections.reverseOrder());

                for (int y = 0; y < 6; y++) {
                    if ((cardsInPlayerHandSFSorted[y] - 1) == cardsInPlayerHandSFSorted[y + 1]) {
                        counterHolder = counterHolder + 1;
                        if (counterHolder == 1) {
                            playerStraightFlushCardHigh = cardsInPlayerHandSFSorted[y];
                        }
                        if (counterHolder == 4) {
                            playerStraightFlushCardLow = cardsInPlayerHandSFSorted[y + 1];
                            straightFlush = "True";
                        }
                    }
                    else {
                        if (y != 0) {
                            if (cardsInPlayerHandSFSorted[y - 1] != cardsInPlayerHandSFSorted[y]) {
                                counterHolder = 0;
                            }
                        }
                    }
                }
            }

            if (straightFlush == "True") {
                playerHandStrength = 9;
            }
        }



        if (whoseCheck == "Computer") {
            counterHolder = 0;
            cardsInComputerHandSF[0] = card3.getFaceValue();
            cardsInComputerHandSF[1] = card4.getFaceValue();
            cardsInComputerHandSF[2] = card5.getFaceValue();
            cardsInComputerHandSF[3] = card6.getFaceValue();
            cardsInComputerHandSF[4] = card7.getFaceValue();
            cardsInComputerHandSF[5] = card8.getFaceValue();
            cardsInComputerHandSF[6] = card9.getFaceValue();

            numOccurrencesSpades = Collections.frequency(Arrays.asList(suitsInComputerHandSF), "s");
            numOccurrencesHearts = Collections.frequency(Arrays.asList(suitsInComputerHandSF), "h");
            numOccurrencesClubs = Collections.frequency(Arrays.asList(suitsInComputerHandSF), "c");
            numOccurrencesDiamonds = Collections.frequency(Arrays.asList(suitsInComputerHandSF), "d");

            if (numOccurrencesSpades >= 5) { suitUsed = "s"; }
            if (numOccurrencesHearts >= 5) { suitUsed = "h"; }
            if (numOccurrencesClubs >= 5) { suitUsed = "c"; }
            if (numOccurrencesDiamonds >= 5) { suitUsed = "d"; }

            for (int i = 0; i < 7; i++) {
                if (suitsInComputerHandSF[i].equals(suitUsed)) {
                    counterHolder = counterHolder + 1;
                    if (counterHolder == 0) {
                        cardsInComputerHandSFSorted[0] = cardsInComputerHandSF[i];
                    }
                    if (counterHolder == 1) {
                        cardsInComputerHandSFSorted[1] = cardsInComputerHandSF[i];
                    }
                    if (counterHolder == 2) {
                        cardsInComputerHandSFSorted[2] = cardsInComputerHandSF[i];
                    }
                    if (counterHolder == 3) {
                        cardsInComputerHandSFSorted[3] = cardsInComputerHandSF[i];
                    }
                    if (counterHolder == 4) {
                        cardsInComputerHandSFSorted[4] = cardsInComputerHandSF[i];
                    }
                    if (counterHolder == 5) {
                        cardsInComputerHandSFSorted[5] = cardsInComputerHandSF[i];
                    }
                    if (counterHolder == 6) {
                        cardsInComputerHandSFSorted[6] = cardsInComputerHandSF[i];
                    }
                    Arrays.sort(cardsInComputerHandSFSorted, Collections.reverseOrder());
                }
            }

            for (int x = 0; x < 6; x++) {
                if ((cardsInComputerHandSFSorted[x] - 1) == cardsInComputerHandSFSorted[x + 1]) {
                    counterHolder = counterHolder + 1;
                    if (counterHolder == 1) {
                        computerStraightFlushCardHigh = cardsInComputerHandSFSorted[x];
                    }
                    if (counterHolder == 4) {
                        computerStraightFlushCardLow = cardsInComputerHandSFSorted[x + 1];
                        straightFlush = "True";
                    }
                }
                else {
                    if (x != 0) {
                        if (cardsInComputerHandSFSorted[x - 1] != cardsInComputerHandSFSorted[x]) {
                            counterHolder = 0;
                        }
                    }
                }
            }

            if (straightFlush != "True") {
                counterHolder = 0;
                if (cardsInComputerHandSFSorted[0] == 14) {
                    cardsInComputerHandSFSorted[0] = 1;
                }
                if (cardsInComputerHandSFSorted[1] == 14) {
                    cardsInComputerHandSFSorted[1] = 1;
                }
                if (cardsInComputerHandSFSorted[2] == 14) {
                    cardsInComputerHandSFSorted[2] = 1;
                }
                if (cardsInComputerHandSFSorted[3] == 14) {
                    cardsInComputerHandSFSorted[3] = 1;
                }
                if (cardsInComputerHandSFSorted[4] == 14) {
                    cardsInComputerHandSFSorted[4] = 1;
                }
                if (cardsInComputerHandSFSorted[5] == 14) {
                    cardsInComputerHandSFSorted[5] = 1;
                }
                if (cardsInComputerHandSFSorted[6] == 14) {
                    cardsInComputerHandSFSorted[6] = 1;
                }
                Arrays.sort(cardsInComputerHandSFSorted, Collections.reverseOrder());

                for (int y = 0; y < 6; y++) {
                    if ((cardsInComputerHandSFSorted[y] - 1) == cardsInComputerHandSFSorted[y + 1]) {
                        counterHolder = counterHolder + 1;
                        if (counterHolder == 1) {
                            computerStraightFlushCardHigh = cardsInComputerHandSFSorted[y];
                        }
                        if (counterHolder == 4) {
                            computerStraightFlushCardLow = cardsInComputerHandSFSorted[y + 1];
                            straightFlush = "True";
                        }
                    }
                    else {
                        if (y != 0) {
                            if (cardsInComputerHandSFSorted[y - 1] != cardsInComputerHandSFSorted[y]) {
                                counterHolder = 0;
                            }
                        }
                    }
                }
            }
            if (straightFlush == "True") {
                computerHandStrength = 9;
            }
        }
    }

    protected void checkRoyalFlush() {
        String royalFlush = "False";

        if (whoseCheck == "Player") {
            if (playerStraightFlushCardHigh == 14 & playerStraightFlushCardLow == 10) {
                royalFlush = "True";
            }
            if (royalFlush == "True") {
                playerHandStrength = 10;
            }
        }

        if (whoseCheck == "Computer") {
            if (computerStraightFlushCardHigh == 14 & computerStraightFlushCardLow == 10) {
                royalFlush = "True";
            }
            if (royalFlush == "True") {
                computerHandStrength = 10;
            }
        }
    }
}

