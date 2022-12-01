package fr.radi3nt.behavior.tree.nodes.composite;

import fr.radi3nt.behavior.tree.nodes.TreeNode;
import fr.radi3nt.behavior.tree.nodes.types.CollectionNode;

import java.util.Collection;

public abstract class CompositeNode extends CollectionNode {

    public CompositeNode(Collection<TreeNode> animationTreeNodes) {
        super(animationTreeNodes);
    }

    public void add(TreeNode treeNode) {
        treeNodes.add(treeNode);
    }

    public void remove(TreeNode treeNode) {
        treeNodes.remove(treeNode);
    }

    public boolean contains(TreeNode treeNode) {
        return treeNodes.contains(treeNode);
    }
}
