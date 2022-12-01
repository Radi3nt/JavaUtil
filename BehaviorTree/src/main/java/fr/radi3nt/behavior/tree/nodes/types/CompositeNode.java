package fr.radi3nt.behavior.tree.nodes.types;

public interface CompositeNode extends TreeNode {

    void add(TreeNode treeNode);

    void remove(TreeNode treeNode);

    boolean contains(TreeNode treeNode);

}
