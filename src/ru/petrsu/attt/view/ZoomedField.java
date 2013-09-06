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
 * Date: 8/26/13
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZoomedField extends View {
    SmallFieldModel model;
    Sprite field;
    List<CrossZero> crossZeros;
    private boolean isRenderer = true;
    private Color color = Color.BLACK;

    public ZoomedField(SmallFieldModel model, Sprite field) {
        super(field.getWidth(), field.getHeight());
        this.model = model;
        this.type = Type.FIELD;
        crossZeros = new ArrayList<CrossZero>();
        this.field = field;
        initCrosZeros();
    }

    private void initCrosZeros() {
        for (int i = 0; i < 9; i++) {
            crossZeros.add(new CrossZero(width / 3, height / 3, CrossZero.Cell.NONE));
            crossZeros.get(i).setSprites(new Sprite(Assets.bigCross),
                    new Sprite(Assets.bigZero));
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (isRenderer) {
                field.draw(spriteBatch);
                for (int i = 0; i < 9; i++) {
                    crossZeros.get(i).draw(spriteBatch);
                }
            }
    }

    @Override
    public void update(float delta) {
        field.setPosition(x, y);
        field.setSize(width, height);
        field.setColor(color);
        for (int i = 0; i < 9; i++) {
            CrossZero cz = crossZeros.get(i);
            cz.setPosition(x + (width / 3) * (i % 3),
                    y + (height / 3) * (2 - (i / 3)));
            cz.setAbsoluteSize(width / 3, height / 3);
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
            cz.update(delta);
        }
    }

    public void setModel(SmallFieldModel model) {
        this.model = model;
    }

    public boolean isRenderer() {
        return isRenderer;
    }

    public void setRenderer(boolean renderer) {
        isRenderer = renderer;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
