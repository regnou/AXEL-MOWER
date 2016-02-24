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

package cma.xebia.lawnmower.utils.helpers;

import cma.xebia.lawnmower.application.Main;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class StringHelperTest extends TestCase {
    
    public StringHelperTest(String testName) {
        super(testName);
    }
    
    
    public void testIdentity () {
        assertEquals(
            Arrays.asList(new String [] {}),
            StringHelper.getChars(""));
        
    }
    
    /**
     * Test of getChars method, of class StringHelper.
     */
    public void testGetChars() {
        assertEquals(
            Arrays.asList(new String [] {"5", "5"}),
            StringHelper.getChars("55"));
        
        assertEquals(
            Arrays.asList(new String [] {"1", "2", "N"}),
            StringHelper.getChars("12N"));
        
        assertEquals(
            Arrays.asList(new String [] {"3", "3", "E"}),
            StringHelper.getChars("33E"));
        
        assertEquals(
            Arrays.asList(new String [] {
                "G", "A", "G", "A", "G", "A", "G", "A", "A"}),
            StringHelper.getChars("GAGAGAGAA"));
        
        assertEquals(
            Arrays.asList(new String [] {
                "A", "A", "D", "A", "A", "D", "A", "D", "D", "A"}),
            StringHelper.getChars("AADAADADDA"));
    }
    
    public void testJoin () {
        
        assertEquals(
            "GAGAGAGAA",
            StringHelper.join(Arrays.asList("G", "A", "G", "A", "G", "A", "G", "A", "A")));
        
        assertEquals(
            "AADAADADDA",
            StringHelper.join(Arrays.asList("AA", "D", "AADA", "DDA")));
        
    }
    
    public void testGetResourceAsString () {
        
        for (String path : new String[] {
                "/cmd/help.txt",
                "/cmd/header.txt",
                "/cmd/footer.txt"}) {
            
            
            log.warn(path);
            log.warn(this.getResourceAsString(path, "UTF-8"));
            log.warn(StringHelper.getResourceAsString(path, "UTF-8"));
            
            assertEquals(
                this.getResourceAsString(path, "UTF-8"),
                StringHelper.getResourceAsString(path, "UTF-8"));
            
        }
        
        
        
    }
    
    
    protected String getResourceAsString (String path, String charset) {
        StringBuilder builder = new StringBuilder();
        try (   BufferedReader in = new BufferedReader(new InputStreamReader(
                    Main.class.getResourceAsStream(path),
                    charset))) {
            String inputLine;
            while (true) {
                inputLine = in.readLine();
                if (null == inputLine) {
                    break;
                }
                
                builder.append(inputLine);
                builder.append("\n");
            }
            
        } catch (Exception ex) {
            log.error("error {}", ex);
            return "";
        }
        
        return builder.toString();
    }
    
}
