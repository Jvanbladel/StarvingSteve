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
import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GRect;
import acm.util.JTFTools;

public class GamePane extends GraphicsPane 
{
	public static final float DELAY_MS = 15;
	public static final float PLAYER_DELAY_MS = 15;
	
	public static final int BLOCK_SIZE = 50;
	
	private MainApplication program; 
	private Level level;
	private Timer mainTimer, playerAnimationTimer, jumpTimer, superPowerUpTimer, hungryTimer;
	
	private ArrayList<GObject> pauseElements = new ArrayList<GObject>(); // Elements seen on pause
	private boolean isPaused;
	private GImage pauseExit;
	int deathCount;
	boolean showingEnergy;
	
	public GamePane(MainApplication app) 
	{
		super();
		program = app;
		
		this.isPaused = false;
		objToImg = new HashMap<Obstacle, ArrayList<GImage>>();
		powerToImg = new HashMap<PowerUp, GImage>();
		level = new Level(app);
		setUpLevel();
		deathCount = 0;
		showingEnergy = false;
		
		mainTimer = new Timer((int) DELAY_MS, null);
		playerAnimationTimer = new Timer((int) PLAYER_DELAY_MS, null);
		jumpTimer = new Timer((int) DELAY_MS, null);
		superPowerUpTimer = new Timer((int) DELAY_MS, null);
		hungryTimer = new Timer(200, null);
		
		mainTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				if(!isPaused)
				{
					if(level.getPlayer().getState() == PlayerStates.DEAD)
					{
						if(level.getPlayer().getImageNumb() == 15)
						{
							playerAnimationTimer.stop();
							if(deathCount == 10)
							{
								endGame();
							}
							deathCount++;
						}
					}
					else
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
							hungry.move(0,15);
						}
						recompileEnergyBar();
						drawPlayerInventory();
						checkPlayerInventory();
						score.setLabel("Score: "+level.getScore());
						score.setLocation(MainApplication.WINDOW_WIDTH - score.getWidth() - 6, 18);
						
						if(level.getPlayer().getEnergy() < 10)
						{
							if(!hungryTimer.isRunning())
							{
								showingEnergy = false;
								hungryTimer.start();
							}
								
						}
						else if (hungryTimer.isRunning())
						{
							hungryTimer.stop();
							program.remove(hungry);
						}
							
					}
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
					hungry.move(0, -17.5);
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
		
		hungryTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
				
				if(!showingEnergy)
				{
					program.add(hungry);
					showingEnergy = !showingEnergy;
				}
				else
				{
					showingEnergy = !showingEnergy;
					program.remove(hungry);
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
		setUpScore();
		setUpHungryImage();
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
	
	private GLabel score;
	
	private void setUpScore()
	{
		score = new GLabel("0");
		score.setLocation(MainApplication.WINDOW_WIDTH - score.getWidth() - 6, 18);
		if (JTFTools.findFontFamily("Kristen ITC") != null) {
			score.setFont("Kristen ITC-Bold-14");
		} else {
			score.setFont("Arial-Bold-12");
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
		player.setLocation(200, 415);
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
		initPauseElements();
		mainTimer.start();
		playerAnimationTimer.start();
		program.add(score);
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
		removePauseElements();
		
		for(int i = 0; i < inventory.length; i++)
		{
			if(inventory[i] != null)
				program.remove(inventory[i]);
		}
		program.remove(score);
		
		if (hungryTimer.isRunning())
		{
			hungryTimer.stop();
			program.remove(hungry);
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == pauseExit) {
			program.switchToMenu();
			program.playSound("click");
		}
	}

	@Override 
	public void keyPressed(KeyEvent e){
		
		if(e.getKeyCode()==KeyEvent.VK_SPACE && !isPaused) {
			if(level.jump())
			{
				makePlayerJump();
			}
		} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			if (!isPaused) {
				isPaused = true;
				///mainTimer.stop();
				playerAnimationTimer.stop();
				//jumpTimer.stop();
				addPauseElements();
			} else {
				removePauseElements();
				isPaused = false;
				playerAnimationTimer.start();
				//jumpTimer.start();
				//mainTimer.start();
			}
		}
		/*else if(e.getKeyCode() == KeyEvent.VK_R)
		{
			restartLevel();
		}*/
	}
	
	private int jumpIncrement;
	
	public void makePlayerJump()
	{
		jumpIncrement = 1;
		jumpTimer.start();
		program.playSound("jump");
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
		
		energySymbol = new GImage("../media/images/energy.png");
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
			program.playSound("super");
			
		}
	}
	
	private void initPauseElements() {
		if (pauseElements.isEmpty()) {
			GImage backing = new GImage("../media/images/pause.png");
			GImage text = new GImage("../media/images/pause-text.png");
			backing.setSize(800, 600);
			text.setSize(800, 600);
			pauseElements.add(backing);
			pauseElements.add(text);

			pauseExit = new GImage("../media/Buttons/exit1.png");
			pauseExit.setLocation(MainApplication.WINDOW_WIDTH / 2 - pauseExit.getWidth() / 2, 360);
			pauseElements.add(pauseExit);
		}
	}

	private void addPauseElements() {
		for (GObject obj : pauseElements) {
			program.add(obj);
		}
	}
	
	public void endGame()
	{
		program.playSound("gameover");
		program.switchToGameOver();
	}
	
	public void restartLevel()
	{
		hideContents();
		this.isPaused = false;
		objToImg = new HashMap<Obstacle, ArrayList<GImage>>();
		powerToImg = new HashMap<PowerUp, GImage>();
		level = new Level(program);
		setUpLevel();
		showContents();
		deathCount = 0;
	}

	private void removePauseElements() {
		for (GObject obj : pauseElements) {
			program.remove(obj);
		}
	}
	
	public int getScore()
	{
		return level.getScore();
	}
	
	private GImage hungry;
	
	public void setUpHungryImage()
	{
		hungry = new GImage("../media/images/energy.png");
		hungry.setLocation(player.getX()+5, player.getY() - 50);
		hungry.setSize(50, 50);
	}
	
	@Override
	public void mouseMoved(MouseEvent e)
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
		if (obj == pauseExit) 
		{
			pauseExit.setImage("../media/Buttons/exit2.png");
		}
		else 
		{
			pauseExit.setImage("../media/Buttons/exit1.png");
		}
	}
}
