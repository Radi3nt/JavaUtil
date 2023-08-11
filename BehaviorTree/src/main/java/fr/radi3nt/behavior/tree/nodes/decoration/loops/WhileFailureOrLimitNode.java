package fr.radi3nt.behavior.tree.nodes.decoration.loops;

import fr.radi3nt.behavior.tree.nodes.BehaviorTreeNode;
import fr.radi3nt.behavior.tree.nodes.NodeStatus;
import fr.radi3nt.behavior.tree.nodes.decoration.DecorationNode;

public class WhileFailureOrLimitNode extends DecorationNode {

    private final int limit;

    public WhileFailureOrLimitNode(BehaviorTreeNode node, int limit) {
        super(node);
        this.limit = limit;
    }

    @Override
    public NodeStatus run() {
        NodeStatus status = NodeStatus.FAILURE;
        for (int i = 0; i < limit; i++) {
            if ((status = child.run()) != NodeStatus.FAILURE)
                break;
        }

        return status;
    }
}
