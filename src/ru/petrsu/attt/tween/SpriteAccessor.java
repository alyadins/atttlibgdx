package ru.petrsu.attt.tween;

import aurelienribon.tweenengine.TweenAccessor;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/10/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpriteAccessor implements TweenAccessor<Sprite> {

    public static final int ALPHA = 0;
    @Override
    public int getValues(Sprite sprite, int i, float[] floats) {
        switch (i) {
            case ALPHA:
                floats[0] = sprite.getColor().a;
                return  1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Sprite sprite, int i, float[] floats) {
        switch (i) {
            case ALPHA:
                sprite.setColor(sprite.getColor().r,
                        sprite.getColor().g,
                        sprite.getColor().b,
                        floats[0]);
                break;
            default:
                assert false;
        }
    }
}
