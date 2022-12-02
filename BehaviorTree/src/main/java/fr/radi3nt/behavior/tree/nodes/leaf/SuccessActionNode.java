package fr.radi3nt.behavior.tree.nodes.leaf;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;

public abstract class SuccessActionNode implements ActionNode {
    @Override
    public NodeStatus run() {
        run_();
        return NodeStatus.SUCCESS;
    }

    protected abstract void run_();
}
