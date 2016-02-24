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

package cma.xebia.lawnmower.business.entity.lawnmower.commands.impl;

import cma.xebia.lawnmower.business.entity.Movable;
import cma.xebia.lawnmower.business.entity.impl.Position;
import cma.xebia.lawnmower.business.entity.constants.CompassPoint;
import cma.xebia.lawnmower.business.entity.lawnmower.commands.DefaultAction;
import lombok.extern.slf4j.Slf4j;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class G extends DefaultAction {
    
    public G () {
        super("G");
    }
    
    @Override
    public Position apply (Movable movable) {
        Position result = new Position(movable.getPosition());
        CompassPoint newInFrontOf;
        
        switch (movable.getPosition().getInFrontOf()) {
            case N :
                newInFrontOf = CompassPoint.W;
                break;
                
            case E :
                newInFrontOf = CompassPoint.N;
                break;
                
            case S :
                newInFrontOf = CompassPoint.E;
                break;
            
            case W :
            default :
                newInFrontOf = CompassPoint.S;
                break;
        }
        
        result.setInFrontOf(newInFrontOf);
        
        return result;
    }
    
    
}
