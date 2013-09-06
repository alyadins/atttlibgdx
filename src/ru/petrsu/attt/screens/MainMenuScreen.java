package ru.petrsu.attt.screens;

import android.util.Log;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.Settings;
import ru.petrsu.attt.input.MyInputProcessor;
import ru.petrsu.attt.input.MyInputProcessor.ButtonClickListener;
import ru.petrsu.attt.view.Button;
import ru.petrsu.attt.view.Picture;
import ru.petrsu.attt.view.View;
import ru.petrsu.attt.view.View.Position;


public class MainMenuScreen implements Screen {
    Game game;
    private SpriteBatch spriteBatch;
    private Picture logo;
    private Sprite background;
    private Button startButton;
    private Button highscoresButton;
    private Button helpButton;
    private Button soundButton;
    private Button settingsButton;

    private MyInputProcessor inputProcessor = new MyInputProcessor();

    public MainMenuScreen(Game game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        inputProcessor.setButtonClickListener(buttonClickListener);
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
        Sprite startButtonSprite = new Sprite(Assets.startButton);
        startButton = new Button(startButtonSprite, Color.BLACK);
        startButton.setPosition(Position.CENTER_VERTICAL);
        startButton.setY(Assets.heightRatio * 662);
        startButton.setPressedColor(Color.LIGHT_GRAY);
        startButton.setSound(Assets.click);
        inputProcessor.addView(startButton);

        //high scores button
        Sprite highscoresButtonSprite = new Sprite(Assets.highscoresButton);
        highscoresButton = new Button(highscoresButtonSprite, Color.BLACK);
        highscoresButton.setPosition(Position.CENTER_VERTICAL);
        highscoresButton.setY(Assets.heightRatio * 480);
        highscoresButton.setPressedColor(Color.LIGHT_GRAY);
        highscoresButton.setSound(Assets.click);
        inputProcessor.addView(highscoresButton);

        //help button
        Sprite helpButtonSprite = new Sprite(Assets.helpButton);
        helpButton = new Button(helpButtonSprite, Color.BLACK);
        helpButton.setPosition(Position.CENTER_VERTICAL);
        helpButton.setY(Assets.heightRatio * 302);
        helpButton.setPressedColor(Color.LIGHT_GRAY);
        helpButton.setSound(Assets.click);
        inputProcessor.addView(helpButton);

        //settings button
        Sprite settingsButtonSprite = new Sprite(Assets.settingsButton);
        settingsButton = new Button(settingsButtonSprite);
        settingsButton.setPosition(Position.BOTTOM);
        settingsButton.setPosition(Position.RIGHT);
        settingsButton.setSound(Assets.click);
        inputProcessor.addView(settingsButton);


        Sprite soundOnButtonSprite = new Sprite(Assets.soundOnButton);
        Sprite soundOffButtonSprite = new Sprite(Assets.soundOffButton);
        //sound button
        if (Settings.isSoundEnabled()) {
            soundButton = new Button(soundOnButtonSprite);
        } else {
            soundButton = new Button(soundOffButtonSprite);
        }
        soundButton.setPosition(Position.BOTTOM);
        soundButton.setPosition(Position.LEFT);
        soundButton.setSound(Assets.click);
        inputProcessor.addView(soundButton);
    }

    private void drawButtons() {
        startButton.draw(spriteBatch);
        highscoresButton.draw(spriteBatch);
        helpButton.draw(spriteBatch);
        soundButton.draw(spriteBatch);
        settingsButton.draw(spriteBatch);
    }


    private ButtonClickListener buttonClickListener = new ButtonClickListener() {
        @Override
        public void touchUp(View view) {
            if (view != null) {
                if (view.id == startButton.id) {
                    startButton.playSound();
                    game.setScreen(new GameScreen(game));
                }

                if (view.id == highscoresButton.id) {
                     highscoresButton.playSound();
                    Log.d("TEST", "highscores");
                }

                if (view.id == helpButton.id) {
                    helpButton.playSound();
                    Log.d("TEST", "help");
                }

                if (view.id == settingsButton.id) {
                    settingsButton.playSound();
                    Log.d("TEST", "settings");
                }

                if (view.id == soundButton.id) {
                    soundButton.playSound();
                    Settings.setSoundEnabled(!Settings.isSoundEnabled());
                    if (Settings.isSoundEnabled()) {
                        soundButton.setTexture(Assets.soundOnButton);
                    } else {
                        soundButton.setTexture(Assets.soundOffButton);
                    }
                }
            } else {
                startButton.setNormal();
                highscoresButton.setNormal();
                helpButton.setNormal();
                settingsButton.setNormal();
                soundButton.setNormal();
            }
        }

        @Override
        public void touchDown(View view) {
            if (view.id == startButton.id) {
                startButton.setClicked();
            }

            if (view.id == highscoresButton.id) {
                highscoresButton.setClicked();
            }

            if (view.id == helpButton.id) {
                helpButton.setClicked();
            }
        }
    };
}
