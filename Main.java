import domain.model.Menu;
import service.dom.Document;
import service.dom.Element;
import service.menuanalis.DomMenuAnalyser;
import service.parser.DomParser;
import service.parser.ParserFactory;


/**
 * Created by user on 26.05.2016.
 */
public class Main {
    public static final String PATH = "src\\source\\xml\\Menu_XML.xml";

    public static void main(String[] args) {

        DomParser parser = ParserFactory.getFactory().getParser(ParserFactory.HEAVY);
        parser.parseFully(PATH);
        Document document = parser.getDocument();
        Element root = document.getRootElement();
        Menu menu = DomMenuAnalyser.buildMenu(root);

    }
}


