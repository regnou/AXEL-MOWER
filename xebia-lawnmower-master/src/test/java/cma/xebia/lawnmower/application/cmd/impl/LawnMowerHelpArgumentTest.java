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

import cma.xebia.lawnmower.application.cmd.argument.impl.LawnMowerHelpArgument;
import cma.xebia.lawnmower.SpringLoaded;
import cma.xebia.lawnmower.utils.file.printer.impl.ResourceToSysoutPrinter;
import cma.xebia.lawnmower.utils.helpers.StringHelper;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class LawnMowerHelpArgumentTest extends SpringLoaded {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    public LawnMowerHelpArgumentTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        System.setOut(new PrintStream(outContent));
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.setOut(System.out);
    }
    
    /**
     * Test of applyOn method, of class LawnMowerHelpArgument.
     */
    public void testApplyOn() {
        
        
        
        LawnMowerHelpArgument instance = new LawnMowerHelpArgument(
            "help",
            new ResourceToSysoutPrinter(System.out, "UTF-8"),
            "/cmd/help.txt");
        
        
        String expResult = StringHelper.getResourceAsString("/cmd/help.txt", "UTF-8");
        instance.applyOn("--help", controller);
        assertEquals(expResult, outContent.toString());
        
    }
    
}
