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

package cma.xebia.lawnmower.utils.helpers;

import cma.xebia.lawnmower.application.Constant;
import cma.xebia.lawnmower.application.Main;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
public class StringHelper {
    
    private StringHelper () {
    }
    
    public static List<String> getChars (String str) {
        List<String> result = new ArrayList<>();
        
        if (str.length() < 1) {
            return result;
        }
        
        result.addAll(Arrays.asList(str.split("")));
        
        // jdk 1.7 ...
        if ("".equals(result.get(0))) {
            result.remove(0);
        }
        
        return result;
    }
    
    public static String join (List<String> str) {
        
        StringBuilder result = new StringBuilder();
        for (String o : str) {
            result.append(o);
        }
        
        return result.toString();
    }
    
    public static String getResourceAsString (String path, String charset) {
        return new Scanner(
                Main.class.getResourceAsStream(path),
                charset)
            .useDelimiter("\\A")
            .next();
    }
    
    public static String getResourceAsString (String path) {
        return StringHelper
            .getResourceAsString(path, Constant.LAWNMOWER_FILE_CHARSET);
        
    }
}
