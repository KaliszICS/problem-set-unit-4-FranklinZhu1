//don't forget to import anything else you need (ArrayLists, HashMaps, Scanners, etc)
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.beans.Transient;
import java.io.*;

public class ProblemSetTest {

   //Create your tests here if you want any

   /*

   //Example Test
   
   @Test
   public void exampleTest()
   {
      Cat cat = new Cat();
      assertEquals("whiskers", cat.getName());
   }
   */

   @Test
   public void unshuffledTest1() {
      Deck unshuffled = new Deck();
      assertEquals("AceHearts14", unshuffled.getDeck()[0].getName() + unshuffled.getDeck()[0].getSuit() + unshuffled.getDeck()[0].getValue());
   }

   @Test
   public void unshuffledTest2() {
      Deck unshuffled = new Deck();
      assertEquals("5Clubs5", unshuffled.getDeck()[17].getName() + unshuffled.getDeck()[17].getSuit() + unshuffled.getDeck()[17].getValue());
   }

   @Test
   public void deckConstructorTest1() {
      Card card = new Card("Ace", "Spades", 14);
      Card[] c = {card};
      Deck d = new Deck(c);
      assertArrayEquals(c, d.getDeck());
   }

   @Test
   public void deckConstructorTest2() {
      Card[] c = {};
      Deck d = new Deck(c);
      assertEquals(c, d.getDeck());
   }

   @Test
   public void shuffleTest1() { // actually passing
      Deck d = new Deck();
      d.shuffle();
      assertEquals("Ace of Hearts", d.draw().toString());
   }

   @Test
   public void discardPileToStringTest1() {
      Deck d = new Deck();
      DiscardPile dis = new DiscardPile(d.getDeck());
      assertEquals("Ace of Hearts, 2 of Hearts, 3 of Hearts, 4 of Hearts, 5 of Hearts, 6 of Hearts, 7 of Hearts, 8 of Hearts, 9 of Hearts, 10 of Hearts, Jack of Hearts, Queen of Hearts, King of Hearts, Ace of Clubs, 2 of Clubs, 3 of Clubs, 4 of Clubs, 5 of Clubs, 6 of Clubs, 7 of Clubs, 8 of Clubs, 9 of Clubs, 10 of Clubs, Jack of Clubs, Queen of Clubs, King of Clubs, Ace of Diamonds, 2 of Diamonds, 3 of Diamonds, 4 of Diamonds, 5 of Diamonds, 6 of Diamonds, 7 of Diamonds, 8 of Diamonds, 9 of Diamonds, 10 of Diamonds, Jack of Diamonds, Queen of Diamonds, King of Diamonds, Ace of Spades, 2 of Spades, 3 of Spades, 4 of Spades, 5 of Spades, 6 of Spades, 7 of Spades, 8 of Spades, 9 of Spades, 10 of Spades, Jack of Spades, Queen of Spades, King of Spades.", dis.toString());
   }

   @Test
   public void playerTest1() {
      Card c = new Card("Ace", "Spades", 14);
      Card[] hand = {c};
      Player p = new Player("Franklin", 17, hand);
      assertEquals("Franklin, 17, Ace of Spades.", p.toString());
   }

   @Test
   public void playerTest2() {
      Player p = new Player("James", 30);
      assertEquals("James, 30, .", p.toString());
   }

   @Test
   public void playerTest3() {
      Player p = new Player("Sokol", 45);
      Deck unshuffled = new Deck();
      for (int i = 0; i < 5; ++i) p.draw(unshuffled);
      assertEquals("Sokol, 45, Ace of Hearts, 2 of Hearts, 3 of Hearts, 4 of Hearts, 5 of Hearts.", p.toString());
   }

   @Test
   public void cardEqualsTest1() {
      Card c = new Card("10", "Spades", 10), d = new Card("10", "Spades", 14);
      assertEquals(false, c.equals(d));
   }

   @Test
   public void playerTest4() {
      Player p = new Player("Alan", 45);
      Deck unshuffled = new Deck();
      for (int i = 0; i < 5; ++i) p.draw(unshuffled);
      assertEquals("6 of Hearts", unshuffled.draw().toString());
   }

   @Test
   public void addCardTest1() {
      Card c = null;
      Deck d = new Deck(), e = new Deck();
      d.addCard(c);
      e.addCard(null);
      assertArrayEquals(e.getDeck(), d.getDeck());
   }

   @Test
   public void reshuffleTest1() {
      Deck d = new Deck(), e = new Deck();
      d.reshuffle(e.getDeck());
      assertEquals("Ace of Spades", d.draw().toString());
   }

   @Test
   public void discardPileRemoveCardTest1() {
      Deck unshuffled = new Deck();
      DiscardPile d = new DiscardPile(unshuffled.getDeck());
      Card c = new Card("Ace", "Hearts", 14);
      Card removed = d.removeCard(c);
      Card nothing = d.removeCard(null);
      assertEquals("2 of Hearts, 3 of Hearts, 4 of Hearts, 5 of Hearts, 6 of Hearts, 7 of Hearts, 8 of Hearts, 9 of Hearts, 10 of Hearts, Jack of Hearts, Queen of Hearts, King of Hearts, Ace of Clubs, 2 of Clubs, 3 of Clubs, 4 of Clubs, 5 of Clubs, 6 of Clubs, 7 of Clubs, 8 of Clubs, 9 of Clubs, 10 of Clubs, Jack of Clubs, Queen of Clubs, King of Clubs, Ace of Diamonds, 2 of Diamonds, 3 of Diamonds, 4 of Diamonds, 5 of Diamonds, 6 of Diamonds, 7 of Diamonds, 8 of Diamonds, 9 of Diamonds, 10 of Diamonds, Jack of Diamonds, Queen of Diamonds, King of Diamonds, Ace of Spades, 2 of Spades, 3 of Spades, 4 of Spades, 5 of Spades, 6 of Spades, 7 of Spades, 8 of Spades, 9 of Spades, 10 of Spades, Jack of Spades, Queen of Spades, King of Spades.", d.toString());
      assertEquals("Ace of Hearts", removed.toString());
      assertEquals(null, nothing);
   }

   @Test
   public void playerTest5() {
      Deck d = new Deck();
      Card[] empty = {};
      Card ace = new Card("Ace", "Hearts", 14);
      Deck nothing = new Deck(empty);
      Player p = new Player("Wes", 40, d.getDeck());
      assertEquals(true, p.returnCard(ace, nothing));
      assertEquals("1Ace of Hearts", nothing.size() + nothing.draw().toString());
      assertEquals("Wes, 40, 2 of Hearts, 3 of Hearts, 4 of Hearts, 5 of Hearts, 6 of Hearts, 7 of Hearts, 8 of Hearts, 9 of Hearts, 10 of Hearts, Jack of Hearts, Queen of Hearts, King of Hearts, Ace of Clubs, 2 of Clubs, 3 of Clubs, 4 of Clubs, 5 of Clubs, 6 of Clubs, 7 of Clubs, 8 of Clubs, 9 of Clubs, 10 of Clubs, Jack of Clubs, Queen of Clubs, King of Clubs, Ace of Diamonds, 2 of Diamonds, 3 of Diamonds, 4 of Diamonds, 5 of Diamonds, 6 of Diamonds, 7 of Diamonds, 8 of Diamonds, 9 of Diamonds, 10 of Diamonds, Jack of Diamonds, Queen of Diamonds, King of Diamonds, Ace of Spades, 2 of Spades, 3 of Spades, 4 of Spades, 5 of Spades, 6 of Spades, 7 of Spades, 8 of Spades, 9 of Spades, 10 of Spades, Jack of Spades, Queen of Spades, King of Spades.", p.toString());
   }

   @Test
   public void playerTest6() {
      DiscardPile d = new DiscardPile();
      Deck unshuffled = new Deck();
      Player p = new Player("Kev", 30, unshuffled.getDeck());
      Card c = new Card("Ace", "Hearts", 14);
      assertEquals(true, p.discardCard(c, d));
      assertEquals("Ace of Hearts.", d.toString());
   }

}
