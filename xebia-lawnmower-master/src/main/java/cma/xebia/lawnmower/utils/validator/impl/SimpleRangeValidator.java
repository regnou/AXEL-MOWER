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

package cma.xebia.lawnmower.utils.validator.impl;

import cma.xebia.lawnmower.utils.validator.DefaultRangeValidator;


/**
 *
 * maximal limit "mawHeight" and "minHeight" are included in range.
 * "acceptingZero" change minimal limit between "0" (if FALSE) and "-1"
 * minimal limit is excluded from range.
 * 
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class SimpleRangeValidator extends DefaultRangeValidator {
    /**
     * 
     * @param x
     * @param y
     * @return 
     */
    @Override
    public boolean isValid(int x, int y) {
        return this.isValidRange(this.getMinWidth(), x)
            && this.isValidRange(x, this.getMaxWidth())
            && this.isValidRange(this.getMinHeight(), y)
            && this.isValidRange(y, this.getMaxHeight());
    }
    
    /**
     * 
     * @param min
     * @param max
     * @return return TRUE if i is between 0 and max
     */
    protected boolean isValidRange (int min, int max) {
        
        return this.isIncluding()
            ? min <= max
            : min < max;
    }
    
}
