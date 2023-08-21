package application;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	//instance variables
	private ArrayList<Card> cards; 
	
	//constructor
	public Deck(){
		this.cards = new ArrayList<Card>();
		
	}
	
	public void createFullDeck() {
		//generate cards 
		for(Suit cardSuit : Suit.values()) {
			for(Value cardValue : Value.values()) {
				//add a new card to the deck
				this.cards.add(new Card(cardSuit,cardValue));
			}
		}
		
	}
	
	public void Shuffle() {
		ArrayList<Card> tmpDeck = new ArrayList<Card>();
		//use Random 
		Random random = new Random();
		int randomCardIndex = 0; 
		int originalSize = this.cards.size();
		for(int i = 0;i < originalSize;i++) {
			//generate Random Index rand.nextInt((max-min) + 1) + min; 
			randomCardIndex = random.nextInt((this.cards.size()-1-0)+1)+0;
			tmpDeck.add(this.cards.get(randomCardIndex));
			//remove from original deck
			this.cards.remove(randomCardIndex);
		}
		
		this.cards = tmpDeck;
	}
	
	public String toString() {
		String cardListOutput = "";
		int i = 0;
		for(Card aCard : this.cards) {
			cardListOutput += "\n" +  aCard.toString();
			i++;
		}
		return cardListOutput;
			
	}
	
	public void removeCard(int i) {
		this.cards.remove(i);
	}
	
	public Card getCard(int i) {
		return this.cards.get(i);
	}
	
	public void addCard(Card addCard) {
		this.cards.add(addCard);
	}
	
	public int deckSize() {
		return this.cards.size();
	}
	
	public void moveAllToTheDeck(Deck moveTo) {
		int thisdecksize = this.cards.size();
		
		//puts cards into moveToDeck
		for(int i = 0;i<thisdecksize;i++) {
			moveTo.addCard(this.getCard(i));
		}
		
		for(int j = 0;j<thisdecksize;j++) {
			this.removeCard(0);
		}
		
	}
	
	//draws from deck 
	public void draw(Deck comingFrom) {
		this.cards.add(comingFrom.getCard(0));
		comingFrom.removeCard(0);
	}
	
	public int cardsValue(){
		int totalValue = 0; 
		//aces can be worth 1 or 11
		int aces = 0;
		
		for(Card aCard: this.cards) {
			switch(aCard.getValue()) {
			case  TWO: totalValue += 2; break;
			case  THREE: totalValue += 3;break; 
			case  FOUR : totalValue += 4;break;
			case  FIVE : totalValue += 5;break; 
			case  SIX : totalValue += 6;break; 
			case  SEVEN : totalValue += 7;break; 
			case  EIGHT : totalValue +=8;break; 
			case  NINE : totalValue +=9;break; 
			case  TEN : totalValue +=10;break; 
			case  JACK: totalValue += 10;break; 
			case  KING: totalValue += 10;break; 
			case  QUEEN: totalValue += 10;break; 
			case  ACE: totalValue += 1;break;
			}
		}
		
		for(int i = 0;i<aces;i++) {
			if(totalValue >10) {
				totalValue +=1;
			}
			else {
				totalValue += 11;
			}
		}
		
		return totalValue;
	}
	

}
