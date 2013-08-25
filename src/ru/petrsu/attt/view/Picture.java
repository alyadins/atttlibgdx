package ru.petrsu.attt.view;

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
public class Picture extends View{
    public Sprite sprite;

    public Picture(TextureRegion texture) {
        super(Assets.screenWidth, Assets.screenHeight);
        sprite = new Sprite(texture);
        setSize(sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        updateSprite();
        sprite.draw(spriteBatch);
    }

    private void updateSprite() {
        sprite.setPosition(x, y);
        sprite.setSize(width, height);
    }
}
