package starter;

import acm.util.RandomGenerator;

public enum PowerUpType {
	
	BANNANA, POPCORN, PIZZA, DOUNUT, SODA, SANDWITCH,
	FISH, HOTDOG, SALAD, APPLE, BROCKLEY, BREAD,
	SOUP, SUSHI, TACO, PASTA, CHOCOLATE, FRENCHFRIES,
	WATER, NACHOS, CARROTS, ICECREAM, LEMONADE, FULLMEAL,
	BURGER, ;
	
	public static final String FP = "../media/powerups/";
	public static final String EXT = ".png";
	
	public String getImagePath()
	{
		
		switch(this) 
		{
		case BANNANA: return FP + "bannana" + EXT;
		case POPCORN: return FP + "popcorn" + EXT;
		case PIZZA: return FP + "pizza" + EXT;
		case DOUNUT: return FP + "doughnut" + EXT;
		case SODA: return FP + "soda" + EXT;
		case SANDWITCH: return FP + "sandwich" + EXT;
		case FISH: return FP + "fish" + EXT;
		case HOTDOG: return FP + "hotdog" + EXT;
		case SALAD: return FP + "salad" + EXT;
		case APPLE: return FP + "apple" + EXT;
		case BROCKLEY: return FP + "brockely" + EXT;
		case BREAD: return FP + "bread" + EXT;
		case SOUP: return FP + "soup" + EXT;
		case SUSHI: return FP + "sushi" + EXT;
		case TACO: return FP + "taco" + EXT;
		case PASTA: return FP + "pasta" + EXT;
		case CHOCOLATE: return FP + "chocolate" + EXT;
		case FRENCHFRIES: return FP + "frenchfries" + EXT;
		case WATER: return FP + "water" + EXT;
		case NACHOS: return FP + "nachos" + EXT;
		case CARROTS: return FP + "carrot" + EXT;
		case ICECREAM: return FP + "icecream" + EXT;
		case LEMONADE: return FP + "lemonade" + EXT;
		case FULLMEAL: return FP + "fullmeal" + EXT;
		}	
		
		return"N/A";
	}
	
	public boolean isHealthy()
	{
		switch(this) 
		{
		case BANNANA: return true;
		case POPCORN: return false;
		case PIZZA: return false;
		case DOUNUT: return false;
		case SODA: return false;
		case SANDWITCH: return true;
		case FISH: return true;
		case HOTDOG: return false;
		case SALAD: return true;
		case APPLE: return true;
		case BROCKLEY: return true;
		case BREAD: return true;
		case SOUP: return true;
		case SUSHI: return true;
		case TACO: return false;
		case PASTA: return false;
		case CHOCOLATE: return false;
		case FRENCHFRIES: return false;
		case WATER: return true;
		case NACHOS: return false;
		case CARROTS: return true;
		case ICECREAM: return false;
		case LEMONADE: return true;
		case FULLMEAL: return true;
		}	
		
		return true;
	}
	
	public FoodType getFoodType()
	{
		switch(this) 
		{
		case BANNANA: return FoodType.SNACK;
		case POPCORN: return FoodType.SNACK;
		case PIZZA: return FoodType.MAINDISH;
		case DOUNUT: return FoodType.DESSERT;
		case SODA: return FoodType.DRINK;
		case SANDWITCH: return FoodType.MAINDISH;
		case FISH: return FoodType.MAINDISH;
		case HOTDOG: return FoodType.MAINDISH;
		case SALAD: return FoodType.STARTER;
		case APPLE: return FoodType.SNACK;
		case BROCKLEY: return FoodType.SIDEDISH;
		case BREAD: return FoodType.STARTER;
		case SOUP: return FoodType.STARTER;
		case SUSHI: return FoodType.MAINDISH;
		case TACO: return FoodType.MAINDISH;
		case PASTA: return FoodType.MAINDISH;
		case CHOCOLATE: return FoodType.DESSERT;
		case FRENCHFRIES: return FoodType.SIDEDISH;
		case WATER: return FoodType.DRINK;
		case NACHOS: return FoodType.STARTER;
		case CARROTS: return FoodType.SIDEDISH;
		case ICECREAM: return FoodType.DESSERT;
		case LEMONADE: return FoodType.DRINK;
		case FULLMEAL: return FoodType.FULLMEAL;
		}	
		
		return null;
	}
	
	public int getEnergy()
	{
		int output = getFoodType().nutrishionalValue();
		if(!isHealthy())
		{
			output *= -1;
		}
			
		return output;
	}
	
	public PowerUpType createRandomPowerUp()
	{
		RandomGenerator rgen = RandomGenerator.getInstance();
		int integer = rgen.nextInt(0, 23);
		PowerUpType output = PowerUpType.APPLE;
		
		switch(integer) 
		{
		case 0: return PowerUpType.BANNANA;
		case 1: return PowerUpType.POPCORN;
		case 2: return PowerUpType.PIZZA;
		case 3: return PowerUpType.DOUNUT;
		case 4: return PowerUpType.SODA;
		case 5: return PowerUpType.SANDWITCH;
		case 6: return PowerUpType.FISH;
		case 7: return PowerUpType.HOTDOG;
		case 8: return PowerUpType.SALAD;
		case 9: return PowerUpType.APPLE;
		case 10: return PowerUpType.BROCKLEY;
		case 11: return PowerUpType.BREAD;
		case 12: return PowerUpType.SOUP;
		case 13: return PowerUpType.SUSHI;
		case 14: return PowerUpType.TACO;
		case 15: return PowerUpType.PASTA;
		case 16: return PowerUpType.CHOCOLATE;
		case 17: return PowerUpType.FRENCHFRIES;
		case 18: return PowerUpType.WATER;
		case 19: return PowerUpType.NACHOS;
		case 20: return PowerUpType.CARROTS;
		case 21: return PowerUpType.ICECREAM;
		case 22: return PowerUpType.LEMONADE;
		}	
		
		return output;
	}

}
