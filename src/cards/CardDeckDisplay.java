package cards;

import cards.DisplayCards;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class CardDeckDisplay extends Application {

	public final static double WINDOW_WIDTH = 600;
	public final static double WINDOW_HEIGHT = 500;

	public DisplayCards theGUI;

	/**********
	 * This is the start method that is called once the application has been loaded into memory and is 
	 * ready to get to work.
	 * 
	 * In designing this application I have elected to IGNORE all opportunities for automatic layout
	 * support and instead have elected to manually position each GUI element and its properties in
	 * order to exercise complete control over the user interface look and feel.
	 * 
	 */
	public void start(Stage theStage) throws Exception {

		theStage.setTitle("Card Deck Display");		// Label the stage (a window)

		Pane theRoot = new Pane();							// Create a pane within the window

		theGUI = new DisplayCards(theRoot);					// Create the Graphical User Interface

		Scene theScene = new Scene(theRoot, WINDOW_WIDTH, WINDOW_HEIGHT);	// Create the scene

		theStage.setScene(theScene);							// Set the scene on the stage

		theStage.show();										// Show the stage to the user

		// When the stage is shown to the user, the pane within the window is visible.  This means that the
		// labels, fields, and buttons of the Graphical User Interface (GUI) are visible and it is now 
		// possible for the user to select input fields and enter values into them, click on buttons, and 
		// read the labels, the results, and the error messages.
	}



	/*******************************************************************************************************/

	/*******************************************************************************************************
	 * This is the method that launches the JavaFX application
	 * 
	 */
	public static void main(String[] args) {						// This method may not be required
		launch(args);											// for all JavaFX applications using
	}															// other IDEs.
}
