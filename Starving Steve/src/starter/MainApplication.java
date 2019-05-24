package starter;
public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	private MenuPane menu;
	private GamePane game;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		menu = new MenuPane(this);
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

}
//Hi Jason! I installed eclipse and connected to github//
