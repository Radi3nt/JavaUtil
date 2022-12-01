package fr.radi3nt.behavior.tree.nodes.composite;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;
import fr.radi3nt.behavior.tree.nodes.TreeNode;

import java.util.ArrayList;

/**
 * This BasicSelectorNode returns the first failure or running result in its child. If not found, it returns 'NodeStatus.SUCCESS'
 */
public class SequenceNode extends CompositeNode {

    public SequenceNode() {
        super(new ArrayList<>());
    }

    @Override
    public NodeStatus run() {
        for (TreeNode treeNode : treeNodes) {
            NodeStatus nodeStatus = treeNode.run();
            if (nodeStatus != NodeStatus.SUCCESS)
                return nodeStatus;
        }
        return NodeStatus.SUCCESS;
    }

}
