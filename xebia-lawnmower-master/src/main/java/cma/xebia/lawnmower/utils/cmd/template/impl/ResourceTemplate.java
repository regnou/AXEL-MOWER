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

package cma.xebia.lawnmower.utils.cmd.template.impl;

import cma.xebia.lawnmower.utils.cmd.template.Template;
import cma.xebia.lawnmower.utils.file.printer.Printer;
import lombok.NonNull;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class ResourceTemplate implements Template {
    
    
    private final Printer out;
    
    private final String headerResource;
    
    private final String footerResource;
    
    public ResourceTemplate(
            @NonNull Printer out,
            @NonNull String headerResource,
            @NonNull String footerResource) {
        this.out = out;
        this.headerResource = headerResource;
        this.footerResource = footerResource;
    }

    @Override
    public Template printHeader() {
        this.out.print(this.headerResource);
        
        return this;
    }

    @Override
    public Template printFooter() {
        this.out.print(this.footerResource);
        
        return this;
    }
    
    
    
}
