package ru.petrsu.attt.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/24/13
 * Time: 2:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class View {
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
    private int screenWidth;
    private int screenHeight;

    public View(int screenWidth, int screenHeight) {
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

    /**
     * calculate sizes from 1280*720
     * @param oldWidth width in 1280*720
     * @param oldHeight height in 1280*720
     */
    protected void calculateSizes(float oldWidth, float oldHeight) {
        width = screenWidth / 720.0f * oldWidth;
        height = width * (oldHeight / oldWidth);
    }

    public void setPosition(int x, int y) {
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

    public void draw(SpriteBatch spriteBatch){

    }
}
