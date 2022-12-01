package fr.radi3nt.behavior.tree.nodes.decoration.templates;

import fr.radi3nt.behavior.tree.nodes.types.DecorationNode;
import fr.radi3nt.behavior.tree.nodes.types.TreeNode;

public abstract class BasicDecorationNode implements DecorationNode {

    protected TreeNode treeNode;

    public BasicDecorationNode() {
    }

    public BasicDecorationNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    @Override
    public void set(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    @Override
    public TreeNode get() {
        return treeNode;
    }

}
