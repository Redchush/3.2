package service.parser;

import service.dom.Document;

/**
 * Created by user on 26.05.2016.
 */
public interface DomParser {
    Document getDocument();
    void parseFully(String path);

}
