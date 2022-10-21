package danbl.game.spaceshoot;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import danbl.game.spaceshoot.controller.*;


// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("经典复刻-太空射击");
		config.setWindowedMode(500, 900);
//        config.setResizable(true);
		config.useVsync(true);
		config.setForegroundFPS(60);
		new Lwjgl3Application(new GameController(), config);
	}
}
