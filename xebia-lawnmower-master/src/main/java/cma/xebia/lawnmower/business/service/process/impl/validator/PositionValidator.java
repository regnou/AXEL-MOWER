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
import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.service.process.validator.PositionableValidator;
import cma.xebia.lawnmower.utils.validator.impl.SimpleRangeValidator;
import cma.xebia.lawnmower.utils.validator.RangeValidator;



/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class PositionValidator implements PositionableValidator {
    
    private RangeValidator positionValidator = new SimpleRangeValidator();
    
    public PositionValidator(RangeValidator positionValidator) {
        this.positionValidator = positionValidator;
    }
    
    /**
     * 
     * @param positionable
     * @param dimensionable
     * @return 
     */
    @Override
    public boolean isValid (
            Positionable positionable,
            Dimensionable dimensionable) {
        boolean result = false;
        
        if ((null == positionable)
                || (null == dimensionable)) {
            return result;
            
        }
        
        
        return this
            .positionValidator
            .setMaxWidth(dimensionable.getDimension().width)
            .setMaxHeight(dimensionable.getDimension().height)
            .isValid(
                positionable.getPosition().getX(),
                positionable.getPosition().getY());
        
    }
    
    
    
}
