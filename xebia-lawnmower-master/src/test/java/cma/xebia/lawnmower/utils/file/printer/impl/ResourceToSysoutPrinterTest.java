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

package cma.xebia.lawnmower.utils.file.printer.impl;

import cma.xebia.lawnmower.utils.helpers.StringHelper;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import junit.framework.TestCase;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class ResourceToSysoutPrinterTest extends TestCase {
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    
    public ResourceToSysoutPrinterTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        System.setOut(new PrintStream(outContent));
    }
    
    @Override
    protected void tearDown() throws Exception {
        System.setOut(System.out);
    }
    
    /**
     * Test of print method, of class SysoutPrinter.
     */
    public void testPrintHelp() {
        
        ResourceToSysoutPrinter printer =
            new ResourceToSysoutPrinter(System.out, "UTF-8");
        
        String expResult = StringHelper.getResourceAsString("/cmd/help.txt", "UTF-8");
        printer.print("/cmd/help.txt");
        
        
        
        
        assertEquals(expResult, outContent.toString());
        
        System.out.println(expResult);
        System.out.println(outContent.toString());
        
        
    }
    
    /**
     * Test of print method, of class SysoutPrinter.
     */
    public void testPrintHeader() {
        
        ResourceToSysoutPrinter printer =
            new ResourceToSysoutPrinter(System.out, "UTF-8");
        
        String expResult = StringHelper.getResourceAsString("/cmd/header.txt", "UTF-8");
        printer.print("/cmd/header.txt");
        assertEquals(expResult, outContent.toString());
        
    }
    
    /**
     * Test of print method, of class SysoutPrinter.
     */
    public void testPrintFooter() {
        
        ResourceToSysoutPrinter printer =
            new ResourceToSysoutPrinter(System.out, "UTF-8");
        
        String expResult = StringHelper.getResourceAsString("/cmd/footer.txt", "UTF-8");
        printer.print("/cmd/footer.txt");
        assertEquals(expResult, outContent.toString());
        
    }
    
}
