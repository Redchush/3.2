package service.dom.impl;

import service.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Student on 23-May-16.
 */
public abstract class NodeImpl implements Node {
    protected List<Node> children = new ArrayList<>();
    protected Node prevSibling;
    protected Node nextSibling;
    protected Node parent;
    protected String name;

    protected int id;

    @Override
    public String getName() {
        return name;
    }
    @Override
    public void setName(String name) {
        this.name = name;
    }

    protected NodeImpl(NodeImpl parent) {
       this.parent = parent;
    }
    protected NodeImpl(){  }

    @Override
    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<Node> children) {
        this.children = children;
    }
    @Override
    public List<Node> addChild(Node element){
        children.add(element);
        return children;
    }
    @Override
    public List<Node> removeChild(Node element){
        children.remove(element);
        return children;
    }
    @Override
    public Node getPrevSibling() {
        return prevSibling;
    }
    @Override
    public void setPrevSibling(Node prevSibling) {
        this.prevSibling = prevSibling;
    }
    @Override
    public Node getNextSibling() {
        return nextSibling;
    }
    @Override
    public void setNextSibling(Node nextSibling) {
        this.nextSibling = nextSibling;
    }
    @Override
    public Node getParent() {
        return this.parent;
    }
    @Override
    public void setParent(Node root) {
        this.parent = root;
    }
    @Override
    public boolean hasChildren(){
        return !children.isEmpty();
    }

    @Override
    public void setChildren(List<Node> children) {

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public NodeImpl setFamily(NodeImpl root, NodeImpl nextSibling, NodeImpl prevSibling){
        this.parent = root;
        this.nextSibling = nextSibling;
        this.prevSibling = prevSibling;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeImpl node = (NodeImpl) o;

        if (children != null ? !children.equals(node.children) : node.children != null) return false;
        if (prevSibling != null ? !prevSibling.equals(node.prevSibling) : node.prevSibling != null) return false;
        if (nextSibling != null ? !nextSibling.equals(node.nextSibling) : node.nextSibling != null) return false;
        return parent != null ? parent.equals(node.parent) : node.parent == null;

    }

    @Override
    public int hashCode() {
        int result = children != null ? children.hashCode() : 0;
        result = 31 * result + (prevSibling != null ? prevSibling.hashCode() : 0);
        result = 31 * result + (nextSibling != null ? nextSibling.hashCode() : 0);
        result = 31 * result + (parent != null ? parent.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "NodeImpl{" +
                "children=" + children +
                ", prevSibling=" + prevSibling +
                ", nextSibling=" + nextSibling +
                ", parent=" + parent +
                '}';
    }
}
