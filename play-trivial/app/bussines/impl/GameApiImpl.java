package bussines.impl;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import models.Question;
import models.Score;
import models.Square;
import models.User;
import persistence.PersistenceServices;
import bussines.GameAPI;
import bussines.exceptions.IllegalActionException;
import bussines.gameClasses.BoardOption;
import bussines.gameClasses.BoardOptionsFactory;
import bussines.gameClasses.Game;
import factories.PersistenceFactory;

public class GameApiImpl implements GameAPI {
	
	private Game game;
	
	@Override
	public List<BoardOption> getBoardOptions() {
		return BoardOptionsFactory.getBoardOptions();
	}

	@Override
	public int rollDice() throws IllegalActionException {
		assertGameIsRunning();
		return game.rollDice();
	}

	@Override
	public String getWinner() throws IllegalActionException {
		assertGameIsRunning();
		return game.getWinner();
	}

	@Override
	public boolean isFinished() throws IllegalActionException {
		assertGameIsRunning();
		return game.isFinished();
	}

	@Override
	public void startGame(List<String> userNames, BoardOption boardOption)
			throws IllegalActionException {
		//New
		players=new ArrayList<String>();
		for(String p:userNames)
			players.add(p);
		//**
		game = new Game(boardOption, userNames);
	}

	@Override
	public int getPlayerLocation(String userName) throws IllegalActionException {
		assertGameIsRunning();
		return game.getPlayerLocation(userName);
	}

	@Override
	public Score getPlayerScore(String userName) throws IllegalActionException {
		assertGameIsRunning();
		return game.getPlayerScore(userName);
	}

	@Override
	public Square movePlayerTo(int squareNumber, String userName)
			throws IllegalActionException {
		assertGameIsRunning();
		return game.movePlayerTo(squareNumber, userName);
	}

	@Override
	public Set<Integer> getMovements(String userName, int squareNumber)
			throws IllegalActionException {
		assertGameIsRunning();
		return game.getMovements(userName, squareNumber);
	}

	@Override
	public Question getQuestion(String userName, int squareNumber)
			throws IllegalActionException {
		assertGameIsRunning();
		return game.getQuestion(userName, squareNumber);
	}

	@Override
	public boolean isAnswerCorrect(int questionId, String answer,
			String userName, int squareNumber) throws IllegalActionException {
		assertGameIsRunning();
		return game.isAnswerCorrect(questionId, answer, userName, squareNumber);
	}

	@Override
	public String getActivePlayer() throws IllegalActionException {
		assertGameIsRunning();
		return game.getActivePlayer();
	}

	@Override
	public int getStartSquare() throws IllegalActionException {
		assertGameIsRunning();
		return game.getStartSquare();
	}

	@Override
	public List<String> getUserNameList() {
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		List<String> usuarios = new ArrayList<String>();
		
		for(User u : factory.createUserDao().getUsers()){
			String name = u.getLogin();
			
			usuarios.add(name);
		}
		
		return usuarios;
	}

	@Override
	public void createUser(String userName, String passwd) {
		PersistenceServices factory = PersistenceFactory.persistenceFactory();
		
		System.out.println("Accediendo a la bd para registrar al usuario " + userName + " - " + passwd);
		
		factory.createUserDao().save(new User(userName, passwd));
		
		System.out.println("Usuario registrado con éxtio!");
	}

	@Override
	public Map<Integer, Point> getSquares() throws IllegalActionException {
		assertGameIsRunning();
		return game.getSquares();
	}

	private void assertGameIsRunning() throws IllegalActionException {
		if (game == null)
			throw new IllegalActionException("No se ha iniciado partida.");
	}
	
	
	//New
	private List<String> players;

	@Override
	public List<String> getPlayersNameList() {
		return players;
	}

	@Override
	public boolean existUser(String login, String passwd) {
		List<User> usuarios = PersistenceFactory.persistenceFactory().createUserDao().getUsers();
		System.out.println("Buscando credenciales en " + usuarios.size());
		for(User usuario:usuarios){
			System.out.println("Datos a comparar("+usuario.getLogin() + "," + usuario.getPasswd() + ") - Introducidos(" + login + "," + passwd + ")");
			if(usuario.getLogin().equals(login) && usuario.getPasswd().equals(passwd)){
				return true;
			}
		}
		return false;
	}

	@Override
	public int contarUsuarios() {
		return PersistenceFactory.persistenceFactory().createUserDao().contarUsuarios();
	}
}
