package starter;

public class SoundHub {

	MainApplication program;
	AudioPlayer soundHub;
	String FILE_PATH = "sounds";
	boolean isPlaying;
	
	String background;
	
	public SoundHub(MainApplication mainApp){
		this.background = "background1.mp3";
		this.isPlaying=true;
		this.program = mainApp;
		this.soundHub = AudioPlayer.getInstance();
		play();
	}
	
	public void play()
	{
		soundHub.playSound(FILE_PATH, background, true);
	}
	
	public void stop()
	{
		soundHub.stopSound(FILE_PATH, background);
	}
	
	public void toggleSound() {// allows user to toggle audio 
		if(isPlaying) {
			stop();
		}else {
			play();
		}
		isPlaying=!isPlaying;
	}
	
	public void playInput(String str) {// special FX that aren't tied to enumerators that need to be called only once or twice
		if(isPlaying) {
			switch(str) {
			case"eat":
				soundHub.playSound(FILE_PATH, "eating.mp3");
				return;
			case"gameover":
				soundHub.playSound(FILE_PATH, "gameover.mp3");
				return;
			case"jump":
				soundHub.playSound(FILE_PATH, "jumping.mp3");
				return;
			case"run":
				soundHub.playSound(FILE_PATH, "runinng.mp3");
				return;
			case"super":
				soundHub.playSound(FILE_PATH, "superpowerup.mp3");
				return;
			case"click":
				soundHub.playSound(FILE_PATH, "buttonclick.mp3");
				return;
			case"energy":
				soundHub.playSound(FILE_PATH, "e.mp3");
				return;
			}
		}
	}
	
}
