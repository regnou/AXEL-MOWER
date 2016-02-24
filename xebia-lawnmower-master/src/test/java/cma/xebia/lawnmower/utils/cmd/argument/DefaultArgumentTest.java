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

package cma.xebia.lawnmower.utils.cmd.argument;

import java.util.ArrayList;
import junit.framework.TestCase;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class DefaultArgumentTest extends TestCase {
    
    public DefaultArgumentTest(String testName) {
        super(testName);
    }

    /**
     * Test of mustStop method, of class DefaultArgument.
     */
    public void testMustStop() {
        
        DefaultArgument instance = new DefaultArgument("test", true) {
            @Override
            public Argument applyOn(String argument, Object object) {
                this.stop = true;
                return this;
            }
        };
        
        assertTrue(instance.applyOn("", this).mustStop());
        
        
    }

    /**
     * Test of isCorrespondingTo method, of class DefaultArgument.
     */
    public void testIsCorrespondingTo() {
        
        DefaultArgument instance = new DefaultArgument("test", true) {
            @Override
            public Argument applyOn(String argument, Object object) {
                return this;
            }
        };
        
        assertTrue(instance.isCorrespondingTo("--test=test"));
        assertFalse(instance.isCorrespondingTo("--test"));
        
        instance = new DefaultArgument("test", false) {
            
            @Override
            public Argument applyOn(String argument, Object object) {
                return this;
            }
        };
        
        assertFalse(instance.isCorrespondingTo("--test=test"));
        assertTrue(instance.isCorrespondingTo("--test"));
        
        
    }

    /**
     * Test of getDecodedArgument method, of class DefaultArgument.
     */
    public void testGetDecodedArgument() {
        
        DefaultArgument<ArrayList> instance = new DefaultArgument<ArrayList>("test", true) {
            @Override
            public Argument applyOn(String argument, ArrayList lst) {
                
                lst.add(this.getDecodedArgument(argument));
                
                return this;
            }
        };
        
        ArrayList<String> contener = new ArrayList<>();
        instance.applyOn("--test=test", contener);
        
        assertEquals(1, contener.size());
        assertEquals("test", contener.get(0));
        
        contener = new ArrayList<>();
        instance.applyOn("--test=test=test", contener);
        
        assertEquals(1, contener.size());
        assertEquals("test=test", contener.get(0));
        
    }
    
}
