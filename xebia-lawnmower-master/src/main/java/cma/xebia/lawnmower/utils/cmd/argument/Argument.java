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

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 * @param <T>
 */
public interface Argument<T> {
    
    public String getName ();
    
    public boolean mustStop ();
    
    public boolean isCorrespondingTo (String argument);
    
    /**
     *
     * @param argument
     * @param object
     * @return
     */
    public Argument<T> applyOn (String argument, T object);
    
}
