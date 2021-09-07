package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class CreditsView extends AppCompatActivity
{
	private ImageView creditsImage;

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
		setCreditsImage();
	}

	private void setCreditsImage()
	{
		creditsImage.setBackgroundResource(getIntent().getExtras().get("winner") == null ? R.drawable.tie : R.drawable.trophy);
	}
}