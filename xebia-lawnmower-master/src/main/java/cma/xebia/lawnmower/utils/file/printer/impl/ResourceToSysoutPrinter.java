package cma.xebia.lawnmower.utils.file.printer.impl;

import cma.xebia.lawnmower.utils.file.printer.Printer;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import lombok.extern.slf4j.Slf4j;

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

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class ResourceToSysoutPrinter implements Printer {
    
    private final String charset;
    
    private final PrintStream out;
    
    
    public ResourceToSysoutPrinter (
            PrintStream out,
            String charset) {
        this.out = out;
        this.charset = charset;
    }
    
    @Override
    public ResourceToSysoutPrinter print (String path) {
        try (   BufferedReader in = new BufferedReader(new InputStreamReader(
                    this.getClass().getResourceAsStream(path),
                    this.charset))) {
            String inputLine;
            while (true) {
                inputLine = in.readLine();
                if (null == inputLine) {
                    break;
                }
                out.println(inputLine);
            }
            
        } catch (Exception ex) {
            log.error("Cannot print {}", ex);
        }
        
        return this;
    }
    
}
