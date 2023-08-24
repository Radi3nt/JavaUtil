package fr.radi3nt.ik.solvers.fabrik;

import fr.radi3nt.ik.solvers.IkIterativeSolver;
import fr.radi3nt.ik.solvers.fabrik.chain.IKChain;
import fr.radi3nt.ik.solvers.fabrik.chain.IkJointTransform;
import fr.radi3nt.maths.components.advanced.quaternions.ComponentsQuaternion;
import fr.radi3nt.maths.components.vectors.Vector3f;
import fr.radi3nt.maths.components.vectors.implementations.SimpleVector3f;

public class FABRIKSolver extends IkIterativeSolver {

    private final IKChain chain;
    private IkJointTransform goal = new IkJointTransform(new SimpleVector3f(), ComponentsQuaternion.zero());
    private IkJointTransform start = new IkJointTransform(new SimpleVector3f(), ComponentsQuaternion.zero());

    public FABRIKSolver(int maxIterations, float allowedMarginOfError, IKChain chain) {
        super(maxIterations, allowedMarginOfError);
        this.chain = chain;
    }

    @Override
    public void solve() {
        Vector3f dirToGoal = goal.getResultPosition().duplicate().sub(start.getResultPosition());
        float chainLength = chain.getTotalLength();
        if (dirToGoal.lengthSquared()>chainLength*chainLength)
            extendTowardsGoal();
        else
            super.solve();
    }

    private void extendTowardsGoal() {
        chain.setEndPointAndReturnCalculatedStart(goal, 0);
        IkJointTransform lastTransform = chain.setStartPointAndReturnCalculatedEnd(start, 0);
        for (int i = 1; i < chain.getLinkAmount(); i++) {
            chain.setEndPointAndReturnCalculatedStart(goal, i);
            lastTransform = chain.setStartPointAndReturnCalculatedEnd(lastTransform, i);
        }
    }

    @Override
    protected void iteration() {
        backwardsPass();
        forwardPass();
    }

    private void backwardsPass() {
        IkJointTransform lastTransform = goal;
        for (int i = getEndLinkIndex(); i >= 0; i--) {
            lastTransform = chain.setEndPointAndReturnCalculatedStart(lastTransform, i);
        }
    }

    private void forwardPass() {
        IkJointTransform lastTransform = start;
        for (int i = 0; i < chain.getLinkAmount(); i++) {
            lastTransform = chain.setStartPointAndReturnCalculatedEnd(lastTransform, i);
        }
    }

    @Override
    protected boolean isInAllowedMarginOfError() {
        return evaluateMarginOfError() <= allowedMarginOfError*allowedMarginOfError;
    }

    protected float evaluateMarginOfError() {
        return getTipJointPosition().duplicate().sub(goal.getResultPosition()).lengthSquared();
    }

    private Vector3f getTipJointPosition() {
        return chain.getEndPoint(getEndLinkIndex()).getResultPosition();
    }

    private int getEndLinkIndex() {
        return chain.getLinkAmount() - 1;
    }

    public void setStart(IkJointTransform start) {
        this.start = start;
    }

    public void setGoal(IkJointTransform goal) {
        this.goal = goal;
    }
}
