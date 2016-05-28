package service.dom;

import java.util.List;

/**
 * Created by user on 28.05.2016.
 */
public interface Node {

    public String getName() ;
    public void setName(String name);

    public List<Node> getChildren();
    public void setChildren(List<Node> children);

    public List<Node> addChild(Node element);
    public List<Node> removeChild(Node element);

    public Node getPrevSibling();
    public void setPrevSibling(Node prevSibling);

    public Node getNextSibling();
    public void setNextSibling(Node nextSibling);

    public Node getParent();
    public void setParent(Node root);

    public boolean hasChildren();

    public String getAttribute(String name);
}
