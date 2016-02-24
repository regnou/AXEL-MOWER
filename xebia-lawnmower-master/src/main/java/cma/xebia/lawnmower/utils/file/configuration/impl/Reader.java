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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import cma.xebia.lawnmower.application.Constant;
import cma.xebia.lawnmower.utils.file.configuration.DimensionableDesc;
import cma.xebia.lawnmower.utils.file.configuration.MovableDesc;
import cma.xebia.lawnmower.utils.file.configuration.DescReader;
import cma.xebia.lawnmower.utils.file.configuration.PositionDesc;
import cma.xebia.lawnmower.utils.helpers.StringHelper;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author Christophe Martel <mail.christophe.martel@gmail.com>
 */
@Slf4j
public class Reader implements DescReader {
    
    private static final String ERROR_MESSAGE_TWO_CHARS = "two characters expected";
    
    private static final String ERROR_MESSAGE_THREE_CHARS = "three characters expected";
    
    private static final String ERROR_MESSAGE_TWO_DIGITS = "two digits expected";
    
    private static final String ERROR_MESSAGE_TWO_DIGITS_AND_ONE_DIRECTION = "two digits followed by a direction [N|E|S|W] expected";
    
    private static final String ERROR_MESSAGE_UNVALID_MOVEMENT = "Only valid movement [D|G|A] expected";
    
    @Accessors(chain = true)
    @Getter
    @Setter
    private String defaultResourcePath = Constant.RES_LAWNMOWER_DESC;
    
    @Accessors(chain = true)
    @Getter
    @Setter
    private String descriptorPath = null;
    
    
    @Accessors(chain = true)
    @Getter
    @Setter
    private String charset = Constant.LAWNMOWER_FILE_CHARSET;
    
    @Accessors(chain = true)
    @Getter
    @Setter
    private DimensionableDesc lawn = new LawnDesc();
    
    @Accessors(chain = true)
    @Getter
    @Setter
    private List<PositionDesc> obstacles = new ArrayList<>();
    
    @Accessors(chain = true)
    @Getter
    @Setter
    private List<MovableDesc> lawnMowers = new ArrayList<>();
    
    private int lastLawnMower = -1;
    
    @Accessors(chain = true)
    @Getter
    private boolean fail = true;
    
    
    @Override
    public Reader read() {
        this.fail = false;
        this.lastLawnMower = 0;
        this.lawn = new LawnDesc();
        this.lawnMowers = new ArrayList<>();
        this.readFile();
        
        return this;
    }
    
    
    protected Reader readFile () {
        String inputLine = null;
        int lineCounter = -1;
        
        
        
        try (BufferedReader in = this.getReader()) {
            
            if (null == in) {
                return this.fail("cannot read configuration file");
                
            }
            
            while (true) {
                inputLine = in.readLine();
                if ((null == inputLine)
                        || (this.isFail())) {
                    log.debug("End of file");
                    break;
                }
                
                lineCounter++;
                
                inputLine = inputLine.trim();
                if (!inputLine.isEmpty()) {
                    this.parseLine(lineCounter, inputLine);
                }
                
                
            }
        } catch (IOException ex) {
            log.error("An exception occurs", ex);
            this.fail = true;
        }
        
        return this;
    }
    
    protected BufferedReader getReader () throws IOException {
        return null != this.descriptorPath
            ? this.getReaderFromFile()
            : this.getReaderFromResource();
    }
    
    protected BufferedReader getReaderFromResource () throws IOException {
        log.info("load resource file {} with charset {}", this.defaultResourcePath, this.charset);
        return new BufferedReader(
            new InputStreamReader(
                this.getClass().getResourceAsStream(this.defaultResourcePath),
                this.charset));
    }
    
    protected BufferedReader getReaderFromFile () throws IOException {
        log.debug("load local file {} with charset {}", this.descriptorPath, this.charset);
        File f = new File(this.descriptorPath);
        
        if (!f.exists()) {
            log.debug("file {} doesn't exist", this.descriptorPath);
            return null;
        }
        
        if (!f.isFile()) {
            log.debug("file {} isn't regular", this.descriptorPath);
            return null;
        }
        
        return new BufferedReader(
            new InputStreamReader(
                new FileInputStream(this.descriptorPath),
                this.charset));
    }
    
    protected Reader parseLine (int lineCounter, String line) {
        log.debug("Parse line #{} with value:{}", lineCounter, line);
        
        if (0 == lineCounter) {
            this.parseLawnDimension(line);
            return this;
            
        }
        
        if (line.startsWith("!")) {
            this.parseObstacle(line);
            return this;
        }
        
        int lineCounterWithoutObstacle = lineCounter - this.obstacles.size();
        
        if (1 == (lineCounterWithoutObstacle % 2)) {
            this.parseLawnMowerPosition(line);
            return this;
            
        }
        
        this.parseLawnMowerMovements(line);
        
        
        return this;
    }
    
    
    protected Reader parseLawnDimension (String line) {
        if (2 != line.length()) {
            return this.fail(Reader.ERROR_MESSAGE_TWO_CHARS);
        }
        
        if (!line.matches("^\\d{2}$")) {
            return this.fail(Reader.ERROR_MESSAGE_TWO_DIGITS);
            
        }
        
        List<String> data = StringHelper.getChars(line);
        
        this.lawn.getDimension().setSize(
            Integer.parseInt(data.get(0), 10),
            Integer.parseInt(data.get(1), 10));
        
        log.debug(
            "init lawn with dimension {}x{}",
            this.lawn.getDimension().width,
            this.lawn.getDimension().height);
        
        return this;
    }
    
    
    protected Reader parseObstacle (String line) {
        if (3 != line.length()) {
            return this.fail(Reader.ERROR_MESSAGE_TWO_CHARS);
        }
        
        
        if (!line.matches("^!\\d{2}$")) {
            return this.fail(Reader.ERROR_MESSAGE_TWO_DIGITS);
            
        }
        
        List<String> data = StringHelper.getChars(line.substring(1));
        
        PositionDesc obstacle = new ObstacleDesc();
        obstacle.getPosition().x = Integer.parseInt(data.get(0), 10);
        obstacle.getPosition().y = Integer.parseInt(data.get(1), 10);
        
        this.obstacles.add(obstacle);
        
        log.debug(
            "add obstacle on {}x{}",
            obstacle.getPosition().x,
            obstacle.getPosition().y);
        
        return this;
    }
    
    protected Reader parseLawnMowerPosition (String line) {
        if (3 != line.length()) {
            return this.fail(Reader.ERROR_MESSAGE_THREE_CHARS);
        }
        
        if (!line.matches("^\\d{2}[NESW]$")) {
            return this.fail(Reader.ERROR_MESSAGE_TWO_DIGITS_AND_ONE_DIRECTION);
            
        }
        
        MovableDesc lmd = this
            .createNewLawnMowerDesc()
            .getCurrentLawnMowerDesc();
        
        List<String> data = StringHelper.getChars(line);
        lmd
            .getPosition()
                .setLocation(
                    Integer.parseInt(data.get(0), 10),
                    Integer.parseInt(data.get(1), 10));
        
        log.debug(
            "init lawn mower with position ({}, {})",
            lmd.getPosition().x,
            lmd.getPosition().y);
        
        lmd.setInFrontOf(data.get(2));
        
        log.debug("turn lawn on {}", lmd.getInFrontOf());
        
        return this;
    }
    
    protected Reader parseLawnMowerMovements (String line) {
        
        if (!line.matches("^[DGA]+$")) {
            return this.fail(Reader.ERROR_MESSAGE_UNVALID_MOVEMENT);
            
        }
        
        log.debug("add actions {} to current lawn mower", line);
        
        MovableDesc lmd = this
            .getCurrentLawnMowerDesc();
        
        for(String movement : StringHelper.getChars(line)) {
            log.trace("add action {} to current lawn mower", movement);
            
            lmd.getMovements().add(movement.substring(0, 1));
        }
        
        
        return this;
    }
    
    protected Reader createNewLawnMowerDesc () {
        this.lawnMowers.add(new LawnMowerDesc());
        this.lastLawnMower = this.lawnMowers.size() - 1;
        
        return this;
    }
    
    protected MovableDesc getCurrentLawnMowerDesc () {
        if (this.lastLawnMower < 0) {
            this.lawnMowers.add(new LawnMowerDesc());
            this.lastLawnMower = this.lawnMowers.size() - 1;
        }
        
        return this.lawnMowers.get(this.lastLawnMower);
    }
    
    protected Reader fail (String message) {
        log.error(message);
        this.fail = true;
        return this;
    }
    
    @Override
    public String getDescriptorFileInfo () {
        return String.format("%s:%s:%s",
            null == this.descriptorPath
                ? "resource"
                : "file",
            this.charset,
            null == this.descriptorPath
                ? this.defaultResourcePath
                : this.descriptorPath
        );
    }
}
