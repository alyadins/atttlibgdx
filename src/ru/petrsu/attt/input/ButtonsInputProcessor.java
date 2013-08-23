package ru.petrsu.attt.input;

import android.util.Log;
import com.badlogic.gdx.InputProcessor;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.Settings;
import ru.petrsu.attt.util.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/23/13
 * Time: 8:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class ButtonsInputProcessor implements InputProcessor {
    List<Button> buttons = new ArrayList<Button>();
    int screenHeight = Assets.screenHeight;
    private OnClickListener onClickListener = null;

    public void addButton(Button button) {
        buttons.add(button);
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int clickedY = screenHeight - screenY;
        for (Button i : buttons) {
            if (Assets.inBounds(screenX, clickedY, i.x, i.y, (int) i.width, (int) i.height)) {
                i.setClicked();
            }
        }

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        int clickedY = screenHeight - screenY;
        for (Button i : buttons) {
            if (Assets.inBounds(screenX, clickedY, i.x, i.y, (int) i.width, (int) i.height)) {
                onClickListener.onClick(i);
            }
            i.setNormal();
        }
        if (Settings.isSoundEnabled()) {
            Assets.click.play(1);
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }


    public interface OnClickListener {
        public void onClick(Button button);
    }

}
