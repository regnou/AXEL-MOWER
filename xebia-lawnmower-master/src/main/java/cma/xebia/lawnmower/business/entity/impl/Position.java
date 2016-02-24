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
package cma.xebia.lawnmower.business.entity.impl;

import cma.xebia.lawnmower.business.entity.Positionable;
import cma.xebia.lawnmower.business.entity.constants.CompassPoint;
import java.util.Objects;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class Position implements Positionable, Comparable<Positionable> {

    @Accessors(chain = true)
    @Getter
    @Setter
    private int x = 0;

    @Accessors(chain = true)
    @Getter
    @Setter
    private int y = 0;

    @Accessors(chain = true)
    @Getter
    @Setter
    private CompassPoint inFrontOf = CompassPoint.N;

    public Position() {
    }

    public Position(Position position) {
        this.x = position.x;
        this.y = position.y;
        this.inFrontOf = position.inFrontOf;
    }

    @Override
    public Position getPosition() {
        return this;
    }

    public boolean isInSameLocation(@NonNull Positionable positionable) {
        return this.isInSameLocation(positionable.getPosition());
    }

    public boolean isInSameLocation(@NonNull Position position) {
        return (position.x == this.x)
                && (position.y == this.y);
    }

    @Override
    public String toString() {
        return String.format("Position [%s%s%s]",
                this.getX(),
                this.getY(),
                this.getInFrontOf());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.x;
        hash = 83 * hash + this.y;
        hash = 83 * hash + Objects.hashCode(this.inFrontOf);
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
        final Position other = (Position) obj;
        if (this.x != other.x) {
            return false;
        }
        if (this.y != other.y) {
            return false;
        }
        
        return this.inFrontOf == other.inFrontOf;
    }
    
    @Override
    public int compareTo(@NonNull Positionable o) {
        Position p = o.getPosition();
        
        if ((this == o)
                || (this == p)) {
            return 0;
        }
        
        final String pattern = "%s%s%s";
        
        String selfRepresentation = String.format(pattern,
            this.getX(),
            this.getY(),
            this.getInFrontOf().name());
        
        String otherRepresentation = String.format(pattern,
            p.getX(),
            p.getY(),
            p.getInFrontOf().name());
        
        int strCompareTo = selfRepresentation.compareTo(otherRepresentation);
        int result = 0;
        
        if (strCompareTo < 0) {
            result = -1;
            
        } else if (0 < strCompareTo) {
            result = 1;
            
        }
        
        return result;
        
    }

}
