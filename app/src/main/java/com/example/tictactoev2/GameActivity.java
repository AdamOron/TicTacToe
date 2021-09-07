package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import com.example.tictactoev2.game.GameLogic;
import com.example.tictactoev2.game.GameView;

public class GameActivity extends AppCompatActivity implements GameView.OnCellClickListener
{
	private GameLogic gameLogic;
	private GameView game;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);

		initVars();
	}

	private void initVars()
	{
		this.gameLogic = new GameLogic();

		this.game = new GameView(this);
		this.game.setOnCellClickListener(this);

		LinearLayout mainLayout = findViewById(R.id.mainLayout);
		mainLayout.addView(this.game);
	}

	@Override
	public void onCellClick(int cellIndex, Button cellButton)
	{
		GameLogic.Player current = gameLogic.currentPlayer();

		/* If current player is null, the game is over */
		if(current == null) return;

		/* Make pressed cell show current player */
		switch(current)
		{
		case X:
			cellButton.setBackgroundResource(R.drawable.letter_x);
			break;
		case O:
			cellButton.setBackgroundResource(R.drawable.letter_o);
			break;
		}

		/* Update the cell within the game's logic */
		gameLogic.setCell(cellIndex);

		/* If the last move caused the game to end, enter the credits view */
		if(gameLogic.isOver())
		{
			enterCredits();
		}
	}

	private void enterCredits()
	{
		Intent intent = new Intent(this, CreditsActivity.class);
		intent.putExtra("winner", gameLogic.getWinner());
		startActivity(intent);
	}
}