package fr.radi3nt.behavior.tree.nodes.leaf;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;

public abstract class FailureActionNode implements ActionNode {
    @Override
    public NodeStatus run() {
        run_();
        return NodeStatus.FAILURE;
    }

    protected abstract void run_();
}
