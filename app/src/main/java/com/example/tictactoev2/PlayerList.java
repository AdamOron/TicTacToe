package com.example.tictactoev2;

import android.content.Context;
import com.example.tictactoev2.player.Player;
import com.example.tictactoev2.player.PlayerData;
import java.util.ArrayList;

public class PlayerList
{
	private PlayerData playerData;
	private ArrayList<Player> players;

	public PlayerList(Context context)
	{
		this.playerData = new PlayerData(context);

		this.players = new ArrayList<>();
		this.playerData.read(this.players);
	}

	public void clear()
	{
		players.clear();
		playerData.clear();
	}

	public void update()
	{
		playerData.read(players);
	}

	public Player get(int index)
	{
		return players.get(index);
	}

	public void add(Player player)
	{
		playerData.write(player);
		players.add(player);
	}

	public void add(ArrayList<Player> players)
	{
		playerData.write(players);
		players.addAll(players);
	}

	public void increaseRecord(Player player)
	{
		player.increaseRecord();
		playerData.write(players);
	}

	public ArrayList<Player> getPlayers()
	{
		return players;
	}
}
