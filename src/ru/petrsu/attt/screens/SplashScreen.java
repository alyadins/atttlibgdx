package ru.petrsu.attt.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.tween.SpriteAccessor;
import ru.petrsu.attt.view.Picture;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/10/13
 * Time: 2:26 AM
 * To change this template use File | Settings | File Templates.
 */
public class SplashScreen implements Screen, TweenCallback {
    private final static float LOGO_WIDTH = 0.8f;
    private SpriteBatch spriteBatch;
    private Picture logo;
    private Sprite background;
    private TweenManager tweenManager;
    Game game;

    public SplashScreen(Game game) {
        this.game = game;
        spriteBatch = new SpriteBatch();
    }


    @Override
    public void render(float delta) {
        Gdx.gl20.glClearColor(255.0f, 255.0f, 255.0f, 1);
        Gdx.gl20.glClear(GL10.GL_COLOR_BUFFER_BIT);
        tweenManager.update(delta);

        spriteBatch.begin();
        background.draw(spriteBatch);
        logo.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
        showLogo();
        setTween();
        showBackground();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
    }

    @Override
    public void onEvent(int i, BaseTween<?> baseTween) {
        game.setScreen(new MainMenuScreen(game));
    }

    private void showLogo() {
        logo = new Picture(Assets.logo);
        logo.setPosition(Picture.Position.CENTER_HORIZONTAL);
        logo.setPosition(Picture.Position.CENTER_VERTICAL);
    }

    private void showBackground() {
        background = new Sprite(Assets.background);
        background.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void setTween() {
        tweenManager = new TweenManager();
        Tween.registerAccessor(Picture.class, new SpriteAccessor());
        Tween.set(logo, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.to(logo, SpriteAccessor.ALPHA, 1.5f).target(1).repeatYoyo(1, 0).setCallback(this).start(tweenManager);
    }
}
