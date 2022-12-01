package fr.radi3nt.behavior.tree.nodes.types;

import fr.radi3nt.behavior.tree.nodes.TreeNode;

import java.util.Collection;

public abstract class CollectionNode implements TreeNode {

    protected final Collection<TreeNode> treeNodes;

    public CollectionNode(Collection<TreeNode> animationTreeNodes) {
        this.treeNodes = animationTreeNodes;
    }
}
