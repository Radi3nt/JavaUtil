package fr.radi3nt.behavior.tree.nodes.decoration;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;
import fr.radi3nt.behavior.tree.nodes.TreeNode;

public class SuccessNode extends DecorationNode {

    public SuccessNode() {
    }

    public SuccessNode(TreeNode treeNode) {
        super(treeNode);
    }

    @Override
    public NodeStatus run() {
        treeNode.run();
        return NodeStatus.SUCCESS;
    }

}
