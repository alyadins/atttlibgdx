package ru.petrsu.attt.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.input.MyInputProcessor;
import ru.petrsu.attt.input.MyInputProcessor.ButtonClickListener;
import ru.petrsu.attt.input.MyInputProcessor.FieldClickListener;
import ru.petrsu.attt.model.FieldModel;
import ru.petrsu.attt.model.Player;
import ru.petrsu.attt.model.SmallFieldModel;
import ru.petrsu.attt.view.*;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/24/13
 * Time: 1:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameScreen implements Screen {
    public static final float fieldY = Assets.heightRatio * 300;

    private Game game;
    private SpriteBatch spriteBatch;

    private Sprite background;
    private Button settingsButton;
    private ZoomedField zoomedField;
    private Button backButton;
    private Field field;
    private View currentField;

    private FieldModel model = new FieldModel();

    private Player crossPlayer;
    private Player zeroPlayer;
    private Player currentPlayer;

    private MyInputProcessor fieldInput;
    private MyInputProcessor zoomedFieldInput;

    public GameScreen(Game game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
        initInput();
        crossPlayer = new Player();
        zeroPlayer = new Player();
        currentPlayer = crossPlayer;
    }

    private void initInput() {
        fieldInput = new MyInputProcessor();
        fieldInput.setButtonClickListener(buttonClickListener);
        fieldInput.setFieldClickListener(fieldClickListener);
        Gdx.input.setInputProcessor(fieldInput);

        zoomedFieldInput = new MyInputProcessor();
        zoomedFieldInput.setButtonClickListener(buttonClickListener);
        zoomedFieldInput.setFieldClickListener(fieldClickListener);
    }

    @Override
    public void render(float delta) {
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
        field.update(delta);
        zoomedField.update(delta);
        spriteBatch.begin();
        background.draw(spriteBatch);
        settingsButton.draw(spriteBatch);
        backButton.draw(spriteBatch);
        field.draw(spriteBatch);
        zoomedField.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        background = new Sprite(Assets.background);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        Sprite fieldSprite = new Sprite(Assets.bigField);
       // View.calculateSpriteSizes(fieldSprite);
        field = new Field(model, fieldSprite);
        field.setPosition(View.Position.CENTER_VERTICAL);
        field.setY(fieldY);
        fieldInput.addView(field);
        currentField = field;

        Sprite zoomedFieldSprite = new Sprite(Assets.bigField);
      //  View.calculateSpriteSizes(zoomedFieldSprite);
        zoomedField = new ZoomedField(model.sfm.get(0), zoomedFieldSprite);
        zoomedField.setPosition(View.Position.CENTER_VERTICAL);
        zoomedField.setY(fieldY);
        zoomedFieldInput.addView(zoomedField);


        Sprite settingsButtonSprite = new Sprite(Assets.settingsButton);
        settingsButton = new Button(settingsButtonSprite);
        settingsButton.setPosition(Picture.Position.BOTTOM);
        settingsButton.setPosition(Picture.Position.RIGHT);
        settingsButton.setSound(Assets.click);
        fieldInput.addView(settingsButton);
        zoomedFieldInput.addView(settingsButton);

        Sprite backButtonSprite = new Sprite(Assets.back);
        backButton = new Button(backButtonSprite, Color.BLACK);
        backButton.setPosition(View.Position.CENTER_VERTICAL);
        backButton.setY(field.y - backButton.height);
        backButton.setSound(Assets.click);
        backButton.hide();
        zoomedFieldInput.addView(backButton);
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
                if (view.id == settingsButton.id) {
                    settingsButton.playSound();
                    game.setScreen(new MainMenuScreen(game));
                }
                if (view.id == backButton.id) {
                    if (zoomedField.isRenderer()) {
                        changeField(field);
                    }
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
            if (view.id == zoomedField.id) {
                zoomedFieldClick(view, row, column);
            } else if (view.id == field.id) {
                fieldClick(view, row, column);
            }
        }
    };

    private void fieldClick(View view, int row, int column) {
        // if it isn't first step of game
        if (model.activeFieldRow != -1 || model.activeFieldColumn != -1) {
            // if user click on nonactive field and it isn't first step of game
            int activeRow = model.activeFieldRow;
            int activeColumn = model.activeFieldColumn;
            if (activeRow != row || activeColumn != column) {
                field.smallFields.get(activeRow * 3 + activeColumn).blink();
            } else { // user click on right field
                setZoomedField(row, column);
            }
        } else {
            model.setActiveField(row, column);
            setZoomedField(row, column); // Called in first step
        }
    }

    private void zoomedFieldClick(View view, int row, int column) {
        if (checkCell(row, column)) {
            field.smallFields.get(model.activeFieldRow * 3 + model.activeFieldColumn).setNormal();
            if (currentPlayer == crossPlayer) {
                model.update(SmallFieldModel.CROSS, row, column);
                currentPlayer = zeroPlayer;
            } else if (currentPlayer == zeroPlayer) {
                model.update(SmallFieldModel.ZER0, row, column);
                currentPlayer = crossPlayer;
            }
            changeField(field);
            if (model.activeFieldRow != -1 && model.activeFieldColumn != -1) {
                field.smallFields.get(model.activeFieldRow * 3 + model.activeFieldColumn).setActive();
            }
        }
    }


    /**
     * Check cell before put cross or zero
     *
     * @param row
     * @param column
     * @return true if it is possible
     */
    private boolean checkCell(int row, int column) {
        int aRow = model.activeFieldRow;
        int aColumn = model.activeFieldColumn;
        SmallFieldModel sfm = model.sfm.get(aRow * 3 + aColumn);
        if (!model.sfm.get(row * 3 + column).isFull) {
            if (SmallFieldModel.NONE == sfm.cells.get(row * 3 + column)) {
                return true;
            } else {
                return false;
            }
        } else return false;
    }

    private void setZoomedField(int row, int column) {
        field.smallFields.get(row * 3 + column).stopBlink();
        SmallFieldModel sfm = model.sfm.get(row * 3 + column);
        if (!sfm.isFull) {
            zoomedField.setModel(sfm);

            changeField(zoomedField);
        }
    }

    private void changeField(View field) {
        if (field.id == this.field.id) {
            this.field.unFade();
            zoomedField.setRenderer(false);
            for (int i = 0; i < 9; i++) {
                this.field.smallFields.get(i).checkCaptured();
            }
            if (model.gameOver) {
                game.setScreen(new MainMenuScreen(game));
            }
            backButton.hide();
            Gdx.input.setInputProcessor(fieldInput);
        }

        if (field.id == this.zoomedField.id) {
            this.field.fade();
            backButton.show();
            zoomedField.setRenderer(true);
            Gdx.input.setInputProcessor(zoomedFieldInput);
        }
    }
}
