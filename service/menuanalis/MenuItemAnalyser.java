package service.menuanalis;


import domain.model.menu_items.MenuItemBuilder;
import domain.model.menu_items.MenuItemComponent;
import service.dom.Element;
import service.dom.Node;

import java.util.*;


public class MenuItemAnalyser {
    private Element element;
    private final static String DEFAULT = "default";

    public MenuItemAnalyser(Element item) {
       this.element = item;
    }

    public MenuItemComponent buildSimpleMenuItem(){
        //        System.out.println(this.element.getTextContent());
//        System.out.println(this.element.getNodeName());
        String price ="";
        String name = "";
        String description ="";
        String foto = "";
        String ratioUnit ="";
        List<String> ratio = new ArrayList<>();


       for (Node node : element.getChildren()){

          Element item = (Element) node;
          switch (item.getName()){
              case DocumentConstants.NAME :
                  name = item.getContent();
                  break;
              case DocumentConstants.DESCRIPTION :
                  description = item.getContent();
                  break;
              case DocumentConstants.FOTO :
                  foto = item.getAttribute(DocumentConstants.PATH);
                  break;
              case DocumentConstants.PRICE :
                  price = item.getContent();
                  break;
              case DocumentConstants.RATIO :
                  ratioUnit = item.getAttribute(DocumentConstants.UNIT);
                  ratio = Arrays.asList(item.getContent().split(" "));

          }
       }


//       System.out.println("name: " + name
//        + ", foto: " + foto
//        + ", desc: " + description
//        + ", price " + price
//        + ", ratio " + ratio
//        + ", ratioUnit " + ratioUnit
//       );
        return MenuItemBuilder.createSimpleMenuComponent(name,foto,description,price,ratio,ratioUnit);
    }
//    public static MenuItemComponent createSimpleMenuComponent(String name, String foto, String description, String price,
//                                                              List<String> ratio, String ratioUnit) {
    
    public MenuItemComponent buildComplexMenuItem(){
        String price ="";
        String name = "";
        String description ="";
        String foto = "";
        String ratioUnit ="";
        List<String> ratio = new ArrayList<>();
        Map<String, Map<String, String>> mapOfSubElements = new HashMap<>();
        int counter = 0;

        for (Node node : element.getChildren()){
            Element item = (Element) node;
            switch (item.getName()){
                case DocumentConstants.NAME :
                    name = item.getContent();
                    break;
                case DocumentConstants.DESCRIPTION :
                    mapOfSubElements =  getComplexContentOfElement(item, mapOfSubElements, counter++ );
                    break;
                case DocumentConstants.FOTO :
                    foto = item.getAttribute(DocumentConstants.PATH);
                    break;
                case DocumentConstants.PRICE :
                    mapOfSubElements =  getComplexContentOfElement(item,mapOfSubElements, counter++ );
                    break;
                case DocumentConstants.RATIO :
                    ratioUnit = item.getAttribute(DocumentConstants.UNIT);
                    ratio = Arrays.asList(item.getContent().split(" "));

            }
        }
       return MenuItemBuilder.createComplexMenuComponent(name, foto, mapOfSubElements, ratio, ratioUnit);


        /*System.out.println("name: " + name
                + ", foto: " + foto
                + ", desc: " + mapOfSubElements
                + ", ratio " + ratio
                + ", ratioUnit " + ratioUnit
        );*/

    }




    private Map<String, Map<String, String>> getComplexContentOfElement(Element type, Map<String, Map<String, String>> map,
                                                                        int propertyNumber){

        String elName = type.getName();
        for (Node element : type.getChildren()){
            Element item = (Element) element;
            Map<String, String> innerMap = new HashMap<>();
            innerMap.put(elName, item.getContent());
            if (!map.containsKey(item.getAttribute(DocumentConstants.VARIANT_NUMBER)))
                map.put(item.getAttribute(DocumentConstants.VARIANT_NUMBER), innerMap);
            else {
                map.get(item.getAttribute(DocumentConstants.VARIANT_NUMBER)).put(elName, item.getContent());
            }
        }


        return map;
        //     System.out.println(map.containsKey(item.getAttribute("variantNumber")));

        //  System.out.println(childrens.item(i) + " " + innerMap);
    }
    //    System.out.println(map);      return map;
}


   

