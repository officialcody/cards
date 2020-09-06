
package cards;

import java.util.Scanner;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * <p> Title: DisplayCards Class. </p>
 * 
 * <p> Description: The Java/FX-based user interface class for the Card Deck Exemplar.</p>
 * 
 * <p> Copyright: Lynn Robert Carter © 2018 </p>
 * 
 * @author Lynn Robert Carter & Sparsh Goel
 * 
 * @version 1.00	2018-01-25 The baseline for the Card Deck Display
 * 
 */

public class DisplayCards {
	
	/**********************************************************************************************

	Attributes
	
	**********************************************************************************************/
	
	/* Constants used to parameterize the graphical user interface.  We do not use a layout manager for
	   this application. Rather we manually control the location of each graphical element for exact
	   control of the look and feel. */

	// These are the application values required by the user interface
	private Label label_Instruction = new Label("Specify a value from 0 to 52 and then press Display button!");
	private TextField text_CardNumber = new TextField();
	
	// Information on the Unicode values for the various card faces can be found on the following web page:
	// https://en.wikipedia.org/wiki/Playing_cards_in_Unicode
	private Label label_Card = new Label("\uD83C\uDCA1");
	private Button button_Perform = new Button("Display");

	/* The attribute that holds the card number to be processed */
	int card_Number = 0;
	
	/**********************************************************************************************

	Constructors
	
	**********************************************************************************************/

	/**********
	 * This method creates and instance of the DisplayCards class, sets up the display, initializes 
	 * all of the other elements of the graphical user interface.  These assignments determine the 
	 * location, size, font, color, and the change and event handlers for each GUI object.
	 */
	public DisplayCards(Pane theRoot) {
						
		// Label theScene with the name of the exemplar, centered at the top of the pane
		setupLabelUI(label_Instruction, "Arial", 18, CardDeckDisplay.WINDOW_WIDTH, Pos.CENTER, 0, 10);

		
		// Establish a text input field and when anything changes in it, process the field to ensure that 
		// we are ready to perform as soon as possible.
		setupTextUI(text_CardNumber, "Arial", 16, 100, Pos.BASELINE_LEFT, CardDeckDisplay.WINDOW_WIDTH/2-110, 50, true);
		
		// Add a listener to the input text field.  The listener will be call when any change to the text is made.
		text_CardNumber.textProperty().addListener((observable, oldValue, newValue) -> {setCardNumber(); });

		// Move focus to the perform button when the user presses the enter (return) key
		text_CardNumber.setOnAction((event) -> { button_Perform.requestFocus(); });

		
		// Establish the Perform button, position it, and link it to methods to accomplish its work
		setupButtonUI(button_Perform, "Arial", 18, 100, Pos.CENTER, CardDeckDisplay.WINDOW_WIDTH/2+10, 50);
		button_Perform.setOnAction((event) -> { perform(); });		

		
		// Label theScene with the a card, centered in the middle of the pane
		setupLabelUI(label_Card, "Arial", 400, CardDeckDisplay.WINDOW_WIDTH, Pos.CENTER, 0, 50);

		
		// Place all of the just-initialized GUI elements into the pane
		// Be very care about the order of these items or the program will not work (Can you figure out why?)
		theRoot.getChildren().addAll(label_Card, label_Instruction, text_CardNumber, button_Perform);

	}
	
	/**********
	 * Private local method to initialize the standard fields for a label
	 */
	private static void setupLabelUI(Label l, String ff, double f, double w, Pos p, double x, double y){
		l.setFont(Font.font(ff, f));		// Set the font face and font size
		l.setMinWidth(w);				// Set the minimum width of the label
		l.setAlignment(p);				// Set the alignment (left, right, centered)
		l.setLayoutX(x);					// Set the x-axis location for the left most edge of the field
		l.setLayoutY(y);					// Set the y-axis location for the upper most edge of the field	
	}
	
	/**********
	 * Private local method to initialize the standard fields for a text field
	 */
	private static void setupTextUI(TextField t, String ff, double f, double w, Pos p, double x, double y, boolean e){
		t.setFont(Font.font(ff, f));		// Set the font face and font size
		t.setMinWidth(w);				// Set the minimum width of the label
		t.setMaxWidth(w);				// Set the maximum width of the label
		t.setAlignment(p);				// Set the alignment (left, right, centered)
		t.setLayoutX(x);					// Set the x-axis location for the left most edge of the field
		t.setLayoutY(y);					// Set the y-axis location for the upper most edge of the field	
		t.setEditable(e);				// Specify if this text field change be edited by the user
	}
	
	/**********
	 * Private local method to initialize the standard fields for a button
	 */
	private static void setupButtonUI(Button b, String ff, double f, double w, Pos p, double x, double y){
		b.setFont(Font.font(ff, f));		// Set the font face and font size
		b.setMinWidth(w);					// Set the minimum width of the label
		b.setAlignment(p);					// Set the alignment (left, right, centered)
		b.setLayoutX(x);					// Set the x-axis location for the left most edge of the field
		b.setLayoutY(y);					// Set the y-axis location for the upper most edge of the field		
	}

	
	/**********************************************************************************************

	User Interface Actions
	
	**********************************************************************************************/

	/**********
	 * Private local method that changes the card_Number attribute when the associated text field is
	 * changed *and* the new text is a valid integer, otherwise the card_Number is set to zero.
	 */
	private void setCardNumber () {
		// User a Scanner object to examine the input so we can determine if the input is an integer
		Scanner textInput = new Scanner(text_CardNumber.getText());
		
		// The hasNextInt checks to see if the text token is an integer
		if (textInput.hasNextInt())
			card_Number = textInput.nextInt();	// If it is, we convert it to an integer and set
												// the card_Number to that integer
		else										
			card_Number = 0;						// If it isn't, we set card_Number to zero
		
		textInput.close();						// We are done, so we can dismiss the Scanner
	}
	
	/**********
	 * Private local method that uses the card_Number attribute to select the right character from
	 * the playing cards portion of the Unicode character set and displays it in the proper color.
	 */
	private void perform () {
		// First check to see if the input value is valid (in the range from 0 - 52)
		
		// If a negative value, do not change anything... this is not a valid integer.
		if (card_Number < 0) return;
		
		// If the value is 52, display the joker card
		if (card_Number == 52) {
			label_Card.setText("\uD83C\uDCCF");
			label_Card.setTextFill(Color.BLACK);
			return;
		}
		
		// If greater than 52, do not change anything... this is not a valid integer.
		if (card_Number > 52) return;
		
		// If we get here, the value is in the range 0 through 51 (the integer is valid)
		// Compute the suit and rank values base on the card_Number entered
		int suit = card_Number / 13;
		int rank = card_Number % 13;
		
		// The playing cards character has a knight card in a 56 card deck, so we must skip it
		// when we construct the proper character.
		if (rank >= 11) rank++;						// rank value 0 through 10 are okay as is
													// rank value 11 and 12 need to become 12 and 13
		
		// The suits in the character set are in groups of 16, so we multiply the suit by
		int new_Card_Number = suit * 16 + rank;		// 16 and then and the rank
		
		// Start with the base value (the ace of spaces) and the offset to it.
		String s = "\uD83C\uDCA1";					// This is the Unicode for the Ace of Spades
		
		// We construct the desired two character Unicode by using the left most character as is 
		// and then add the new_Card_Number to the second character (The smallest card in the deck.)
		s = "\uD83C" + (char)((int)s.charAt(1) + new_Card_Number);
		
		// Given this new two character string (which is actually just one Unicode character)
		// set establish the label that will display as a card face.
		label_Card.setText(s);
		
		// , while Hearts and Diamonds are red
		if (suit == 0 || suit == 3)					// See if Spades (suit == 0) and Clubs (suit == 3)
			label_Card.setTextFill(Color.BLACK);		//     If so, display them as black
		else											// Otherwise, it is Hearts (1) or Diamonds (2)
			label_Card.setTextFill(Color.RED);		//     and they should be displayed as red
	}
}
