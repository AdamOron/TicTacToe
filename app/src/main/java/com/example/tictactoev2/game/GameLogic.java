package com.example.tictactoev2.game;

/**
 * GameLogic class that handles back-end TicTacToe game logic.
 *
 * @author AdamOron
 */
public class GameLogic
{
	public static final int GRID_SIZE = 3;

	/**
	 * Enum representing a Player (X/0).
	 */
	public enum Player
	{
		X, O,
	}

	/**
	 * Array of every possible streak that results in a win.
	 * Each streak is represented with its cell indices.
	 */
	private static final int[][] WINNING_STREAKS =
	{
			/* Horizontal */
			{0, 1, 2},
			{3, 4, 5},
			{6, 7, 8},
			/* Vertical */
			{0, 3, 6},
			{1, 4, 7},
			{2, 5, 8},
			/* Diagonal */
			{0, 4, 8},
			{2, 4, 6},
	};

	/**
	 * Array of every grid cell. Each cell is represented by the Player occupying it, with a null
	 * value representing a free cell.
	 */
	private Player[] cells;
	/**
	 * Keeps track of all valid moves made by players. Each valid move is another occupied cell.
	 */
	private int moveCounter;
	/**
	 * The winning Player. Null on initialization, is assigned a proper value when someone wins.
	 * If the game is over but the winner is still null, then there was a tie.
	 */
	private Player winner;

	/**
	 * Constructs GameLogic by initializing its values.
	 */
	public GameLogic()
	{
		this.cells = new Player[GRID_SIZE * GRID_SIZE];
		this.moveCounter = 0;
		this.winner = null;
	}

	/**
	 * Attempts to place given player at given x & y indices.
	 *
	 * @param x the column to place at.
	 * @param y the row to place at.
	 * @param player the player that is being placed.
	 * @return whether the player was placed or not.
	 */
	public boolean set(int x, int y, Player player)
	{
		int cellIndex = getCellIndex(x, y);

		if(!canPlace(cellIndex)) return false;

		cells[cellIndex] = player;
		moveCounter++;

		return true;
	}

	/**
	 * @return the winner of the game.
	 */
	public Player getWinner()
	{
		/* If we are already aware of a winner, return it. */
		if(winner != null) return winner;

		/* For every possible winning streak. */
		for(int[] streak : WINNING_STREAKS)
		{
			/* If even one cell on given streak is empty, the streak is irrelevant. Skip it. */
			if(cells[streak[0]] == null) continue;

			/* If all cells on given streak are identical, the winner is the occupying Player. */
			if(cells[streak[0]] == cells[streak[1]] && cells[streak[1]] == cells[streak[2]])
			{
				winner = cells[streak[0]];
			}
		}

		return winner;
	}

	/**
	 * @return whether the game is over or not.
	 */
	public boolean isOver()
	{
		/* If a winner exists the game is over. If there were 9 or more moves made (cells occupied) the game is over. */
		return getWinner() != null || moveCounter >= 9;
	}

	/**
	 * @param cellIndex to be checked.
	 * @return whether a Player can be placed at given cell.
	 */
	private boolean canPlace(int cellIndex)
	{
		/* Can't place if game is over. Can't place if cell is occupied. */
		return !isOver() && cells[cellIndex] == null;
	}

	public Player getCell(int cellIndex)
	{
		return cells[cellIndex];
	}

	/**
	 * @param col the column of the cell.
	 * @param row the row of the cell.
	 * @return the index of the cell at the given column & row.
	 */
	public static int getCellIndex(int col, int row)
	{
		return row * GRID_SIZE + col;
	}

	public static int getColumn(int cellIndex)
	{
		return cellIndex % GRID_SIZE;
	}

	public static int getRow(int cellIndex)
	{
		return cellIndex / GRID_SIZE;
	}
}
