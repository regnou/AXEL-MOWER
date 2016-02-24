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

package cma.xebia.lawnmower;

import cma.xebia.lawnmower.business.service.Shearer;
import cma.xebia.lawnmower.business.entity.Dimensionable;
import cma.xebia.lawnmower.business.entity.Movable;
import cma.xebia.lawnmower.business.entity.impl.Position;
import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.entity.constants.CompassPoint;
import cma.xebia.lawnmower.business.entity.constants.Movement;
import cma.xebia.lawnmower.business.entity.lawn.Lawn;
import cma.xebia.lawnmower.business.entity.obstacle.Obstacle;
import cma.xebia.lawnmower.utils.file.configuration.MovableDesc;
import cma.xebia.lawnmower.utils.file.configuration.DescReader;
import cma.xebia.lawnmower.utils.file.configuration.PositionDesc;
import java.util.ArrayList;
import java.util.List;
import static junit.framework.Assert.assertEquals;
import lombok.extern.slf4j.Slf4j;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public abstract class ShearerStub extends SpringLoaded {
    
    protected DescReader reader;
    
    public ShearerStub(String testName) {
        super(testName);
        
    }
    
    @Override
    protected void setUp() throws Exception {
        this.configureEnv();
        super.setUp();
        reader = controller.getReader()
            .setDefaultResourcePath("/setup/lawnmower+obstacles.desc")
            .setCharset("UTF-8")
            .read();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        this.useStandardController();
        
    }
    
    protected abstract void configureEnv ();
    
    public void testNonNull () {
        assertNotNull(controller.getReader());
        assertNotNull(controller.getBuilder());
        assertNotNull(controller.getShearer());
    }
    
    /**
     * Test of run method, of class Shearer.
     */
    public void testRunWithoutObstacle() {
        
        Shearer shearer = controller.getShearer();
        
        Dimensionable lawn = this.getPlayground();
        List<Positionable> obstacles = this.getObstacles();
        List<Movable> lawnMowers = this.getMovables();
        
        shearer
            .init()
            .on(lawn)
            .withObstacles(obstacles)
            .use(lawnMowers)
            .validate()
            .mow();
        
        this.checkTestRunWithObstacle(shearer, lawnMowers);
        
    }
    
    
    protected void checkTestRunWithoutObstacle (
            Shearer shearer,
            List<Movable> lawnMowers) {
        
        assertEquals(false, shearer.isFail());
        
        assertEquals(0, shearer.getObstacles().size());
        
        assertEquals(2, shearer.getMovables().size());
        assertEquals(lawnMowers.get(0), shearer.getMovables().get(0));
        assertEquals(lawnMowers.get(1), shearer.getMovables().get(1));
        
        assertEquals(1, shearer.getMovables().get(0).getPosition().getX());
        assertEquals(3, shearer.getMovables().get(0).getPosition().getY());
        assertEquals(CompassPoint.N, shearer.getMovables().get(0).getPosition().getInFrontOf());
        
        assertEquals(5, shearer.getMovables().get(1).getPosition().getX());
        assertEquals(1, shearer.getMovables().get(1).getPosition().getY());
        assertEquals(CompassPoint.E, shearer.getMovables().get(1).getPosition().getInFrontOf());
        
    }
    
    /**
     * Test of run method, of class Shearer.
     */
    public void testRunWithObstacle () {
        
        
        Shearer shearer = controller.getShearer();
        
        Dimensionable lawn = this.getPlayground();
        List<Positionable> obstacles = this.getObstacles();
        List<Movable> lawnMowers = this.getMovables();
        
        shearer
            .init()
            .on(lawn)
            .withObstacles(obstacles)
            .use(lawnMowers)
            .validate()
            .mow();
        
        this.checkTestRunWithObstacle(shearer, lawnMowers);
    }
    
    protected void checkTestRunWithObstacle (
            Shearer shearer,
            List<Movable> lawnMowers) {
        
        assertEquals(false, shearer.isFail());
        
        assertEquals(2, shearer.getObstacles().size());
        assertEquals(1, shearer.getObstacles().get(0).getPosition().getX());
        assertEquals(3, shearer.getObstacles().get(0).getPosition().getY());
        assertEquals(4, shearer.getObstacles().get(1).getPosition().getX());
        assertEquals(1, shearer.getObstacles().get(1).getPosition().getY());
        
        
        assertEquals(2, shearer.getMovables().size());
        assertEquals(lawnMowers.get(0), shearer.getMovables().get(0));
        assertTrue(lawnMowers.get(0) == shearer.getMovables().get(0));
        
        assertEquals(lawnMowers.get(1), shearer.getMovables().get(1));
        assertTrue(lawnMowers.get(1) == shearer.getMovables().get(1));
        
        assertEquals(1, shearer.getMovables().get(0).getPosition().getX());
        assertEquals(2, shearer.getMovables().get(0).getPosition().getY());
        assertEquals(CompassPoint.N, shearer.getMovables().get(0).getPosition().getInFrontOf());
        
        
        assertEquals(5, shearer.getMovables().get(1).getPosition().getX());
        assertEquals(1, shearer.getMovables().get(1).getPosition().getY());
        assertEquals(CompassPoint.E, shearer.getMovables().get(1).getPosition().getInFrontOf());
        
        
    }
    
    protected Dimensionable getPlayground () {
        
        return new Lawn(
            this.reader.getLawn().getDimension().width,
            this.reader.getLawn().getDimension().height);
    }
    
    protected List<Positionable> getObstacles () {
        List<Positionable> result = new ArrayList<>();
        
        for(PositionDesc desc : this.reader.getObstacles()) {
            
            result.add((new Obstacle())
                .setX(desc.getPosition().x)
                .setY(desc.getPosition().y))
            ;
            
        }
        
        
        return result;
        
    }
    
    protected List<Movable> getMovables () {
        List<Movable> result = new ArrayList<>();
        
        for(MovableDesc desc : this.reader.getLawnMowers()) {
            result.add(controller.getBuilder().create()
                .program(Movement.parseMovements(desc.getMovements()))
                .moveTo(
                    (new Position())
                        .setX(desc.getPosition().x)
                        .setY(desc.getPosition().y)
                        .setInFrontOf(CompassPoint.valueOf(desc.getInFrontOf())))
                );
            
        }
        
        return result;
    }
}
