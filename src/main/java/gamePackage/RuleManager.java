/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamePackage;

/*
 * #%L
 * UysalProject
 * %%
 * Copyright (C) 2016 Puzzle Inc.
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */
import javax.swing.JOptionPane;
import static java.lang.Math.abs;

/**
 * This class holds whole rules of the game.
 *
 * @author Oskay
 */
public class RuleManager {

    /**
     * Stores score of the game.
     */
    static int score = 100;
    /**
     * Used to find how many 4s in the {@code squareArrays} , 4 represents
     * non-movable coins.
     */
    static int counter;

    /**
     * This method {@link Rules(int row, int col)} takes 2 parameters It sets
     * the rules of the game {@code int row} takes row of the square
     * {@code int col} takes column of the square.
     */
    public static void Rules(int row, int col) {

        if (Puzzle.squareArrays[row][col] == 0 && Puzzle.firstMove || Puzzle.squareArrays[row][col] == 2 && Puzzle.firstMove) {
            if (MoveChecker(row, col)) {
                Puzzle.squareArrays[row][col] = 1;
                Puzzle.squareArrays[Puzzle.firstRow][Puzzle.firstCol] = 0;
                score--;

                Puzzle.firstMove = false;

            }
        } else if (Puzzle.squareArrays[row][col] == 1 && !Puzzle.firstMove) {
            Puzzle.firstRow = row;
            Puzzle.firstCol = col;

            Puzzle.firstMove = true;

        }
    }

    /**
     * This method takes parameters {@code row} and {@code col} from
     * {@link Rules(int rol, int col)} It doesn't allow the coin to move cross.
     */
    public static boolean MoveChecker(int row, int col) {
        if (row == Puzzle.firstRow && col == Puzzle.firstCol) {
            Puzzle.firstMove = false;
            return false;
        }

        if (row == Puzzle.firstRow) {

            if (abs(col - Puzzle.firstCol) == 2) {
                if (col > Puzzle.firstCol && Puzzle.squareArrays[row][col - 1] != 1 && Puzzle.squareArrays[row][col - 1] != 4) {
                    return true;
                }
                if (col < Puzzle.firstCol && Puzzle.squareArrays[row][col + 1] != 1 && Puzzle.squareArrays[row][col + 1] != 4) {
                    return true;
                }
            }
            if (abs(col - Puzzle.firstCol) == 3) {
                
                if (Puzzle.squareArrays[row][2] != 1 && Puzzle.squareArrays[row][3] != 1
                        && Puzzle.squareArrays[row][2] != 4 && Puzzle.squareArrays[row][3] != 4) {
                    return true;
                }
            }
            if (abs(col - Puzzle.firstCol) == 1) {
             
                return true;
            }
        }

        if (col == Puzzle.firstCol) {

            if (abs(row - Puzzle.firstRow) == 2) {
                
                if (row > Puzzle.firstRow && Puzzle.squareArrays[row - 1][col] != 1
                        && Puzzle.squareArrays[row - 1][col] != 4) {
                    return true;
                }
                if (row < Puzzle.firstRow && Puzzle.squareArrays[row + 1][col] != 1
                        && Puzzle.squareArrays[row + 1][col] != 4) {
                    return true;
                }
            }
            if (abs(row - Puzzle.firstRow) == 3) {
               
                if (Puzzle.squareArrays[2][col] != 1 && Puzzle.squareArrays[3][col] != 1
                        && Puzzle.squareArrays[3][col] != 1) {
                    return true;
                }
            }
            if (abs(row - Puzzle.firstRow) == 1) {
              
                return true;
            }
        }

        Puzzle.firstMove = false;
        return false;
    }

    /**
     * When we try to move the coin, {@link CoinCheck()} method checks if there
     * is another coin next to it, Returns boolean value.
     *
     * @deprecated
     */
    @Deprecated
    public static boolean CoinCheck() {
        Puzzle.squareArrays[Puzzle.firstRow][Puzzle.firstCol] = 0;

        if (Puzzle.squareArrays[Puzzle.firstRow - 1][Puzzle.firstCol] == 1
                || Puzzle.squareArrays[Puzzle.firstRow][Puzzle.firstCol + 1] == 1
                || Puzzle.squareArrays[Puzzle.firstRow + 1][Puzzle.firstCol] == 1
                || Puzzle.squareArrays[Puzzle.firstRow][Puzzle.firstCol - 1] == 1
                || Puzzle.squareArrays[Puzzle.firstRow - 1][Puzzle.firstCol] == 4
                || Puzzle.squareArrays[Puzzle.firstRow][Puzzle.firstCol + 1] == 4
                || Puzzle.squareArrays[Puzzle.firstRow + 1][Puzzle.firstCol] == 4
                || Puzzle.squareArrays[Puzzle.firstRow][Puzzle.firstCol - 1] == 4) {

            Puzzle.squareArrays[Puzzle.firstRow][Puzzle.firstCol] = 1;
            return true;

        }

        if (Puzzle.squareArrays[Puzzle.firstRow - 1][Puzzle.firstCol] != 1
                && Puzzle.squareArrays[Puzzle.firstRow][Puzzle.firstCol + 1] != 1
                && Puzzle.squareArrays[Puzzle.firstRow + 1][Puzzle.firstCol] != 1
                && Puzzle.squareArrays[Puzzle.firstRow][Puzzle.firstCol - 1] != 1) {
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    if (Puzzle.squareArrays[i][j] == 1) {
                        counter++;
                        System.out.println(counter);
                    }

                }
            }
            new Highscore().addScore();
            JOptionPane.showMessageDialog(null, "Game Over! Your Score: " + score);
            System.exit(0);
        }
        Puzzle.squareArrays[Puzzle.firstRow][Puzzle.firstCol] = 1;
        return false;
    }

    /**
     * This method checks is game finished or not If all goal squares has coin
     * then finished, Otherwise not.
     *
     * @deprecated
     */
    @Deprecated
    public static void isFinish() {

        if (Puzzle.squareArrays[1][1] == 1 && Puzzle.squareArrays[4][1] == 1 && Puzzle.squareArrays[1][4] == 1 && Puzzle.squareArrays[4][4] == 1
                && Puzzle.squareArrays[1][1] == 4 && Puzzle.squareArrays[4][1] == 4 && Puzzle.squareArrays[1][4] == 4 && Puzzle.squareArrays[4][4] == 4) {
            new Highscore().addScore();
            JOptionPane.showMessageDialog(null, "Game Over! Your Score: " + score);
            System.exit(0);
        } else if (score == 0) {
            new Highscore().addScore();
            JOptionPane.showMessageDialog(null, "Game Over! Your Score: " + score);
            System.exit(0);
        }

    }
}
