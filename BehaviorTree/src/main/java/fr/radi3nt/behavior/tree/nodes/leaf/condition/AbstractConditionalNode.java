package fr.radi3nt.behavior.tree.nodes.leaf.condition;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;

public abstract class AbstractConditionalNode implements ConditionalNode {

    @Override
    public NodeStatus run() {
        return isConditionMet() ? NodeStatus.SUCCESS : NodeStatus.FAILURE;
    }

    protected abstract boolean isConditionMet();
}
