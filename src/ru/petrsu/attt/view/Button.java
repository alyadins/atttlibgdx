package ru.petrsu.attt.view;

import android.util.Log;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ru.petrsu.attt.Settings;

public class Button extends View {
    private Sprite sprite;
    private Color normalColor = Color.BLACK;
    private Color pressedColor = null;
    private Color currentColor = normalColor;
    private Sound sound = null;
    private boolean isHide = false;

    public Button(Sprite sprite, Color normalColor) {
        super(sprite.getWidth(), sprite.getHeight());
        this.sprite = sprite;
        this.normalColor = normalColor;
        this.type = Type.BUTTON;
    }

    public Button(Sprite sprite) {
        super(sprite.getWidth(), sprite.getHeight());
        this.sprite = sprite;
        this.type = Type.BUTTON;
    }

    public void setPressedColor(Color color) {
        this.pressedColor = color;
    }

    public void setNormalColor(Color color) {
        this.normalColor = color;
    }


    @Override
    public void draw(SpriteBatch spriteBatch) {
        update(1);
        if (!isHide) {
            sprite.draw(spriteBatch);
        }
    }

    @Override
    public void update(float delta) {
        sprite.setPosition(x, y);
        sprite.setSize(width, height);
        sprite.setColor(currentColor);
    }

    public void setClicked() {
        if (pressedColor != null) {
            currentColor = pressedColor;
        }
    }

    public void setNormal() {
        currentColor = normalColor;
    }

    public void setSound(Sound sound) {
        this.sound = sound;
    }

    public void playSound() {
        if (sound != null && Settings.isSoundEnabled()) {
            sound.play(1);
        }
    }

    public void setTexture(TextureRegion texture) {
        sprite = new Sprite(texture);
    }

    public void hide() {
        this.isHide = true;
    }

    public void show() {
        this.isHide = false;
    }
}
