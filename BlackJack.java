import java.util.ArrayList;
import java.util.Random;
import javax.swing.JOptionPane;

public class BlackJack {
    private static int cardsLeftInDeck = 48;



    public static void buildDeck(ArrayList<Card> deck) {
        int deckIndexCounter = 0;
        if(deck.size()>0){
            deck.clear();
        }
        for(int i =0;i<4;i++) {
            String[] suitArray = new String[]{"club", "diamond", "heart", "spade"};
            for (int k = 2; k < 11; k++) {
                deck.add(new Card(k, "numberCard", suitArray[i]));
            }
            deck.add(new Card(11,"Ace",suitArray[i]));
            deck.add(new Card(10,"Queen",suitArray[i]));
            deck.add(new Card(10,"King",suitArray[i]));
            deck.add(new Card(10,"Jack",suitArray[i]));
            
        }

    }

    public static void initialDeal(ArrayList<Card> deck, ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
        if(dealerHand.size()>0 || playerHand.size()>0){
            dealerHand.clear();
            playerHand.clear();
        }
        int initialRandomIndexPlayer = (int) (Math.random()*52);
        playerHand.add(deck.get(initialRandomIndexPlayer));
        deck.remove(initialRandomIndexPlayer);

        int initial2RandomIndexPlayer = (int) (Math.random()*51);
        playerHand.add(deck.get(initial2RandomIndexPlayer));
        deck.remove(initial2RandomIndexPlayer);

        int initialRandomIndexDealer = (int) (Math.random()*50);
        dealerHand.add(deck.get(initialRandomIndexDealer));
        deck.remove(initialRandomIndexDealer);

        int initial2RandomIndexDealer = (int) (Math.random()*49);
        dealerHand.add(deck.get(initial2RandomIndexDealer));
        deck.remove(initial2RandomIndexDealer);

        System.out.println("remaining cards: " + deck.size());

    }

    public static void dealOne(ArrayList<Card> deck, ArrayList<Card> hand){
        int randomlyDealtCard = (int) (Math.random()*cardsLeftInDeck);
        deck.remove(randomlyDealtCard);
        hand.add(deck.get(randomlyDealtCard));
        cardsLeftInDeck -= 1;
        System.out.println("remaining cards: " + deck.size());


    }

    public static boolean checkBust(ArrayList<Card> hand){

        int totalHandValue = 0;
        int fourAcesReached = 0;
        for(Card p: hand){
            totalHandValue += p.number;
        }
        while(totalHandValue>21&&hand.contains("Ace")&&fourAcesReached<4){
            fourAcesReached+=1;
            totalHandValue-=10;
        }
        if(totalHandValue>21){
            return true;
        }else {
            return false;
        }
    }


    public static boolean dealerTurn(ArrayList<Card> deck, ArrayList<Card> hand){
        int dealerTotalHandValue = 0;
        for(Card x: hand){
            dealerTotalHandValue += x.number;
        }
        while(dealerTotalHandValue<17) {
            dealOne(deck, hand);
            dealerTotalHandValue = 0;
            for(Card x: hand){
                dealerTotalHandValue += x.number;
            }
        }
        if(dealerTotalHandValue>=17 && dealerTotalHandValue<=21){
            return false;
        }else{
            return true;
        }



    }

    public static int whoWins(ArrayList<Card> playerHand, ArrayList<Card> dealerHand){
        // fill in code here
        int finalPlayerHandValue = 0;
        int finalDealerHandValue = 0;
        for(Card z: playerHand){
            finalPlayerHandValue += z.number;
        }
        for(Card i: dealerHand){
            finalDealerHandValue += i.number;
        }
        if(finalPlayerHandValue>finalDealerHandValue){
            return 1;
    }else if(finalDealerHandValue>finalPlayerHandValue){
            return 2;
        }else{
            return 2;
        }
    }

    public static String displayCard(ArrayList<Card> hand){
        return (Card.toString(hand.get(0)));

    }

    public static String displayHand(ArrayList<Card> hand){
        String handReturned = "";
        for(Card a: hand){
            handReturned = handReturned + (Card.toString(a)) + " ";
        }
        return handReturned;

    }




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

        do{
            buildDeck(deck);

            for(Card c: deck) {
                System.out.println(Card.toString(c));
            }
            initialDeal(deck, playerHand, dealerHand);
            for(Card b: playerHand){
                System.out.print(Card.toString(b));
            }

            isPlayerTurn=true;
            isBusted=false;
            dealerBusted=false;

            while(isPlayerTurn){

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
                    //checkpoint delete after
                    for(Card f : playerHand){
                        System.out.println("playerhand is now: " + Card.toString(f));
                    }

                    isBusted = checkBust(playerHand);
                    if(isBusted){
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

            if(!isBusted){
                dealerBusted = dealerTurn(deck, dealerHand);
                if(dealerBusted){ // Case: Dealer Busts
                    playerChoice = JOptionPane.showConfirmDialog(
                            null,
                            "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: "
                                    + displayHand(playerHand) + "\nThe dealer busted.\n Congratulations!",
                            "You Win!!!",
                            JOptionPane.OK_CANCEL_OPTION
                    );

                    if((playerChoice == JOptionPane.CLOSED_OPTION) || (playerChoice == JOptionPane.CANCEL_OPTION))
                        System.exit(0);
                }


                else{
                    winner = whoWins(playerHand, dealerHand);

                    if(winner == 1){ //Player Wins
                        playerChoice = JOptionPane.showConfirmDialog(
                                null,
                                "The dealer's hand: " +displayHand(dealerHand) + "\n \n Your hand: "
                                        + displayHand(playerHand) + "\n Congratulations!",
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

    int number;
    String suit;
    String faceCardName;

    Card(int givenNumber, String givenSuit){
        number = givenNumber;
        suit = givenSuit;

    }
    Card(int givenNumber, String faceCard, String givenSuit){
        number = givenNumber;
        faceCardName = faceCard;
        suit = givenSuit;
    }

    static String toString(Card c) {
        if(c.faceCardName.equals("Ace")||c.faceCardName.equals("Jack")||c.faceCardName.equals("Queen")||c.faceCardName.equals("King")){
            return c.faceCardName + " " + c.suit;
        }
        else{
            return c.number + " " + c.suit;
        }
    }
}
