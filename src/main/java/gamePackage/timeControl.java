/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gamePackage;

import static gamePackage.RuleManager.counter;
import static gamePackage.RuleManager.score;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class checks all coins every 1 second, if there is a coin without
 * neighbor then this class sets it to the non-movable coin, If there is a
 * non-movable coin with neighbor coin, then it sets it to the movable coin,.
 *
 * @author Oskay
 */
public class timeControl {

    public int time = 0;
    public Logger log = LoggerFactory.getLogger(timeControl.class);

    /**
     * Checks every square to find number 1 or 4 (1 represents movable coins and
     * 4 represents movable coins).
     */
    public void timeController() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                time++;

                /**
                 * This for loop checks every square.
                 */
                for (int i = 1; i <= 4; i++) {
                    for (int j = 1; j <= 4; j++) {

                        /**
                         * If all goal states are non-movable coins then game
                         * finished, add score to database.
                         */
                        if (Puzzle.squareArrays[1][1] == 4
                                && Puzzle.squareArrays[1][4] == 4
                                && Puzzle.squareArrays[4][1] == 4
                                && Puzzle.squareArrays[4][4] == 4) {
                            new Highscore().addScore();
                            JOptionPane.showMessageDialog(null, "Game Over! Your Score: " + score);
                            System.exit(0);

                        } /**
                         * If counter is 4 (that means all coins are non-movable
                         * and can't move) and at least one coin is not on the
                         * goal state, then game finished {@code score} will be
                         * 0 and add it to database.
                         */
                        else if (counter == 4 && Puzzle.squareArrays[1][1] == 2
                                || counter == 4 && Puzzle.squareArrays[1][4] == 2
                                || counter == 4 && Puzzle.squareArrays[4][1] == 2
                                || counter == 4 && Puzzle.squareArrays[4][4] == 2) {
                            score = 0;
                            counter = 0; // This if statement will be true twice. In this way, we can handle it.
                            new Highscore().addScore();
                            JOptionPane.showMessageDialog(null, "Game Over! Your Score: " + score);
                            System.exit(0);
                        }

                        /**
                         * If coin is non-movable and there is another coin or
                         * non-movable coin next to it, then make it movable
                         * coin and decrease counter by 1.
                         */
                        if (Puzzle.squareArrays[i][j] == 4) {
                            if (Puzzle.squareArrays[i - 1][j] == 1
                                    || Puzzle.squareArrays[i][j + 1] == 1
                                    || Puzzle.squareArrays[i + 1][j] == 1
                                    || Puzzle.squareArrays[i][j - 1] == 1
                                    || Puzzle.squareArrays[i - 1][j] == 4
                                    || Puzzle.squareArrays[i][j + 1] == 4
                                    || Puzzle.squareArrays[i + 1][j] == 4
                                    || Puzzle.squareArrays[i][j - 1] == 4) {

                                Puzzle.squareArrays[i][j] = 1;
                                counter--;
                                log.debug("Counter = " + counter);
                            }
                        } /**
                         * If the coin is movable
                         */
                        else if (Puzzle.squareArrays[i][j] == 1) {

                            /**
                             * and there is another non-movable coin next to it,
                             * then don't change the current coin.
                             */
                            if (Puzzle.squareArrays[i - 1][j] == 4
                                    || Puzzle.squareArrays[i][j + 1] == 4
                                    || Puzzle.squareArrays[i + 1][j] == 4
                                    || Puzzle.squareArrays[i][j - 1] == 4) {
                                Puzzle.squareArrays[i][j] = 1;

                            } /**
                             * and there is no movable coin next to it, then
                             * change current coin to the movable coin.
                             */
                            else if (Puzzle.squareArrays[i - 1][j] != 1
                                    && Puzzle.squareArrays[i][j + 1] != 1
                                    && Puzzle.squareArrays[i + 1][j] != 1
                                    && Puzzle.squareArrays[i][j - 1] != 1) {

                                Puzzle.squareArrays[i][j] = 4;
                                counter++;
                                log.debug("Counter = " + counter);

                            }
                        }
                    }
                }

                if (Puzzle.squareArrays[1][1] == 0) {
                    Puzzle.squareArrays[1][1] = 2;
                }
                if (Puzzle.squareArrays[1][4] == 0) {
                    Puzzle.squareArrays[1][4] = 2;
                }
                if (Puzzle.squareArrays[4][1] == 0) {
                    Puzzle.squareArrays[4][1] = 2;
                }
                if (Puzzle.squareArrays[4][4] == 0) {
                    Puzzle.squareArrays[4][4] = 2;
                }

            }
        };
        timer.schedule(task, 0, 1000);
    }
}
