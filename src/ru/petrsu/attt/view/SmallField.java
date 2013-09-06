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
    Color currentColor;
    Color color = Color.LIGHT_GRAY;

    boolean isBlinked = false;
    int numberOfBlinkes = 0;
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
        smallField.setColor(color);
        for (int i = 0; i < 9; i++) {
            CrossZero cz = crossZeros.get(i);
            cz.setPosition(x + (width / 3) * (i % 3),
                    y + (height / 3) * (2 -(i / 3)));
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
            //save color
            currentColor = color;
            isBlinked = true;
        }
    }

    private void blink(float delta) {
        if (isBlinked) {
            if (timer > BLINKED_TIME / (NUMBER_OF_BLINKES * 2)) {
                if (numberOfBlinkes % 2 == 1) {
                    color = Color.RED;
                } else {
                    color = Color.LIGHT_GRAY;
                }
                numberOfBlinkes++;
                timer = 0;
                if (numberOfBlinkes > NUMBER_OF_BLINKES * 2 - 1) {
                    isBlinked = false;
                    numberOfBlinkes = 0;
                    color = currentColor;
                }
            } else {
                timer += delta;
            }
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setActive() {
        this.color = Color.RED;
    }

    public void setNormal() {
        this.color = Color.LIGHT_GRAY;
    }

    public void fade() {
        for(CrossZero cz : crossZeros) {
            cz.fade();
        }
        Color newColor = new Color(this.color.r,
                this.color.g,
                this.color.b,
                Assets.FADE_VALUE);
        setColor(newColor);
    }
    public void unfade() {
        for (CrossZero cz : crossZeros) {
            cz.unFade();
        }
        Color newColor = new Color(this.color.r,
                this.color.g,
                this.color.b,
                1);
        setColor(newColor);
    }
}
