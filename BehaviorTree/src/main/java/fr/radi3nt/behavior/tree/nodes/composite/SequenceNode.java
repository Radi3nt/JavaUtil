package fr.radi3nt.behavior.tree.nodes.composite;

import fr.radi3nt.behavior.tree.nodes.BehaviorTreeNode;
import fr.radi3nt.behavior.tree.nodes.NodeStatus;

import java.util.Collection;

public class SequenceNode extends CompositeNode {

    public SequenceNode(Collection<BehaviorTreeNode> nodes) {
        super(nodes);
    }

    public SequenceNode(BehaviorTreeNode... nodes) {
        super(nodes);
    }

    @Override
    public NodeStatus run() {
        for (BehaviorTreeNode behaviorTreeNode : children) {
            NodeStatus nodeStatus = behaviorTreeNode.run();
            if (nodeStatus != NodeStatus.SUCCESS)
                return nodeStatus;
        }
        return NodeStatus.SUCCESS;
    }

}
