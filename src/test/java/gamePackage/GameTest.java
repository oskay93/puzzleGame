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
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Oskay
 */
public class GameTest {

    @Test
    public void iconTest() {
        assertNull(IconManager.icon(0, 0));
    }

    @Test
    public void scoreTest() {
        int testScore = RuleManager.score;
        RuleManager.Rules(2, 2);
        RuleManager.Rules(1, 2);
        assertTrue(testScore - 1 == RuleManager.score);
    }

    @Test
    public void rulesTest() {
        Puzzle.firstMove = true;
        RuleManager.Rules(1, 1);
        assertTrue(Puzzle.firstMove == false);
    }

    @Test
    public void sameRowNColTest() {
        Puzzle.firstRow = 1;
        Puzzle.firstCol = 1;
        RuleManager.MoveChecker(1, 1);
        assertTrue(RuleManager.MoveChecker(1, 1) == false);
    }

}
