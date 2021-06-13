package ru.durnov.imageconverter;

import org.freehep.graphicsio.svg.SVGGraphics2D;

import java.util.Properties;

public class SVGConverterProperties {
    public Properties properties(){
        Properties properties = new Properties();
        properties.setProperty(SVGGraphics2D.TEXT_AS_SHAPES, "true");
        properties.setProperty(SVGGraphics2D.CLIP, "false");
        return properties;
    }
}
