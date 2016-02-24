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

package cma.xebia.lawnmower.business.entity.lawnmower;

import cma.xebia.lawnmower.SpringLoaded;
import cma.xebia.lawnmower.business.entity.constants.CompassPoint;
import cma.xebia.lawnmower.business.entity.impl.Position;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class BuilderTest extends SpringLoaded {
    
    public BuilderTest(String testName) {
        super(testName);
    }
    
    /**
     * Test of create method, of class Builder.
     */
    public void testCreate() {
        LawnMowerBuilder instance = this.controller.getBuilder();
        
        LawnMower result = instance.create();
        
        assertTrue(result.getMovements().isEmpty());
        assertEquals(new Position(), result.getPosition());
        
    }
    
    /**
     * Test of create method, of class Builder.
     */
    public void testCreate_Positionable() {
        LawnMowerBuilder instance = this.controller.getBuilder();
        
        LawnMower result = instance.create((new Position())
            .setX(45)
            .setY(78)
            .setInFrontOf(CompassPoint.W));
        
        Position expPosition = (new Position())
            .setX(45)
            .setY(78)
            .setInFrontOf(CompassPoint.W);
        
        assertTrue(result.getMovements().isEmpty());
        assertEquals(expPosition, result.getPosition());
        
        assertEquals(instance.create().moveTo(expPosition), result);
        
    }

    /**
     * Test of create method, of class Builder.
     */
    public void testCreate_3args() {
        
        LawnMowerBuilder instance = this.controller.getBuilder();
        
        LawnMower result = instance.create(54, 87, CompassPoint.S);
        
        Position expPosition = (new Position())
            .setX(54)
            .setY(87)
            .setInFrontOf(CompassPoint.S);
        
        assertTrue(result.getMovements().isEmpty());
        assertEquals(expPosition, result.getPosition());
        assertEquals(instance.create(expPosition), result);
        
        
    }
    
}
