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

package cma.xebia.lawnmower.business.service.process.impl.runner;

import cma.xebia.lawnmower.business.entity.Movable;
import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.entity.impl.Position;
import cma.xebia.lawnmower.business.entity.lawnmower.commands.Action;
import cma.xebia.lawnmower.business.service.Shearer;
import cma.xebia.lawnmower.business.service.ShearerRunnerDelegator;
import cma.xebia.lawnmower.business.service.process.validator.MovableValidator;
import cma.xebia.lawnmower.business.service.process.validator.PositionableValidator;
import java.util.HashSet;
import java.util.Set;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class MovementRunnerDelegator implements ShearerRunnerDelegator {
    
    
    protected final PositionableValidator   positionableValidator;
    
    protected final MovableValidator        movableValidator;
    
    private Shearer shearer = null;
    
    private Set<Positionable> obstacles = new HashSet<>();
    
    private Movable movable = null;
    
    public MovementRunnerDelegator(
            @NonNull PositionableValidator positionableValidator,
            @NonNull MovableValidator movableValidator) {
        this.positionableValidator = positionableValidator;
        this.movableValidator = movableValidator;
    }
    
    @Override
    public ShearerRunnerDelegator on(@NonNull Shearer shearer) {
        this.shearer = shearer;
        return this;
    }
    
    @Override
    public ShearerRunnerDelegator with(@NonNull Set<Positionable> obstacles) {
        this.obstacles = obstacles;
        return this;
    }

    @Override
    public ShearerRunnerDelegator use(@NonNull Movable movable) {
        this.movable = movable;
        return this;
    }
    
    
    
    @Override
    public Positionable run () {
        if (null == this.shearer) {
            throw new NullPointerException("shearer");
            
        }
        if (null == this.movable) {
            throw new NullPointerException("movable");
            
        }
        
        Position nextPosition;
        
        for (Action action: movable.getMovements()) {
            
            nextPosition = action.apply(movable);
            
            // position validation
            if (!this.positionableValidator
                    .isValid(nextPosition, this.shearer.getPlayground())) {
                // Out of bound...
                // nothing to do ...
                log.warn("Action {}, next position {} is unreachable ...",
                    action,
                    nextPosition);
                
            } else if (!this
                    .movableValidator
                    .isValid(movable, nextPosition, this.obstacles)) {
                // collision detected
                // nothing to do ...
                
                log.warn("Action {}, next position {} is already taken by {}",
                    action,
                    nextPosition,
                    this
                        .movableValidator
                        .getAlreadyPositioned(movable, nextPosition, this.obstacles));
                
            } else {
                log.info("Action {}, move to {}",
                    action,
                    nextPosition);
                movable.moveTo(nextPosition);
                
            }
            
            
        }
        
        log.info("Lawn mower is now : {}", movable);
        
        return movable.getPosition();
    }
    
}
