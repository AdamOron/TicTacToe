package com.example.tictactoev2.player;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.example.tictactoev2.R;
import java.util.ArrayList;

public class PlayerAdapter extends ArrayAdapter<Player>
{
	private Activity activity;
	private ArrayList<Player> players;

	public PlayerAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull ArrayList<Player> players)
	{
		super(context, resource, textViewResourceId, players);

		this.activity = (Activity) context;
		this.players = players;
	}

	@NonNull
	@Override
	public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
	{
		LayoutInflater inflater = activity.getLayoutInflater();

		View view = inflater.inflate(R.layout.layout_player_list, parent, false);

		Player current = players.get(position);

		TextView playerName = view.findViewById(R.id.playerName);
		playerName.setText(current.getName());

		TextView playerRecord = view.findViewById(R.id.playerRecord);
		playerRecord.setText(current.getRecord() + "");

		ImageView playerPicture = view.findViewById(R.id.playerPicture);
		playerPicture.setBackgroundResource(getImage(current.getGender()));

		return view;
	}

	private static int getImage(Player.Gender gender)
	{
		switch(gender)
		{
		case MALE:
			return R.drawable.man_icon;

		case FEMALE:
			return R.drawable.woman_icon;

		case OTHER:
			return 0;

		default:
			return 0;
		}
	}
}
