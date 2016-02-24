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

import cma.xebia.lawnmower.application.cmd.argument.impl.LawnMowerCtlFileArgument;
import cma.xebia.lawnmower.SpringLoaded;
import cma.xebia.lawnmower.controller.impl.LawnMowerController;
import lombok.extern.slf4j.Slf4j;



/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class LawnMowerCtlFileArgumentTest extends SpringLoaded  {
    
    public LawnMowerCtlFileArgumentTest(String testName) {
        super(testName);
    }
    
    /**
     * Test of applyOn method, of class LawnMowerCtlFileArgument.
     */
    public void testApplyOn() {
        
        LawnMowerCtlFileArgument<LawnMowerController> instance =
            new LawnMowerCtlFileArgument<>("file");
        
        String filepath = this.getClass().getResource("/setup/lawnmower.desc").getPath();
        
        instance.applyOn(String.format("--resource=%s", filepath), controller);
        assertFalse(instance.mustStop());
        assertEquals(
            String.format("file:UTF-8:%s", filepath),
            controller.getReader().getDescriptorFileInfo());
        
        
        String currentDescriptor = controller
            .getReader()
            .getDescriptorFileInfo();
        filepath = ":really:bad:path";
        instance.applyOn(String.format("--resource=%s", filepath), controller);
        assertTrue(instance.mustStop());
        assertEquals(
            currentDescriptor,
            controller.getReader().getDescriptorFileInfo());
        
    }
    
}
