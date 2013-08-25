package ru.petrsu.attt.view;

import android.util.Log;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.model.SmallFieldModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/25/13
 * Time: 8:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SmallField extends View {
    SmallFieldModel model;
    Sprite smallField;
    List<CrosZero> crosZeros;
    Color color = Color.LIGHT_GRAY;

    public SmallField(SmallFieldModel model, float width, float height) {
        super(width, height);
        smallField = new Sprite(Assets.small_feild);
        calculateSpriteSizes(smallField);
        crosZeros = new ArrayList<CrosZero>();
        initCrosZeros();
    }

    private void initCrosZeros() {
        for (int i = 0; i < 9; i++) {
            crosZeros.add(new CrosZero(width / 3, height / 3, CrosZero.Type.NONE));
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < 9; i++) {
            crosZeros.get(i).draw(spriteBatch);
        }
        smallField.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {
        smallField.setPosition(x, y);
        smallField.setSize(width, height);
        smallField.setColor(color);
        for (int i = 0; i < 9; i++) {
            CrosZero cz = crosZeros.get(i);
            cz.setPosition(x + (width / 3) * (i / 3),
                    y + (height / 3) * (i % 3));
            cz.setAbsoluteSize(width / 3, height / 3);
            cz.update(delta);
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
