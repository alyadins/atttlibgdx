package ru.petrsu.attt.screens;

import android.util.Log;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.Settings;
import ru.petrsu.attt.input.ButtonsInputProcessor;
import ru.petrsu.attt.view.Button;
import ru.petrsu.attt.view.Picture;
import ru.petrsu.attt.view.View.Position;
import ru.petrsu.attt.view.View;


public class MainMenuScreen implements Screen, ButtonsInputProcessor.OnClickListener {
    Game game;
    private SpriteBatch spriteBatch;
    private Picture logo;
    private Sprite background;
    private Button startButton;
    private Button highscoresButton;
    private Button helpButton;
    private Button soundButton;
    private Button settingsButton;

    private ButtonsInputProcessor inputProcessor;

    public MainMenuScreen(Game game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        inputProcessor = new ButtonsInputProcessor();
        inputProcessor.setOnClickListener(this);
        Gdx.input.setInputProcessor(inputProcessor);
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
        startButton.setClickedTexture(Assets.startPressed);
        startButton.setSound(Assets.click);
        inputProcessor.addButton(startButton);

        //high scores button
        highscoresButton = new Button(Assets.highscoresButton);
        highscoresButton.setPosition(Position.CENTER_VERTICAL);
        highscoresButton.setY(Assets.heightRatio * 560);
        highscoresButton.setClickedTexture(Assets.highcoresPressed);
        highscoresButton.setSound(Assets.click);
        inputProcessor.addButton(highscoresButton);

        //help button
        helpButton = new Button(Assets.helpButton);
        helpButton.setPosition(Position.CENTER_VERTICAL);
        helpButton.setY(Assets.heightRatio * 382);
        helpButton.setClickedTexture(Assets.helpPressed);
        helpButton.setSound(Assets.click);
        inputProcessor.addButton(helpButton);

        //settings button
        settingsButton = new Button(Assets.settingsButton);
        settingsButton.setPosition(Position.BOTTOM);
        settingsButton.setPosition(Position.RIGHT);
        settingsButton.setSound(Assets.click);
        inputProcessor.addButton(settingsButton);

        //sound button
        if (Settings.isSoundEnabled()) {
            soundButton = new Button(Assets.soundOnButton);
        } else {
            soundButton = new Button(Assets.soundOffButton);
        }
        soundButton.setPosition(Position.BOTTOM);
        soundButton.setPosition(Position.LEFT);
        soundButton.setSound(Assets.click);
        inputProcessor.addButton(soundButton);
    }

    private void drawButtons() {
        startButton.draw(spriteBatch);
        highscoresButton.draw(spriteBatch);
        helpButton.draw(spriteBatch);
        soundButton.draw(spriteBatch);
        settingsButton.draw(spriteBatch);
    }

    @Override
    public void onClick(Button button) {
        if (button.equals(startButton)) {
            game.setScreen(new GameScreen(game));
        }
        if (button.equals(highscoresButton)) {
            Log.d("TEST", "highscores");
        }
        if (button.equals(helpButton)) {
        }
        if (button.equals(settingsButton)) {
            Log.d("TEST", "settings");
        }
        if (button.equals(soundButton)) {
            Settings.setSoundEnabled(!Settings.isSoundEnabled());
            if (Settings.isSoundEnabled()) {
                soundButton.setNormalTexture(Assets.soundOnButton);
            } else {
                soundButton.setNormalTexture(Assets.soundOffButton);
            }
        }
    }
}
