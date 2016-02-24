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

package cma.xebia.lawnmower.utils.cmd.argument.impl;

import cma.xebia.lawnmower.utils.cmd.argument.Argument;
import cma.xebia.lawnmower.utils.cmd.argument.Arguments;
import java.util.Set;
import lombok.Getter;
import lombok.NonNull;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class SimpleArguments<T> implements Arguments<T> {
    
    @Getter
    private final Set<Argument<T>> arguments;
    
    @Getter
    private final Argument<T> help;
    
    private boolean stop = false;
    
    public SimpleArguments (
            @NonNull Set<Argument<T>> arguments,
            @NonNull Argument<T> help) {
        this.arguments = arguments;
        this.help = help;
    }
    
    @Override
    public Arguments<T> parse (String[] args, T object) {
        
        boolean argumentsAreCorrect = true;
        
        for (String arg : args) {
            argumentsAreCorrect &= this.parseArgument(arg, object);
            
        }
        
        this.stop = !argumentsAreCorrect;
        
        return this;
    }
    
    
    protected boolean parseArgument (String argument, T object) {
        
        for (Argument<T> a : this.arguments) {
            if (!a.isCorrespondingTo(argument)) {
                continue;
            }
            if (a.applyOn(argument, object).mustStop()) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public Arguments<T> showHelp(T object) {
        if (!this.help.mustStop()) {
            this.help.applyOn("", object);
        }
        
        return this;
    }
    
    @Override
    public boolean mustStop () {
        return stop;
    }
    
}
