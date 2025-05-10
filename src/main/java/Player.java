import java.util.ArrayList;
import java.util.Arrays;

public class Player {

    /**
     * A class to describe a card game player with a name, age, and hand of cards.
     * Uses methods from the Card, Deck, and DiscardPile classes.
     * 
     * @author Franklin Zhu
     * @version 1.0
     */
    
    private String name;
    private int age;
    private Card[] hand;

    /**
     * A constructor to assign a player's name, age, and hand of cards.
     * 
     * @param name the player's name to be assigned
     * @param age the player's age to be assigned
     * @param hand the player's hand of cards to be assigned
     */

    public Player(String name, int age, Card[] hand) {
        this.name = name;
        this.age = age;
        this.hand = hand;
    }

    /**
     * A constructor that assigns the player's name and age, and automatically assigns them an empty hand (empty card array).
     * 
     * @param name the player's name to be assigned
     * @param age the player's age to be assigned
     */

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        this.hand = new Card[0];
    }

    /**
     * A getter for the player's name.
     * 
     * @return the player's name
     */

    public String getName() {
        if (this.name == null) return null; // is this valid
        return this.name;
    }

    /**
     * A getter for the player's age.
     * 
     * @return the player's age
     */

    public int getAge() {
        return this.age;
    }

    /**
     * A getter that returns the player's hand's card array.
     * 
     * @return the player's hand's card array
     */

    public Card[] getHand() {
        return this.hand;
    }

    /**
     * A method to return the size of the player's hand; i.e., their hand's card array's length
     * 
     * @return the size of the player's hand
     */

    public int size() {
        return this.hand.length;
    }

    /**
     * A method to draw the top card of a deck (the one at the first index of its card array) and add it to the player's hand.
     * This reduces the deck's size by 1 and increases the player's hand's size by 1.
     * Calls on the Deck class's draw() method.
     * 
     * @param deck the deck to be drawn from for the player
     * If null, the method returns immediately with no effect.
     */

    public void draw(Deck deck) {
        if (deck == null) return;
        ArrayList<Card> newHand = new ArrayList<Card>(Arrays.asList(this.hand)); // Make a new hand as a array list of the original
        newHand.add(deck.draw()); // Draw from the deck and add that card to the bottom of the new hand
        this.hand = newHand.toArray(new Card[0]); // Assign the hand to the new hand
    }

    /**
     * A method to discard a certain card from the player's hand into a given discard pile.
     * This reduces the player's hand's size by 1 and increases the discard pile's size by 1.
     * Calls on the DiscardPile class's addCard(Card card) method.
     * 
     * @param card the card to be removed from the player's hand
     * If null, the method returns immediately with no effect.
     * @param discardPile the discard pile for the discarded card to be added to
     * If null, the method returns immediately with no effect.
     * @return true if the card exists within the hand, false otherwise
     */

    public boolean discardCard(Card card, DiscardPile discardPile) {
        if (card == null || discardPile == null) return false;
        ArrayList<Card> newHand = new ArrayList<Card>(Arrays.asList(this.hand)); // Make a new hand as a array list of the original
        int indexFound = newHand.indexOf(card); // The index of the first occurence of the card
        if (indexFound >= 0) {
            Card c = newHand.get(indexFound); // Save the card
            newHand.remove(indexFound); // Remove the card
            this.hand = newHand.toArray(new Card[0]); // Assign the hand to the new hand
            discardPile.addCard(c); // Add the found card to the discard pile
            return true; // Card was found and discarded, return true
        }
        return false; // Card wasn't found, return false
    }

    /**
     * A method to return a certain card from the player's hand to the BOTTOM of a given deck (to the last index of its card array).
     * Calls on the Deck class's addCard(Card card) method.
     * 
     * @param card the card to be removed from the player's hand
     * If null, the method returns immediately with no effect.
     * @param deck the deck that the removed card is returned to
     * If null, the method returns immediately with no effect.
     * @return true if the card exists within the hand, false otherwise
     */

    public boolean returnCard(Card card, Deck deck) {
        if (card == null || deck == null) return false;
        ArrayList<Card> newHand = new ArrayList<Card>(Arrays.asList(this.hand)); // Make a new hand as a array list of the original
        int indexFound = newHand.indexOf(card); // The index of the first occurence of the card
        if (indexFound >= 0) {
            Card c = newHand.get(indexFound); // Save the card
            newHand.remove(indexFound); // Remove the card
            this.hand = newHand.toArray(new Card[0]); // Assign the hand to the new hand
            deck.addCard(c); // Add the found card to the deck
            return true; // Card was found and returned, return true
        }
        return false; // Card wasn't found, return false
    }
    
    /**
     * A toString() method to override the default and return the player's name, age, and every card in their hand in a specific format.
     * 
     * @return "{name}, {age}, {{card}, {card}, ..., {card}}.", e.g., "Mr. Kalisz, 30, Ace of Hearts, King of Spades."
     * If the player's hand is empty, the string will look like "{name}, {age}, .".
     */

    @Override
    public String toString() {
        String s = this.name + ", " + this.age + ", ";
        for (int i = 0; i < this.hand.length; ++i) {
            s += (i != this.hand.length - 1) ? this.hand[i].getName() + " of " + this.hand[i].getSuit() + ", " : this.hand[i].getName() + " of " + this.hand[i].getSuit();
            // Ternary operator that puts a comma after the card name depending on if it's the last card or not
        }
        s += "."; // End string with a period
        return s; // Return the string (empty if nothing in the hand)
    }
    
}
