package ru.petrsu.attt.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/24/13
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class FieldModel {
    public List<SmallFieldModel> smallFieldModels;
    public Vector2 activeField = null;

    public FieldModel() {
        smallFieldModels = new ArrayList<SmallFieldModel>();
        for (int i = 0; i < 9; i++) {
            smallFieldModels.add(new SmallFieldModel());
        }
    }

    public void setActiveField(int row, int column) {
        if (activeField == null) {
            activeField = new Vector2(row, column);
        } else {
            activeField.set(row, column);
        }
    }

    public void update(int row, int column) {
        setActiveField(row, column);
    }
}
