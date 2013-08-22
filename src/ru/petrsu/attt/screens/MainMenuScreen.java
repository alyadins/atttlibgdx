package ru.petrsu.attt.screens;

import android.util.Log;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.util.Button;
import ru.petrsu.attt.util.Picture;
import ru.petrsu.attt.util.Picture.Position;


public class MainMenuScreen implements Screen {
    private final static float LOGO_WIDTH = 0.8f;
    private final static float LOGO_Y = 1.7f;

    Game game;
    private SpriteBatch spriteBatch;
    private Picture logo;
    private Sprite background;
    private Button startButton;
    private Button highscoresButton;
    private Button helpButton;
    private Button soundButton;
    private Button settingsButton;

    public MainMenuScreen(Game game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        // clear previous frame
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);

        spriteBatch.begin();
        background.draw(spriteBatch);
        logo.draw(spriteBatch);
        drawButtons();
        spriteBatch.end();
    }


    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        showLogo();
        showBackground();
        showMenuButtons();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

    private void showLogo() {
        logo = new Picture(Assets.logo);
        logo.setPosition(Position.CENTER_VERTICAL);
        logo.setY(Assets.heightRatio * 930);
        Stage stage = new Stage();
    }

    private void showBackground() {
        background = new Sprite(Assets.background);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void showMenuButtons() {
        //start button
        startButton = new Button(Assets.startButton);
        startButton.setPosition(Position.CENTER_VERTICAL);
        startButton.setY(Assets.heightRatio * 742);

        //high scores button
        highscoresButton = new Button(Assets.highscoresButton);
        highscoresButton.setPosition(Position.CENTER_VERTICAL);
        highscoresButton.setY(Assets.heightRatio * 560);

        //help button
        helpButton = new Button(Assets.helpButton);
        helpButton.setPosition(Position.CENTER_VERTICAL);
        helpButton.setY(Assets.heightRatio * 382);

        //settings button
        settingsButton = new Button(Assets.settingsButton);
        settingsButton.setPosition(Position.BOTTOM);
        settingsButton.setPosition(Position.RIGHT);

        //sound button
        soundButton = new Button(Assets.soundOnButton);
        soundButton.setPosition(Position.BOTTOM);
        soundButton.setPosition(Position.LEFT);
    }

    private void drawButtons() {
        startButton.draw(spriteBatch);
        highscoresButton.draw(spriteBatch);
        helpButton.draw(spriteBatch);
        soundButton.draw(spriteBatch);
        settingsButton.draw(spriteBatch);
    }
}