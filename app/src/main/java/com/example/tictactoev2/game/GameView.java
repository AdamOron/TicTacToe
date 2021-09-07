package com.example.tictactoev2.game;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

/**
 * GameView class that is a visualizer of a TicTacToe game. Contains a 3x3 grid of buttons.
 *
 * @author AdamOron
 */
public class GameView extends LinearLayout
{
	/* Properties for creating the Views. */
	private static final int CELL_DIM = 350, CELL_MARGIN = 5;

	/* Simple interface for handling a cell click. */
	public interface OnCellClickListener
	{
		/**
		 * Called whenever a cell Button is pressed.
		 *
		 * @param cellIndex the index of the cell that was pressed.
		 * @param cellButton the button of the cell that was pressed.
		 */
		void onCellClick(int cellIndex, Button cellButton);
	}

	private OnCellClickListener onCellClickListener;

	/**
	 * @param context to be passed to super constructor.
	 *
	 * Constructs LinearLayout that visualizes a TicTacToe game.
	 */
	public GameView(Context context)
	{
		super(context);

		createView();
	}

	/**
	 * Updates the OnMoveListener instance.
	 *
	 * @param onCellClickListener to be set.
	 */
	public void setOnCellClickListener(OnCellClickListener onCellClickListener)
	{
		this.onCellClickListener = onCellClickListener;
	}

	/**
	 * Create the game's view.
	 */
	private void createView()
	{
		/* Prepare LinearLayout properties */
		setGravity(Gravity.CENTER);
		setOrientation(VERTICAL);
		setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

		/* Construct Grid */
		for(int i = 0; i < GameLogic.GRID_SIZE; i++)
		{
			addView(createButtonRow(i));
		}
	}

	/**
	 * @param row the number of the row we are creating.
	 * @return a new row of buttons.
	 */
	private LinearLayout createButtonRow(int row)
	{
		LinearLayout rowLayout = new LinearLayout(getContext());

		rowLayout.setOrientation(LinearLayout.HORIZONTAL);
		rowLayout.setGravity(Gravity.CENTER);

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		rowLayout.setLayoutParams(params);

		for(int col = 0; col < GameLogic.GRID_SIZE; col++)
		{
			int cellIndex = row * GameLogic.GRID_SIZE + col;

			rowLayout.addView(createButton(cellIndex));
		}

		return rowLayout;
	}

	/**
	 * @param cellIndex the index of the cell whom we are creating a Button for.
	 * @return a Button for the given cell index.
	 */
	private Button createButton(int cellIndex)
	{
		Button button = new Button(getContext());

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CELL_DIM, CELL_DIM);
		params.setMargins(CELL_MARGIN, CELL_MARGIN, CELL_MARGIN, CELL_MARGIN);
		button.setLayoutParams(params);

		/* Whenever the cell Button is clicked, call onCellClick for the Button. */
		button.setOnClickListener(event ->
		{
			if(onCellClickListener != null)
			{
				onCellClickListener.onCellClick(cellIndex, (Button) event);
			}
		});

		return button;
	}
}
