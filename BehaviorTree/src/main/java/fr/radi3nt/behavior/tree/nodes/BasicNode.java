package fr.radi3nt.behavior.tree.nodes;

import fr.radi3nt.behavior.tree.nodes.types.TreeNode;

import java.util.Collection;

public abstract class BasicNode implements TreeNode {

    protected final Collection<TreeNode> treeNodes;

    public BasicNode(Collection<TreeNode> animationTreeNodes) {
        this.treeNodes = animationTreeNodes;
    }
}
