package ru.petrsu.attt;

import android.util.Log;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/20/13
 * Time: 11:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Assets {
    public static final int VIRTUAL_WIDTH = 720;
    public static final int VIRTUAL_HEIGHT = 1280;
    //Textures
    public static Texture background;
    public static TextureRegion logo;
    public static TextureRegion startButton;
    public static TextureRegion highscoresButton;
    public static TextureRegion helpButton;
    public static TextureRegion soundOnButton;
    public static TextureRegion soundOffButton;
    public static TextureRegion settingsButton;


    //fonts
    public static BitmapFont textFont;

    public static final int screenWidth = Gdx.graphics.getWidth();
    public static final int screenHeight = Gdx.graphics.getHeight();
    public static float widthRatio = (float)screenWidth / (float)VIRTUAL_WIDTH;
    public static float heightRatio = (float)screenHeight/ (float)VIRTUAL_HEIGHT;

    public static void load() {
        loadTextures();
        loadFonts();
    }


    private static void loadTextures() {
        TextureAtlas atlas = new TextureAtlas("images/attt.pack");
        background = newTexture("images/background.jpg");
        logo = atlas.findRegion("logo");
        startButton = atlas.findRegion("start");
        highscoresButton = atlas.findRegion("highscores");
        helpButton = atlas.findRegion("help");
        soundOnButton = atlas.findRegion("speaker_on");
        settingsButton = atlas.findRegion("settings");
    }

    private static void loadFonts() {
        textFont = new BitmapFont(Gdx.files.internal("fonts/neucha.fnt"), false);

        textFont.setScale(1.0f);
    }

    private static Texture newTexture(String path) {
        Texture texture = new Texture(Gdx.files.internal(path));
        texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return texture;
    }
}
