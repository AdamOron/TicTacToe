package com.example.tictactoev2.player;

import org.json.JSONException;
import org.json.JSONObject;

public class Player
{
	public static final String KEY_NAME = "KEY_NAME";
	public static final String KEY_GENDER = "KEY_GENDER";
	public static final String KEY_RECORD = "KEY_RECORD";

	public enum Gender
	{
		MALE, FEMALE, OTHER
	}

	private String name;
	private Gender gender;
	private int record;

	public Player(String name, Gender gender, int record)
	{
		this.name = name;
		this.gender = gender;
		this.record = record;
	}

	public Player(String name, Gender gender)
	{
		this(name, gender, 0);
	}

	public static Player fromJSON(JSONObject object)
	{
		String name = null;
		Gender gender = null;
		int record = 0;

		try
		{
			name = (String) object.get(KEY_NAME);
			gender = Gender.valueOf((String) object.get(KEY_GENDER));
			record = (int) object.get(KEY_RECORD);
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}

		return new Player(name, gender, record);
	}

	public JSONObject toJSON()
	{
		JSONObject object = new JSONObject();

		try
		{
			object.put(KEY_NAME, name);
			object.put(KEY_GENDER, gender.toString());
			object.put(KEY_RECORD, record);
		}
		catch(JSONException e)
		{
			e.printStackTrace();
		}

		return object;
	}

	public void increaseRecord()
	{
		this.record++;
	}

	public String getName()
	{
		return name;
	}

	public Gender getGender()
	{
		return gender;
	}

	public int getRecord()
	{
		return record;
	}

	@Override
	public String toString()
	{
		return "<" + getName() + ">";
	}
}
