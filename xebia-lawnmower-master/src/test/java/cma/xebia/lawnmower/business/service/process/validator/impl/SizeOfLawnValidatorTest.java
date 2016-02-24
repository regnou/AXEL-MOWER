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

package cma.xebia.lawnmower.business.service.process.validator.impl;

import cma.xebia.lawnmower.business.service.process.impl.validator.SizeOfLawnValidator;
import cma.xebia.lawnmower.business.entity.lawn.Lawn;
import cma.xebia.lawnmower.utils.validator.impl.SimpleRangeValidator;
import junit.framework.TestCase;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class SizeOfLawnValidatorTest extends TestCase {
    
    private SizeOfLawnValidator validator = null;
    
    public SizeOfLawnValidatorTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        validator = new SizeOfLawnValidator(
            (new SimpleRangeValidator())
                .setIncluding(true)
                .setMinHeight(1)
                .setMaxHeight(9)
                .setMinWidth(1)
                .setMaxWidth(9));
    }
    
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        validator = null;
    }
    
    
    
    /**
     * Test of isValid method, of class SizeOfLawnValidator.
     */
    public void testIsValid() {
        
        
        
        assertFalse(validator.isValid(new Lawn(1, 0)));
        assertFalse(validator.isValid(new Lawn(0, 1)));
        assertFalse(validator.isValid(new Lawn(0, 0)));
        
        assertTrue(validator.isValid(new Lawn(1, 1)));
        assertTrue(validator.isValid(new Lawn(9, 9)));
        
        assertFalse(validator.isValid(new Lawn(10, 9)));
        assertFalse(validator.isValid(new Lawn(9, 10)));
        assertFalse(validator.isValid(new Lawn(10, 10)));
        
        
    }
    
    /**
     * Test of isValid method, of class SizeOfLawnValidator with null Ilawn.
     */
    public void testIsValidWithNullLawn() {
        assertFalse(validator.isValid(null));
    }
    
}
