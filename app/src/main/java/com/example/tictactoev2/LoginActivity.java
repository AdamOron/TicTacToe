package com.example.tictactoev2;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.tictactoev2.player.Player;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener
{
	private ActivityResultLauncher<Intent> activityResultLauncher;
	private Button xPicker, oPicker, start;

	private PlayerList playerList;
	private int xPlayer, oPlayer;
	private boolean selectedPlayer;

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
				setSelectedPlayer(true);
				break;

			case R.id.bSelectO:
				setSelectedPlayer(false);
				break;

			case R.id.bStart:
				enterGame();
				break;
		}
	}

	private void enterGame()
	{
		Intent intent = new Intent(this, GameActivity.class);
		intent.putExtra(GameActivity.KEY_X_PLAYER, xPlayer);
		intent.putExtra(GameActivity.KEY_O_PLAYER, oPlayer);

		startActivity(intent);
	}

	private void onResult(ActivityResult result)
	{
		if(result.getResultCode() != Activity.RESULT_OK)
		{
			return;
		}

		playerList.update();

		int selectedId = result.getData().getExtras().getInt(PlayerSelectionActivity.KEY_SELECTED_PLAYER);

		setPlayer(selectedId);
	}

	private void setPlayer(int playerId)
	{
		Player player = playerList.get(playerId);

		if(selectedPlayer)
		{
			xPlayer = playerId;
			xPicker.setText(player.getName());
		}
		else
		{
			oPlayer = playerId;
			oPicker.setText(player.getName());
		}
	}

	private void setSelectedPlayer(boolean player)
	{
		activityResultLauncher.launch(new Intent(this, PlayerSelectionActivity.class));

		selectedPlayer = player;
	}

	private void initVars()
	{
		activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> onResult(result));

		xPicker = findViewById(R.id.bSelectX);
		oPicker = findViewById(R.id.bSelectO);
		start = findViewById(R.id.bStart);

		xPicker.setOnClickListener(this);
		oPicker.setOnClickListener(this);
		start.setOnClickListener(this);

		playerList = new PlayerList(this);
	}
}