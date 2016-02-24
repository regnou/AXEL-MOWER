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

package cma.xebia.lawnmower.application;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class Constant {
    
    public static final String RES_LAWNMOWER_DESC       = "/lawnmower.desc";
    
    public static final String LAWNMOWER_FILE_CHARSET   = "UTF-8";
    
    public static final String BEAN_CONTROLLER_STANDARD = "lawn-mower.controller.standard";
    
    public static final String BEAN_CONTROLLER_THREADED = "lawn-mower.controller.threaded";
    
    public static final String BEAN_CONTROLLER_BROKEN   = "lawn-mower.controller.broken-down";
    
    public static final String BEAN_MAIN_ARGS           = "command-line.main.arguments";
    
    public static final long THREADED_AWAIT_TERMINATION_MS = 250;
    
    private Constant () {
    }
    
    
    
}
