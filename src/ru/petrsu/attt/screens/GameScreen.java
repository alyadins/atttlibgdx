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
import ru.petrsu.attt.input.MyInputProcessor;
import ru.petrsu.attt.input.MyInputProcessor.ButtonClickListener;
import ru.petrsu.attt.input.MyInputProcessor.FieldClickListener;
import ru.petrsu.attt.model.FieldModel;
import ru.petrsu.attt.view.Button;
import ru.petrsu.attt.view.Field;
import ru.petrsu.attt.view.Picture;
import ru.petrsu.attt.view.View;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/24/13
 * Time: 1:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen {
    private Game game;
    private Sprite background;
    private Button backButton;
    private SpriteBatch spriteBatch;
    private Field field;
    private MyInputProcessor inputProcessor = new MyInputProcessor();

    public GameScreen(Game game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        inputProcessor.setButtonClickListener(buttonClickListener);
        inputProcessor.setFieldClickListener(fieldClickListener);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        field.update(delta);
        spriteBatch.begin();
        background.draw(spriteBatch);
        backButton.draw(spriteBatch);
        field.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        background = new Sprite(Assets.background);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        field = new Field(new FieldModel(), new Sprite(Assets.big_field));
        field.setPosition(View.Position.CENTER_VERTICAL);
        field.setPosition(View.Position.CENTER_HORIZONTAL);
        field.setInputProcessor(inputProcessor);
        //inputProcessor.addView(field);

        backButton = new Button(Assets.settingsButton);
        backButton.setPosition(Picture.Position.BOTTOM);
        backButton.setPosition(Picture.Position.RIGHT);
        backButton.setSound(Assets.click);
        inputProcessor.addView(backButton);
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


    private ButtonClickListener buttonClickListener = new ButtonClickListener() {
        @Override
        public void touchUp(View view) {
            if (view != null) {
                if (view.id == backButton.id) {
                    backButton.playSound();
                    game.setScreen(new MainMenuScreen(game));
                }
            }
        }

        @Override
        public void touchDown(View view) {

        }
    };

    private FieldClickListener fieldClickListener = new FieldClickListener() {
        @Override
        public void onClick(View view, int row, int column) {
            Log.d("TEST", "row: " + String.valueOf(row) + " column: " + String.valueOf(column));
        }
    };
}
