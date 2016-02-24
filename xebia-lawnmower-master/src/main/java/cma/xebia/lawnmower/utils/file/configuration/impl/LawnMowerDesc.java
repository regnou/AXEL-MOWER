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

package cma.xebia.lawnmower.utils.file.configuration.impl;
import cma.xebia.lawnmower.utils.file.configuration.MovableDesc;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;


/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class LawnMowerDesc implements MovableDesc {
    
    @Accessors(chain = true)
    @Getter
    @Setter
    private Point position = new Point();
    
    @Accessors(chain = true)
    @Getter
    @Setter
    private String inFrontOf = "N";
    
    @Accessors(chain = true)
    @Getter
    @Setter
    private List<String> movements = new ArrayList<>();
    
    
}
