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
package cma.xebia.lawnmower.controller.impl;



import cma.xebia.lawnmower.business.entity.Dimensionable;
import cma.xebia.lawnmower.business.entity.Movable;
import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.controller.Controller;
import cma.xebia.lawnmower.business.entity.lawnmower.LawnMowerBuilder;
import cma.xebia.lawnmower.business.entity.constants.CompassPoint;
import cma.xebia.lawnmower.business.entity.constants.Movement;
import cma.xebia.lawnmower.business.entity.lawn.Lawn;
import cma.xebia.lawnmower.business.entity.obstacle.Obstacle;
import cma.xebia.lawnmower.business.service.Shearer;
import cma.xebia.lawnmower.utils.cmd.argument.Arguments;
import cma.xebia.lawnmower.utils.cmd.template.Template;
import cma.xebia.lawnmower.utils.exception.LawnMowerException;
import cma.xebia.lawnmower.utils.file.configuration.MovableDesc;
import cma.xebia.lawnmower.utils.file.configuration.DescReader;
import cma.xebia.lawnmower.utils.file.configuration.PositionDesc;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class LawnMowerController implements Controller {
    
    
    @Getter
    private final Arguments<LawnMowerController> arguments;
    
    @Getter
    private final Template template;
    
    
    @Getter
    private final DescReader reader;
    
    @Getter
    private final Shearer shearer;
    
    @Getter
    private final LawnMowerBuilder builder;
    
    private long startTime;
    private long endTime;
    
    
    public LawnMowerController (
            @NonNull Arguments<LawnMowerController> arguments,
            @NonNull Template template,
            @NonNull DescReader reader,
            @NonNull LawnMowerBuilder builder,
            @NonNull Shearer shearer) {
        this.arguments = arguments;
        this.template = template;
        this.reader = reader;
        this.builder = builder;
        this.shearer = shearer;
    }
    
    @Override
    public LawnMowerController init (String[] args) throws LawnMowerException {
        log.debug("init");
        
        this.template.printHeader();
        
        if (this.arguments.parse(args, this).mustStop()) {
            this.arguments.showHelp(this);
            
            return this;
        }
        
        this.getReader().read();
        
        return this;
    }
    
    @Override
    public LawnMowerController run () throws LawnMowerException {
        if (this.arguments.mustStop()) {
            log.debug("run : do not run");
            return this;
            
        }
        
        log.debug("run");
        
        
        Dimensionable lawn = this.computeLawn();
        List<Movable> lawnMowers = this.computeLawnMowers();
        List<Positionable> obstacle = this.computeObstacles();
        
        this.startTime = System.currentTimeMillis();
        
        shearer
            .init()
            .on(lawn)
            .withObstacles(obstacle)
            .use(lawnMowers)
            .mow();
        
        this.endTime = System.currentTimeMillis();
        
        if (shearer.isFail()) {
            log.warn("Oups, an error occurs ...");
            
        } else {
            log.debug("Done");
            int i = -1;
            for (Positionable positionable : shearer.getMovables()) {
                log.info("lawn #{} is to position ({}x{}) and is in front of {}",
                    ++i,
                    positionable.getPosition().getX(),
                    positionable.getPosition().getY(),
                    positionable.getPosition().getInFrontOf());
                
            }
        }
        
        return this;
    }
    
    @Override
    public LawnMowerController finish () {
        
        log.info("shearer duration process is {} ms",
            this.endTime - this.startTime);
        
        this.template.printFooter();
        
        log.debug("end");
        
        return this;
    }
    
    protected Dimensionable computeLawn () throws LawnMowerException {
        log.info("create lawn");
        
        return new Lawn(
            getReader().getLawn().getDimension().width,
            getReader().getLawn().getDimension().height)
        ;
    }
    
    protected List<Positionable> computeObstacles () {
        log.info("create obstacles");
        List<Positionable> result = new ArrayList<>();
        
        for(PositionDesc desc : reader.getObstacles()) {
            
            result.add((new Obstacle())
                .setX(desc.getPosition().x)
                .setY(desc.getPosition().y))
            ;
            
        }
        
        
        return result;
    }
    
    protected List<Movable> computeLawnMowers () throws LawnMowerException {
        log.info("create lawn mowers");
        List<Movable> result = new ArrayList<>();
        
        for(MovableDesc desc : reader.getLawnMowers()) {
            result.add(getBuilder()
                .create(
                    desc.getPosition().x,
                    desc.getPosition().y,
                    CompassPoint.valueOf(desc.getInFrontOf()))
                .program(Movement.parseMovements(desc.getMovements()))
            );
            
        }
        
        return result;
    }
    
    protected File getFileFromArguments (String[] args) {
        
        if (args.length < 1) {
            log.info("no arguments found");
            return null;
            
        }
        
        log.info("Found arguments {}", args[0]);
        
        File result = new File(args[0]);
        if (!result.exists()) {
            log.info("File {} doesn't exist", args[0]);
            return null;
        }
        
        if (!result.isFile()) {
            log.info("File {} doesn't regular", args[0]);
            return null;
        }
        
        return result;
    }
}
