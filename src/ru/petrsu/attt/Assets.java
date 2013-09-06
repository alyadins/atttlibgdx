package ru.petrsu.attt;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

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
    public static final String SETTINGS = "settings";
    public static final String SOUND = "sound";
    public static final float FADE_VALUE = 0.2f;
    //Textures
    public static Texture background;
    public static TextureRegion logo;
    public static TextureRegion startButton;
    public static TextureRegion highscoresButton;
    public static TextureRegion helpButton;
    public static TextureRegion soundOnButton;
    public static TextureRegion soundOffButton;
    public static TextureRegion settingsButton;
    public static TextureRegion bigField;
    public static TextureRegion smallFeild;
    public static TextureRegion cross;
    public static TextureRegion zero;
    public static TextureRegion bigCross;
    public static TextureRegion bigZero;
    public static TextureRegion back;

    //fonts
    public static BitmapFont textFont;

    //Sounds
    public static Sound click;

    public static final int screenWidth = Gdx.graphics.getWidth();
    public static final int screenHeight = Gdx.graphics.getHeight();
    public static float widthRatio = (float)screenWidth / (float)VIRTUAL_WIDTH;
    public static float heightRatio = (float)screenHeight/ (float)VIRTUAL_HEIGHT;

    public static final Color crossColor = new Color(0.6f, 0.4f,0.8f, 1.0f);
    public static final Color zeroColor = new Color(0f, 0.278f, 0.671f, 1.0f);

    public static void load() {
        loadTextures();
        loadFonts();
        loadSounds();
    }

    private static void loadSounds() {
        click = Gdx.audio.newSound(Gdx.files.internal("sound/click.ogg"));
    }


    private static void loadTextures() {
        TextureAtlas atlas = new TextureAtlas("images/attt.pack");
        background = newTexture("images/background.jpg");
        logo = atlas.findRegion("logo");
        startButton = atlas.findRegion("start");
        highscoresButton = atlas.findRegion("highscores");
        helpButton = atlas.findRegion("help");
        soundOnButton = atlas.findRegion("speaker_on");
        soundOffButton = atlas.findRegion("speaker_off");
        settingsButton = atlas.findRegion("settings");
        bigField = atlas.findRegion("big_field");
        smallFeild = atlas.findRegion("small_field");
        cross = atlas.findRegion("cros");
        zero = atlas.findRegion("zero");
        bigCross = atlas.findRegion("big_cros");
        bigZero = atlas.findRegion("big_zero");
        back = atlas.findRegion("back_button");
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

    public static boolean inBounds(int inputX, int inputY, float x, float y, float width, float height) {
        return inputX > x && inputX < x + width - 1 &&
                inputY > y && inputY < y + height - 1;
    }
}
