/*
 * Copyright (C) 2014 Christophe Martel <mail.christophe.martel@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package cma.xebia.lawnmower.controller.impl;

import cma.xebia.lawnmower.ShearerStub;
import cma.xebia.lawnmower.business.entity.Movable;
import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.service.Shearer;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import lombok.extern.slf4j.Slf4j;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class ShearerWithBrokenRunnerTest extends ShearerStub {
    
    @Override
    protected void configureEnv() {
        this.useBrokenController();
    }
    
    
    public ShearerWithBrokenRunnerTest(String testName) {
        super(testName);
    }

    @Override
    protected void checkTestRunWithoutObstacle(Shearer shearer, List<Movable> lawnMowers) {
        this.checkTestRunWithObstacle(shearer, lawnMowers);
    }
    
    
    
    @Override
    protected void checkTestRunWithObstacle (
            Shearer shearer,
            List<Movable> lawnMowers) {
        
        List<Positionable> expObstacles = this.getObstacles();
        
        
        assertEquals(false, shearer.isFail());
        
        assertEquals(expObstacles.size(), shearer.getObstacles().size());
        for (int i = 0; i < expObstacles.size(); i++) {
            assertFalse(expObstacles.get(i) == shearer.getObstacles().get(i));
            assertTrue(expObstacles.get(i)
                .getPosition()
                .isInSameLocation(shearer.getObstacles().get(i)));
            
        }
        
        List<Movable> expLawnMowers = this.getMovables();
        assertEquals(expLawnMowers.size(), shearer.getMovables().size());
        for (int i = 0; i < expLawnMowers.size(); i++) {
            assertFalse(expLawnMowers.get(i) == shearer.getMovables().get(i));
            
            
            assertEquals(expLawnMowers.get(i).getPosition(), shearer.getMovables().get(i).getPosition());
            
        }
        
    }
}
