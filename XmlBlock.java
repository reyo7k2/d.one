import org.w3c.dom.Text;

import java.io.PrintStream;
import java.util.ArrayList;

public class XmlBlock {
    String name;
    ArrayList<String> attributes;
    ArrayList<XmlBlock> children;
    protected XmlBlock() {
        attributes = new ArrayList<String>();
        children = new ArrayList<XmlBlock>();
    }
    XmlBlock(String newName) {
        attributes = new ArrayList<String>();
        children = new ArrayList<XmlBlock>();
        name = newName;
    }
    void addAttribute(String newAttribute) {
        attributes.add(newAttribute);
    }
    void addChild(XmlBlock newChild) {
        children.add(newChild);
    }
    void addChild(String childName) {
        children.add(new NoTextXmlBlock(childName));
    }
    void addChild(String childName, String childText) {
        children.add(new TextXmlBlock(childName, childText));
    }
    void print(PrintStream printStream, String tab) {
        String header = tab + "<" + name;
        for (String attribute : attributes) {
            header += " " + attribute;
        }
        header += ">";
        printStream.println(header);
        for (XmlBlock block : children) {
            block.print(printStream, tab + "  ");
        }
        printStream.println(tab + "</" + name + ">");
    }
}

class TextXmlBlock extends XmlBlock {
    String text;
    TextXmlBlock(String newName, String newText) {
        name = newName;
        text = newText;
    }
    void addChild(XmlBlock newChild) {
    }
    void addChild(String childName, String childText) {
    }
    void print(PrintStream printStream, String tab) {

        String header = tab + "<" + name;
        for (String attribute : attributes) {
            header += " " + attribute;
        }
        header += ">" + text + "</" + name + ">";
        printStream.println(header);
    }
}

class NoTextXmlBlock extends XmlBlock {
    NoTextXmlBlock(String newName) {
        name = newName;
    }
    void addChild(XmlBlock newChild) {
    }
    void addChild(String childName, String childText) {
    }
    void print(PrintStream printStream, String tab) {

        String header = tab + "<" + name;
        for (String attribute : attributes) {
            header += " " + attribute;
        }
        header += "/>";
        printStream.println(header);
    }
}
