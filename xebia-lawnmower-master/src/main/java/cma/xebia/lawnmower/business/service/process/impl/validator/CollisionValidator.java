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

import cma.xebia.lawnmower.business.entity.Movable;
import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.service.process.validator.MovableValidator;
import java.util.Set;
import java.util.TreeSet;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class CollisionValidator implements MovableValidator {
    
    @Override
    public boolean isValid(
            Movable current,
            Positionable nextPosition,
            Set<Positionable> obstacles) {
        
        for (Positionable obstacle : obstacles) {
            if (obstacle == current) {
                continue;
            }
            
            if (obstacle.getPosition().isInSameLocation(nextPosition)) {
                log.debug("for movable %s found obstacle %s", nextPosition, obstacle);
                return false;
            }
            
        }
        
        return true;
    }
    
    @Override
    public Set<Positionable> getAlreadyPositioned (
            Movable current,
            Positionable nextPosition,
            Set<Positionable> obstacles) {
        Set<Positionable> result = new TreeSet<>();
        
        for (Positionable obstacle : obstacles) {
            if ((obstacle == current)
                    || (!obstacle.getPosition().isInSameLocation(nextPosition))) {
                continue;
            }
            
            result.add(obstacle);
        }
        
        return result;
    }
    
}
