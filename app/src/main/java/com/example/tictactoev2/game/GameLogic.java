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
	 * Enum representing a Player (X/O).
	 */
	public enum Cell
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
	private Cell[] cells;
	/**
	 * Keeps track of all valid moves made by players. Each valid move is another occupied cell.
	 */
	private int moveCounter;
	/**
	 * The winning Player. Null on initialization, is assigned a proper value when someone wins.
	 * If the game is over but the winner is still null, then there was a tie.
	 */
	private Cell winner;

	/**
	 * Constructs GameLogic by initializing its values.
	 */
	public GameLogic()
	{
		this.cells = new Cell[GRID_SIZE * GRID_SIZE];
		this.moveCounter = 0;
		this.winner = null;
	}

	/**
	 * Attempts to place given player at given cellIndex.
	 *
	 * @param cellIndex the index of the cell.
	 * @return whether the player was placed or not.
	 */
	public boolean setCell(int cellIndex)
	{
		if(!canPlace(cellIndex)) return false;

		cells[cellIndex] = currentPlayer();
		moveCounter++;

		return true;
	}

	/**
	 * @return the Player that's currently making a move, or null if the game is over.
	 */
	public Cell currentPlayer()
	{
		if(isOver()) return null;
		return moveCounter % 2 == 0 ? Cell.X : Cell.O;
	}

	/**
	 * @return the winner of the game.
	 */
	public Cell getWinner()
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
	 * @return amount of valid moves made (also represents amount of cells occupied).
	 */
	public int getMoveCounter()
	{
		return moveCounter;
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

	public Cell getCell(int cellIndex)
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

	/**
	 * @param cellIndex the index of the cell.
	 * @return the column at which the cell is.
	 */
	public static int getColumn(int cellIndex)
	{
		return cellIndex % GRID_SIZE;
	}

	/**
	 * @param cellIndex the index of the cell.
	 * @return the row at which the cell is.
	 */
	public static int getRow(int cellIndex)
	{
		return cellIndex / GRID_SIZE;
	}
}
