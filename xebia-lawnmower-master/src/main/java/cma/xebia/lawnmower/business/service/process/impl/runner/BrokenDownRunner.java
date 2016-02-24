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
import cma.xebia.lawnmower.business.service.DefaultRunner;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class BrokenDownRunner extends DefaultRunner {
    
    @Override
    protected DefaultRunner doRun() {
        
        log.info("mow law {}x{} ...",
            this.shearer.getPlayground().getDimension().width,
            this.shearer.getPlayground().getDimension().height);
        
        
        for (Positionable positionable : this.allObstacles) {
            log.info("with obstacle {}", positionable);
            
        }
        
        int i = -1;
        for (Movable movable : this.shearer.getMovables()) {
            i++;
            log.warn("do nothing with {} #{} and movements {}: it's broken down !!!",
                movable,
                i,
                movable.getMovements());
        }
        
        
        return this;
    }
}
