package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import com.example.tictactoev2.game.GameLogic;
import com.example.tictactoev2.game.GameView;

public class GameActivity extends AppCompatActivity implements GameView.OnMoveListener
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
		this.game.setOnMoveListener(this);

		LinearLayout mainLayout = findViewById(R.id.mainLayout);
		mainLayout.addView(this.game);
	}

	@Override
	public void onMove(int cellIndex, Button cellButton)
	{
		GameLogic.Player current = gameLogic.getMoveCounter() % 2 == 0 ? GameLogic.Player.X : GameLogic.Player.O;

		if(gameLogic.setCell(cellIndex, current))
		{
			switch(current)
			{
			case X:
				cellButton.setBackgroundResource(R.drawable.letter_x);
				break;
			case O:
				cellButton.setBackgroundResource(R.drawable.letter_o);
				break;
			}
		}
	}
}