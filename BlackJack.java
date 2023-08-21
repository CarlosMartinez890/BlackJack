package application;

import java.util.Scanner;

public class BlackJack {

	public static void main(String[] args) {
		
		//Welcome message 
		System.out.println("Welcome to the game of BlackJack!");
		
		Scanner obj = new Scanner(System.in);
		System.out.println("Is this your first time playing?");
		String answer = obj.nextLine();
		
		answer = answer.toLowerCase();
		//Rules if player does not know rules 
		if(answer.equals("yes"))
		{
			System.out.println("You take your place at the table and get two cards, face up.");
			System.out.println( "The dealer also gets two cards, but one is face down. ");
			System.out.println("Your task is to either get a total closer to 21 than the dealer without going over (busting), or to stand on a total and hope that the dealer busts or gets fewer than you.");
			System.out.println("Dealer must stand on soft 17");
		}
		
		//creates playing deck 
		Deck playingDeck = new Deck();
		playingDeck.createFullDeck();
		playingDeck.Shuffle();
		
		//create a deck for the player 
		Deck playerDeck = new Deck();
		
		Deck dealerDeck = new Deck();
		
		double playerMoney = 100.00;
		
		
		//game loop 
		//Keep playing if player's money is greater than 0 
		while(playerMoney >0){
			//takes the players bet 
			System.out.println("Balance:$" + playerMoney + ", how much would you like to bet?");
			double playerBet = obj.nextDouble();
			
			//while loop if player tries to bet more than they have
			while(playerMoney < playerBet) {
				System.out.println("Sorry you do not have enough, what is your new bet?");
				playerBet = obj.nextDouble();
			}
			
//			//Starts Dealing 
//			//player and dealer draws 2 cards
			for(int r = 1;r<=2;r++) {
				playerDeck.draw(playingDeck);
				dealerDeck.draw(playingDeck);
			}
			
			boolean endRound = false;
			
			while(true) {
				
				System.out.println("Your hand: " + playerDeck.toString());
				System.out.println("Your hand is valued at " + playerDeck.cardsValue());
				
				//if player has blackjack round ends 
				if(playerDeck.cardsValue()==21) {
					System.out.println("BLACKJACK!!!");
					playerMoney += (1.5 * playerBet);
					endRound = true;
					break;
				}
				
				//Display Dealer's hand 
				System.out.println("Dealer Hand: " + dealerDeck.getCard(0).toString() + " and [Hidden]");
				System.out.println();
				
				//Player's Options 
				System.out.println("Hit(1), Double Down(2) or Stand(3)?");
				int response = obj.nextInt();
				
				//Player Hit
				if(response == 1) {
					boolean done = false; 
					while(true) {
					playerDeck.draw(playingDeck);
					System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
					//bust if > 21 
					if(playerDeck.cardsValue() > 21) {
						System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
						playerMoney -= playerBet;
						endRound = true;
						break;
					}
					
					//prints out value after hit 
					System.out.println("Your hand is valued at " + playerDeck.cardsValue());
					
					//Ask user if they want to keep hitting or stand
					System.out.println("Hit(1), or Stand(3)");
					response = obj.nextInt();
					if(response==1) {
						done = true;
					}else {
						break;
					}
				}
				}
				
				//player double downs
				if(response == 2) {
				
					playerDeck.draw(playingDeck);
					System.out.println("You draw a: " + playerDeck.getCard(playerDeck.deckSize()-1).toString());
					//bust if > 21 
					if(playerDeck.cardsValue() > 21) {
						System.out.println("Bust. Currently valued at: " + playerDeck.cardsValue());
						playerMoney -= 2 * playerBet;
						endRound = true;
					}
				}
				
				//player Stands 
				if(response == 3) {
					
				}
				
				//Reveals Dealer's Deck
				System.out.println("Dealer Cards: " + dealerDeck.toString());
				//See if Dealer has more points than player 
				if((dealerDeck.cardsValue() > playerDeck.cardsValue() && endRound == false)) {
					System.out.println("Dealer Wins.");
					playerMoney -= playerBet; 
					endRound = true;
				}
				//Dealer stands on soft 17, if not then draws 
				while((dealerDeck.cardsValue() < 17) && endRound == false) {
					dealerDeck.draw(playingDeck);
					System.out.println("Dealer Draws: " + dealerDeck.getCard(dealerDeck.deckSize()-1).toString());
				}
				//Display total value for Dealer deck 
				System.out.println("Dealer deck is valued at: " + dealerDeck.cardsValue());
				//Determine if dealer busted 
				if((dealerDeck.cardsValue() > 21) && endRound == false) {
					System.out.println("You win!");
					//nested if, else statement to see if player doubled down
					if(response == 2) {
						playerMoney += 2* playerBet;
						endRound = true;
					}else
					playerMoney += playerBet;
					endRound = true;
				}
				
				//Determine if push/tie
				if((playerDeck.cardsValue() == dealerDeck.cardsValue()) && endRound == false) {
					System.out.println("Push");
					endRound = true;
				}
				
				//Determines if player won
				if((playerDeck.cardsValue()> dealerDeck.cardsValue()) && endRound == false){
					System.out.println("You win!");
					//nested if, else statement to see if player won with double down
					if(response ==2) {
						playerMoney += 2 * playerBet;
						endRound = true;
					}else
					playerMoney += playerBet;
					endRound = true;
				}
				
				//else if statement to see if dealer won
				else if (endRound == false) {
					System.out.println("Dealer wins");
					//nested if, else statement to see if user lost with double down
					if(response ==2) {
						playerMoney -= 2 * playerBet;
						endRound = true;
					}
					playerMoney -= playerBet;
					endRound = true;
				}
				
				//calls method to place cards back into deck
				playerDeck.moveAllToTheDeck(playingDeck);
				dealerDeck.moveAllToTheDeck(playingDeck);
				
				System.out.println("End of hand");
				break;
			}
		}
		
		System.out.println("Game Over!");

	}

}
