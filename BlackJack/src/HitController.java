/**HitController class
  * The controller to draw cards for CPU and Player
  * Date Created:  November 4 2020
  * @author  Shavon Thadani
  */ 
//Imports
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class HitController implements ActionListener {
	//instance variable
	private BlackJackGame blackJack; //The Model
	/** Default constructor
      * Links the component to the Model
      * @param blackJack          The Model describing game behaviour (note, no JComponent param since there is no additional data from the view class needed)
      */ 
	public HitController(BlackJackGame blackJack) {
		this.blackJack = blackJack;
	}
	/**Executes the CPU to draw cards ands draws a card for the player and totals it.
	  * Determines if they went bust when the hit me button is clicked
	  * @param e      The event sent from the hit me button component
	  */
	public void actionPerformed(ActionEvent e) {
		
		this.blackJack.dealerBrain();
		this.blackJack.drawPlayersCard();
		this.blackJack.addToPlayersTotal();
		
		if(this.blackJack.playerBust()) {
			this.blackJack.updateForNextCardWithBust();
		}
		else {
			this.blackJack.updateForNextCard();
		}
		
		
	}//end of actionPerformed
}//end of class
