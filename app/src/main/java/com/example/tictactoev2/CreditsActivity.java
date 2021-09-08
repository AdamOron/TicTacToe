package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictactoev2.game.GameLogic;

public class CreditsActivity extends AppCompatActivity
{
	public static final String KEY_WINNER = "key_winner";

	private ImageView creditsImage;
	private TextView creditsText;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_credits_view);

		initVars();
	}

	private void initVars()
	{
		creditsImage = findViewById(R.id.ivCredits);
		creditsText = findViewById(R.id.tvCredits);

		setCredits();
	}

	private void setCredits()
	{
		GameLogic.Cell winner = (GameLogic.Cell) getIntent().getExtras().get(KEY_WINNER);

		if(winner == null)
		{
			creditsImage.setBackgroundResource(R.drawable.tie);
			creditsText.setText("Tie!");
		}
		else
		{
			creditsImage.setBackgroundResource(R.drawable.trophy);
			creditsText.setText(winner.toString() + "'s win!");
		}
	}
}