package ru.petrsu.attt.model;

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
    public List<SmallFieldModel> sfs;
    public int activeFieldRow = -1;
    public int activeFieldColumn = -1;
    boolean gameOver = false;
    public static int[][] finishCombination = {{0, 1, 2},
                                 {3, 4, 5},
                                 {6, 7, 8},
                                 {0, 3, 6},
                                 {1, 4, 7},
                                 {2, 5, 8},
                                 {0, 4, 8},
                                 {2, 4, 6}};

    public FieldModel() {
        sfs = new ArrayList<SmallFieldModel>();
        for (int i = 0; i < 9; i++) {
            sfs.add(new SmallFieldModel());
        }
    }

    public void setActiveField(int row, int column) {
        activeFieldRow = row;
        activeFieldColumn = column;
    }

    public void update(int cell, int row, int column) {
        if (activeFieldRow == -1 || activeFieldColumn == -1)
            sfs.get(row * 3 + column).update(cell, row, column);
        else {
            sfs.get(activeFieldRow * 3 + activeFieldColumn).update(cell, row, column);
            setActiveField(row, column);
        }
//        if (checkFinish()) {
//            gameOver = true;
//        }
    }
//
//    private boolean checkFinish() {
//        for (int i = 0; i < 8; i++) {
//            if (checkFinish(finishCombination[i])) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    private boolean checkFinish(int[] combination) {
//        if (checkFinish(sfs.get(combination[0]), sfs.get(combination[1]), sfs.get(combination[2]))) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    private boolean checkFinish(SmallFieldModel first, SmallFieldModel second, SmallFieldModel third) {
//        if (first.isFinished || second.isFinished || third.isFinished) {
//            if ((first.capturedBy == second.capturedBy) && (second.capturedBy == third.capturedBy)) {
//                return true;
//            } else {
//                return false;
//            }
//        } else {
//            return false;
//        }
//    }
}
