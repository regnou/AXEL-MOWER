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

import cma.xebia.lawnmower.business.service.process.impl.validator.PositionValidator;
import cma.xebia.lawnmower.application.Constant;
import cma.xebia.lawnmower.application.Main;
import cma.xebia.lawnmower.business.entity.constants.CompassPoint;
import cma.xebia.lawnmower.business.entity.lawn.Lawn;
import cma.xebia.lawnmower.business.entity.lawnmower.Builder;

import cma.xebia.lawnmower.business.entity.lawnmower.LawnMowerBuilder;
import cma.xebia.lawnmower.business.entity.lawnmower.commands.impl.DefaultCommands;
import cma.xebia.lawnmower.controller.impl.LawnMowerController;
import cma.xebia.lawnmower.utils.validator.impl.SimpleRangeValidator;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class PositionValidatorTest extends TestCase {
    
    protected LawnMowerBuilder builder = null;
    
    protected PositionValidator validator = null;
    
    public PositionValidatorTest(String testName) {
        super(testName);
    }

    @Override
    protected void setUp() throws Exception {
        
        
        ApplicationContext context = new ClassPathXmlApplicationContext(
                "/configuration/spring.xml",
                Main.class);
        
        
        builder = ((LawnMowerBuilder) context.getBean("lawn-mower.builder.standard"));
        
        ((ConfigurableApplicationContext) context).close();
        
        validator = new PositionValidator(
            (new SimpleRangeValidator())
                .setIncluding(true)
                .setMinHeight(0)
                .setMaxHeight(9)
                .setMinWidth(0)
                .setMaxWidth(9))
        ;
    }

    @Override
    protected void tearDown() throws Exception {
        builder = null;
        validator = null;
    }
    
    
    
    
    /**
     * Test of isValid method, of class SimpleRangeValidator.
     */
    public void testIsValid() {
        
        assertTrue(validator.isValid(
            builder.create(2, 2, CompassPoint.N),
            new Lawn(9, 9)));
        
        assertFalse(validator.isValid(
            builder.create(9, 9, CompassPoint.N),
            new Lawn(5, 5)));
        
        assertFalse(validator.isValid(
            builder.create(9, 2, CompassPoint.N),
            new Lawn(5, 5)));
        
        assertFalse(validator.isValid(
            builder.create(2, 9, CompassPoint.N),
            new Lawn(5, 5)));
        
    }
    
    /**
     * Test of isValid method, of class SizeOfLawnValidator with null Ilawn.
     */
    public void testIsValidWithNullLawn() {
        
        assertFalse(validator.isValid(
            builder.create(0, 0, CompassPoint.N),
            null));
        
    }
    
}
