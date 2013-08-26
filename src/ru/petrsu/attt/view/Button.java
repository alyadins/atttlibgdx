package ru.petrsu.attt.view;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.petrsu.attt.Settings;

public class Button extends Picture {
    private TextureRegion normal;
    private TextureRegion clicked = null;
    private Sound sound = null;
    public Button(TextureRegion texture) {
        super(texture);
        this.normal = texture;
        this.type = Type.BUTTON;
    }

    public Button(TextureRegion normal, TextureRegion clicked) {
        super(normal);
        this.normal = normal;
        this.clicked = clicked;
    }

    public void setClickedTexture(TextureRegion texture) {
        this.clicked = texture;
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

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void playSound() {
        if (sound != null && Settings.isSoundEnabled()) {
            sound.play(1);
        }
    }
}
