package fr.radi3nt.behavior.tree.nodes.composite.templates;

import fr.radi3nt.behavior.tree.nodes.NodeStatus;
import fr.radi3nt.behavior.tree.nodes.composite.SequenceNode;
import fr.radi3nt.behavior.tree.nodes.types.TreeNode;

import java.util.ArrayList;

/**
 * This BasicSelectorNode returns the first failure or running result in its child. If not found, it returns 'NodeStatus.SUCCESS'
 */
public class BasicSequenceNode extends BasicCompositeNode implements SequenceNode {

    public BasicSequenceNode() {
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
