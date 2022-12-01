package fr.radi3nt.behavior.tree.nodes.decoration;

import fr.radi3nt.behavior.tree.nodes.TreeNode;

public abstract class DecorationNode implements TreeNode {

    protected TreeNode treeNode;

    public DecorationNode() {
    }

    public DecorationNode(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    public void set(TreeNode treeNode) {
        this.treeNode = treeNode;
    }

    public TreeNode get() {
        return treeNode;
    }

}
