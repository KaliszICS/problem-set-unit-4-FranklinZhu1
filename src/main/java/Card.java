public class Card {

    /**
     * A class to describe a playing card with a name, suit, and point value.
     * 
     * @author Franklin Zhu
     * @version 1.0
     */

    private String name, suit;
    private int value;

    /**
     * A constructor to create a card that assigns its name, suit, and value.
     * 
     * @param name the name of the card
     * @param suit the suit of the card
     * @param value the value of the card
     */

    public Card(String name, String suit, int value) {
        this.name = name;
        this.suit = suit;
        this.value = value;
    }

    /**
     * A getter for the card's name.
     * 
     * @return the card's name
     */

    public String getName() {
        return this.name;
    }

    /**
     * A getter for the card's suit.
     * 
     * @return the card's suit
     */

    public String getSuit() {
        return this.suit;
    }

    /**
     * A getter for the card's value.
     * 
     * @return the card's value
     */
    
    public int getValue() {
        return this.value;
    }

    /**
     * A toString() method to override the default and return the card's name, suit, and value in a specific format.
     * 
     * @return "{name} of {suit}", e.g., "Ace of Spades"
     */

    @Override
    public String toString() {
        return this.name + " of " + this.suit;
    }

    /**
     * An equals(obj) method to override the default and check if the card is equal to the argument.
     * 
     * @param obj the object to be compared with the card
     * @return true if the object is a card and has the same name, suit, and value. False otherwise
     */

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Card c = (Card) obj;
        if (c.name.equals(this.name) && c.suit.equals(this.suit) && c.value == this.value) return true;
        return false;
    }

}