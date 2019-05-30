package starter;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	private MenuPane menu;
	private GamePane game;
	private SettingsPane settings;
	private GameOverPane gameOver;
	private Timer t;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		menu = new MenuPane(this);
		settings = new SettingsPane(this);
		switchToMenu();
		t = new Timer(10, this);
		t.start();
	}

	public void switchToMenu() {
		switchToScreen(menu);
	}
	
	public void startNewGame()
	{
		game = new GamePane(this);
		switchToScreen(game);
	}
	
	public void changeToSettings()
	{
		switchToScreen(settings);
	}
	
	public void switchToGameOver() {
		gameOver = new GameOverPane(this, game.getScore());
		switchToScreen(gameOver);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		init();
	}

}
//Hi Jason! I installed eclipse and connected to github//
