package service.dom.impl;

import service.dom.Element;
import service.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ElementImpl extends NodeImpl implements Element{

    private Map<String, String> attributes;
    private String content;

    public ElementImpl(){};

    public ElementImpl(Integer id, String tagName,
                       Map<String, String> attributes,
                       String content, Element parent, Element prevSibling)
    {
        super.id = id;
        super.name = tagName;
        super.parent = parent;
        super.prevSibling = prevSibling;

        this.attributes = attributes;
        this.content = content;
        DocumentImpl.tempElementMap.put(id, this);

    }

    @Override
    public String getName() {
        return super.name;
    }
    @Override
    public void setName(String name) {
        super.name = name;
    }

// family
    @Override
    public Element getParent() {
        return (Element) super.parent;
    }
    @Override
    public void setParent(Node root) {
        super.setParent(root);
    }
    @Override
    public Element getPrevSibling() {
        return (Element) super.prevSibling;
    }
    @Override
    public void setPrevSibling(Node prevSibling) {
        super.prevSibling = prevSibling;
    }
    @Override
    public Element getNextSibling() {
        return (Element) super.nextSibling;
    }
    @Override
    public void setNextSibling(Node nextSibling) {
        super.nextSibling = nextSibling;
    }




 //children


    public void addChild(Element child) {
        super.addChild(child);
    }
    @Override
    public void setChildren(List<Node> children) {
        super.children = children;
    }
    @Override
    public boolean hasChildren() {
        return super.hasChildren();
    }

    @Override
    public String getAttribute(String name) {
         return attributes.get(name);
    }

    //declared Element interface
    @Override
    public String getContent() {
        return content;
    }
    @Override
    public void setContent(String content) {
        this.content = content;
    }
    @Override
    public Map<String, String> getAttributes() {
        return attributes;
    }
    @Override
    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public List<Element> getChildrenElementsByTagName(String name) {
        List<Element> list = new ArrayList<>();

        for (Node child : super.children){
             if ((child.getName()).equals(name))
                 list.add((Element) child);
        }
        return list;
    }


    @Override
    public String toString() {
        printChilds();
        return "TempElement{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                ", content='" + content + '\'' + "\n" +
                ", parent='" + (parent==null ? null : parent.getName())+"\n" +
                ", prevSibling='" +(prevSibling==null ? null : prevSibling.getName()) +"\n" +
                ", nextSibling='" +(nextSibling==null ? null : nextSibling.getName()) +"\n" +
                '}';
    }
    public void printChilds(){
        System.out.println("childs:  ");
        if (super.children != null)
            for(Node child : super.children){
                System.out.print((child==null ? null : child.getName()) + " ");
            }
        System.out.println();
    }

}
