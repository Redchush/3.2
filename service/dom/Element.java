package service.dom;

import java.util.List;
import java.util.Map;

/**
 * Created by user on 28.05.2016.
 */
public interface Element extends Node{
    public String getContent();
    public void setContent(String content);
    public Map<String, String> getAttributes();
    public void setAttributes(Map<String, String> attributes);
    public List<Element> getChildrenElementsByTagName(String name);
}
