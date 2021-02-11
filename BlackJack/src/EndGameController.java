/**EndGameController class
  * The controller to end the game
  * Date Created:  November 4 2020
  * @author  Shavon Thadani
  */ 
//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndGameController implements ActionListener {
	//instance variable
	private BlackJackGame blackJack; //The Model
	 /** Default constructor
	   * Links the component to the Model
	   * @param blackJack          The Model describing game behaviour (note, no JComponent param since there is no additional data from the view class needed)
       */ 
	public EndGameController(BlackJackGame blackJack) {
		this.blackJack = blackJack;
	}
	
	 /** saves and then resets all the data when the end game button is clicked
	   * @param e      The event sent from the endGame button
	   */
	public void actionPerformed(ActionEvent e) {
		blackJack.updateForQuitGame();
		if(blackJack.getRound() > 1) {
			this.blackJack.saveResults();
		}

	}//end of actionPerformed
}//end of class
