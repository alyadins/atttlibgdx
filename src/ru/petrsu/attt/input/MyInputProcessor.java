package ru.petrsu.attt.input;

import com.badlogic.gdx.InputProcessor;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/26/13
 * Time: 2:16 PM
 * To change this template use File | Settings | File Templates.
 */
public class MyInputProcessor implements InputProcessor {
    private List<View> views;
    private ButtonClickListener buttonClickListener = null;
    private FieldClickListener fieldClickListener = null;

    public MyInputProcessor() {
        views = new ArrayList<View>();
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
        for (View i : views) {
            switch (i.type) {
                case BUTTON:
                    buttonTouchDown(i, screenX, screenY);
                    break;
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (View i : views) {
            switch (i.type) {
                case FIELD:
                    fieldTouchUp(i, screenX, screenY);
                    break;
                case BUTTON:
                    buttonTouchUp(i, screenX, screenY);
                    break;
            }
        }
        buttonClickListener.touchUp(null);
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

    public void addView(View view) {
        views.add(view);
    }

    private void fieldTouchUp(View field, int screenX, int screenY) {
        int row = 0;
        int column = 0;
        if (Assets.inBounds(screenX, screenY, field.x, field.y, field.width, field.height)) {
            column = (int) ((screenX - field.x) * 3 / field.width);
            row =  (int) ((screenY - field.y) * 3 / field.height);
            if (fieldClickListener != null) {
                fieldClickListener.onClick(field, row, column);
            }
        }
    }

    private void buttonTouchUp(View button, int screenX, int screenY) {
        int clickedY = Assets.screenHeight - screenY;
        if (Assets.inBounds(screenX, clickedY, button.x, button.y, button.width, button.height)) {
            if (buttonClickListener != null) {
                buttonClickListener.touchUp(button);
            }
        }
    }

    private void buttonTouchDown(View button, int screenX, int screenY) {
        int clickedY = Assets.screenHeight - screenY;
        if (Assets.inBounds(screenX, clickedY, button.x, button.y, button.width, button.height)) {
            if (buttonClickListener !=null) {
                buttonClickListener.touchDown(button);
            }
        }
    }

    public interface ButtonClickListener {
        public void touchUp(View view);
        public void touchDown(View view);
    }

    public void setButtonClickListener(ButtonClickListener listener) {
        this.buttonClickListener = listener;
    }

    public interface FieldClickListener {
        public void onClick(View view, int row, int column);
    }

    public void setFieldClickListener(FieldClickListener listener) {
        this.fieldClickListener = listener;
    }
}
