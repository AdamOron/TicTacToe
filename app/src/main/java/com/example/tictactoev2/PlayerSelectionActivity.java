package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.tictactoev2.player.Player;
import com.example.tictactoev2.player.PlayerAdapter;
import com.example.tictactoev2.player.PlayerDB;

import java.util.ArrayList;

public class PlayerSelectionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener
{
	private PlayerDB playerDB;
	private ListView lvPlayers;
	private ArrayList<Player> playerList;
	private PlayerAdapter adapter;

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

	private void initVariables()
	{
		playerDB = new PlayerDB(this);

		writePlayers();

		playerList = new ArrayList<>();
		playerDB.read(playerList);

		adapter = new PlayerAdapter(this, 0, 0, playerList);

		lvPlayers = findViewById(R.id.lvPlayers);
		lvPlayers.setAdapter(adapter);
		lvPlayers.setOnItemClickListener(this);
	}

	private void writePlayers()
	{
		ArrayList<Player> players = new ArrayList<>();

		players.add(new Player("Adam", Player.Gender.MALE, 39));
		players.add(new Player("Eve", Player.Gender.FEMALE, 7));
		players.add(new Player("Jamal", Player.Gender.MALE, 69));

		playerDB.write(players);
	}
}