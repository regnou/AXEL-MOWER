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

package cma.xebia.lawnmower.business.service.process.impl.validator;

import cma.xebia.lawnmower.business.entity.Dimensionable;
import cma.xebia.lawnmower.business.service.process.validator.DimentionableValidator;
import cma.xebia.lawnmower.utils.validator.impl.SimpleRangeValidator;
import cma.xebia.lawnmower.utils.validator.RangeValidator;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class SizeOfLawnValidator
        implements DimentionableValidator {
    
    
    private RangeValidator positionValidator = new SimpleRangeValidator();
    
    
    public SizeOfLawnValidator (RangeValidator positionValidator) {
        this.positionValidator = positionValidator;
    }
    
    /**
     * 
     * @param dimensionable
     * @return return TRUE if width and height are between 1 and 9, included
     */
    @Override
    public boolean isValid (Dimensionable dimensionable) {
        boolean result = false;
        
        if (null == dimensionable) {
            return result;
            
        }
        
        return this
            .positionValidator
            .isValid(
                dimensionable.getDimension().width,
                dimensionable.getDimension().height);
    }
    
}
