package fr.radi3nt.behavior.tree.nodes.leaf;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;

import java.util.function.Supplier;

public class BasicConditionalNode implements ConditionalNode {

    private final Supplier<NodeStatus> supplier;

    public BasicConditionalNode(Supplier<NodeStatus> supplier) {
        this.supplier = supplier;
    }

    @Override
    public NodeStatus run() {
        return supplier.get();
    }
}
