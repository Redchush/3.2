package service.dom;

import java.util.List;

/**
 * Created by user on 23.05.2016.
 */
public interface Document{
     void setRootElement(Node element);
     Element getRootElement();
     List<Element> getElementsByTagName(String name);

}
