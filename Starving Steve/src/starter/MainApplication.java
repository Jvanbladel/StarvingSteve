package starter;
public class MainApplication extends GraphicsApplication {
	public static final int WINDOW_WIDTH = 800;
	public static final int WINDOW_HEIGHT = 600;

	private MenuPane menu;

	public void init() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
	}

	public void run() {
		System.out.println("Hello, world!");
		menu = new MenuPane(this);
		switchToMenu();
	}

	public void switchToMenu() {
		switchToScreen(menu);
	}

}
