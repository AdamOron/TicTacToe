package com.example.tictactoev2.player;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class PlayerDB
{
	private static final String FILENAME = "players.txt";

	private Context context;

	public PlayerDB(Context context)
	{
		this.context = context;
	}

	public void clear()
	{
		FileOutputStream fos = null;

		try
		{
			fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			FileWriter fw = new FileWriter(fos.getFD());
			fw.write("");
			fos.close();

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void write(ArrayList<Player> players)
	{
		clear();

		for (int i = 0; i < players.size(); i++)
		{
			write(players.get(i));
		}
	}

	public void write(Player player)
	{
		String userString = player.toJSON().toString() + "\n";

		try
		{
			FileOutputStream fos = null;
			fos = context.openFileOutput(FILENAME , Context.MODE_APPEND);
			OutputStreamWriter fileWriter = new OutputStreamWriter(fos);

			BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
			bufferedWriter.write(userString);
			bufferedWriter.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void read(ArrayList<Player> players)
	{
		File file = new File(context.getFilesDir(), FILENAME);
		FileReader fileReader = null;
		String line= null;
		BufferedReader bufferedReader;

		try
		{
			fileReader = new FileReader(file);

			bufferedReader = new BufferedReader(fileReader);

			line = bufferedReader.readLine();

			while (line != null)
			{
				line += "\n";

				try
				{
					JSONObject jsonObject  = new JSONObject(line);

					players.add(Player.fromJSON(jsonObject));
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}

				line = bufferedReader.readLine();
			}

			bufferedReader.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
