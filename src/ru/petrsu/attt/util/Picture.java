package ru.petrsu.attt.util;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.petrsu.attt.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/22/13
 * Time: 10:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class Picture {
    public enum Position {
    LEFT,
    RIGHT,
    BOTTOM,
    TOP,
    CENTER_VERTICAL,
    CENTER_HORIZONTAL
}
    int screenWidth = Assets.screenWidth;
    int screenHeight = Assets.screenHeight;
    private float width;
    private float height;
    private int x = 0;
    private int y = 0;

    Sprite sprite;

    public Picture(TextureRegion texture) {
        sprite = new Sprite(texture);
        calculateSizes();
    }
    private void calculateSizes() {
        width = screenWidth / 720.0f * sprite.getWidth();
        height = width * (sprite.getHeight()  / sprite.getWidth());
        sprite.setSize(width, height);
    }

    public void setPosition(int x, int y) {
        setX(x);
        setY(y);
    }

    public void setPosition(Position position) {
        switch (position) {
            case LEFT:
                setX(0);
                break;
            case RIGHT:
                setX(screenWidth - sprite.getWidth());
                break;
            case TOP:
                setY(screenHeight - sprite.getHeight());
                break;
            case BOTTOM:
                setY(0);
                break;
            case CENTER_VERTICAL:
                setX(screenWidth / 2 - sprite.getWidth() / 2);
                break;
            case CENTER_HORIZONTAL:
                setY(screenHeight / 2 - sprite.getHeight() / 2);
                break;
        }
    }

    public void setX(float x) {
        this.x = (int) x;
        sprite.setPosition(x, sprite.getY());
    }

    public void setY(float y) {
        this.y = (int) y;
        sprite.setPosition(sprite.getX(), y);
    }

    public void draw(SpriteBatch spriteBatch) {
        sprite.draw(spriteBatch);
    }
}
