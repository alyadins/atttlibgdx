package ru.petrsu.attt.model;


import android.util.Log;
import ru.petrsu.attt.view.CrossZero;
import ru.petrsu.attt.view.CrossZero.Cell;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/25/13
 * Time: 8:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class SmallFieldModel {
    public static final int CROSS = 0;
    public static final int ZER0 = 1;
    public static final int NONE = 2;

    public boolean isFinished = false;
    public boolean isFull = false;
    public int capturedBy;
    public List<Integer> cells;

    public SmallFieldModel() {
        cells = new ArrayList<Integer>();
        for (int i = 0; i < 9; i++) {
            cells.add(NONE);
        }
    }

    public void update(int cell, int row, int column) {
        cells.set(row * 3 + column, cell);
        isFinished = checkFinish();
        Log.d("TEST", "Is this field finished? " + String.valueOf(isFinished));
        isFull = checkFull();
    }

    private boolean checkFinish() {
        for (int i = 0; i < 8; i++) {
            if (checkFinish(FieldModel.finishCombination[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkFinish(int[] combination) {
        if (checkFinish(cells.get(combination[0]), cells.get(combination[1]), cells.get(combination[2]))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkFinish(int first, int second, int third) {
        if ((first == second) && (second == third) && (first != NONE) && (second != NONE) && (third != NONE)) {
            capturedBy = first;
            return  true;
        } else {
            return false;
        }
    }

    private boolean checkFull() {
        for (int i = 0; i < 9; i++) {
            if (cells.get(i) == NONE) {
                return false;
            }
        }
        return true;
    }
}
