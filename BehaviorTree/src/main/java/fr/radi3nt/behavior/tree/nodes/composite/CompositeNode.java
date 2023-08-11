package fr.radi3nt.behavior.tree.nodes.composite;

import fr.radi3nt.behavior.tree.nodes.BehaviorTreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public abstract class CompositeNode implements BehaviorTreeNode {

    protected final Collection<BehaviorTreeNode> children;

    public CompositeNode(Collection<BehaviorTreeNode> children) {
        this.children = children;
    }

    public CompositeNode(BehaviorTreeNode... children) {
        this(new ArrayList<>(Arrays.asList(children)));
    }

    public void add(BehaviorTreeNode behaviorTreeNode) {
        children.add(behaviorTreeNode);
    }

    public void remove(BehaviorTreeNode behaviorTreeNode) {
        children.remove(behaviorTreeNode);
    }

    public boolean contains(BehaviorTreeNode behaviorTreeNode) {
        return children.contains(behaviorTreeNode);
    }
}
