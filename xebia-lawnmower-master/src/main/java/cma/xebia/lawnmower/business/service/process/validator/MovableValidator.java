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

package cma.xebia.lawnmower.business.service.process.validator;

import cma.xebia.lawnmower.business.entity.Movable;
import cma.xebia.lawnmower.business.entity.Positionable;
import java.util.Set;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public interface MovableValidator {
    
    public boolean isValid (
            Movable current,
            Positionable nextPosition,
            Set<Positionable> obstacles);
    
    public Set<Positionable> getAlreadyPositioned (
            Movable current,
            Positionable nextPosition,
            Set<Positionable> obstacles);
    
}
