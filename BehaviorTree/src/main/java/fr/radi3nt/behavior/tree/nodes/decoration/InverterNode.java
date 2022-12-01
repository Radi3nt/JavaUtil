package fr.radi3nt.behavior.tree.nodes.decoration;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;
import fr.radi3nt.behavior.tree.nodes.TreeNode;

public class InverterNode extends DecorationNode {

    public InverterNode() {
    }

    public InverterNode(TreeNode treeNode) {
        super(treeNode);
    }

    @Override
    public NodeStatus run() {
        NodeStatus nodeStatus = treeNode.run();
        switch (nodeStatus) {
            case SUCCESS:
                return NodeStatus.FAILURE;
            case FAILURE:
                return NodeStatus.SUCCESS;
            default:
                return nodeStatus;
        }
    }

}
