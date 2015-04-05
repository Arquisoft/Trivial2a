package es.uniovi.asw.trivial.bussines.impl;

import es.uniovi.asw.trivial.model.Question;

public class StateGame {

	private int currentPlayer; // Number of the current player
	private int winner;

	public int getWinner() {
		return winner;
	}

	public void setWinner(int winner) {
		this.winner = winner;
	}

	private int diceNumber; // Number of the dice

	private boolean diceThrown;

	private Question question;

	public void resetTurn() {
		diceThrown = false;
		question = null;
	}

	public void nextTurn() {
		currentPlayer++;

	}

	public int getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(int currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public int getDiceNumber() {
		return diceNumber;
	}

	public void setDiceNumber(int diceNumber) {
		this.diceNumber = diceNumber;
	}

	public boolean isDiceThrown() {
		return diceThrown;
	}

	public void setDiceThrown(boolean diceThrown) {
		this.diceThrown = diceThrown;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

}
