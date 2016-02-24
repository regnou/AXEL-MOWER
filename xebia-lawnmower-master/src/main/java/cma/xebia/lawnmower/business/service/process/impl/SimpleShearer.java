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

package cma.xebia.lawnmower.business.service.process.impl;

import cma.xebia.lawnmower.business.entity.Dimensionable;
import cma.xebia.lawnmower.business.entity.Movable;
import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.service.Shearer;
import cma.xebia.lawnmower.business.service.ShearerRunner;
import cma.xebia.lawnmower.business.service.process.validator.DimentionableValidator;
import cma.xebia.lawnmower.business.service.process.validator.MovableValidator;
import cma.xebia.lawnmower.business.service.process.validator.PositionableValidator;
import cma.xebia.lawnmower.utils.exception.LawnMowerException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class SimpleShearer implements Shearer {
    
    private final DimentionableValidator    playgroundValidator;
    private final PositionableValidator     positionValidator;
    private final MovableValidator          collisionValidator;
    private final ShearerRunner             runner;
    
    @Accessors(chain = true)
    @Getter
    private Dimensionable playground = null;
    
    @Accessors(chain = true)
    @Getter
    private List<Movable> movables = new ArrayList<>();
    
    
    @Accessors(chain = true)
    @Getter
    private List<Positionable> obstacles = new ArrayList<>();
    
    @Accessors(chain = true)
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private boolean validated = false;
    
    @Accessors(chain = true)
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private boolean mowed = false;
    
    @Accessors(chain = true)
    @Getter
    @Setter(AccessLevel.PRIVATE)
    private boolean fail = false;
    
    @Accessors(chain = true)
    @Getter
    private final List<String> errors = new ArrayList<>();
    
    public SimpleShearer (
            @NonNull DimentionableValidator playgroundValidator,
            @NonNull PositionableValidator  positionValidator,
            @NonNull MovableValidator       collisionValidator,
            @NonNull ShearerRunner          runner) {
        this.playgroundValidator = playgroundValidator;
        this.positionValidator = positionValidator;
        this.collisionValidator = collisionValidator;
        this.runner = runner;
        
    }
    
    @Override
    public Shearer init () {
        this.fail = false;
        this.validated = false;
        this.mowed = false;
        this.movables = new ArrayList<>();
        this.obstacles = new ArrayList<>();
        this.playground = null;
        this.errors.clear();
        
        return this;
    }
    
    @Override
    public Shearer on(Dimensionable dimensionable) {
        this.playground = dimensionable;
        return this;
    }
    
    @Override
    public Shearer use(Movable movable) {
        if (this.obstacles.contains(movable)) {
            log.warn("cannot add an obstacle into movables stack");
            return this;
        }
        if (this.movables.contains(movable)) {
            return this;
        }
        this.movables.add(movable);
        return this;
    }
    
    @Override
    public Shearer use(List<Movable> movables) {
        for (Movable movable : movables) {
            this.use(movable);
            
        }
        return this;
    }
    
    @Override
    public Shearer withObstacle(Positionable obstacle) {
        if (this.obstacles.contains(obstacle)) {
            return this;
        }
        this.obstacles.add(obstacle);
        return this;
    }
    
    @Override
    public Shearer withObstacles(List<Positionable> obstacles) {
        for (Positionable obstacle : obstacles) {
            this.withObstacle(obstacle);
            
        }
        return this;
    }
    
    
    @Override
    public Shearer validate () {
        if (this.isValidated()) {
            return this;
        }
        
        return this
            .setValidated(true)
            .verifyLawn()
            .verifyObstacles()
            .verifyLawnMower();
    }
    
    
    
    protected SimpleShearer verifyLawn () {
        log.info("verification of lawn");
        this.playgroundValidator.isValid(this.playground);
        
        return this;
    }
    
    protected SimpleShearer verifyObstacles () {
        log.info("verification of obstacles");
        int i = -1;
        for (Positionable positionable : this.obstacles) {
            log.info("verify obstacle #{}, {}", ++i, positionable);
            if (this.positionValidator
                    .isValid(positionable, this.playground)) {
                continue;
            }
            
            this.setFail(true);
            this.errors.add(String.format("obstacle #%s is out of bounds", i));
        }
        
        return this;
    }
    
    protected SimpleShearer verifyLawnMower () {
        log.info("verification of lawn mowers");
        int i = -1;
        for (Movable movable : this.movables) {
            log.info("verify lawn mowers #{}, {}", ++i, movable);
            if (this.positionValidator
                    .isValid(movable, this.playground)) {
                continue;
            }
            
            this.setFail(true);
            this.errors.add(String.format("lawn mower #%s is out of bounds", i));
        }
        
        return this;
    }
    
    @Override
    public Shearer mow () {
        if (this.isMowed()) {
            log.info("already mowed");
            return this;
        }
        if (!this.isValidated()) {
            log.info("automatic validation");
            this.validate();
        }
        this.setMowed(true);
        if (this.isFail()) {
            log.info("an error occurs...");
            return this;
            
        }
        this.setFail(true);
        
        try {
            this.runner
                .setPositionableValidator(positionValidator)
                .setMovableValidator(collisionValidator)
                .run(this)
            ;
            this.setFail(false);
            
        } catch (LawnMowerException ex) {
            log.error("LawnMowerException : {}", ex);
            
        }
        
        return this;
    }
    
    
    protected Set<Positionable> getAllObstacles () {
        Set<Positionable> result = new HashSet<>();
        result.addAll(this.obstacles);
        result.addAll(this.movables);
        
        return result;
    }
    
    
}
