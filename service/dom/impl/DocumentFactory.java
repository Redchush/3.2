package service.dom.impl;

import service.dom.Document;
import service.dom.Element;

import java.util.Map;

/**
 * Created by user on 28.05.2016.
 */
public class DocumentFactory {
    public static final int HEAVY = 3;

    private static DocumentFactory instance;
    private DocumentImpl document = new DocumentImpl();
    private DocumentFactory(){};

    public static DocumentFactory getFactory() {
        if (instance == null)
        {
            instance = new DocumentFactory();
        }
        return instance;
    }
    public Document getNewDocument(){
       return document;
    }
    public Element createNewElement(Integer id, String tagName,
                                    Map<String, String> attributes,
                                    String content, Element parent, Element prevSibling){
        return new ElementImpl( id, tagName, attributes, content, parent, prevSibling) ;
    }

    public void checkDocument(){
        for (Map.Entry<Integer, ElementImpl> elementEntry : document.getTempElementMap().entrySet() ){
            System.out.println(elementEntry.getKey() + " " + elementEntry.getValue());
            System.out.println();
        }

    }
}
