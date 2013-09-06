package ru.petrsu.attt.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.input.MyInputProcessor;
import ru.petrsu.attt.model.FieldModel;

import java.util.ArrayList;
import java.util.List;

public class Field extends View {
    FieldModel model;
    Sprite field;
    Sprite smallField;
    public List<SmallField> smallFields;

    private Color color = Color.BLACK;

    public Field(FieldModel model, Sprite fieldSprite) {
        super(fieldSprite.getWidth(), fieldSprite.getHeight());
        this.model = model;
        this.field = fieldSprite;
        this.type = Type.FIELD;
        setSize(field.getWidth(), field.getHeight());
        initSmallFields();
    }

    private void initSmallFields() {
        smallField = new Sprite(Assets.smallFeild);
        smallFields = new ArrayList<SmallField>();
        for (int i = 0; i < 9; i++) {
            smallFields.add(new SmallField(model.sfs.get(i), width / 3, height / 3));
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        for (int i = 0; i < 9; i++) {
            smallFields.get(i).draw(spriteBatch);
        }
        field.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {
        field.setPosition(x, y);
        field.setSize(width, height);
        field.setColor(color);
        for (int i = 0; i < 9; i++) {
            SmallField sf = smallFields.get(i);
            sf.setPosition(x + (width / 3) * (i % 3),
                    y + (height / 3) * (2 - (i / 3)));
            sf.setAbsoluteSize(width / 3, height / 3);
            sf.update(delta);
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void fade() {
        for (SmallField sf : smallFields) {
            sf.fade();
        }
    }

    public void unFade() {
        for (SmallField sf : smallFields) {
            sf.unfade();
        }
    }
}
