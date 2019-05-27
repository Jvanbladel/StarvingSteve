package starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class GamePane extends GraphicsPane 
{
	public static final float DELAY_MS = 15;
	public static final float PLAYER_DELAY_MS = 15;
	
	public static final int BLOCK_SIZE = 50;
	
	private MainApplication program; 
	private Level level;
	private Timer mainTimer, playerAnimationTimer, jumpTimer, superPowerUpTimer;
	
	public GamePane(MainApplication app) 
	{
		super();
		program = app;
		
		objToImg = new HashMap<Obstacle, ArrayList<GImage>>();
		powerToImg = new HashMap<PowerUp, GImage>();
		level = new Level();
		setUpLevel();
		
		mainTimer = new Timer((int) DELAY_MS, null);
		playerAnimationTimer = new Timer((int) PLAYER_DELAY_MS, null);
		jumpTimer = new Timer((int) DELAY_MS, null);
		superPowerUpTimer = new Timer((int) DELAY_MS, null);
		
		mainTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				level.move();
				level.changePlayerState();
				level.collectPowerUp();
				for(int i = 0; i < list.size(); i++)
				{
					list.get(i).move(-5, 0);
					recompileObstacles();
				}
				
				if(level.getPlayer().getState() == PlayerStates.IDLE)
				{
					level.gravity();
					player.move(0, 15);
				}
				recompileEnergyBar();
				drawPlayerInventory();
				checkPlayerInventory();
				
				if(level.getPlayer().getState() == PlayerStates.DEAD)
				{
					program.switchToGameOver();
				}
			}
		});
		
		playerAnimationTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				GImage newPlayerImage = new GImage(level.getPlayerImage());
				newPlayerImage.setLocation(player.getX(), player.getY());
				newPlayerImage.setSize(150, 150);
				
				program.remove(player);
				program.add(newPlayerImage);
				player = newPlayerImage;
			}
		});
		
		jumpTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(jumpIncrement < 16)
				{
					player.move(0, -17.5);
					level.jumping();
					jumpIncrement++;
				}
				else
				{
					jumpTimer.stop();
					level.getPlayer().changePlayerState(PlayerStates.IDLE);
				}
			}
		});
		
		superPowerUpTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(superPowerUpSpawn < 15)
				{
					//TODO: ANIMATION
					
					
					superPowerUpSpawn++;
				}
				else {
					PowerUp fullmealObj = level.spawnSuperPowerUp();
					GImage fullmeal = new GImage("../media/powerups/fullmeal.png");
					fullmeal.setLocation(650, 100);
					fullmeal.setSize(100, 100);
					list.add(fullmeal);
					powerToImg.put(fullmealObj, fullmeal);
					program.add(fullmeal);
					superPowerUpTimer.stop();
				}
			}
		});
		
	}
	
	public void setUpLevel()
	{
		drawBackGround();
		drawObstacles();
		drawPowerUps();
		drawPlayer();
		drawEngeryBar();
		setUpInventory();
	}
	
	private GImage backGround;
	
	public void drawBackGround()
	{
		backGround = new GImage("../media/images/BG.png");
		backGround.setSize(program.WINDOW_WIDTH, program.WINDOW_HEIGHT);
	}
	
	private HashMap<Obstacle, ArrayList<GImage>> objToImg;
	private ArrayList<GImage> list;
	
	public void drawObstacles()
	{
		list = new ArrayList<GImage>();
		
		ArrayList<Obstacle> obstacleList = level.getObstacles();
		for(int i = 0; i < obstacleList.size(); i++)
		{
			ArrayList<GImage> listToAdd = new ArrayList<GImage>();
			for(int j = 0; j < obstacleList.get(i).getBlocks().size(); j++)
			{
				GImage b = new GImage(obstacleList.get(i).getBlocks().get(j).getFP(),
						obstacleList.get(i).getBlocks().get(j).getX()*(BLOCK_SIZE),
						obstacleList.get(i).getBlocks().get(j).getY()*BLOCK_SIZE);
				b.setSize(BLOCK_SIZE, BLOCK_SIZE);
				list.add(b);
				listToAdd.add(b);
			}
			objToImg.put(obstacleList.get(i), listToAdd);
		}
	}
	
	private HashMap<PowerUp, GImage> powerToImg;
	
	public void drawPowerUps()
	{
		ArrayList<PowerUp> powerupList = level.getPowerUps();
	
		for(int i = 0; i < powerupList.size(); i++)
		{
			GImage powerUpImg = new GImage(powerupList.get(i).getFP(),
					powerupList.get(i).getX()*BLOCK_SIZE, powerupList.get(i).getY()*BLOCK_SIZE);
			powerUpImg.setSize(BLOCK_SIZE, BLOCK_SIZE);
			list.add(powerUpImg);
			powerToImg.put(powerupList.get(i), powerUpImg);
		}
	}
	
	//private ArrayList<> recomplileList
	public void recompileObstacles()
	{
		checkToDelete();
		checkToAdd();
	}
	
	public void checkToDelete()
	{
		Obstacle deleteThis = level.deleteOldPlatforms();
		ArrayList<GImage> deleteList = objToImg.remove(deleteThis);
		if(deleteList != null)
		{
			for(int i = 0; i< deleteList.size(); i++)
			{
				program.remove(deleteList.get(i));
				for(int j = 0; j < list.size(); j++)
				{
					if(list.get(j)==deleteList.get(i))
					{
						list.remove(j);
						j--;
					}
				}
			}
		}
		
		PowerUp deleteThisPU = level.deleteOldPowerUps();
		GImage deleteImg = powerToImg.remove(deleteThisPU);
		if(deleteImg != null)
		{
			program.remove(deleteImg);
			for(int j = 0; j < list.size(); j++)
			{
				if(list.get(j)==deleteImg)
				{
					list.remove(j);
					j--;
				}
			}
		}
		
	}
	
	public void checkToAdd()
	{
		Obstacle add = level.addPlatform();
		if(add != null)
		{
			ArrayList<GImage> listToAdd = new ArrayList<GImage>();
			for(int j = 0; j < add.getBlocks().size(); j++)
			{
				GImage b = new GImage(
						add.getBlocks().get(j).getFP(),
						add.getBlocks().get(j).getX()*(BLOCK_SIZE),
						add.getBlocks().get(j).getY()*BLOCK_SIZE);
				b.setSize(BLOCK_SIZE, BLOCK_SIZE);
				list.add(b);
				listToAdd.add(b);
				program.add(b);
			}
			objToImg.put(add, listToAdd);
		}
		
		PowerUp addPU = level.addPowerUp();
		if(addPU != null)
		{
			GImage powerUpImg = new GImage(addPU.getFP(),
					addPU.getX()*BLOCK_SIZE, addPU.getY()*BLOCK_SIZE);
			powerUpImg.setSize(BLOCK_SIZE, BLOCK_SIZE);
			list.add(powerUpImg);
			powerToImg.put(addPU, powerUpImg);
			program.add(powerUpImg);
		}
	}
	
	private GImage player;
	public void drawPlayer()
	{
		player = new GImage(PlayerStates.IDLE.getImagePath(1));
		player.setLocation(200, 365);
		player.setSize(150, 150);
	}
	
	@Override
	public void showContents() 
	{
		program.add(backGround);
		addEnergy();
		for(int i = 0; i < list.size(); i++)
		{
			program.add(list.get(i));
		}
		program.add(player);
		mainTimer.start();
		playerAnimationTimer.start();
	}
	
	@Override
	public void hideContents() 
	{
		mainTimer.stop();
		playerAnimationTimer.stop();
		program.remove(backGround);
		
		hideEnergy();
		
		for(int i = 0; i < list.size(); i++)
		{
			program.remove(list.get(i));
		}
		program.remove(player);
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
	}

	@Override 
	public void keyPressed(KeyEvent e){
		if(e.getKeyCode()==KeyEvent.VK_SPACE) {
			if(level.jump())
			{
				makePlayerJump();
			}
		}
	}
	
	private int jumpIncrement;
	
	public void makePlayerJump()
	{
		jumpIncrement = 1;
		jumpTimer.start();
	}
	
	
	private GRect outline, energy;
	private GImage energySymbol;
	
	private static final int ENERGYBAR_HEIGHT = 28;
	
	private void drawEngeryBar()
	{
		outline = new GRect(200, ENERGYBAR_HEIGHT+2);
		outline.setColor(Color.BLACK);
		
		energy = new GRect(1, 1,198, ENERGYBAR_HEIGHT);
		energy.setColor(Color.YELLOW);
		energy.setFilled(true);
		
		energySymbol = new GImage("../media/images/energy_symbol.png");
		energySymbol.setLocation(1, 1);
		energySymbol.setSize(ENERGYBAR_HEIGHT, ENERGYBAR_HEIGHT);
	}
	
	
	private void hideEnergy()
	{
		program.remove(outline);
		
		program.remove(energy);
		program.remove(energySymbol);
	}
	
	
	private void addEnergy()
	{
		program.add(outline);
		program.add(energy);
		program.add(energySymbol);
	}
	
	private void recompileEnergyBar()
	{
		int currentEnergyLevel = level.getPlayer().getEnergy();
		
		energy.setSize(6*currentEnergyLevel, ENERGYBAR_HEIGHT);
	}
	
	private void setUpInventory()
	{
		inventory = new GImage[5];
	}
	
	private GImage[] inventory;
	
	private boolean drawPlayerInventory()
	{
		PowerUp[] currentInventory = level.getPlayer().getInventory();
		
		
		for(int i = 0; i < inventory.length; i++)
		{
			if(inventory[i] == null && currentInventory[i] != null)
			{
				 GImage powerupToAdd = new GImage(currentInventory[i].getFP());
				 powerupToAdd.setSize(40, 40);
				 powerupToAdd.setLocation(i*40, 31);
				 program.add(powerupToAdd);
				 inventory[i] = powerupToAdd;
			}
		}
		
		
		return false;
	}
	
	private int superPowerUpSpawn;
	
	private void checkPlayerInventory()
	{
		int count  = 0;
		for(int i = 0; i < inventory.length; i++)
		{
			if(inventory[i] != null)
				count++;
		}
		
		if(count == inventory.length)
		{
			for(int i = 0; i < inventory.length; i++)
			{
				program.remove(inventory[i]);
			}
			level.getPlayer().clearInventory();
			
			for(int i = 0; i < inventory.length; i++)
			{
				inventory[i] = null;
			}
			
			superPowerUpSpawn = 0;
			superPowerUpTimer.start();
			
		}
	}
	
	
	
	
	
	
}
