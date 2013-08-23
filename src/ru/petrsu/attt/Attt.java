package ru.petrsu.attt;

import com.badlogic.gdx.Game;
import ru.petrsu.attt.screens.SplashScreen;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/20/13
 * Time: 10:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Attt extends Game {
    @Override
    public void create() {
        Assets.load();
        setScreen(new SplashScreen(this));
    }
}
