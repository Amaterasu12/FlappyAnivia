package com.amaterasu.flappynerd.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.amaterasu.flappynerd.FlappyNerd;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                config.width = FlappyNerd.WIDTH;
                config.height = FlappyNerd.HEIGHT;
		new LwjglApplication(new FlappyNerd(), config);
	}
}
