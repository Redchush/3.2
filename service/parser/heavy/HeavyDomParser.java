package service.parser.heavy;

import dao.DaoFactory;
import dao.IDao;
import service.dom.Document;
import service.dom.impl.DocumentFactory;
import service.parser.DomParser;

/**
 * Created by user on 26.05.2016.
 */
public class HeavyDomParser implements DomParser {

    private String source;
    private static final DocumentFactory FACTORY = DocumentFactory.getFactory();
    private final Document document = FACTORY.getNewDocument();


    @Override
    public Document getDocument() {
        return document;
    }

    @Override
    public void parseFully(String path) {
        DaoFactory factory = DaoFactory.getInstance();
        IDao inputHelper = factory.getDAO(path);
        source = inputHelper.readSource();
        System.out.println(source);
        buildDom();
    }

    private void buildDom(){
        source = DomBuilder.clipMeta(source);
        DomBuilder.buildElementTree(source);
        document.setRootElement(null);
    }
}
