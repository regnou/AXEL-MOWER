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

package cma.xebia.lawnmower.application.cmd.argument.impl;

import cma.xebia.lawnmower.controller.impl.LawnMowerController;
import cma.xebia.lawnmower.utils.cmd.argument.Argument;
import cma.xebia.lawnmower.utils.cmd.argument.DefaultArgument;
import cma.xebia.lawnmower.utils.file.printer.Printer;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 * @param <T>
 */
@Slf4j
public class LawnMowerHelpArgument<T extends LawnMowerController>
        extends DefaultArgument<T> {
    
    private final String helpTextPath;
    
    private final Printer out;
    
    public LawnMowerHelpArgument(
            String name,
            Printer out,
            String helpTextPath) {
        super(name, false);
        this.out = out;
        this.helpTextPath = helpTextPath;
    }
    
    @Override
    public Argument<T> applyOn(String argument, T object) {
        this.stop = true;
        
        this.out.print(this.helpTextPath);
        
        return this;
    }
    
}
