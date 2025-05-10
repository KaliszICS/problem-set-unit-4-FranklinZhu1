import java.util.ArrayList;
import java.util.Arrays;

public class DiscardPile {

    /**
     * A class to describe a "discard pile" for a card game, where used cards are discarded.
     * Similar to the Deck class, but with different methods appropriate for a discard pile.
     * Uses methods from the Card and Deck classes.
     * 
     * @author Franklin Zhu
     * @version 1.0
     */
    
    private Card[] deck;

    /**
     * A constructor that assigns a card array to the discard pile.
     * 
     * @param cards the card array to be assigned to the discard pile
     */

    public DiscardPile(Card[] cards) {
        this.deck = cards;
    }

    /**
     * A default constructor that automatically assigns the discard pile an empty card array.
     */

    public DiscardPile() {
        this.deck = new Card[0];
    }

    /**
     * A getter that returns the discard pile's card array.
     * 
     * @return the discard pile's card array
     */

    public Card[] getDiscardPile() {
        return this.deck;
    }

    /**
     * A method to return the size of the discard pile; i.e., the length of its card array.
     * 
     * @return the size of the discard pile
     */

    public int size() {
        return this.deck.length;
    }

    /**
     * A method to add a card to the FRONT of the discard pile (at its card array's first index).
     * This increases the size of the discard pile by 1.
     * 
     * @param card the card to be added to the discard pile
     * Null cards are allowed.
     */

    public void addCard(Card card) {
        ArrayList<Card> newDeck = new ArrayList<Card>(Arrays.asList(this.deck)); // Make a new deck as a array list of the original
        newDeck.add(0, card); // Add the card to the top of the deck
        this.deck = newDeck.toArray(new Card[0]); // Assign the deck to the new deck
    }

    /**
     * A method to remove a card from the discard pile.
     * The method removes the first instance of the card and returns it.
     * 
     * @param card the card to be removed from the discard pile
     * If null, the method immediately returns null with no effect
     * @return the card removed from the discard pile
     * Null if the card is not found within the discard pile.
     */

    public Card removeCard(Card card) {
        if (card == null) return null;
        ArrayList<Card> newDeck = new ArrayList<Card>(Arrays.asList(this.deck)); // Make a new deck as a array list of the original
        int indexFound = newDeck.indexOf(card); // The index of the first occurence of the card
        if (indexFound >= 0) {
            Card c = newDeck.get(indexFound); // Save the card
            newDeck.remove(indexFound); // Remove the card
            this.deck = newDeck.toArray(new Card[0]);
            return c; // Return the card
        }
        return null; // If not found (indexFound returns -1), return null
    }

    /**
     * A method to clear the discard pile, removing all of its cards and returning them as a card array.
     * 
     * @return the entire discard pile
     * If the discard pile is empty, an empty card array is returned.
     */

    public Card[] removeAll() {
        Card[] arr = this.deck;
        this.deck = new Card[0]; // Assign deck to empty card array
        return arr;
    }

    /**
     * A toString() method to override the default and return every card in the discard pile in a specific format.
     * 
     * @return "Card, card, ..., card.", e.g., "Ace of Hearts, King of Spades.".
     * If the discard pile is empty, an empty string is returned.
     */

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < deck.length; ++i) {
            s += (i != deck.length - 1) ? deck[i].getName() + " of " + deck[i].getSuit() + ", " : deck[i].getName() + " of " + deck[i].getSuit() + ".";
            // Ternary operator that either puts a comma after the card name or a period depending on if it's the last card or not
        }
        return s; // Return the string (empty if nothing in the discard pile)
    }

}
