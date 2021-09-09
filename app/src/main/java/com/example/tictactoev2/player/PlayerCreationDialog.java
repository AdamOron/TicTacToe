package com.example.tictactoev2.player;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import com.example.tictactoev2.PlayerList;
import com.example.tictactoev2.R;

public class PlayerCreationDialog extends Dialog implements View.OnClickListener
{
	private EditText playerName, playerGender;
	private Button submit;

	public interface OnPlayerCreationListener
	{
		void onPlayerCreation(Player created);
	}

	private OnPlayerCreationListener onPlayerCreationListener;

	public PlayerCreationDialog(@NonNull Context context)
	{
		super(context);
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

	public void setOnPlayerCreationListener(OnPlayerCreationListener onPlayerCreationListener)
	{
		this.onPlayerCreationListener = onPlayerCreationListener;
	}

	private void submitPlayer()
	{
		dispatchEvent();
		dismiss();
	}

	private void dispatchEvent()
	{
		String name = playerName.getText().toString();
		Player.Gender gender = getGender(playerGender.getText().toString());

		onPlayerCreationListener.onPlayerCreation(new Player(name, gender, 0));
	}

	private void initVars()
	{
		playerName = findViewById(R.id.nameForm);
		playerGender = findViewById(R.id.genderForm);

		submit = findViewById(R.id.submit);
		submit.setOnClickListener(this);
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
