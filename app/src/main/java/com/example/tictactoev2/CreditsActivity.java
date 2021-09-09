package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictactoev2.game.GameLogic;
import com.example.tictactoev2.player.Player;

public class CreditsActivity extends AppCompatActivity
{
	public static final String KEY_WINNER_SHAPE = "key_winner_shape";
	public static final String KEY_PLAYER_X = "KEY_PLAYER_X";
	public static final String KEY_PLAYER_O = "KEY_PLAYER_O";

	private ImageView creditsImage;
	private TextView creditsText;

	private PlayerList playerList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits_view);

		initVars();
	}

	private void initVars()
	{
		playerList = new PlayerList(this);

		creditsImage = findViewById(R.id.ivCredits);
		creditsText = findViewById(R.id.tvCredits);

		setCredits();
	}

	private void setCredits()
	{
		GameLogic.Cell winner = (GameLogic.Cell) getIntent().getExtras().get(KEY_WINNER_SHAPE);

		if(winner == null)
		{
			creditsImage.setBackgroundResource(R.drawable.tie);
			creditsText.setText("Tie!");
		}
		else
		{
			creditsImage.setBackgroundResource(R.drawable.trophy);

			Player winningPlayer = null;

			switch(winner)
			{
			case X:
				winningPlayer = playerList.get(getIntent().getIntExtra(KEY_PLAYER_X, 0));
				break;
			case O:
				winningPlayer = playerList.get(getIntent().getIntExtra(KEY_PLAYER_O, 0));
				break;
			}

			creditsText.setText(winningPlayer.getName() + " won!");
		}
	}
}