package ru.petrsu.attt.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/25/13
 * Time: 10:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class CrosZero extends View {
    enum Type {
        CROS,
        ZERO,
        NONE
    }

    private Sprite cros = new Sprite(Assets.cros);
    private Sprite zero = new Sprite(Assets.zero);
    private Sprite sprite;
    private Color color = Color.BLACK;
    public Type type = Type.NONE;

    public CrosZero(float width, float height, Type type) {
        super(width, height);
        this.type = type;
        setType(type);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (type != Type.NONE)
            sprite.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {
        cros.setSize(width, height);
        cros.setPosition(x, y);
        cros.setColor(color);
        zero.setSize(width, height);
        zero.setPosition(x, y);
        zero.setColor(color);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setType(Type type) {
        switch (type) {
            case CROS:
                sprite = cros;
                break;
            case ZERO:
                sprite = zero;
                break;
        }

        this.type = type;
    }
}
