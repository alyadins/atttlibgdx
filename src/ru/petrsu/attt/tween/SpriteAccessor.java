package ru.petrsu.attt.tween;

import aurelienribon.tweenengine.TweenAccessor;
import ru.petrsu.attt.view.Picture;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/10/13
 * Time: 7:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class SpriteAccessor implements TweenAccessor<Picture> {

    public static final int ALPHA = 0;
    @Override
    public int getValues(Picture picture, int i, float[] floats) {
        switch (i) {
            case ALPHA:
                floats[0] = picture.sprite.getColor().a;
                return  1;
            default:
                assert false;
                return -1;
        }
    }

    @Override
    public void setValues(Picture picture, int i, float[] floats) {
        switch (i) {
            case ALPHA:
                picture.sprite.setColor(picture.sprite.getColor().r,
                        picture.sprite.getColor().g,
                        picture.sprite.getColor().b,
                        floats[0]);
                break;
            default:
                assert false;
        }
    }
}
