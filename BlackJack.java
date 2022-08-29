import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class BlackJack {

    // fill in code here
    // define data members

    
    public static void buildDeck(ArrayList<Card> deck) {
	// fill in code here
	// Given an empty deck, construct a standard deck of playing cards
    }

    public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
	// fill in code here
	// Deal two cards from the deck into each of the player's hand and dealer's hand 
    }

    public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand){
	// fill in code here
	// this should deal a single card from the deck to the hand
    }

    public static boolean checkBust(ArrayList<Card> hand){
	// fill in code here
	// This should return whether a given hand's value exceeds 21
	return false;
    }

    public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand){
	// fill in code here
	// This should conduct the dealer's turn and
	// Return true if the dealer busts; false otherwise
	return false;
    }

    public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
	// fill in code here
	// This should return 1 if the player wins and 2 if the dealer wins
	return 0;
    }

    public static String displayCard(ArrayList<Card> hand){
	// fill in code here
	// Return a string describing the card which has index 1 in the hand
	return null;
    }

    public static String displayHand(ArrayList<Card> hand){
	// fill in code here
	// Return a string listing the cards in the hand
	return null;
    }

    // fill in code here (Optional)
    // feel free to add methods as necessary


    public static void main(String[] args) {

		int playerChoice, winner;
		ArrayList<Card> deck = new ArrayList<Card>();
		
		
		playerChoice = JOptionPane.showConfirmDialog(
			null, 
			"Ready to Play Blackjack?", 
			"Blackjack", 
			JOptionPane.OK_CANCEL_OPTION
		);

		if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
		    System.exit(0);
		
		Object[] options = {"Hit","Stand"};
		boolean isBusted;	// Player busts? 
		boolean dealerBusted;
		boolean isPlayerTurn;
		ArrayList<Card> playerHand = new ArrayList<>();
		ArrayList<Card> dealerHand = new ArrayList<>();
	
		do{ // Game loop
			buildDeck(deck);  // Initializes the deck for a new game
		    initialDeal(deck, playerHand, dealerHand);
		    isPlayerTurn=true;
		    isBusted=false;
		    dealerBusted=false;
		    
		    while(isPlayerTurn){

				// Shows the hand and prompts player to hit or stand
				playerChoice = JOptionPane.showOptionDialog(
					null,
					"Dealer shows " + displayCard(dealerHand) + "\n Your hand is: " 
						+ displayHand(playerHand) + "\n What do you want to do?",
					"Hit or Stand",
				   JOptionPane.YES_NO_OPTION,
				   JOptionPane.QUESTION_MESSAGE,
				   null,
				   options,
				   options[0]
				);

				if(playerChoice == JOptionPane.CLOSED_OPTION)
				    System.exit(0);
				
				else if(playerChoice == JOptionPane.YES_OPTION){
				    dealOne(deck, playerHand);
				    isBusted = checkBust(playerHand);
				    if(isBusted){
						// Case: Player Busts
						playerChoice = JOptionPane.showConfirmDialog(
							null,
							"Player has busted!", 
							"You lose", 
							JOptionPane.OK_CANCEL_OPTION
						);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
						
						isPlayerTurn=false;
				    }
				}
			    
				else{
				    isPlayerTurn=false;
				}
		    }

		    if(!isBusted){ // Continues if player hasn't busted
				dealerBusted = dealerTurn(deck, dealerHand);
				if(dealerBusted){ // Case: Dealer Busts
				    playerChoice = JOptionPane.showConfirmDialog(
				    	null, 
				    	"The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " 
				    		+ displayHand(playerHand) + "\nThe dealer busted.\n Congrautions!", 
				    	"You Win!!!", 
				    	JOptionPane.OK_CANCEL_OPTION
				    );		    

					if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						System.exit(0);
				}
			
			
				else{ //The Dealer did not bust.  The winner must be determined
				    winner = whoWins(playerHand, dealerHand);

				    if(winner == 1){ //Player Wins
						playerChoice = JOptionPane.showConfirmDialog(
							null, 
							"The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " 
								+ displayHand(playerHand) + "\n Congrautions!", 
							"You Win!!!", 
							JOptionPane.OK_CANCEL_OPTION
						);

						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }

				    else{ //Player Loses
						playerChoice = JOptionPane.showConfirmDialog(
							null, 
							"The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: " 
								+ displayHand(playerHand) + "\n Better luck next time!", 
							"You lose!!!", 
							JOptionPane.OK_CANCEL_OPTION
						); 
					
						if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
						    System.exit(0);
				    }
				}
		    }
		}while(true);
    }
}



class Card {
	// Specify data fields for an individual card
	int number;
	int suit;


	Card(givenNumber, givenSuit){
		number = givenNumber;
		suit = givenSuit;
	}
}