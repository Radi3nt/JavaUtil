package fr.radi3nt.behavior.tree.nodes.decoration;

import fr.radi3nt.behavior.tree.nodes.BehaviorTreeNode;

public abstract class DecorationNode implements BehaviorTreeNode {

    protected BehaviorTreeNode child;

    public DecorationNode() {
    }

    public DecorationNode(BehaviorTreeNode child) {
        this.child = child;
    }

    public void set(BehaviorTreeNode behaviorTreeNode) {
        this.child = behaviorTreeNode;
    }

}
