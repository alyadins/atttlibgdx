package ru.petrsu.attt.util;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Button extends Picture {
    TextureRegion normal;
    TextureRegion clicked = null;
    public boolean clickable;

    public Button(TextureRegion texture) {
        super(texture);
        this.normal = texture;
    }

    public Button(TextureRegion normal, TextureRegion clicked) {
        super(normal);
        this.normal = normal;
        this.clicked = clicked;
        clickable = true;
    }

    public void setClickedTexture(TextureRegion texture) {
        this.clicked = texture;
        clickable = true;
    }

    public void setNormalTexture(TextureRegion texture) {
        this.normal = texture;
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        super.draw(spriteBatch);
    }

    public void setClicked() {
        if (clicked != null)
            sprite.setRegion(clicked);
    }

    public void setNormal() {
        sprite.setRegion(normal);
    }
}
