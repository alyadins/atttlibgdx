package ru.petrsu.attt.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.input.ButtonsInputProcessor;
import ru.petrsu.attt.view.Button;
import ru.petrsu.attt.view.Field;
import ru.petrsu.attt.view.Picture;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/24/13
 * Time: 1:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen, ButtonsInputProcessor.OnClickListener {
    private Game game;
    private Sprite background;
    private Button backButton;
    private ButtonsInputProcessor inputProcessor;
    private SpriteBatch spriteBatch;
    private Field field;

    public GameScreen(Game game) {
        this.game = game;
        inputProcessor = new ButtonsInputProcessor();
        inputProcessor.setOnClickListener(this);
        spriteBatch = new SpriteBatch();
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        spriteBatch.begin();
        background.draw(spriteBatch);
        backButton.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        background = new Sprite(Assets.background);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        backButton = new Button(Assets.settingsButton);
        backButton.setPosition(Picture.Position.BOTTOM);
        backButton.setPosition(Picture.Position.RIGHT);
        inputProcessor.addButton(backButton);
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

    @Override
    public void onClick(Button button) {
        game.setScreen(new MainMenuScreen(game));
    }
}
