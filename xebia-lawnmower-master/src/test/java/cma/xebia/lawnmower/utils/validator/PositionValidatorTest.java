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

package cma.xebia.lawnmower.utils.validator;

import cma.xebia.lawnmower.utils.validator.impl.SimpleRangeValidator;
import junit.framework.TestCase;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class PositionValidatorTest extends TestCase {
    
    public PositionValidatorTest(String testName) {
        super(testName);
    }
    
    /**
     * Test of isValid method, of class SimpleRangeValidator.
     */
    public void testIsValid() {
        System.out.println("isValid");
        int x = 0;
        int y = 0;
        SimpleRangeValidator validator = new SimpleRangeValidator();
        validator
            .setIncluding(true)
            .setMinHeight(1)
            .setMaxHeight(9)
            .setMinWidth(1)
            .setMaxWidth(9)
        ;
        
        assertFalse(validator.isValid(0, 0));
        assertFalse(validator.isValid(-1, 1));
        assertFalse(validator.isValid(1, -1));
        
        assertTrue(validator.isValid(1, 1));
        assertTrue(validator.isValid(5, 5));
        assertTrue(validator.isValid(9, 9));
        
        assertFalse(validator.isValid(9, 10));
        assertFalse(validator.isValid(10, 10));
        assertFalse(validator.isValid(10, 9));
        
    }
    
    
    /**
     * Test of isValid method, of class SimpleRangeValidator.
     */
    public void testIsValidIncluding () {
        System.out.println("isValid");
        int x = 0;
        int y = 0;
        SimpleRangeValidator validator = new SimpleRangeValidator();
        validator
            .setIncluding(false)
            .setMinHeight(0)
            .setMaxHeight(10)
            .setMinWidth(0)
            .setMaxWidth(10)
        ;
        
        assertFalse(validator.isValid(0, 0));
        assertFalse(validator.isValid(-1, 1));
        assertFalse(validator.isValid(1, -1));
        
        assertTrue(validator.isValid(1, 1));
        assertTrue(validator.isValid(5, 5));
        assertTrue(validator.isValid(9, 9));
        
        assertFalse(validator.isValid(9, 10));
        assertFalse(validator.isValid(10, 10));
        assertFalse(validator.isValid(10, 9));
        
    }
}
