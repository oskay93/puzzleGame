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
import javax.swing.ImageIcon;

/**
 * This class used to set images (coin, goal and empty images).
 *
 * @author Oskay
 */
public class IconManager {

    /**
     * Use {@link icon(int row, int col)} method to set images for coin, goal
     * and empty squares {@code int row} takes row of the square {@code int col}
     * takes column of the square.
     */
    public static ImageIcon icon(int row, int col) {

        ImageIcon coin = new ImageIcon(ClassLoader.getSystemResource("coin.png"));
        ImageIcon goal = new ImageIcon(ClassLoader.getSystemResource("goal.gif"));
        ImageIcon empty = new ImageIcon(ClassLoader.getSystemResource("empty.jpeg"));

        switch (Puzzle.squareArrays[row][col]) {
            case 0:
                return empty;
            case 1:
                return coin;
            case 2:
                return goal;
            case 4:
                return coin;
        }

        return null;
    }

}
