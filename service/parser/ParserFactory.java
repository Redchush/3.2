package service.parser;

import service.parser.heavy.HeavyDomParser;

/**
 * Created by user on 26.05.2016.
 */
public class ParserFactory {
//    public static final int INDI = 1;
//    public static final int EASY = 2;
    public static final int HEAVY = 3;

    private static ParserFactory instance;
    private ParserFactory(){};

    public static ParserFactory getFactory() {
        if (instance == null)
        {
            instance = new ParserFactory();
        }
        return instance;
    }
    public DomParser getParser(int type){
        switch (type){
//            case INDI: return new EasyDomParser();
//            case EASY: return new IndiDomParser();
            case HEAVY: return new HeavyDomParser();

        }
        return null;
    }
}
