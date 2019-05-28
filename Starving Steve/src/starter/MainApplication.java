package starter;
public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	private MenuPane menu;
	private GamePane game;
	private SettingsPane settings;
	private GameOverPane gameOver;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		menu = new MenuPane(this);
		settings = new SettingsPane(this);
		gameOver = new GameOverPane(this);
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
		switchToScreen(gameOver);
	}

}
//Hi Jason! I installed eclipse and connected to github//
