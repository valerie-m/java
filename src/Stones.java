/*
 * Stones.java
 * Summative Culminating Assignment
 * ICS3U1
 * Vanessa Mac
 * Jan 15 2020
 * The stonesGame package contains the files for the Stones Game Application.
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * The Stones class contains the client code for the Stones application. The GUI
 * components are created in the Stones Class.
 */

public class Stones implements ActionListener {

	JFrame firstFrame, secondFrame;
	JPanel firstPane, secondPane;
	JLabel promptGameMode, promptNumPlayer;
	JLabel homeBin1, homeBin2;
	JLabel error;
	JLabel empty1, empty2, empty3, empty4;
	JLabel empty5, empty6, empty7, empty8, empty9;
	JTextField gameMode, numPlayers;
	JButton startGame, computerMove;
	JButton pit11, pit12, pit13, pit14, pit15, pit16;
	JButton pit21, pit22, pit23, pit24, pit25, pit26;
	JButton reset;
	static int mode, players;
	static Board game;
	static boolean turn = true; // true = player 1, false == player 2

	public Stones() {

		/* Create and set up the first frame */
		firstFrame = new JFrame("Stones Application");
		firstFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Create the first content pane with a Grid Layout */
		firstPane = new JPanel();
		firstPane.setLayout(new GridLayout(2, 3, 5, 5));
		firstPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		/* Create and add a prompt for the number of stones then a text field */
		promptGameMode = new JLabel("Enter the number of stones (2-5): ");
		firstPane.add(promptGameMode);
		gameMode = new JTextField(10);
		firstPane.add(gameMode);

		/* Create and add a label which will display "Error" if the user inputs an invalid number of stones */
		error = new JLabel();
		firstPane.add(error);

		/* Create and add a prompt for the number of players then a text field */
		promptNumPlayer = new JLabel("Enter the number of players (1 or 2): ");
		firstPane.add(promptNumPlayer);
		numPlayers = new JTextField(10);
		firstPane.add(numPlayers);

		/* Create and add a button that will hide the first frame and display the second frame with the Mancala board */
		startGame = new JButton("Start!");
		startGame.setActionCommand("START GAME");
		startGame.addActionListener(this);
		firstPane.add(startGame);

		/* Add first content pane to the first frame */
		firstFrame.setContentPane(firstPane);

		/* Size and then display the first frame */
		firstFrame.pack();
		firstFrame.setVisible(true);

		/* Create and set up the second frame */
		secondFrame = new JFrame("Stones Application");
		secondFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/* Create the second content pane with a Grid Layout */
		secondPane = new JPanel();
		secondPane.setLayout(new GridLayout(4, 6, 5, 5));
		secondPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		/* Create and add a computer move button */
		computerMove = new JButton("MOVE PLAYER 2");
		computerMove.addActionListener(this);
		secondPane.add(computerMove);

		/* Create and add empty labels before the reset button. empty6 will display the current player */
		empty6 = new JLabel(" ");
		empty6.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		empty6.setForeground(Color.red);
		empty7 = new JLabel();
		empty8 = new JLabel();
		empty9 = new JLabel();
		secondPane.add(empty6);
		secondPane.add(empty7);
		secondPane.add(empty8);
		secondPane.add(empty9);

		/* Create and add a button that will hide the second frame to reset the board with new value of stones/players */
		reset = new JButton("RESET");
		reset.setForeground(Color.red);
		reset.setActionCommand("RESET");
		reset.addActionListener(this);
		secondPane.add(reset);

		/* Create and add 6 buttons for player 1 pits */
		pit11 = new JButton(" ");
		pit11.setForeground(Color.blue);
		pit11.setActionCommand("pit11");
		pit11.addActionListener(this);
		secondPane.add(pit11);

		pit12 = new JButton(" ");
		pit12.setForeground(Color.blue);
		pit12.setActionCommand("pit12");
		pit12.addActionListener(this);
		secondPane.add(pit12);

		pit13 = new JButton(" ");
		pit13.setForeground(Color.blue);
		pit13.setActionCommand("pit13");
		pit13.addActionListener(this);
		secondPane.add(pit13);

		pit14 = new JButton(" ");
		pit14.setForeground(Color.blue);
		pit14.setActionCommand("pit14");
		pit14.addActionListener(this);
		secondPane.add(pit14);

		pit15 = new JButton(" ");
		pit15.setForeground(Color.blue);
		pit15.setActionCommand("pit15");
		pit15.addActionListener(this);
		secondPane.add(pit15);

		pit16 = new JButton(" ");
		pit16.setForeground(Color.blue);
		pit16.setActionCommand("pit16");
		pit16.addActionListener(this);
		secondPane.add(pit16);

		/* Create and add a label for the player 1 home bin */
		homeBin1 = new JLabel("HB Player 1: 0");
		homeBin1.setForeground(Color.blue);
		homeBin1.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		homeBin1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		secondPane.add(homeBin1);

		/* Create and add empty labels between the 2 home bins. empty3 will display a message for a capture or free turn */
		empty1 = new JLabel();
		empty2 = new JLabel();
		empty3 = new JLabel();
		empty3.setForeground(Color.red);
		empty3.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		empty4 = new JLabel();
		secondPane.add(empty1);
		secondPane.add(empty2);
		secondPane.add(empty3);
		secondPane.add(empty4);

		/* Create and add a label for the player 2 home bin */
		homeBin2 = new JLabel("HB Player 2: 0");
		homeBin2.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		homeBin2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
		secondPane.add(homeBin2);

		/* Create and add 6 buttons for player 2 pits */
		pit21 = new JButton(" ");
		pit21.setActionCommand("pit21");
		pit21.addActionListener(this);
		secondPane.add(pit21);

		pit22 = new JButton(" ");
		pit22.setActionCommand("pit22");
		pit22.addActionListener(this);
		secondPane.add(pit22);

		pit23 = new JButton(" ");
		pit23.setActionCommand("pit23");
		pit23.addActionListener(this);
		secondPane.add(pit23);

		pit24 = new JButton(" ");
		pit24.setActionCommand("pit24");
		pit24.addActionListener(this);
		secondPane.add(pit24);

		pit25 = new JButton(" ");
		pit25.setActionCommand("pit25");
		pit25.addActionListener(this);
		secondPane.add(pit25);

		pit26 = new JButton(" ");
		pit26.setActionCommand("pit26");
		pit26.addActionListener(this);
		secondPane.add(pit26);

		/* Add second content pane to the second frame */
		secondFrame.setContentPane(secondPane);

		/* Size and hide the second frame */
		secondFrame.setSize(1000, 200);
		secondFrame.setVisible(false);
	}

	/*
	 * Handle button click action event 
	 * pre: none 
	 * post: start the Mancala game, move the stones, and display appropriate messages.
	 */
	public void actionPerformed(ActionEvent event) {

		String eventName = event.getActionCommand();

		int winner;
		int[] lastLocation;

		mode = Integer.parseInt(gameMode.getText());
		players = Integer.parseInt(numPlayers.getText());

		if (eventName.equals("START GAME")) {

			Board validate = new Board();

			// validates the a valid number of stones is selected by the user, display the board, and start with player 1
			if (validate.validateGameMode(mode, players)) {

				game = new Board(mode);

				pit11.setText(Integer.toString(game.getNum(0, 1)));
				pit12.setText(Integer.toString(game.getNum(0, 2)));
				pit13.setText(Integer.toString(game.getNum(0, 3)));
				pit14.setText(Integer.toString(game.getNum(0, 4)));
				pit15.setText(Integer.toString(game.getNum(0, 5)));
				pit16.setText(Integer.toString(game.getNum(0, 6)));

				pit21.setText(Integer.toString(game.getNum(1, 1)));
				pit22.setText(Integer.toString(game.getNum(1, 2)));
				pit23.setText(Integer.toString(game.getNum(1, 3)));
				pit24.setText(Integer.toString(game.getNum(1, 4)));
				pit25.setText(Integer.toString(game.getNum(1, 5)));
				pit26.setText(Integer.toString(game.getNum(1, 6)));

				empty6.setText("        PLAYER 1");

				firstFrame.setVisible(false);
				secondFrame.setVisible(true);

				if (players == 1) {
					pit21.setEnabled(false);
					pit22.setEnabled(false);
					pit23.setEnabled(false);
					pit24.setEnabled(false);
					pit25.setEnabled(false);
					pit26.setEnabled(false);
					computerMove.setVisible(true);
					computerMove.setEnabled(false);
				} else if (players == 2) {
					pit21.setEnabled(false);
					pit22.setEnabled(false);
					pit23.setEnabled(false);
					pit24.setEnabled(false);
					pit25.setEnabled(false);
					pit26.setEnabled(false);
					computerMove.setVisible(false);
				}

			} else if (!validate.validateGameMode(mode, players)) {
				error.setText("ERROR! Enter a valid number.");
			}

		} else {

			// Display the new pit values
			pit11.setText(Integer.toString(game.getNum(0, 1)));
			pit12.setText(Integer.toString(game.getNum(0, 2)));
			pit13.setText(Integer.toString(game.getNum(0, 3)));
			pit14.setText(Integer.toString(game.getNum(0, 4)));
			pit15.setText(Integer.toString(game.getNum(0, 5)));
			pit16.setText(Integer.toString(game.getNum(0, 6)));

			pit21.setText(Integer.toString(game.getNum(1, 1)));
			pit22.setText(Integer.toString(game.getNum(1, 2)));
			pit23.setText(Integer.toString(game.getNum(1, 3)));
			pit24.setText(Integer.toString(game.getNum(1, 4)));
			pit25.setText(Integer.toString(game.getNum(1, 5)));
			pit26.setText(Integer.toString(game.getNum(1, 6)));

			homeBin1.setText("HB Player 1: " + (Integer.toString(game.getNum(0, 0))));
			homeBin2.setText("HB Player 2: " + (Integer.toString(game.getNum(0, 7))));

			//Generate random move
			int move = (int)(6 * Math.random() + 1);

			// Move stones according to player's moves, determines free turns and captures, and display message for a free turn and capture
			if (eventName == "pit11") {
				lastLocation = game.moveStonesPlayer1(1, 1);
				turn = false;
				if (game.determineFreeTurn(0, lastLocation)) {
					turn = true;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7
						&& lastLocation[2] != -1) {
					empty3.setText("        Nice!");
				}
			} else if (eventName == "pit12") {
				lastLocation = game.moveStonesPlayer1(2, 1);
				turn = false;
				if (game.determineFreeTurn(0, lastLocation)) {
					turn = true;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7
						&& lastLocation[2] != -1) {
					empty3.setText("        Superb move!");
				}
			} else if (eventName == "pit13") {
				lastLocation = game.moveStonesPlayer1(3, 1);
				turn = false;
				if (game.determineFreeTurn(0, lastLocation)) {
					turn = true;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7
						&& lastLocation[2] != -1) {
					empty3.setText("        Go Go Go!");
				}
			} else if (eventName == "pit14") {
				lastLocation = game.moveStonesPlayer1(4, 1);
				turn = false;
				if (game.determineFreeTurn(0, lastLocation)) {
					turn = true;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7
						&& lastLocation[2] != -1) {
					empty3.setText("        Good Move!");
				}
			} else if (eventName == "pit15") {
				lastLocation = game.moveStonesPlayer1(5, 1);
				turn = false;
				if (game.determineFreeTurn(0, lastLocation)) {
					turn = true;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7
						&& lastLocation[2] != -1) {
						empty3.setText("        Amazing!");
				}
			} else if (eventName == "pit16") {
				lastLocation = game.moveStonesPlayer1(6, 1);
				turn = false;
				if (game.determineFreeTurn(0, lastLocation)) {
					turn = true;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7
						&& lastLocation[2] != -1) {
					empty3.setText("        Wow!");
				}
			} else if (eventName == "pit21") {
				lastLocation = game.moveStonesPlayer2(1, 2);
				turn = true;
				if (game.determineFreeTurn(1, lastLocation)) {
					turn = false;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7 && lastLocation[2] != -1) {
					empty3.setText("        Good move!");
				} 
			} else if (eventName == "pit22") {
				lastLocation = game.moveStonesPlayer2(2, 2);
				turn = true;
				if (game.determineFreeTurn(1, lastLocation)) {
					turn = false;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7 && lastLocation[2] != -1) {
					empty3.setText("        Great!");
				} 
			} else if (eventName == "pit23") {
				lastLocation = game.moveStonesPlayer2(3, 2);
				turn = true;
				if (game.determineFreeTurn(1, lastLocation)) {
					turn = false;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7 && lastLocation[2] != -1) {
					empty3.setText("        *Clap * Clap*!");
				} 
			} else if (eventName == "pit24") {
				lastLocation = game.moveStonesPlayer2(4, 2);
				turn = true;
				if (game.determineFreeTurn(1, lastLocation)) {
					turn = false;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7 && lastLocation[2] != -1) {
					empty3.setText("        Nice job!");
				} 
			} else if (eventName == "pit25") {
				lastLocation = game.moveStonesPlayer2(5, 2);
				turn = true;
				if (game.determineFreeTurn(1, lastLocation)) {
					turn = false;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7 && lastLocation[2] != -1) {
					empty3.setText("        Superb!");
				} 
			} else if (eventName == "pit26") {
				lastLocation = game.moveStonesPlayer2(6, 2);
				turn = true;
				if (game.determineFreeTurn(1, lastLocation)) {
					turn = false;
					empty3.setText("        Free turn!");
				} else {
					empty3.setText(" ");
				}
				if (game.getNum(lastLocation[0], lastLocation[1]) == 1 && lastLocation[1] != 0 && lastLocation[1] != 7 && lastLocation[2] != -1) {
					empty3.setText("        Let's Go!");
				} 
			} else if (eventName == "MOVE PLAYER 2" && !turn) {
				// DETERMINE FREE TURN
				if (game.getNum(1, 1) == 6) {
					game.moveStonesPlayer2(1, 2);
					empty3.setText("        Free turn!");
					turn = false;
				} else if (game.getNum(1, 2) == 5) {
					game.moveStonesPlayer2(2, 2);
					empty3.setText("        Free turn!");
					turn = false;
				} else if (game.getNum(1, 3) == 4) {
					game.moveStonesPlayer2(3, 2);
					empty3.setText("        Free turn!");
					turn = false;
				} else if (game.getNum(1, 4) == 3) {
					game.moveStonesPlayer2(4, 2);
					empty3.setText("        Free turn!");
					turn = false;
				} else if (game.getNum(1, 5) == 2) {
					game.moveStonesPlayer2(5, 2);
					empty3.setText("        Free turn!");
					turn = false;
				} else if (game.getNum(1, 6) == 1) {
					game.moveStonesPlayer2(6, 2);
					empty3.setText("        Free turn!");
					turn = false;
					
				//IF OPPONENT IS ABOUT TO WIN, FILL THEIR PITS WITH STONES
				} else if (((game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 6)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0))
						&& (game.getNum(1, 6) <= 7)){
					game.moveStonesPlayer2(6, 2);
					empty3.setText("");
					turn = true;
				} else if (((game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 6)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0))
						&& (game.getNum(1, 5) <= 8)){
					game.moveStonesPlayer2(5, 2);
					empty3.setText("");
					turn = true;
				} else if (((game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 6)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0))
						&& (game.getNum(1, 4) <= 9)){
					game.moveStonesPlayer2(4, 2);
					empty3.setText("");
					turn = true;
				} else if (((game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 6)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0))
						&& (game.getNum(1, 3) <= 10)){
					game.moveStonesPlayer2(3, 2);
					empty3.setText("");
					turn = true;
				} else if (((game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 6)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0))
						&& (game.getNum(1, 2) <= 11)){
					game.moveStonesPlayer2(2, 2);
					empty3.setText("");
					turn = true;
				} else if (((game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 6)== 0) 
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 2) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 1) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0)
						|| (game.getNum(0, 2) == 0 && game.getNum(0, 3) == 0 && game.getNum(0, 4) == 0 && game.getNum(0, 5) == 0 && game.getNum(0, 6)== 0))
						&& (game.getNum(1, 1) <= 12)){
					game.moveStonesPlayer2(1, 2);
					empty3.setText("");
					turn = true;
				
				//DETERMINE POSSIBLE CAPTURES
				
				} else if (game.getNum(1, 1) == 0 && game.getNum(0, 1) != 0 && game.getNum(1, 2) == 13) {
					turn = true;
					empty3.setText("        Capture!");
					game.moveStonesPlayer2(2, 2);
				} else if (game.getNum(1, 1) == 0 && game.getNum(0, 1) != 0 && game.getNum(1, 3) == 12) {
					turn = true;
					empty3.setText("        Capture!");
					game.moveStonesPlayer2(3, 2);
				} else if (game.getNum(1, 1) == 0 && game.getNum(0, 1) != 0 && game.getNum(1, 4) == 11) {
					turn = true;
					empty3.setText("        Capture!");
					game.moveStonesPlayer2(4, 2);
				} else if (game.getNum(1, 1) == 0 && game.getNum(0, 1) != 0 && game.getNum(1, 5) == 10) {
					turn = true;
					empty3.setText("        Capture!");
					game.moveStonesPlayer2(5, 2);
				} else if (game.getNum(1, 1) == 0 && game.getNum(0, 1) != 0 && game.getNum(1, 6) == 9) {
					turn = true;
					game.moveStonesPlayer2(6, 2);
					empty3.setText("        Capture!");
				
				/*************/
				
				} else if (game.getNum(1, 2) == 0 && game.getNum(0, 2) != 0 && game.getNum(1, 1) == 1) {
					turn = true;
					game.moveStonesPlayer2(1, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 2) == 0 && game.getNum(0, 2) != 0 && game.getNum(1, 3) == 13) {
					turn = true;
					game.moveStonesPlayer2(3, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 2) == 0 && game.getNum(0, 2) != 0 && game.getNum(1, 4) == 12) {
					turn = true;
					game.moveStonesPlayer2(4, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 2) == 0 && game.getNum(0, 2) != 0 && game.getNum(1, 5) == 11) {
					turn = true;
					game.moveStonesPlayer2(5, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 2) == 0 && game.getNum(0, 2) != 0 && game.getNum(1, 6) == 10) {
					turn = true;
					game.moveStonesPlayer2(6, 2);
					empty3.setText("        Capture!");

					/*************/
				
				} else if (game.getNum(1, 3) == 0 && game.getNum(0, 3) != 0 && game.getNum(1, 1) == 2) {
					turn = true;
					game.moveStonesPlayer2(1, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 3) == 0 && game.getNum(0, 3) != 0 && game.getNum(1, 2) == 3) {
					turn = true;
					game.moveStonesPlayer2(2, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 3) == 0 && game.getNum(0, 3) != 0 && game.getNum(1, 4) == 11) {
					turn = true;
					game.moveStonesPlayer2(4, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 3) == 0 && game.getNum(0, 3) != 0 && game.getNum(1, 5) == 10) {
					turn = true;
					game.moveStonesPlayer2(5, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 3) == 0 && game.getNum(0, 3) != 0 && game.getNum(1, 6) == 9) {
					turn = true;
					game.moveStonesPlayer2(6, 2);
					empty3.setText("        Capture!");

					/*************/
				
				} else if (game.getNum(1, 4) == 0 && game.getNum(0, 4) != 0 && game.getNum(1, 1) == 3) {
					turn = true;
					game.moveStonesPlayer2(1, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 4) == 0 && game.getNum(0, 4) != 0 && game.getNum(1, 2) == 2) {
					turn = true;
					game.moveStonesPlayer2(2, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 4) == 0 && game.getNum(0, 4) != 0 && game.getNum(1, 3) == 1) {
					turn = true;
					game.moveStonesPlayer2(3, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 4) == 0 && game.getNum(0, 4) != 0 && game.getNum(1, 5) == 10) {
					turn = true;
					game.moveStonesPlayer2(5, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 4) == 0 && game.getNum(0, 4) != 0 && game.getNum(1, 6) == 9) {
					turn = true;
					game.moveStonesPlayer2(6, 2);
					empty3.setText("        Capture!");

					/*************/
					
				} else if (game.getNum(1, 5) == 0 && game.getNum(0, 5) != 0 && game.getNum(1, 1) == 4) {
					turn = true;
					empty3.setText("        Capture!");
					game.moveStonesPlayer2(1, 2);
				} else if (game.getNum(1, 5) == 0 && game.getNum(0, 5) != 0 && game.getNum(1, 2) == 3) {
					turn = true;
					game.moveStonesPlayer2(2, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 5) == 0 && game.getNum(0, 5) != 0 && game.getNum(1, 3) == 2) {
					turn = true;
					game.moveStonesPlayer2(3, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 5) == 0 && game.getNum(0, 5) != 0 && game.getNum(1, 4) == 1) {
					turn = true;
					game.moveStonesPlayer2(4, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 5) == 0 && game.getNum(0, 5) != 0 && game.getNum(1, 6) == 8) {
					turn = true;
					game.moveStonesPlayer2(6, 2);
					empty3.setText("        Capture!");

					/*************/
					
				} else if (game.getNum(1, 6) == 0 && game.getNum(0, 6) != 0 && game.getNum(1, 1) == 5) {
					turn = true;
					game.moveStonesPlayer2(1, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 6) == 0 && game.getNum(0, 6) != 0 && game.getNum(1, 2) == 4) {
					turn = true;
					game.moveStonesPlayer2(2, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 6) == 0 && game.getNum(0, 6) != 0 && game.getNum(1, 3) == 3) {
					turn = true;
					game.moveStonesPlayer2(3, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 6) == 0 && game.getNum(0, 6) != 0 && game.getNum(1, 4) == 2) {
					turn = true;
					game.moveStonesPlayer2(4, 2);
					empty3.setText("        Capture!");
				} else if (game.getNum(1, 6) == 0 && game.getNum(0, 6) != 0 && game.getNum(1, 5) == 1) {
					turn = true;
					game.moveStonesPlayer2(5, 2);
					empty3.setText("        Capture!");
				} else if (turn == false && game.getNum(1, move) != 0){
					turn = true;
					game.moveStonesPlayer2(move, 2);
					empty3.setText("");
				} else {
					turn = true;
					if (game.getNum(1, 1) != 0) {
						game.moveStonesPlayer2(1, 2);
						empty3.setText("");
					} else if (game.getNum(1, 2) != 0) {
						game.moveStonesPlayer2(2, 2);
						empty3.setText("");
					} else if (game.getNum(1, 3) != 0) {
						game.moveStonesPlayer2(3, 2);
						empty3.setText("");
					} else if (game.getNum(1, 4) != 0) {
						game.moveStonesPlayer2(4, 2);
						empty3.setText("");
					} else if (game.getNum(1, 5) != 0) {
						game.moveStonesPlayer2(5, 2);
						empty3.setText("");
					} else if (game.getNum(1, 6) != 0) {
						game.moveStonesPlayer2(6, 2);
						empty3.setText("");
					} 
				}
			}

			// Display the new pit values
			pit11.setText(Integer.toString(game.getNum(0, 1)));
			pit12.setText(Integer.toString(game.getNum(0, 2)));
			pit13.setText(Integer.toString(game.getNum(0, 3)));
			pit14.setText(Integer.toString(game.getNum(0, 4)));
			pit15.setText(Integer.toString(game.getNum(0, 5)));
			pit16.setText(Integer.toString(game.getNum(0, 6)));

			pit21.setText(Integer.toString(game.getNum(1, 1)));
			pit22.setText(Integer.toString(game.getNum(1, 2)));
			pit23.setText(Integer.toString(game.getNum(1, 3)));
			pit24.setText(Integer.toString(game.getNum(1, 4)));
			pit25.setText(Integer.toString(game.getNum(1, 5)));
			pit26.setText(Integer.toString(game.getNum(1, 6)));

			homeBin1.setText("HB Player 1: " + (Integer.toString(game.getNum(0, 0))));
			homeBin2.setText("HB Player 2: " + (Integer.toString(game.getNum(0, 7))));

			//Display current player
			if (turn) {
				empty6.setText("        PLAYER 1");
				computerMove.setEnabled(false);
			} else if (!turn && players == 2) {
				empty6.setText("        PLAYER 2");
				computerMove.setEnabled(false);
			} else if (!turn && players == 1) {
				empty6.setText("        PLAYER 2");
				computerMove.setEnabled(true);
			}

			// Determine if the game is done. If true, determine the winner and disable the board.
			if (!game.endGame()) {

				pit11.setText(Integer.toString(game.getNum(0, 1)));
				pit12.setText(Integer.toString(game.getNum(0, 2)));
				pit13.setText(Integer.toString(game.getNum(0, 3)));
				pit14.setText(Integer.toString(game.getNum(0, 4)));
				pit15.setText(Integer.toString(game.getNum(0, 5)));
				pit16.setText(Integer.toString(game.getNum(0, 6)));

				pit21.setText(Integer.toString(game.getNum(1, 1)));
				pit22.setText(Integer.toString(game.getNum(1, 2)));
				pit23.setText(Integer.toString(game.getNum(1, 3)));
				pit24.setText(Integer.toString(game.getNum(1, 4)));
				pit25.setText(Integer.toString(game.getNum(1, 5)));
				pit26.setText(Integer.toString(game.getNum(1, 6)));

				homeBin1.setText("HB Player 1: " + (Integer.toString(game.getNum(0, 0))));
				homeBin2.setText("HB Player 2: " + (Integer.toString(game.getNum(0, 7))));

				winner = game.getResults();

				if (winner == 0) {
					empty3.setText("\nDRAW!");
				} else if (winner == 1) {
					empty3.setText("\nPLAYER 1 WINS!");
				} else if (winner == 2) {
					empty3.setText("\nPLAYER 2 WINS!");
				}

				pit11.setEnabled(false);
				pit12.setEnabled(false);
				pit13.setEnabled(false);
				pit14.setEnabled(false);
				pit15.setEnabled(false);
				pit16.setEnabled(false);
				pit21.setEnabled(false);
				pit22.setEnabled(false);
				pit23.setEnabled(false);
				pit24.setEnabled(false);
				pit25.setEnabled(false);
				pit26.setEnabled(false);
				computerMove.setEnabled(false);
			}

			// Disable and enable buttons depending on turn
			if (turn && game.endGame() && players == 2) {
				pit11.setEnabled(true);
				pit12.setEnabled(true);
				pit13.setEnabled(true);
				pit14.setEnabled(true);
				pit15.setEnabled(true);
				pit16.setEnabled(true);

				pit21.setEnabled(false);
				pit22.setEnabled(false);
				pit23.setEnabled(false);
				pit24.setEnabled(false);
				pit25.setEnabled(false);
				pit26.setEnabled(false);
			} else if (!turn && game.endGame() && players == 2) {
				pit11.setEnabled(false);
				pit12.setEnabled(false);
				pit13.setEnabled(false);
				pit14.setEnabled(false);
				pit15.setEnabled(false);
				pit16.setEnabled(false);

				pit21.setEnabled(true);
				pit22.setEnabled(true);
				pit23.setEnabled(true);
				pit24.setEnabled(true);
				pit25.setEnabled(true);
				pit26.setEnabled(true);
			} else if (turn && game.endGame() && players == 1) {
				pit11.setEnabled(true);
				pit12.setEnabled(true);
				pit13.setEnabled(true);
				pit14.setEnabled(true);
				pit15.setEnabled(true);
				pit16.setEnabled(true);

				pit21.setEnabled(false);
				pit22.setEnabled(false);
				pit23.setEnabled(false);
				pit24.setEnabled(false);
				pit25.setEnabled(false);
				pit26.setEnabled(false);
				computerMove.setEnabled(false);
			} else if (!turn && game.endGame() && players == 1) {
				pit11.setEnabled(false);
				pit12.setEnabled(false);
				pit13.setEnabled(false);
				pit14.setEnabled(false);
				pit15.setEnabled(false);
				pit16.setEnabled(false);
				pit21.setEnabled(false);
				pit22.setEnabled(false);
				pit23.setEnabled(false);
				pit24.setEnabled(false);
				pit25.setEnabled(false);
				pit26.setEnabled(false);
				computerMove.setEnabled(true);
			}

			// Disables buttons if pit value is 0.
			if (game.getNum(0, 1) == 0 && game.endGame()) {
				pit11.setEnabled(false);
			}
			if (game.getNum(0, 2) == 0 && game.endGame()) {
				pit12.setEnabled(false);
			}
			if (game.getNum(0, 3) == 0 && game.endGame()) {
				pit13.setEnabled(false);
			}
			if (game.getNum(0, 4) == 0 && game.endGame()) {
				pit14.setEnabled(false);
			}
			if (game.getNum(0, 5) == 0 && game.endGame()) {
				pit15.setEnabled(false);
			}
			if (game.getNum(0, 6) == 0 && game.endGame()) {
				pit16.setEnabled(false);
			}
			if (game.getNum(1, 1) == 0 && game.endGame()) {
				pit21.setEnabled(false);
			}
			if (game.getNum(1, 2) == 0 && game.endGame()) {
				pit22.setEnabled(false);
			}
			if (game.getNum(1, 3) == 0 && game.endGame()) {
				pit23.setEnabled(false);
			}
			if (game.getNum(1, 4) == 0 && game.endGame()) {
				pit24.setEnabled(false);
			}
			if (game.getNum(1, 5) == 0 && game.endGame()) {
				pit25.setEnabled(false);
			}
			if (game.getNum(1, 6) == 0 && game.endGame()) {
				pit26.setEnabled(false);
			}
			
			// Resets the board if the reset button is clicked
			if (eventName.equals("RESET")) {
				firstFrame.setVisible(true);
				secondFrame.setVisible(false);

				pit11.setEnabled(true);
				pit12.setEnabled(true);
				pit13.setEnabled(true);
				pit14.setEnabled(true);
				pit15.setEnabled(true);
				pit16.setEnabled(true);
				pit21.setEnabled(true);
				pit22.setEnabled(true);
				pit23.setEnabled(true);
				pit24.setEnabled(true);
				pit25.setEnabled(true);
				pit26.setEnabled(true);

				homeBin1.setText("HB Player 1: 0");
				homeBin2.setText("HB Player 2: 0");

				empty3.setText(" ");
				empty6.setText("        PLAYER 1");
				turn = true;
			}
		}

	}

	/**
	 * Create and show the GUI.
	 */
	private static void runGUI() {
		JFrame.setDefaultLookAndFeelDecorated(true);

		Stones game = new Stones();
	}

	public static void main(String[] args) {
		/*
		 * Methods that create and show a GUI should be run from an event-dispatching
		 * thread
		 */
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				runGUI();
			}
		});
	}
}
