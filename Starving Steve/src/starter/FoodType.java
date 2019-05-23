package starter;

public enum FoodType {

	STARTER, MAINDISH, SIDEDISH, DRINK, DESSERT, SNACK;
	
	public int nutrishionalValue()
	{
		
		switch(this)
		{
		case STARTER: return 4;
		case MAINDISH: return 8;
		case SIDEDISH: return 4;
		case DRINK: return 2;
		case DESSERT: return 2;
		}
		
		return 0;
	}
}
