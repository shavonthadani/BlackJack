/**  BlackJackGame
*    the model class for BlackJack
*    Date Created: November 4 2020
*    @author Shavon Thadani
*/

//Imports
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

public class BlackJackGame extends Object {

	// Instance Variables
	private int losses = 0;
	private int wins = 0;
	private int round = 1;
	private int playerTotal;
	private int dealerTotal;
	private BlackJackGUI blackJackGUI;
	private ArrayList<Integer> deck;
	private ArrayList<Character> record = new ArrayList<>();
	private ArrayList<Integer> playersCurrentCards;
	private ArrayList<Integer> dealersCurrentCards;
	private int playerCard;
	private int dealerCard;

	/**
	 * Creates a default game that creates and shuffles a deck to setup the first round
	 */
	public BlackJackGame() {
		super();
		playersCurrentCards = new ArrayList<>();
		dealersCurrentCards = new ArrayList<>();
		deck = new ArrayList<>();
		this.setupRound();
	}

	/**
	 * sets up a round by resetting the temporary data 
	 */
	public void setupRound() {
		this.playersCurrentCards.clear();
		this.dealersCurrentCards.clear();
		this.deck.clear();
		this.playerCard = 0;
		this.dealerCard = 0;
		this.playerTotal = 0;
		this.dealerTotal = 0;
		this.deck.add(1);
		this.deck.add(1);
		this.deck.add(1);
		this.deck.add(1);
		this.deck.add(2);
		this.deck.add(2);
		this.deck.add(2);
		this.deck.add(2);
		this.deck.add(3);
		this.deck.add(3);
		this.deck.add(3);
		this.deck.add(3);
		this.deck.add(4);
		this.deck.add(4);
		this.deck.add(4);
		this.deck.add(4);
		this.deck.add(5);
		this.deck.add(5);
		this.deck.add(5);
		this.deck.add(5);
		this.deck.add(6);
		this.deck.add(6);
		this.deck.add(6);
		this.deck.add(6);
		this.deck.add(7);
		this.deck.add(7);
		this.deck.add(7);
		this.deck.add(7);
		this.deck.add(8);
		this.deck.add(8);
		this.deck.add(8);
		this.deck.add(8);
		this.deck.add(9);
		this.deck.add(9);
		this.deck.add(9);
		this.deck.add(9);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		this.deck.add(10);
		Collections.shuffle(this.deck);
	}

	/**
	 * Resets all data that lasts the entirety of one game
	 */
	public void newGame() {
		this.round = 1;
		this.record.clear();
		this.wins = 0;
		this.losses = 0;

	}

	/**
	 * Sets the view for the game
	 * @param currentBlackJackGUI The View used to display the game
	 */
	public void setGUI(BlackJackGUI currentBlackJackGUI) {
		this.blackJackGUI = currentBlackJackGUI;
	}

	/**
	 * Draws a players card from the deck
	 */
	public void drawPlayersCard() {
		this.playerCard = this.deck.get(0);
		this.playersCurrentCards.add(playerCard);
		this.deck.remove(0);

	}

	/**
	 * Adds the players card to their total
	 */
	public void addToPlayersTotal() {
		this.playerTotal += this.playerCard;
	}

	/**
	 * Determines if the player went bust
	 * @return if the player went bust (over 21 is true, under or equal to is false)  
	 */
	public boolean playerBust() {
		if (this.playerTotal > 21) {
			this.playerTotal = -1;
			return true;
		}
		else {
		return false;
		}
	}
		
	/**
	 * Does the thinking of the CPU. Keeps picking up cards and calculates total for the dealer until a satisfied
	 * score of 17 or more is reached
	 */
	public void dealerBrain() {
		while (this.dealerTotal < 17) {
			drawDealersCard();
			addToDealersTotal();
		}
		if (dealerBust()) {
			this.dealerTotal = -1;
		}

	}

	/**
	 * Draws the dealer's card from the deck
	 */
	public void drawDealersCard() {
		this.dealerCard = this.deck.get(0);
		this.dealersCurrentCards.add(this.dealerCard);
		this.deck.remove(0);

	}

	/**
	 * Adds the dealer's card to the dealer's total
	 */
	public void addToDealersTotal() {
		this.dealerTotal += this.dealerCard;
	}

	/**
	 * Determines if the dealer went bust
	 * @return if the dealer went bust (over 21 is true, under or equal to is false)  
	 */
	public boolean dealerBust() {
		if (this.dealerTotal > 21) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Determines overall winner based on who won more rounds
	 * @return the winner of the game
	 */
	public String overallWinner() {
		if (wins > losses) {
			return "You beat the Dealer!!!";
		} 
		else if (losses > wins) {
			return "You lost to the Dealer :(";
		} 
		else {
			return "You tied the Dealer!!!";
		}
	}

	/**
	 * Records the win-loss record of a game
	 */
	public void recordWinner() {
		if (this.dealerTotal >= this.playerTotal) {
			this.record.add('L');
			losses++;
		} 
		else {
			this.record.add('W');
			wins++;
		}
	}

	/**
	 * increments the round by 1
	 * @return the next round
	 */
	public int incrementRound() {
		return this.round++;
	}

	/**
	 * gets the current round
	 * @return the current round
	 */
	public int getRound() {
		return this.round;

	}

	/**
	 * Saves the results of a game in a text file
	 */
	public void saveResults() {
		File outputFile = new File("output.txt");

		try {
			// constructs & returns a PrintWriter object connected to the output file
			PrintWriter output = new PrintWriter(outputFile);
			output.print(getFinalResults());
			output.close();
		} 
		catch (FileNotFoundException ex) {
			// Error occurred inform user
			System.out.println(ex.getMessage());
			System.out.println("in" + System.getProperty("user.dir"));
			System.exit(1);
		}

	}

	/**
	 * Determines who won the round (dealer or player)
	 * @return the winner of a round
	 */
	public String getWinnerOfRound() {
		if (this.dealerTotal >= this.playerTotal) {
			return "dealer won the round";
		} 
		else {
			return "You won the round!";
		}
	}

	/** Determines the dealers cards and their score of the round
	 * @return	the dealer's score and their cards
	 */
	public String getDealerScore() {
		String cards = "";
		String dealersScore = "";
		String result = "";
		for (int i = 0; i <= this.dealersCurrentCards.size() - 1; i++) {
			if (i == 0) {
				cards += this.dealersCurrentCards.get(i);
			} 
			else {
				cards += ", " + this.dealersCurrentCards.get(i);
			}
			if (this.dealerTotal == -1) {
				result = " and Dealer went bust";
			} 
			else if (this.dealerTotal == 21) {
				result = " Dealer got BlackJack!";
			} 
			else {
				result = " Dealer's Score: " + this.dealerTotal;
			}
		}
		dealersScore = "Dealer's Cards are: " + cards + result;
		return dealersScore;
	}
	/** Determines the player's cards and their score of the round
	 * @return	the player's score and their cards
	 */
	public String getPlayerScore() {
		String playersScore = "";
		String cards = "";
		String result = "";
		for (int i = 0; i <= this.playersCurrentCards.size() - 1; i++) {
			if (i == 0) {
				cards += this.playersCurrentCards.get(i);
			} 
			else {
				cards += ", " + this.playersCurrentCards.get(i);
			}
		}
		if (this.playerTotal == -1) {
			result = " and you went bust";
		} 
		else if (this.playerTotal == 21) {
			result = " and you got Blackjack!";
		} 
		else {
			result = " Your Score: " + this.playerTotal;
		}
		playersScore = "Your cards are: " + cards + result;
		return playersScore;

	}

	/**
	 * updates the view in the GUI for when the user ends a game
	 */
	public void updateForQuitGame() {
		this.blackJackGUI.updateForQuitGame();
	}
	/**
	 * updates the view in the GUI for when the user draws a card
	 */
	public void updateForNextCard() {
		this.blackJackGUI.updateForNextCard();
	}
	/**
	 * updates the view in the GUI for when the user draws a card and total is a bust
	 */
	public void updateForNextCardWithBust() {
		this.blackJackGUI.updateForNextCardWithBust();
	}
	/**
	 * updates the view in the GUI for the end of a round
	 */
	public void updateForEndOfRound() {
		this.blackJackGUI.updateForEndOfRound();
	}
	/**
	 * updates the view in the GUI for the next round
	 */
	public void updateForNextRound() {
		this.blackJackGUI.updateForNextRound();

	}
	/**
	 * updates the view in the GUI for when the game ends
	 */
	public void updateForEndOfGame() {
		this.blackJackGUI.updateForEndOfGame();
	}
	/**
	 * tells the GUI class to exit the game
	 */
	public void updateForExit() {
		this.blackJackGUI.exit();
	}
	/**
	 * gets the final results of a game detailing round scores and winner
	 * @return the round results and the overall winner
	 */
	public String getFinalResults() {
		String finalResults = "";
		int roundNum = 1;
		for (int i : this.record) {
			finalResults += "Round " + roundNum + ": " + (char) i + "\n";
			roundNum++;
		}
		finalResults += overallWinner();
		return finalResults;

	}//end of getFinalResults
}//end of class
