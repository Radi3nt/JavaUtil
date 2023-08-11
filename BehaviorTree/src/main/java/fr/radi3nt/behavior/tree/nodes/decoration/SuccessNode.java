package fr.radi3nt.behavior.tree.nodes.decoration;

import fr.radi3nt.behavior.tree.nodes.BehaviorTreeNode;
import fr.radi3nt.behavior.tree.nodes.NodeStatus;

public class SuccessNode extends DecorationNode {

    public SuccessNode() {
    }

    public SuccessNode(BehaviorTreeNode node) {
        super(node);
    }

    @Override
    public NodeStatus run() {
        child.run();
        return NodeStatus.SUCCESS;
    }

}
