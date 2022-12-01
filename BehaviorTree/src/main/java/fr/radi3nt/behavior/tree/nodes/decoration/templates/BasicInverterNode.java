package fr.radi3nt.behavior.tree.nodes.decoration.templates;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;
import fr.radi3nt.behavior.tree.nodes.decoration.InverterNode;
import fr.radi3nt.behavior.tree.nodes.types.TreeNode;

public class BasicInverterNode extends BasicDecorationNode implements InverterNode {

    public BasicInverterNode() {
    }

    public BasicInverterNode(TreeNode treeNode) {
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
