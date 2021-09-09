package com.example.tictactoev2;

import androidx.appcompat.app.AppCompatActivity;
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
	private ListView lvPlayers;
	private PlayerAdapter adapter;
	private PlayerList playerList;
	private Button addPlayer;

	public interface OnPlayerSelectionListener
	{
		void onPlayerSelection(Player selected);
	}

	private OnPlayerSelectionListener onPlayerSelectionListener;

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
		Player clicked = playerList.get(position);

		submitSelection(clicked);
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

	public void setOnPlayerSelectionListener(OnPlayerSelectionListener onPlayerSelectionListener)
	{
		this.onPlayerSelectionListener = onPlayerSelectionListener;
	}

	private void submitSelection(Player selected)
	{
		onPlayerSelectionListener.onPlayerSelection(selected);

		setResult();
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