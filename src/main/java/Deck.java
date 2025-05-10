import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class Deck {

    /**
     * A class to describe a deck of playing cards.
     * Uses methods from the Card class.
     * 
     * @author Franklin Zhu
     * @version 1.0
     */

    private Card[] deck;

    /**
     * A constructor that assigns a card array to the deck.
     * 
     * @param cards the card array to be assigned to the deck
     */

    public Deck(Card[] cards) {
        this.deck = cards;
    }

    /**
     * A default constructor that automatically assigns the deck an unshuffled card array.
     * The unshuffled card array assumes a standard deck of 52 playing cards, organized by name, then suit (in order of Hearts, Clubs, Diamonds, then Spades).
     */

    public Deck() {
        Card[] arr = new Card[52];
        String[] names = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Hearts", "Clubs", "Diamonds", "Spades"};
        int[] values = {14, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        for (int i = 0; i < 52; ++i) {
            Card c = new Card(names[i%13], suits[i/13], values[i%13]);
            // i%13 will take on 0, 1, 2, ..., 10, 11, 12, 0, 1, 2, etc., iterating through the names and values sequentially
            // i/13 will take on 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, etc., iterating through the suits every 13 cards
            arr[i] = c;
        }
        this.deck = arr;
    }

    /**
     * A getter for the deck that returns its card array.
     * Used purely for debugging, since the user should not be able to access the whole deck.
     * 
     * @return the deck of cards as a card array
     */

    // public Card[] getDeck() { // for debugging
    //     return this.deck;
    // }

    /**
     * A method to return the size of the deck; i.e., how many cards are in its card array.
     * 
     * @return the size of the deck
     */

    public int size() {
        return this.deck.length;
    }

    /**
     * A method to draw a card from the top of the deck.
     * This entails removing the card at the first index of the deck's card array and shrinking the array by 1.
     * 
     * @return the top card that is removed
     * Null if the deck is empty.
     */

    public Card draw() {
        if (this.deck.length == 0) return null;
        Card top = this.deck[0]; // Save the top (first) card in the deck
        ArrayList<Card> newDeck = new ArrayList<Card>(Arrays.asList(this.deck)); // Copy the deck to an array list
        newDeck.remove(0); // Remove the first card from the new deck
        this.deck = newDeck.toArray(new Card[0]); // Convert the new deck back to a card array and assign the deck to it
        return top; // Return the top card
    }

    /**
     * A method to shuffle the deck.
     * The method creates an empty array with the same size as the deck's card array, then maps each card one-by-one to a random index in the new array.
     * If a duplicate index is encountered (i.e., the same index is picked twice), the index is incremented and/or wrapped around until an empty index is found.
     * Indexes that have already been used are tracked with the "checked" boolean array.
     */

    public void shuffle() {
        Card[] newDeck = new Card[this.deck.length]; // Create another deck with the same size as the original
        boolean[] checked = new boolean[this.deck.length]; // A boolean array to store which indexes in the new deck already have cards
        int randomIndex; // To store random int values
        Random random = new Random();
        for (int i = 0; i < this.deck.length; ++i) { // Iterate over the original deck's cards
            randomIndex = random.nextInt(this.deck.length); // Assign randomIndex to a random integer
            while (checked[randomIndex] == true) randomIndex = (randomIndex + 1)%this.deck.length;
            // If the new deck already has a card at that index, loop the following:
            // (randomIndex + 1) increments randomIndex, "moving" along the new deck's indexes until an empty index is found
            // %this.deck.length wraps randomIndex around the size of the deck to avoid index errors
            checked[randomIndex] = true; // Once an empty random index is found, mark it in the "checked" array so that it isn't used again
            newDeck[randomIndex] = this.deck[i]; // Copy the card to the new deck at the random index
        }
        this.deck = newDeck; // Reassign the deck to the new, shuffled deck
    }

    /**
     * A method to add a card to the deck.
     * The card is added to the BACK of the deck, in the last index.
     * The size of the deck's card array is also therefore increased by 1.
     * 
     * @param card the card to be added to the deck
     * Null cards are allowed.
     */

    public void addCard(Card card) {
        ArrayList<Card> newDeck = new ArrayList<Card>(Arrays.asList(this.deck)); // Make a new deck as a array list of the original
        newDeck.add(card); // Add the card to the bottom of the deck
        this.deck = newDeck.toArray(new Card[0]); // Assign the deck to the new deck
    }

    /**
     * A method to add a card array to the deck, merging it with the deck's card array, and then shuffle the whole thing.
     * Calls on shuffle() to perform the shuffling.
     * 
     * @param cards the card array to be added to the deck
     * If null, the method returns immediately with no effect.
     */

    public void reshuffle(Card[] cards) {
        if (cards == null) return;
        ArrayList<Card> newDeck = new ArrayList<Card>(Arrays.asList(this.deck)); // Make a new deck as an array list of the original
        newDeck.addAll(Arrays.asList(cards)); // Add all of the cards to the new deck
        this.deck = newDeck.toArray(new Card[0]); // Assign the deck to the new deck
        shuffle(); // Shuffle the deck
    }

}