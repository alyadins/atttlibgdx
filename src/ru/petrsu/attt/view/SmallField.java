package ru.petrsu.attt.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.model.SmallFieldModel;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/25/13
 * Time: 8:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class SmallField extends View{
    SmallFieldModel model;
    Sprite smallField;
    public SmallField(SmallFieldModel model, Sprite smallField) {
        super(smallField.getWidth(), smallField.getHeight());
        this.smallField = smallField;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        updateSmallField();
        smallField.draw(spriteBatch);
    }

    private void updateSmallField() {
        smallField.setPosition(x, y);
        smallField.setSize(width, height);
    }
}
