package UI.Tools;

import org.junit.Test;

import static org.junit.Assert.*;

public class UIComponentXMLParserTest {

    @Test
    public void parser() {
        UIComponentXMLParser uiComponentXMLParser=new UIComponentXMLParser();
        uiComponentXMLParser.parser("/Users/yangzhen/StudioProjects/VisualizationPart/app/src/main/java/UI/SimpleProperties.xml");
        uiComponentXMLParser.getParameterNumber("(#,null)");
    }
}