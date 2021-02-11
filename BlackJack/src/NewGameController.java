/**NewGameController class
  * The controller to start a new game
  * Date Created:  November 4 2020
  * @author  Shavon Thadani
  */ 
//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewGameController implements ActionListener {
	//instance variable
	private BlackJackGame blackJack; //The Model
	/** Default constructor
	  * Links the component to the Model
      * @param blackJack          The Model describing game behaviour (note, no JComponent param since there is no additional data from the view class needed)
      */ 
	public NewGameController(BlackJackGame blackJack) {
		this.blackJack = blackJack;
	}
	/** Saves data (if any) then resets all the data when the new game button is clicked
	  * @param e      The event sent from the newGame button
	  */
	public void actionPerformed(ActionEvent e) {
		if(blackJack.getRound() > 1) {
			this.blackJack.saveResults();
		}
		this.blackJack.newGame();
		this.blackJack.setupRound();
		this.blackJack.updateForNextRound();
		
	}//end of actionPerformed
}//end of class