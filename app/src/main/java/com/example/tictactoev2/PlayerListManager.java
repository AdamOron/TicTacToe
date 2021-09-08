package com.example.tictactoev2;

import android.content.Context;
import com.example.tictactoev2.player.Player;
import com.example.tictactoev2.player.PlayerAdapter;
import com.example.tictactoev2.player.PlayerDB;
import java.util.ArrayList;

public class PlayerListManager
{
	private PlayerDB playerDB;
	private ArrayList<Player> playerList;
	private PlayerAdapter adapter;

	public PlayerListManager(Context context)
	{
		this.playerList = new ArrayList<>();

		this.playerDB = new PlayerDB(context);
		this.playerDB.read(this.playerList);

		this.adapter = new PlayerAdapter(context, 0, 0, playerList);
	}

	public Player get(int index)
	{
		return playerList.get(index);
	}

	public void add(Player player)
	{
		playerDB.write(player);
		playerList.add(player);
		adapter.notifyDataSetChanged();
	}

	public void increaseRecord(Player player)
	{
		player.increaseRecord();
		adapter.notifyDataSetChanged();

		playerDB.write(playerList);
	}

	public PlayerDB getPlayerDB()
	{
		return playerDB;
	}

	public ArrayList<Player> getPlayerList()
	{
		return playerList;
	}

	public PlayerAdapter getAdapter()
	{
		return adapter;
	}
}
