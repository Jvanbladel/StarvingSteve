package starter;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	public boolean showTutorial, sound;
	
	private MenuPane menu;
	private GamePane game;
	private SettingsPane settings;
	private GameOverPane gameOver;
	private AboutUsPane aboutUs;
	private TutorialPane tutorial;
	
	private SoundHub soundDriver;

	private Timer t;
	
	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		this.sound = true;
		menu = new MenuPane(this);
		settings = new SettingsPane(this);
		tutorial = new TutorialPane(this);
		showTutorial = true;
		t = new Timer(10, this);
		t.start();
		soundDriver = new SoundHub(this);
		switchToMenu();
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
	

	public void switchToAboutUs()
	{
		aboutUs = new AboutUsPane(this);
		switchToScreen(aboutUs);
	}
	
	public void switchToTutorial() {
		if (showTutorial) {
			switchToScreen(tutorial);
		} else {
			startNewGame();
		}
	}
	
	public void toggleSound()
	{
		this.sound = !sound;
		soundDriver.toggleSound();
	}
	
	public void playSound(String s)
	{
		soundDriver.playInput(s);
	}
	
	public boolean isSoundOn()
	{
		return sound;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		init();
	}

}
//Hi Jason! I installed eclipse and connected to github//
