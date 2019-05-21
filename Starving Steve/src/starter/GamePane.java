package starter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.Timer;

import acm.graphics.GImage;
import acm.graphics.GObject;
import acm.graphics.GRect;

public class GamePane extends GraphicsPane 
{
	public static final float DELAY_MS = 10;
	public static final int BLOCK_SIZE = 50;
	
	private MainApplication program; 
	private Level level;
	private Timer mainTimer;
	
	public GamePane(MainApplication app) 
	{
		super();
		program = app;
		
		level = new Level();
		setUpLevel();
		
		mainTimer = new Timer((int) DELAY_MS, null);
		mainTimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt)
			{
			
			}
		});
	}
	
	public void setUpLevel()
	{
		drawBackGround();
		drawObstacles();
		drawPlayer();
	}
	
	private GImage backGround;
	
	public void drawBackGround()
	{
		backGround = new GImage("../media/images/BG.png");
		backGround.setSize(program.WINDOW_WIDTH, program.WINDOW_HEIGHT);
	}
	
	ArrayList<GImage> list;
	
	public void drawObstacles()
	{
		list = new ArrayList<GImage>();
		
		ArrayList<Obstacle> obstacleList = level.getObstacles();
		for(int i = 0; i < obstacleList.size(); i++)
		{
			for(int j = 0; j < obstacleList.get(i).getBlocks().size(); j++)
			{
				GImage b = new GImage(obstacleList.get(i).getBlocks().get(j).getFP(),
						obstacleList.get(i).getBlocks().get(j).getX()*(BLOCK_SIZE),
						obstacleList.get(i).getBlocks().get(j).getY()*BLOCK_SIZE);
				b.setSize(BLOCK_SIZE, BLOCK_SIZE);
				list.add(b);
			}
			
		}
	}
	
	public void drawPlayer()
	{
		
	}
	
	@Override
	public void showContents() 
	{
		program.add(backGround);
		for(int i = 0; i < list.size(); i++)
		{
			program.add(list.get(i));
		}
	}
	
	@Override
	public void hideContents() 
	{
		program.remove(backGround);
	}
	
	@Override
	public void mousePressed(MouseEvent e) 
	{
		GObject obj = program.getElementAt(e.getX(), e.getY());
	}

}
