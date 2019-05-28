package starter;

public enum FoodType {

	STARTER, MAINDISH, SIDEDISH, DRINK, DESSERT, SNACK, FULLMEAL;
	
	public int nutrishionalValue()
	{
		
		switch(this)
		{
		case STARTER: return 4;
		case MAINDISH: return 8;
		case SIDEDISH: return 4;
		case DRINK: return 2;
		case DESSERT: return 2;
		case FULLMEAL: return 100;
		}
		
		return 0;
	}
	
	public int getScore()
	{
		switch(this)
		{
		case STARTER: return 10;
		case MAINDISH: return 50;
		case SIDEDISH: return 20;
		case DRINK: return 15;
		case DESSERT: return 20;
		case FULLMEAL: return 100;
		}
		
		return 0;
	}
}
