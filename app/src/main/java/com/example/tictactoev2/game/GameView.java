package com.example.tictactoev2.game;

import android.content.Context;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

public class GameView extends LinearLayout
{
	private static final int CELL_DIM = 350, CELL_MARGIN = 5;

	interface OnMoveListener
	{
		void onMove(int cellIndex);
	}

	private OnMoveListener onMoveListener;

	public GameView(Context context)
	{
		super(context);

		createView();
	}

	public void setOnMoveListener(OnMoveListener onMoveListener)
	{
		this.onMoveListener = onMoveListener;
	}

	private void createView()
	{
		/* Prepare LinearLayout settings */
		setGravity(Gravity.CENTER);
		setOrientation(VERTICAL);
		setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

		/* Construct Grid */
		for(int i = 0; i < GameLogic.GRID_SIZE; i++)
		{
			addView(createButtonRow(i));
		}
	}

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

	private Button createButton(int cellIndex)
	{
		Button button = new Button(getContext());

		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(CELL_DIM, CELL_DIM);
		params.setMargins(CELL_MARGIN, CELL_MARGIN, CELL_MARGIN, CELL_MARGIN);
		button.setLayoutParams(params);

		button.setOnClickListener(view ->
		{
			if(onMoveListener != null)
			{
				onMoveListener.onMove(cellIndex);
			}
		});

		return button;
	}
}
