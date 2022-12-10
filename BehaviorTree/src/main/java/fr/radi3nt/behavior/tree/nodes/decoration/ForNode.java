package fr.radi3nt.behavior.tree.nodes.decoration;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;
import fr.radi3nt.behavior.tree.nodes.TreeNode;

public class ForNode extends DecorationNode {

    private final int limit;

    public ForNode(TreeNode treeNode, int limit) {
        super(treeNode);
        this.limit = limit;
    }

    @Override
    public NodeStatus run() {
        NodeStatus status = NodeStatus.FAILURE;
        for (int i = 0; i < limit; i++) {
            if ((status = treeNode.run()) != NodeStatus.FAILURE)
                break;
        }

        return status;
    }
}
