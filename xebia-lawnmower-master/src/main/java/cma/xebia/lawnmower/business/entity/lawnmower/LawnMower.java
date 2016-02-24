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
package cma.xebia.lawnmower.business.entity.lawnmower;

import cma.xebia.lawnmower.business.entity.Movable;
import cma.xebia.lawnmower.business.entity.impl.Position;
import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.entity.constants.Movement;
import cma.xebia.lawnmower.business.entity.lawnmower.commands.Action;
import cma.xebia.lawnmower.business.entity.lawnmower.commands.Commands;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.experimental.Accessors;
import lombok.Getter;
import lombok.NonNull;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class LawnMower
        implements  Positionable,
                    Movable {
    
    @Accessors(chain = true)
    @Getter
    private Position position;
    
    
    @Accessors(chain = true)
    @Getter
    private List<Action> movements = new ArrayList<>();
    
    @Accessors(chain = true)
    private final Commands commands;
    
    LawnMower(@NonNull Commands commands) {
        this.commands = commands;
        this.position = new Position();
    }
    
    public LawnMower program (List<Movement> movements) {
        this.movements = new ArrayList<>();
        
        for (Movement movement : movements) {
            this.movements.add(this.commands.getMovements().get(movement));
            
        }
        
        return this;
    }
    
    @Override
    public Movable moveTo(@NonNull Positionable anotherLocation) {
        this.position = new Position(anotherLocation.getPosition());
        
        return this;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.position);
        hash = 73 * hash + Objects.hashCode(this.movements);
        hash = 73 * hash + Objects.hashCode(this.commands);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final LawnMower other = (LawnMower) obj;
        
        return Objects.equals(this.position, other.position)
            && Objects.equals(this.movements, other.movements)
            && Objects.equals(this.commands, other.commands);
    }
    
    
    
    @Override
    public String toString() {
        return String.format("LawnMower {%s}",
            this.getPosition());
    }
    
}
