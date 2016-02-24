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

package cma.xebia.lawnmower.business.entity.impl;

import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.entity.constants.CompassPoint;
import com.rits.cloning.Cloner;
import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class PositionTest extends TestCase {
    
    private Cloner cloner;
    
    public PositionTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        this.cloner = new Cloner();
    }
    
    
    
    /**
     * Test of getPosition method, of class SynchronizedPosition.
     */
    public void testGetPosition() {
        
        Position expResult = (new Position())
            .setX(0)
            .setY(0)
            .setInFrontOf(CompassPoint.N);
        
        
        Position instance = (new Position())
            .setX(0)
            .setY(0)
            .setInFrontOf(CompassPoint.N);
        
        assertEquals(expResult, instance);
        assertEquals(expResult, instance.getPosition());
        
    }
    
    /**
     * Test of isInSameLocation method, of class Position.
     */
    public void testIsInSameLocation_Positionable() {
        
        Positionable positionable = (new Position())
            .setX(0)
            .setY(0);
        
        Position instance = (new Position())
            .setX(0)
            .setY(0);
        
        for (CompassPoint cpInstance : CompassPoint.values()) {
            instance.setInFrontOf(cpInstance);
            
            assertTrue(instance.isInSameLocation(positionable));
        }
        
    }

    /**
     * Test of isInSameLocation method, of class Position.
     */
    public void testIsInSameLocation_Position() {
        Position position = (new Position())
            .setX(0)
            .setY(0);
        
        Position instance = (new Position())
            .setX(0)
            .setY(0);
        
        for (CompassPoint cpInstance : CompassPoint.values()) {
            instance.setInFrontOf(cpInstance);
            
            for (CompassPoint cpPosition : CompassPoint.values()) {
                position.setInFrontOf(cpPosition);
                assertTrue(instance.isInSameLocation(position));
                
            }
        }
        
    }
    
    /**
     * Test of equals method, of class Position.
     */
    public void testEquals() {
        
        Position instance = (new Position())
            .setX(0)
            .setY(0)
            .setInFrontOf(CompassPoint.N)
        ;
        
        Position other = (new Position())
            .setX(0)
            .setY(0)
            .setInFrontOf(CompassPoint.N)
        ;
        
        assertTrue(instance.equals(other));
        
        Position another = this.cloner.deepClone(other)
            .setInFrontOf(CompassPoint.E);
        assertFalse(instance.equals(another));
        
        another = this.cloner.deepClone(other).setX(1);
        assertFalse(instance.equals(another));
        
        another = this.cloner.deepClone(other).setY(2);
        assertFalse(instance.equals(another));
        
        
    }

    /**
     * Test of compareTo method, of class Position.
     */
    public void testCompareTo() {
        
        Position instance = (new Position())
            .setX(5)
            .setY(5)
            .setInFrontOf(CompassPoint.N)
        ;
        
        List<Position> beforeList = Arrays.asList(
            (new Position())
                .setX(2)
                .setY(5)
                .setInFrontOf(CompassPoint.E),
            (new Position())
                .setX(5)
                .setY(2)
                .setInFrontOf(CompassPoint.N),
            (new Position())
                .setX(5)
                .setY(5)
                .setInFrontOf(CompassPoint.E)
        );
        
        
        
        for (Position before : beforeList) {
            assertEquals(1, instance.compareTo(before));
            assertEquals(-1, before.compareTo(instance));
        }
        
        Position same = (new Position())
            .setX(5)
            .setY(5)
            .setInFrontOf(CompassPoint.N)
        ;
        
        assertEquals(0, same.compareTo(instance));
        assertEquals(0, instance.compareTo(same));
        assertTrue(same.equals(instance));
        
        List<Position> afterList = Arrays.asList(
            (new Position())
                .setX(5)
                .setY(5)
                .setInFrontOf(CompassPoint.S),
            (new Position())
                .setX(5)
                .setY(5)
                .setInFrontOf(CompassPoint.W),
            (new Position())
                .setX(5)
                .setY(6)
                .setInFrontOf(CompassPoint.N),
            (new Position())
                .setX(6)
                .setY(6)
                .setInFrontOf(CompassPoint.N)
        );
        
        for (Position after : afterList) {
            assertEquals(-1, instance.compareTo(after));
            assertEquals(1, after.compareTo(instance));
            
        }
        
        
        
    }

}
