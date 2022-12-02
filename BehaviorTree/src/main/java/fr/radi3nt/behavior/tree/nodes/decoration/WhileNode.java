package fr.radi3nt.behavior.tree.nodes.decoration;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;
import fr.radi3nt.behavior.tree.nodes.TreeNode;

public class WhileNode extends DecorationNode {

    public WhileNode(TreeNode treeNode) {
        super(treeNode);
    }

    @Override
    public NodeStatus run() {
        NodeStatus status;
        while (true) {
            if ((status = treeNode.run()) != NodeStatus.FAILURE)
                break;
        }

        return status;
    }
}
