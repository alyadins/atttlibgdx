package ru.petrsu.attt.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import ru.petrsu.attt.Assets;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/24/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class View {
    public enum Position {
        LEFT,
        RIGHT,
        BOTTOM,
        TOP,
        CENTER_VERTICAL,
        CENTER_HORIZONTAL
    }

    public float width;
    public float height;
    public float x = 0;
    public float y = 0;
    private int screenWidth = Gdx.graphics.getWidth();
    private int screenHeight = Gdx.graphics.getHeight();

    public View(float width, float height) {
        setSize(width, height);
    }

    /**
     * calculate sizes from 1280*720
     * @param width width in 1280*720
     * @param height height in 1280*720
     */
    protected void setSize(float width, float height) {
        this.width = screenWidth / 720.0f * width;
        this.height = this.width * (height / width);
    }

    protected void setAbsoluteSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public static void calculateSpriteSizes(Sprite sprite) {
        float width = Gdx.graphics.getWidth() / 720.0f * sprite.getWidth();
        float height = width * (sprite.getHeight() / sprite.getWidth());
        sprite.setSize(width, height);
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }
    public void setPosition(Position position) {
        switch (position) {
            case LEFT:
                x = 0;
                break;
            case RIGHT:
                x = screenWidth - width;
                break;
            case TOP:
                y = screenHeight - height;
                break;
            case BOTTOM:
                y = 0;
                break;
            case CENTER_VERTICAL:
                x = screenWidth / 2 - width / 2;
                break;
            case CENTER_HORIZONTAL:
                y = screenHeight / 2 - height / 2;
                break;
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y =  y;
    }

    public abstract void draw(SpriteBatch spriteBatch);
    public abstract void update(float delta);
}
