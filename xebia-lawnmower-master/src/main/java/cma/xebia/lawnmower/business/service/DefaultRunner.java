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

package cma.xebia.lawnmower.business.service;

import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.service.process.validator.MovableValidator;
import cma.xebia.lawnmower.business.service.process.validator.PositionableValidator;
import cma.xebia.lawnmower.utils.exception.LawnMowerException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public abstract class DefaultRunner implements ShearerRunner {
    
    protected Shearer shearer = null;
    
    protected Set<Positionable> allObstacles = new HashSet<>();
    
    
    @Accessors(chain = true)
    @Setter
    protected PositionableValidator   positionableValidator   = null;
    
    @Accessors(chain = true)
    @Setter
    protected MovableValidator        movableValidator  = null;
    
    @Override
    public ShearerRunner run (@NonNull Shearer shearer) throws LawnMowerException {
        
        if (null == this.positionableValidator) {
            throw new NullPointerException("positionableValidator");
        }
        
        if (null == this.movableValidator) {
            throw new NullPointerException("movableValidator");
        }
        
        this.shearer = shearer;
        this.allObstacles = Collections
            .unmodifiableSet(this.getAllObstacles());
        
        this.doRun();
        
        this.shearer = null;
        this.allObstacles = new HashSet<>();
        return this;
    }
    
    protected Set<Positionable> getAllObstacles () {
        Set<Positionable> result = new HashSet<>();
        
        if (null == this.shearer) {
            return result;
        }
        
        result.addAll(this.shearer.getObstacles());
        result.addAll(this.shearer.getMovables());
        
        return result;
    }
    
    protected abstract DefaultRunner doRun () throws LawnMowerException;
    
}
