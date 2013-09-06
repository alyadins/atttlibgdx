package ru.petrsu.attt.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.model.SmallFieldModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/25/13
 * Time: 8:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SmallField extends View {
    private static final int NUMBER_OF_BLINKES = 4;
    private static final float BLINKED_TIME = 0.5f;

    SmallFieldModel model;
    Sprite smallField;
    List<CrossZero> crossZeros;
    Color currentColor = Color.LIGHT_GRAY;
    Color regularColor = Color.LIGHT_GRAY;
    Color activeColor = Color.RED;
    Color tmpColor;

    boolean isBlinked = false;
    int numberOfBlinkers = 0;
    float timer;

    public SmallField(SmallFieldModel model, float width, float height) {
        super(width, height);
        this.type = Type.FIELD;
        this.model = model;
        smallField = new Sprite(Assets.smallFeild);
        calculateSpriteSizes(smallField);
        crossZeros = new ArrayList<CrossZero>();
        initCrosZeros();
    }

    private void initCrosZeros() {
        for (int i = 0; i < 9; i++) {
            crossZeros.add(new CrossZero(width / 3, height / 3, CrossZero.Cell.NONE));
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < 9; i++) {
            crossZeros.get(i).draw(spriteBatch);
        }
        smallField.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {
        blink(delta);
        smallField.setPosition(x, y);
        smallField.setSize(width, height);
        smallField.setColor(currentColor);
        for (int i = 0; i < 9; i++) {
            CrossZero cz = crossZeros.get(i);
            cz.setPosition(x + (width / 3) * (i % 3),
                    y + (height / 3) * (2 - (i / 3)));
            cz.setAbsoluteSize(width / 3, height / 3);
            cz.update(delta);
            switch (model.cells.get(i)) {
                case SmallFieldModel.CROSS:
                    cz.setCell(CrossZero.Cell.CROSS);
                    break;
                case SmallFieldModel.ZER0:
                    cz.setCell(CrossZero.Cell.ZERO);
                    break;
                case SmallFieldModel.NONE:
                    cz.setCell(CrossZero.Cell.NONE);
                    break;
            }
        }
    }

    public void blink() {
        if (!isBlinked) {      //lock for other blinks
            //save current
            tmpColor = new Color(currentColor.r, currentColor.g, currentColor.b, currentColor.a);
            isBlinked = true;
        }
    }

    private void blink(float delta) {
        if (isBlinked) {
            if (timer > BLINKED_TIME / (NUMBER_OF_BLINKES * 2)) {
                if (numberOfBlinkers % 2 == 1) {
                    currentColor = activeColor;
                } else {
                    currentColor = regularColor;
                }
                numberOfBlinkers++;
                timer = 0;
                if (numberOfBlinkers > NUMBER_OF_BLINKES * 2 - 1) {
                    stopBlink();
                }
            } else {
                timer += delta;
            }
        }
    }


    public void checkCaptured() {
        if (model.isFinished) {
            if (model.capturedBy == SmallFieldModel.CROSS) {
                regularColor = Assets.crossColor;
                currentColor = regularColor;
            } else if (model.capturedBy == SmallFieldModel.ZER0) {
                regularColor = Assets.zeroColor;
                currentColor = regularColor;
            }
        }
    }

    public void stopBlink() {
        if (isBlinked) {
            isBlinked = false;
            numberOfBlinkers = 0;
            currentColor = tmpColor;
        }
    }

    public void setColor(Color color) {
        this.currentColor = color;
    }

    public void setActive() {
        this.currentColor = activeColor;
    }

    public void setNormal() {
        this.currentColor = regularColor;
    }

    public void fade() {
        for (CrossZero cz : crossZeros) {
            cz.fade();
        }
        Color newColor = new Color(this.currentColor.r,
                this.currentColor.g,
                this.currentColor.b,
                Assets.FADE_VALUE);
        setColor(newColor);
    }

    public void unfade() {
        for (CrossZero cz : crossZeros) {
            cz.unFade();
        }
        Color newColor = new Color(this.currentColor.r,
                this.currentColor.g,
                this.currentColor.b,
                1);
        setColor(newColor);
    }
}
