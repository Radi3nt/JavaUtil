package fr.radi3nt.behavior.tree.nodes.decoration;

import fr.radi3nt.behavior.tree.nodes.BehaviorTreeNode;
import fr.radi3nt.behavior.tree.nodes.NodeStatus;

public class InverterNode extends DecorationNode {

    public InverterNode(BehaviorTreeNode node) {
        super(node);
    }

    @Override
    public NodeStatus run() {
        NodeStatus nodeStatus = child.run();
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
