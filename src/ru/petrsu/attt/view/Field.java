package ru.petrsu.attt.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.model.FieldModel;

public class Field extends View {
    FieldModel model;
    public Field(FieldModel model) {
        super(Assets.screenWidth, Assets.screenHeight);
        this.model = model;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }
}
