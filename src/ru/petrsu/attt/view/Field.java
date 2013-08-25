package ru.petrsu.attt.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.model.FieldModel;

import java.util.ArrayList;
import java.util.List;

public class Field extends View {
    FieldModel model;
    Sprite field;
    Sprite smallField;
    List<SmallField> smallFields;
    public Field(FieldModel model, Sprite fieldSprite) {
        super(fieldSprite.getWidth(), fieldSprite.getHeight());
        this.model = model;
        this.field = fieldSprite;
        setSize(field.getWidth(), field.getHeight());
        initSmallFields();
    }

    private void initSmallFields() {
        smallField = new Sprite(Assets.small_feild);
        calculateSpriteSizes(smallField);
        smallFields = new ArrayList<SmallField>();
        for (int i = 0; i < 9; i++) {
                smallFields.add(new SmallField(model.smallFieldModels.get(i), smallField));
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        updateField();
        for (int i = 0; i < 9; i++) {
            smallFields.get(i).draw(spriteBatch);
        }
        field.draw(spriteBatch);
    }

    private void updateField() {
        field.setPosition(x, y);
        field.setSize(width, height);
        for (int i = 0; i < 9; i++) {
            SmallField smallField = smallFields.get(i);
            smallField.setPosition(x + (width / 3) * (i / 3),
                    y + (height / 3) * (i % 3));
            smallField.setAbsoluteSize(width / 3, height / 3);
        }
    }
}
