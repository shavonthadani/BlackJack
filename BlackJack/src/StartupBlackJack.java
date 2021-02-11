/**  StartupBlackJack
*    Creates the blackjack game where the player tries to get a closer score than the dealer to 21
*    Date Created: November 4 2020
*    @author Shavon Thadani
*/ 

//imports
import javax.swing.JFrame;

public class StartupBlackJack {
	
	//Initializes frame and sets up model and view classes
	public static void main(String[] args) {
		BlackJackGame blackJack = new BlackJackGame();            //Model Class
		BlackJackGUI blackJackGUI = new BlackJackGUI(blackJack);  //View Class
		
		//Initialize the Frame
		JFrame f = new  JFrame("Black Jack");
	    f.setSize(1500,400);
	    f.setLocation(300,300);
	    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    f.setContentPane(blackJackGUI);
	    f.setVisible(true);
	}//end of main
}//end of class
