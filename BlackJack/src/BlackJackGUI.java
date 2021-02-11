/**BlackJackGUI
  *the view class for BlackJack
  *Date Created: November 4 2020
  *@author Shavon Thadani
*/
//Imports
import javax.swing.*;
import java.awt.*;

public class BlackJackGUI extends JPanel {
	//Instance Variables
	private BlackJackGame blackJack = new BlackJackGame();                                      //the game Model             
	private JButton endGame = new JButton("End Game");                                          //end game button
	private JButton startNewGame = new JButton("Start a New Game");
	private JButton nextRound = new JButton("Go to Round " + blackJack.getRound()  + " of 10"); // next round button
	private JButton hit = new JButton("Hit me");                                                //hit me button
	private JButton stand = new JButton("Stand");                                               //stand button
	private JButton exit = new JButton("Exit");                                                 //exit button
	private JLabel playerScore = new JLabel("player Score: ");                                  //displays player's score and cards
	private JLabel dealerScore = new JLabel("dealer Score");                                    //displays dealer's score and cards
	private JLabel winnerOfRound = new JLabel("winner of round");                               //displays winner of the round
	private JLabel prompt = new JLabel("<html>press the \"Hit me\" button to draw a card <br> \n <br> \n <br> \n <br> \n</html>", SwingConstants.CENTER); //prompts the user   HTML TAGS : makes a label multiline Source: https://stackoverflow.com/questions/685521/multiline-text-in-jlabel/685631
	private JTextArea finalResults = new JTextArea(20, 20);                                     //displays the final results
	private JLabel controlsTitle = new JLabel("Controls");

	
	/** Default constructor for the GUI.  Assigns Model and View, identifies controllers and draws the layout
	  * @param blackJack        The Model for the game
      */
	public BlackJackGUI(BlackJackGame blackJack){
		super();
	    this.blackJack = blackJack;
	    this.blackJack.setGUI(this);
	    this.layoutView();
	    this.registerControllers();
	}
	/**Assigns the controllers to the buttons.
	  */ 
	private void registerControllers() {
		//exit button
		ExitController exitController = new ExitController(this.blackJack);
		this.exit.addActionListener(exitController);
		
		//hit me button
		HitController hitController = new HitController(this.blackJack);
		this.hit.addActionListener(hitController);
		
		//stand button
		StandController standController = new StandController(this.blackJack);
		this.stand.addActionListener(standController);
		
		//next round button
		NextRoundController nextRoundController = new NextRoundController(this.blackJack);
		this.nextRound.addActionListener(nextRoundController);
		
		//end game button
		EndGameController endGameController = new EndGameController(this.blackJack);
		this.endGame.addActionListener(endGameController);
		
		//new game button
		NewGameController newGameController = new NewGameController(this.blackJack);
		this.startNewGame.addActionListener(newGameController);
	
	}
	
	/** Draws the initial layout for the game board
	  */
	public void layoutView() {
		//Fonts
		Font font = new Font("Courier", Font.BOLD,20);
		Font fontResults = new Font ("Courier",Font.BOLD, 18);
		Font fontControls = new Font ("Courier",Font.BOLD, 30);
		
		//Visibility of Components at the start of the game
		stand.setVisible(false);
		nextRound.setVisible(false);
		finalResults.setVisible(false);
		winnerOfRound.setVisible(false);
		playerScore.setVisible(false);
		dealerScore.setVisible(false);
		finalResults.setEditable(false);
		finalResults.setOpaque(false);
		
		//Setting fonts to components
		playerScore.setFont(font);
		dealerScore.setFont(font);
		prompt.setFont(font);
		winnerOfRound.setFont(font);
		finalResults.setFont(fontResults);
		controlsTitle.setFont(fontControls);
		
		//Panel for the controls
		JPanel controls = new JPanel();                      
		BoxLayout btnLayout = new BoxLayout(controls, BoxLayout.Y_AXIS);
		controls.setLayout(btnLayout);
		controls.add(controlsTitle);
		controls.add(hit);
		controls.add(stand);
		controls.add(nextRound);
		controls.add(startNewGame);
		controls.add(endGame);
		controls.add(exit);
		
		//Panel for the text components of the game 
		JPanel texts = new JPanel();                     
		BoxLayout txtLayout = new BoxLayout(texts, BoxLayout.Y_AXIS);
		texts.setLayout(txtLayout);
		texts.add(prompt);
		texts.add(playerScore);
		texts.add(dealerScore);
		texts.add(winnerOfRound);
		texts.add(finalResults);
		
		//setting colors to components
		controlsTitle.setForeground(Color.WHITE);
		controls.setBackground(Color.BLACK);
		texts.setBackground(Color.GREEN);
		
		//adding the panels to the main panel
		this.setLayout(new BorderLayout());
		this.add(controls, BorderLayout.EAST);
		this.add(texts, BorderLayout.CENTER);
		
	}
	/** Redraws the game board if the user ends a game.  Requires data
	  * from the Model.
	  */ 
	public void updateForQuitGame() {
		//Quitting on the first round
		if(this.blackJack.getRound() == 1) {
			prompt.setText("<html>Game over, results have not been saved since no round has completed <br> \n \n \n \n<html>"); 
		}
		//Quitting on any other round
		else {
		prompt.setText("<html>Game Over and results have been saved<br> \n \n \n \n<html>");
		finalResults.setText(this.blackJack.getFinalResults());
		finalResults.setVisible(true);
		}
		//Visibility of components
		playerScore.setVisible(false);
		dealerScore.setVisible(false);
		winnerOfRound.setVisible(false);
		hit.setVisible(false);
		stand.setVisible(false);
		endGame.setVisible(false);
		nextRound.setVisible(false);
	}
	
	/** Redraws the game board when the game ends.  Requires data
	  * from the Model.
	  */ 
	public void updateForEndOfGame() {
		//changes in text
		prompt.setText("<html>Game Over and results have been saved<br> \n <br> \n <br>  \n <br> \n <html>");
		dealerScore.setText(this.blackJack.getDealerScore());
		winnerOfRound.setText(this.blackJack.getWinnerOfRound());
		finalResults.setText(this.blackJack.getFinalResults());
		
		//Visibility of components
		dealerScore.setVisible(true);
		winnerOfRound.setVisible(true);
		hit.setVisible(false);
		stand.setVisible(false);
		finalResults.setVisible(true);
		endGame.setVisible(false);
		nextRound.setVisible(false);
		
	}
	/** Redraws the game board for the next round.
	  * 
	  */ 
	public void updateForNextRound() {
		//changes in text
		playerScore.setText("");
		dealerScore.setText("");
		winnerOfRound.setText("");
		prompt.setText("<html>press the \"hit me\" button to draw a card<br> \n <br> \n <br>  \n <br> \n <html>");
		
		//Visibility of components
		finalResults.setVisible(false);
		playerScore.setVisible(false);
		dealerScore.setVisible(false);
		winnerOfRound.setVisible(false);
		hit.setVisible(true);
		stand.setVisible(false);
		nextRound.setVisible(false);
		endGame.setVisible(true);
		
	}
	/** Redraws the game board for the end of the round.  Requires data
	  * from the Model.
	  */ 
	public void updateForEndOfRound() {
		//changes in text
		dealerScore.setText(this.blackJack.getDealerScore());
		winnerOfRound.setText(this.blackJack.getWinnerOfRound());
		prompt.setText("<html>Click \"Go to Round " + blackJack.getRound()  + " of 10\" <br> \n <br> \n <br> \n <br> \n<html>");
		nextRound.setText("Go to Round " + blackJack.getRound()  + " of 10");
		
		//Visibility of components
		dealerScore.setVisible(true);
		winnerOfRound.setVisible(true);
		hit.setVisible(false);
		stand.setVisible(false);
		nextRound.setVisible(true);
		endGame.setVisible(true);
		
	}
	/** Redraws the game board when the user draws a card.  Requires data
	  * from the Model.
      */ 
	public void updateForNextCard() {
		//changes in text
		prompt.setText("<html>You can add another card or stand if you are happpy with your score<br> \n <br> \n <br>  \n <br> \n <html>");
		
		//Visibility of components
		playerScore.setVisible(true);
		stand.setVisible(true);
		playerScore.setText(blackJack.getPlayerScore());
		
	}
	/** Redraws the game board when the user draws a card but results in a bust.  Requires data
	  * from the Model.
	  */ 
	public void updateForNextCardWithBust() {
		//change in text
		playerScore.setText(this.blackJack.getPlayerScore());
		prompt.setText("<html>Click \"stand\", you can't add more cards because you are above 21 <br> \n <br> \n <br>  \n <br> \n <html>");
		
		//Visibility of components
		playerScore.setVisible(true);
		stand.setVisible(true);
		hit.setVisible(false);
		
	}
	/**
	 * exits the game
	 */
	public void exit() {
		System.exit(0);
	}//end of exit
}//end of class
