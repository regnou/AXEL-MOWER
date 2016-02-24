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

package cma.xebia.lawnmower.utils.file.configuration;

import java.util.List;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public interface DescReader {
    
    public DescReader setDefaultResourcePath (String defaultResourcePath);
    
    public DescReader setDescriptorPath (String descriptorPath);
    
    public DescReader setCharset (String charset);
    
    public String getDescriptorFileInfo ();
    
    public DescReader read ();
    
    public DimensionableDesc getLawn ();
    
    public List<PositionDesc> getObstacles ();
    
    public List<MovableDesc> getLawnMowers ();
    
    public boolean isFail ();
}
