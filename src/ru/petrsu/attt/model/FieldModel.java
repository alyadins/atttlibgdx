package ru.petrsu.attt.model;

import android.util.Log;

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
    public List<SmallFieldModel> sfm;
    public int activeFieldRow = -1;
    public int activeFieldColumn = -1;
    public boolean gameOver = false;
    public static int[][] finishCombination = {{0, 1, 2},
            {3, 4, 5},
            {6, 7, 8},
            {0, 3, 6},
            {1, 4, 7},
            {2, 5, 8},
            {0, 4, 8},
            {2, 4, 6}};

    public FieldModel() {
        sfm = new ArrayList<SmallFieldModel>();
        for (int i = 0; i < 9; i++) {
            sfm.add(new SmallFieldModel());
        }
    }

    public void setActiveField(int row, int column) {
        activeFieldRow = row;
        activeFieldColumn = column;
    }

    public void update(int cell, int row, int column) {
        if (activeFieldRow == -1 || activeFieldColumn == -1)
            sfm.get(row * 3 + column).update(cell, row, column);
        else {
            sfm.get(activeFieldRow * 3 + activeFieldColumn).update(cell, row, column);
            if (!sfm.get(activeFieldRow * 3 + activeFieldColumn).isFull) {
                setActiveField(row, column);
            } else {
                setActiveField(-1, -1);
            }
        }
        if (checkFinish()) {
            gameOver = true;
        }
        Log.d("TEST", "IS this game finished? " + String.valueOf(gameOver));
        Log.d("TEST", String.valueOf(sfm.get(0).isFinished) + " " + String.valueOf(sfm.get(1).isFinished) + " " + String.valueOf(sfm.get(2).isFinished));
        Log.d("TEST", String.valueOf(sfm.get(3).isFinished) + " " + String.valueOf(sfm.get(4).isFinished) + " " + String.valueOf(sfm.get(5).isFinished));
        Log.d("TEST", String.valueOf(sfm.get(6).isFinished) + " " + String.valueOf(sfm.get(7).isFinished) + " " + String.valueOf(sfm.get(8).isFinished));
        Log.d("TEST", "");
    }

    private boolean checkFinish() {
        for (int i = 0; i < 8; i++) {
            if (checkFinish(finishCombination[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean checkFinish(int[] combination) {
        if (checkFinish(sfm.get(combination[0]), sfm.get(combination[1]), sfm.get(combination[2]))) {
            return true;
        } else {
            return false;
        }
    }

    private boolean checkFinish(SmallFieldModel first, SmallFieldModel second, SmallFieldModel third) {
        if (first.isFinished && second.isFinished && third.isFinished) {
            if ((first.capturedBy == second.capturedBy) && (second.capturedBy == third.capturedBy)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
