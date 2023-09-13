package fr.radi3nt.pathfinding.path.optimisation;

import fr.radi3nt.pathfinding.node.PathNode;

import java.util.List;

public interface PathOptimiser {

    List<PathNode> optimise(List<PathNode> pathNodes);

}
