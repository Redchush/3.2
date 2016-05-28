package service.dom.impl;

import service.dom.Document;
import service.dom.Element;
import service.dom.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


class DocumentImpl extends NodeImpl implements Document{

    protected static TreeMap<Integer, ElementImpl> tempElementMap = new TreeMap<>();

    private Element rootElement;
    private Map<String, String> attributes;

    private static DocumentImpl instance;
    protected DocumentImpl(){};
    public static DocumentImpl getInstance() {
        if (instance == null)
        {
            instance = new DocumentImpl();
        }
        return instance;
    }

    public DocumentImpl(Map<String, String> attributes) {
        this.attributes = attributes;
        super.setParent(null);
        super.setNextSibling(null);
        super.setPrevSibling(null);
    }

    public void setRootElement(Element rootElement) {
        this.rootElement = rootElement;
    }

    @Override
    public void setChildren(List<Node> children) {
        super.setChildren(children);
    }

    @Override
    public void setParent(Node root) {

    }

    @Override
    public String getAttribute(String name) {
        return null;
    }


    @Override
    public void setRootElement(Node element) {
         if (element == null) rootElement = tempElementMap.firstEntry().getValue();
    }
    @Override
    public Element getRootElement() {
        return rootElement;
    }

    @Override
    public List<Element> getElementsByTagName(String name) {
        List<Element> elements = new ArrayList<>();
        for (ElementImpl element : tempElementMap.values()){
            if (element.getName().equals(name)) elements.add(element);
        }
        return elements;
    }


    protected Map<Integer, ElementImpl> getTempElementMap() {
        return tempElementMap;
    }
}
