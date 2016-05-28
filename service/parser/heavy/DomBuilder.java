package service.parser.heavy;

import service.dom.Element;
import service.dom.impl.DocumentFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by user on 26.05.2016.
 */
class DomBuilder {
    private static final DocumentFactory FACTORY = DocumentFactory.getFactory();

    private static final String xmlEnterString = "<\\?xml(.*)?\\?>";
    private static final String META_BEGIN = "<?xml";
    private static final String META_END = "?>";

    /**
     *   String common = "<([^/]{1}[^>]+)[\\s]?([^> ]*)((>(.+?)</\\1>)|( />))";
     *   String group1= "<([^/]{1}[^> ]+)";
     *   String group2="[\\s]?([^>]*)";
     *   String group3_4= "(>(.+?)</\\1>)";
     *   String group4= "( />)";
     *   Pattern pattern = Pattern.compile(group1+group2+ "(" + group3 + "|" + group4 + ")")
     */
    private static final Pattern TAG_PATTERN = Pattern.compile("<([^/]{1}[^> ]+)[\\s]?([^>]*)((>(.+?)</\\1>)|( />))");

    protected static final Pattern XML_META = Pattern.compile(xmlEnterString);

    protected static String defineMeta(String source){
        return source.replace(META_BEGIN, "")
                .replace(META_END, "");
    }

    protected static String clipMeta(String bounds){
        return bounds.substring(bounds.indexOf(META_END));
    }

    protected static void buildElementTree(String source){
        findAllTags(source, source, null);
     //   FACTORY.checkDocument();
    }
    /**
     *   matcher.group(1) //tagname
     *  matcher.group(2)  //attibutes
     *  matcher.group(3)  //works,  the /> indicates simpleTag
     *  matcher.group(4) //works
     *  matcher.group(5)); //content
     */

    protected static void findAllTags(String source,
                                      String initialSource,
                                      Element parent){

        Integer id;
        String tagName;
        String content;

        Matcher matcher = TAG_PATTERN.matcher(source);
        int indexToStart = initialSource.indexOf(source);
        Element prevSibling = null;
        Element nextSibling = null;
        Map<String, String> attributes = new HashMap<>();
        while (matcher.find()) {
            String element = matcher.group();
            tagName = matcher.group(1);
            content = matcher.group(5);
            id = initialSource.indexOf(matcher.group(), indexToStart);
            String temp = matcher.group(2);
            if (temp.length() > 1){
                parseAttributes(temp, attributes);
            }
            Element current =  FACTORY.createNewElement(id, tagName,
                                                        attributes, content,
                                                        parent, prevSibling);

            if (parent != null) parent.addChild(current);
            if (prevSibling != null) prevSibling.setNextSibling(current);

            prevSibling = current;
            if (element.contains("<")){
                findAllTags(element.substring(1).trim(), initialSource, current);
            }
        }
    }
       private static void parseAttributes(String bounds, Map<String, String> attributeMap){
       // System.out.println("in parseAttr " + bounds + "\n" );
        String[] attr = bounds.split("\" ");
        String[] conrete;
        if (attr.length == 0)
            attr = bounds.split("=");
        for (String elem : attr){
             conrete = elem.split("=");
             attributeMap.put(conrete[0], conrete[1].replace("\"", ""));
        }
    }

    //    static  int counter = 0;
//    private static void showChilds(  List<TempDom.TempElement> childs){
//        System.out.println(counter++);
//        for (TempDom.TempElement el : childs){
//            System.out.println(el.getName());
//        }
//        System.out.println();
//    }
}
