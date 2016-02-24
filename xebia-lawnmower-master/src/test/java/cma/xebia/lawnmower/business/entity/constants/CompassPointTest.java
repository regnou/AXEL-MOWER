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

package cma.xebia.lawnmower.business.entity.constants;

import cma.xebia.lawnmower.business.entity.constants.CompassPoint;
import junit.framework.TestCase;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class CompassPointTest extends TestCase {
    
    public CompassPointTest(String testName) {
        super(testName);
    }
    
    public void testLength () {
        assertEquals(CompassPoint.values().length, 4);
        
    }
    
    public void testGetByNameString () {
        
        assertEquals(CompassPoint.N, CompassPoint.valueOf("N"));
        assertEquals(CompassPoint.E, CompassPoint.valueOf("E"));
        assertEquals(CompassPoint.S, CompassPoint.valueOf("S"));
        assertEquals(CompassPoint.W, CompassPoint.valueOf("W"));
        
    }
    
    public void testGetLabel () {
        
        assertEquals("North", CompassPoint.valueOf("N").getLabel());
        assertEquals("Est", CompassPoint.valueOf("E").getLabel());
        assertEquals("South", CompassPoint.valueOf("S").getLabel());
        assertEquals("West", CompassPoint.valueOf("W").getLabel());
        
    }
}
