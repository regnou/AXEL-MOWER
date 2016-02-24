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

import cma.xebia.lawnmower.application.Constant;
import cma.xebia.lawnmower.application.Main;
import cma.xebia.lawnmower.controller.impl.LawnMowerController;
import junit.framework.TestCase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class SpringLoaded extends TestCase {
    
    protected ApplicationContext context = null;
    
    protected LawnMowerController controller = null;
    
    private String selectedController = null;
    
    public SpringLoaded(String testName) {
        super(testName);
        this.selectedController = Constant.BEAN_CONTROLLER_STANDARD;
    }
    
    protected void useStandardController () {
        this.selectedController = Constant.BEAN_CONTROLLER_STANDARD;
        
    }
    
    protected void useThreadedController () {
        this.selectedController = Constant.BEAN_CONTROLLER_THREADED;
        
    }
    
    protected void useBrokenController () {
        this.selectedController = Constant.BEAN_CONTROLLER_BROKEN;
        
    }
    
    @Override
    protected void setUp() throws Exception {
        context = new ClassPathXmlApplicationContext(
                "/configuration/spring.xml",
                Main.class);
        
        controller = ((LawnMowerController) context.getBean(this.selectedController));
        
    }
    
    @Override
    protected void tearDown() throws Exception {
        ((ConfigurableApplicationContext) context).close();
    }
    
    
}
