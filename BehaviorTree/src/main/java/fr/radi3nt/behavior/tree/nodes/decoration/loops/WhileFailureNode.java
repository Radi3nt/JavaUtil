package fr.radi3nt.behavior.tree.nodes.decoration.loops;

import fr.radi3nt.behavior.tree.nodes.BehaviorTreeNode;
import fr.radi3nt.behavior.tree.nodes.NodeStatus;
import fr.radi3nt.behavior.tree.nodes.decoration.DecorationNode;

public class WhileFailureNode extends DecorationNode {

    public WhileFailureNode(BehaviorTreeNode behaviorTreeNode) {
        super(behaviorTreeNode);
    }

    @Override
    public NodeStatus run() {
        NodeStatus status;
        while (true) {
            if ((status = child.run()) != NodeStatus.FAILURE)
                break;
        }

        return status;
    }
}
