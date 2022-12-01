package fr.radi3nt.behavior.tree.nodes.composite.templates;

import fr.radi3nt.behavior.tree.nodes.BasicNode;
import fr.radi3nt.behavior.tree.nodes.types.CompositeNode;
import fr.radi3nt.behavior.tree.nodes.types.TreeNode;

import java.util.Collection;

public abstract class BasicCompositeNode extends BasicNode implements CompositeNode {

    public BasicCompositeNode(Collection<TreeNode> animationTreeNodes) {
        super(animationTreeNodes);
    }

    @Override
    public void add(TreeNode treeNode) {
        treeNodes.add(treeNode);
    }

    @Override
    public void remove(TreeNode treeNode) {
        treeNodes.remove(treeNode);
    }

    @Override
    public boolean contains(TreeNode treeNode) {
        return treeNodes.contains(treeNode);
    }
}
