package com.dto;


public class Player
{
	private int jerseyNumber;

	private String sportName;

	private int age;

	private double height;
	
	private String name;

	public Player()
	{
	}

	public Player(String name, int jerseyNumber, String sportName, int age, double height)
	{
		this.name = name;
		this.jerseyNumber = jerseyNumber;
		this.sportName = sportName;
		this.age = age;
		this.height = height;
	}
	
	public static Player getPlayer(int jerseyNumber)
	{
		Player player = new Player();
		player.setName("Virat");
		player.setAge(34);
		player.setHeight(5.10);
		player.setJerseyNumber(jerseyNumber);
		player.setSportName("Cricket");
		return player;
	}

	public double getHeight()
	{
		return height;
	}

	public void setHeight(double height)
	{
		this.height = height;
	}

	public int getAge()
	{
		return age;
	}

	public void setAge(int age)
	{
		this.age = age;
	}

	public String getSportName()
	{
		return sportName;
	}

	public void setSportName(String sportName)
	{
		this.sportName = sportName;
	}

	public int getJerseyNumber()
	{
		return jerseyNumber;
	}

	public void setJerseyNumber(int jerseyNumber)
	{
		this.jerseyNumber = jerseyNumber;
	}
	
	@Override
	public String toString()
	{
		return new StringBuilder("Player [name=")
		.append(name)
		.append(", jerseyNumber=")
		.append(jerseyNumber)
		.append(", sportName=")
		.append(sportName)
		.append(", age=")
		.append(age)
		.append(", height=")
		.append(height)
		.append("]").toString();
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
