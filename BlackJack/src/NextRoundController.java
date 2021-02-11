/** NextRoundController class
  * The controller to go to the next round
  * Date Created:  November 4 2020
  * @author  Shavon Thadani
  */ 
//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NextRoundController implements ActionListener {
	//instance variable
	private BlackJackGame blackJack; //The Model
	/** Default constructor
	  * Links the component to the Model
      * @param blackJack          The Model describing game behaviour (note, no JComponent param since there is no additional data from the view class needed)
      */ 
	public NextRoundController(BlackJackGame blackJack) {
		this.blackJack = blackJack;
	}
	 /** Resets all temporary data and sets up the next round when the next round button is clicked
	   * @param e      The event sent from the nextRound button
	   */
	public void actionPerformed(ActionEvent e) {
		this.blackJack.updateForNextRound();
		this.blackJack.setupRound();

	}//end of actionPerformed
}//end of class
