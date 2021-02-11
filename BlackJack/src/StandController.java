/**StandController class
  * The controller to end the game
  * Date Created:  November 4 2020
  * @author  Shavon Thadani
  */ 
//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StandController implements ActionListener {
	//instance variable
	private BlackJackGame blackJack;
	/** Default constructor
	  * Links the component to the Model
	  * @param blackJack          The Model describing game behaviour (note, no JComponent param since there is no additional data from the view class needed)
      */ 
	public StandController(BlackJackGame blackJack) {
		this.blackJack = blackJack;
	}
	 /** Calculates winner of round and gets the GUI ready for the next round when the stand button is clicked
	   * @param e      The event sent from the stand button
	   */
	public void actionPerformed(ActionEvent e) {
		
		this.blackJack.incrementRound();
		this.blackJack.recordWinner();

		if (this.blackJack.getRound() > 10) {
			this.blackJack.updateForEndOfGame();
			this.blackJack.saveResults();
		} else {
			this.blackJack.updateForEndOfRound();
		}

	}//end of actionPerformed
}//end of class
