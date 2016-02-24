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

import cma.xebia.lawnmower.application.Constant;
import cma.xebia.lawnmower.business.entity.Movable;
import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.service.DefaultRunner;
import cma.xebia.lawnmower.business.service.ShearerRunnerDelegator;
import cma.xebia.lawnmower.utils.exception.LawnMowerException;
import com.rits.cloning.Cloner;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class ThreadedRunner extends DefaultRunner {
    
    private final ShearerRunnerDelegator delegator;
    
    public ThreadedRunner(
            ShearerRunnerDelegator delegator) {
        this.delegator = delegator;
    }
    
    @Override
    protected DefaultRunner doRun () throws LawnMowerException {

        log.info("mow law {}x{} ...",
                this.shearer.getPlayground().getDimension().width,
                this.shearer.getPlayground().getDimension().height);
        
        for (Positionable positionable : this.allObstacles) {
            log.info("with obstacle {}", positionable);
            
        }
        
        ExecutorService pool = Executors.newFixedThreadPool(
            this.shearer.getMovables().size());
        
        
        ShearerRunnerDelegator injectedDelegator;
        Cloner cloner = new Cloner();
        
        Set<Future<ThreadedRunner.Job>> futureJobs = new HashSet<>();
        
        int i = -1;
        for (Movable movable : this.shearer.getMovables()) {
            i++;
            log.debug("create callable with {} #{}", movable, i);
            
            injectedDelegator = cloner.deepClone(this.delegator);
            injectedDelegator
                .on(this.shearer)
                .with(this.allObstacles)
                .use(movable)
            ;
            
            futureJobs.add(
                pool.submit(new ThreadedRunner.Job(injectedDelegator)));
            
        }
        
        pool.shutdown();
        
        
        try {
            while (!pool.isTerminated()) {
                log.info("waiting...");
                pool.awaitTermination(
                        Constant.THREADED_AWAIT_TERMINATION_MS,
                        TimeUnit.MILLISECONDS);
                
            }
            
            i = -1;
            for (Future<ThreadedRunner.Job> future : futureJobs) {
                i++;
                log.info("callable #{} run during {} ms",
                    i,
                    future.get().getConsumedTime());
                
            }
            
        } catch (InterruptedException | ExecutionException ex) {
             throw new LawnMowerException(ex);
        }
        return this;
    }
    
    @Slf4j
    private static class Job
            implements Callable<ThreadedRunner.Job> {
        
        
        private long startTime;
        
        private long endTime;
        
        private final ShearerRunnerDelegator delegator;
        
        public Job(ShearerRunnerDelegator delegator) {
            this.delegator = delegator;
        }
        
        @Override
        public ThreadedRunner.Job call () throws Exception {
            this.startTime = System.currentTimeMillis();
            this.delegator.run();
            this.endTime = System.currentTimeMillis();
            return this;
        }
        
        long getConsumedTime () {
            return this.endTime - this.startTime;
        }
        
    }

}
