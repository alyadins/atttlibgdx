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
public class CrossZero extends View {
    public enum Cell {
        CROSS,
        ZERO,
        NONE
    }

    private Sprite cross = new Sprite(Assets.cross);
    private Sprite zero = new Sprite(Assets.zero);
    private Sprite sprite;
    private Color crossColor = Assets.crossColor;
    private Color zeroColor = Assets.zeroColor;
    public Cell cell = Cell.NONE;

    public CrossZero(float width, float height, Cell cell) {
        super(width, height);
        this.cell = cell;
        setCell(cell);
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        if (cell != Cell.NONE)
            sprite.draw(spriteBatch);
    }

    @Override
    public void update(float delta) {
        cross.setSize(width, height);
        cross.setPosition(x, y);
        cross.setColor(crossColor);
        zero.setSize(width, height);
        zero.setPosition(x, y);
        zero.setColor(zeroColor);
    }

    public void setColor(Color crossColor, Color zeroColor) {
        this.zeroColor = zeroColor;
        this.crossColor = crossColor;
    }

    public void setSprites(Sprite cross, Sprite zero) {
        this.cross = cross;
        this.zero = zero;
    }

    public void setCell(Cell cell) {
        switch (cell) {
            case CROSS:
                sprite = cross;
                break;
            case ZERO:
                sprite = zero;
                break;
        }

        this.cell = cell;
    }

    public void fade() {
        Color newCrossColor = new Color(crossColor.r, crossColor.g, crossColor.b, Assets.FADE_VALUE);
        Color newZeroColor = new Color(zeroColor.r, zeroColor.g, zeroColor.b, Assets.FADE_VALUE);
        setColor(newCrossColor, newZeroColor);
    }

    public void unFade() {
        Color newCrossColor = new Color(crossColor.r, crossColor.g, crossColor.b, 1);
        Color newZeroColor = new Color(zeroColor.r, zeroColor.g, zeroColor.b, 1);
        setColor(newCrossColor, newZeroColor);
    }
}
