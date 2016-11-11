package cs222.bsu.edu.pigeonchat;

import org.junit.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class MessageCensor {
    private static ArrayList<String> badWords = new ArrayList<>();

    public String checkMessage(String message) throws SAXException, TransformerException, ParserConfigurationException, IOException {
        ArrayList<String> messageList = parseStringToArray(message);
        parse();
        //ArrayList<String> bad_words = new ArrayList<>(Arrays.asList("shit", "piss", "fuck", "cunts", "trucks"));
        String censoredMessage = "";
        for (String word : messageList) {
            censoredMessage += " ";
            if (badWords.contains(word)) {
                censoredMessage += starify(word.length());
            } else {
                censoredMessage += word;
            }
        }
        return censoredMessage.substring(1);
    }

    private String starify(int length) {
        String censored = "";
        for(int i=0;i<length;i++){
            censored += "*";
        }
        System.out.println(censored);
        return censored;
    }

    public ArrayList parseStringToArray(String message){
        ArrayList<String> splitList = new ArrayList<>(Arrays.asList(message.split(" ")));
        return splitList;
    }

    public static void parse() throws SAXException, IOException,
            ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new FileInputStream("/Users/Joel/Desktop/PigeonChat/app/src/main/res/bad_words.xmll"));

        NodeList words = document.getElementsByTagName("word");
        for (int i = 0; i < words.getLength(); i++) {
            Node node = words.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                badWords.add(node.getTextContent());
            }
        }
    }
    /**
    public void testParse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new FileInputStream("/Users/Joel/Desktop/PigeonChat/app/src/main/res/bad_wordss.xml"));

        NodeList words = document.getElementsByTagName("word");
        Element firstWord = (Element)words.item(0);

        Assert.assertEquals("anal", firstWord.getTextContent());



    }
     **/

}
