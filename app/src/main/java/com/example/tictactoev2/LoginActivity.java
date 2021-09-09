package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
	private Button xPicker, oPicker, start;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		initVars();
	}

	@Override
	public void onClick(View view)
	{
		switch(view.getId())
		{
			case R.id.bSelectX:
				break;

			case R.id.bSelectO:
				break;

			case R.id.bStart:
				break;
		}
	}

	private void initVars()
	{
		xPicker = findViewById(R.id.bSelectX);
		oPicker = findViewById(R.id.bSelectO);
		start = findViewById(R.id.bStart);

		xPicker.setOnClickListener(this);
		oPicker.setOnClickListener(this);
		start.setOnClickListener(this);
	}
}