package application;

public class Card
{
	//instance variables
	private Suit suit; 
	private Value value;
	
	//constructor
	public Card(Suit suit, Value value){
		this.value = value; 
		this.suit = suit;
	}
	
	//method to display suit and value to screen
	public String toString(){
		return this.suit.toString() + "-" + this.value.toString();
	}
	
	//method to get value of card
	public Value getValue() {
		return value;
	}
		
}


