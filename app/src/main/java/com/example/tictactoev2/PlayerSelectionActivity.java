package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.example.tictactoev2.player.Player;
import com.example.tictactoev2.player.PlayerAdapter;
import com.example.tictactoev2.player.PlayerCreationDialog;

import java.util.ArrayList;

public class PlayerSelectionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener, PlayerCreationDialog.OnPlayerCreationListener
{
	public static final String KEY_SELECTED_PLAYER = "key_selected_id";

	private ListView lvPlayers;
	private PlayerAdapter adapter;
	private PlayerList playerList;
	private Button addPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_selection);

		initVars();
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
	{
		submitSelection(position);
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

	@Override
	public void onPlayerCreation(Player created)
	{
		playerList.add(created);
		adapter.notifyDataSetChanged();
	}

	private void submitSelection(int position)
	{
		Intent intent = new Intent();
		intent.putExtra(KEY_SELECTED_PLAYER, position);
		setResult(Activity.RESULT_OK, intent);
		finish();
	}

	private void enterPlayerCreation()
	{
		PlayerCreationDialog dialog = new PlayerCreationDialog(this);
		dialog.setOnPlayerCreationListener(this);
		dialog.show();
	}

	private void initVars()
	{
		playerList = new PlayerList(this);
		adapter = new PlayerAdapter(this, 0, 0, playerList.getPlayers());

		//writePlayers();
		//playerList.clear();

		lvPlayers = findViewById(R.id.lvPlayers);
		lvPlayers.setAdapter(adapter);
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

		playerList.add(players);
	}
}