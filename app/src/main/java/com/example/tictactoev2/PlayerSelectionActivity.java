package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.example.tictactoev2.player.Player;
import com.example.tictactoev2.player.PlayerCreationDialog;

import java.util.ArrayList;

public class PlayerSelectionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener
{
	private ListView lvPlayers;
	private PlayerListManager playerList;
	private Button addPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_selection);

		initVariables();
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
	{
		Player clicked = playerList.get(position);

		System.out.println(clicked.getName());
	}

	@Override
	public void onClick(View view)
	{
		switch(view.getId())
		{
		case R.id.createPlayer:
			enterPlayerCreation();
			break;
		}
	}

	private void enterPlayerCreation()
	{
		PlayerCreationDialog dialog = new PlayerCreationDialog(this, playerList);
		dialog.show();
	}

	private void initVariables()
	{
		playerList = new PlayerListManager(this);

		//writePlayers();

		lvPlayers = findViewById(R.id.lvPlayers);
		lvPlayers.setAdapter(playerList.getAdapter());
		lvPlayers.setOnItemClickListener(this);

		addPlayer = findViewById(R.id.createPlayer);
		addPlayer.setOnClickListener(this);
	}

	private void writePlayers()
	{
		ArrayList<Player> players = new ArrayList<>();

		players.add(new Player("Adam", Player.Gender.MALE, 39));
		players.add(new Player("Eve", Player.Gender.FEMALE, 7));
		players.add(new Player("Dave", Player.Gender.MALE, 69));

		playerList.getPlayerDB().write(players);
	}
}