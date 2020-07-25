package com.kafkaexample.dto;

import java.util.HashMap;
import java.util.Map;

public class Player {
    private static final Map<Integer, Player> playerMap;
    private int jerseyNumber;
    private String sportName;
    private int age;
    private double height;
    private String name;

    public Player() {
    }

    public Player(String name, int jerseyNumber, String sportName, int age, double height) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.sportName = sportName;
        this.age = age;
        this.height = height;
    }

    public static Player getPlayer(int jerseyNumber) {
    	return playerMap.get(jerseyNumber);
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(int jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    @Override
    public String toString() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    static {
        playerMap = new HashMap<>();

        Player player1 = new Player();
        player1.setName("Virat Kohli");
        player1.setAge(31);
        player1.setHeight(5.10);
        player1.setJerseyNumber(18);
        player1.setSportName("Cricket");

        Player player2 = new Player();
        player2.setName("Rohit Sharma");
        player2.setAge(33);
        player2.setHeight(5.8);
        player2.setJerseyNumber(45);
        player2.setSportName("Cricket");

        Player player3 = new Player();
        player3.setName("Lionel Messi");
        player3.setAge(33);
        player3.setHeight(5.8);
        player3.setJerseyNumber(10);
        player3.setSportName("Football");

        Player player4 = new Player();
        player4.setName("Cristiano Ronaldo");
        player4.setAge(35);
        player4.setHeight(6.3);
        player4.setJerseyNumber(7);
        player4.setSportName("Football");

        Player player5 = new Player();
        player5.setName("Roger Federer");
        player5.setAge(38);
        player5.setHeight(6.2);
        player5.setJerseyNumber(4);
        player5.setSportName("Tennis");

        Player player6 = new Player();
        player6.setName("Novak Djokovic");
        player6.setAge(33);
        player6.setHeight(6.3);
        player6.setJerseyNumber(1);
        player6.setSportName("Tennis");

        playerMap.put(1, player1);
        playerMap.put(2, player2);
        playerMap.put(3, player3);
        playerMap.put(4, player4);
        playerMap.put(5, player5);
        playerMap.put(6, player6);
    }
}
