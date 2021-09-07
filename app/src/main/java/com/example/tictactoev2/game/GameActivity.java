package com.example.tictactoev2.game;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.tictactoev2.R;

public class GameActivity extends AppCompatActivity implements GameView.OnMoveListener
{
	private LinearLayout mainLayout;
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
		this.mainLayout = findViewById(R.id.mainLayout);

		this.game = new GameView(this);
		this.game.setOnMoveListener(this);
		this.mainLayout.addView(this.game);
	}

	@Override
	public void onMove(int cellIndex)
	{
		System.out.println();
	}
}