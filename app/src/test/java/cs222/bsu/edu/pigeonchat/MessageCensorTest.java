package cs222.bsu.edu.pigeonchat;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class MessageCensorTest {

    @Test
    public void testParse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        //Document document = builder.parse(new FileInputStream("/Users/Joel/Desktop/PigeonChat/app/src/main/res/bad_wordss.xml"));
        Document document = builder.parse("src/main/res/xml/bad_words.xml");

        NodeList words = document.getElementsByTagName("word");
        Element firstWord = (Element)words.item(0);

        Assert.assertEquals("anal", firstWord.getTextContent());



    }
/**
    @Test
    public void testCensor() throws SAXException, ParserConfigurationException, IOException, TransformerException {
        MessageCensor censor = new MessageCensor();
        System.out.println(censor.checkMessage("f u c k"));
        //Assert.assertEquals("*********** **** is my name joel", censor.checkMessage("fudgepacker cunt is my name joel"));
    }
    **/
}
