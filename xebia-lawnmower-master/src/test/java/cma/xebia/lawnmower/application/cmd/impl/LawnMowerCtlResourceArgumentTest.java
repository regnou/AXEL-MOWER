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

package cma.xebia.lawnmower.application.cmd.impl;

import cma.xebia.lawnmower.application.cmd.argument.impl.LawnMowerCtlResourceArgument;
import cma.xebia.lawnmower.SpringLoaded;
import cma.xebia.lawnmower.controller.impl.LawnMowerController;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class LawnMowerCtlResourceArgumentTest extends SpringLoaded {
    
    public LawnMowerCtlResourceArgumentTest(String testName) {
        super(testName);
    }
    
    /**
     * Test of applyOn method, of class LawnMowerCtlResourceArgument.
     */
    public void testApplyOn() {
        
        
        LawnMowerCtlResourceArgument<LawnMowerController> instance =
            new LawnMowerCtlResourceArgument<>("resource");
        
        instance.applyOn("--resource=/lawnmower.desc", controller);
        assertFalse(instance.mustStop());
        assertEquals(
            "resource:UTF-8:/lawnmower.desc",
            controller.getReader().getDescriptorFileInfo());
        
        instance.applyOn("--resource=/lawnmower+obstacles.desc", controller);
        assertFalse(instance.mustStop());
        assertEquals(
            "resource:UTF-8:/lawnmower+obstacles.desc",
            controller.getReader().getDescriptorFileInfo());
        
        instance.applyOn("--resource=/lawnmower+obstacles.desc", controller);
        assertFalse(instance.mustStop());
        assertEquals(
            "resource:UTF-8:/lawnmower+obstacles.desc",
            controller.getReader().getDescriptorFileInfo());
        
        String currentDescriptor = controller
            .getReader()
            .getDescriptorFileInfo();
        instance.applyOn("--resource=/foo/bar.desc", controller);
        assertTrue(instance.mustStop());
        assertEquals(
            currentDescriptor,
            controller.getReader().getDescriptorFileInfo());
        
    }
    
}
