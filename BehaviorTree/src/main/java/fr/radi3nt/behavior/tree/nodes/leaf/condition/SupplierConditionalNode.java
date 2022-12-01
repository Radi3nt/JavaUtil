package fr.radi3nt.behavior.tree.nodes.leaf.condition;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;

import java.util.function.Supplier;

public class SupplierConditionalNode implements ConditionalNode {

    private final Supplier<NodeStatus> supplier;

    public SupplierConditionalNode(Supplier<NodeStatus> supplier) {
        this.supplier = supplier;
    }

    @Override
    public NodeStatus run() {
        return supplier.get();
    }
}
