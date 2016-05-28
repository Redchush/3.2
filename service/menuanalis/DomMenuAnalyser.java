package service.menuanalis;


import domain.model.Menu;
import domain.model.MenuCategory;
import domain.model.menu_items.MenuItemBuilder;
import domain.model.menu_items.MenuItemComponent;
import service.dom.Element;
import service.dom.Node;

import java.util.ArrayList;
import java.util.List;


public class DomMenuAnalyser {

    public static Menu buildMenu(Element element){

        List<Element> list = element.getChildrenElementsByTagName(DocumentConstants.CATEGORY);
//        System.out.println(list + DocumentConstants.CATEGORY);
//
        List<MenuCategory> menuCategoris = parceCategoryList(list);
        String name  = element.getAttribute(DocumentConstants.NAME);
        String version = element.getAttribute(DocumentConstants.VERSION);
        return  MenuItemBuilder.createMenu(menuCategoris, name, version);
    }

    public static List<MenuCategory> parceCategoryList(List<Element> list){
       List<MenuCategory> menuCategoris = new ArrayList<>();
        for (Element element : list){
            MenuCategory category = parceCategory(element);
            menuCategoris.add(category);
        }
        return menuCategoris;

    }

    private static MenuCategory parceCategory(Element node){
        List<MenuItemComponent> componentList = new ArrayList<>();
        String title = "";
        List<Node> children = node.getChildren();
        for ( Node child : children) {
            MenuItemComponent component;
            Element item = (Element) child;
            switch (child.getName()){
                case DocumentConstants.TITLE :
                    title = item.getContent();
                    break;
                case DocumentConstants.SIMPLE_MENU_ITEM  :
                    MenuItemAnalyser simpleBuilder = new MenuItemAnalyser(item);
                    component = simpleBuilder.buildSimpleMenuItem();
                    componentList.add(component);
                    break;
                case DocumentConstants.COMPLEX_MENU_ITEM :
                    MenuItemAnalyser complexBuilder = new MenuItemAnalyser(item);
                    component =  complexBuilder.buildComplexMenuItem();
                    componentList.add(component);
                    break;
            }
        }
        System.out.println("COMPONENTLIST" + node.getName() + " " + componentList);
        MenuCategory menuCategory = MenuItemBuilder.createMenuCategory(title, componentList);
        return menuCategory;

    }



}
