package fr.radi3nt.behavior.tree.nodes.composite.templates;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;
import fr.radi3nt.behavior.tree.nodes.composite.FallbackNode;
import fr.radi3nt.behavior.tree.nodes.types.TreeNode;

import java.util.ArrayList;

/**
 * This BasicSelectorNode returns the first success or running result in its child. If not found, it returns 'NodeStatus.FAILURE'
 */
public class BasicFallbackNode extends BasicCompositeNode implements FallbackNode {

    public BasicFallbackNode() {
        super(new ArrayList<>());
    }

    @Override
    public NodeStatus run() {
        for (TreeNode treeNode : treeNodes) {
            NodeStatus nodeStatus = treeNode.run();
            if (nodeStatus != NodeStatus.FAILURE)
                return nodeStatus;
        }
        return NodeStatus.FAILURE;
    }
}
