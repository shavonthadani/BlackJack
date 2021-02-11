/**ExitController class
  * The controller to exit
  * Date Created:  November 4 2020
  * @author  Shavon Thadani
  */ 
//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitController implements ActionListener {
	//instance variable
	private BlackJackGame blackJack; //The Model
	 /** Default constructor
	   * Links the component to the Model
	   * @param blackJack          The Model describing game behaviour (note, no JComponent param since there is no additional data from the view class needed)
	   */ 
	public ExitController(BlackJackGame blackJack) {
		this.blackJack = blackJack;
	}
	
	 /** exits the game when the exit button is clicked. Saves any data before the game quits
	   * @param e      The event sent from the exit button
	   */
	public void actionPerformed(ActionEvent e) {
		if(blackJack.getRound() > 1) {
			this.blackJack.saveResults();
		}
		this.blackJack.updateForExit();
		}

	}//end of actionPerformed
//end of class
