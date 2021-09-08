package com.example.tictactoev2.player;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import com.example.tictactoev2.PlayerListManager;
import com.example.tictactoev2.R;

public class PlayerCreationDialog extends Dialog implements View.OnClickListener
{
	private PlayerListManager playerList;
	private EditText playerName, playerGender;
	private Button submit;

	public PlayerCreationDialog(@NonNull Context context, PlayerListManager playerList)
	{
		super(context);

		this.playerList = playerList;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setCancelable(true);
		setContentView(R.layout.dialog_create_player);

		initVars();
	}

	private void initVars()
	{
		playerName = findViewById(R.id.nameForm);
		playerGender = findViewById(R.id.genderForm);

		submit = findViewById(R.id.submit);
		submit.setOnClickListener(this);
	}

	@Override
	public void onClick(View view)
	{
		switch(view.getId())
		{
		case R.id.submit:
			submitPlayer();
			break;
		}
	}

	private void submitPlayer()
	{
		writePlayer();
		dismiss();
	}

	private void writePlayer()
	{
		String name = playerName.getText().toString();
		Player.Gender gender = getGender(playerGender.getText().toString());

		playerList.add(new Player(name, gender, 0));
	}

	private Player.Gender getGender(String genderStr)
	{
		switch(genderStr)
		{
		case "male":
			return Player.Gender.MALE;

		case "female":
			return Player.Gender.FEMALE;

		case "other":
			return Player.Gender.OTHER;

		default:
			throw new IllegalArgumentException("Invalid Gender.");
		}
	}
}
