package fr.radi3nt.behavior.tree.nodes.composite;

import fr.radi3nt.behavior.tree.nodes.BehaviorTreeNode;
import fr.radi3nt.behavior.tree.nodes.NodeStatus;

import java.util.Collection;

public class FallbackNode extends CompositeNode {

    public FallbackNode(Collection<BehaviorTreeNode> nodes) {
        super(nodes);
    }

    public FallbackNode(BehaviorTreeNode... nodes) {
        super(nodes);
    }

    @Override
    public NodeStatus run() {
        for (BehaviorTreeNode behaviorTreeNode : children) {
            NodeStatus nodeStatus = behaviorTreeNode.run();
            if (nodeStatus != NodeStatus.FAILURE)
                return nodeStatus;
        }
        return NodeStatus.FAILURE;
    }
}
