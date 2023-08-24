package fr.radi3nt.ik.solvers.fabrik.chain;

public interface IKChain {

    IkJointTransform setEndPointAndReturnCalculatedStart(IkJointTransform transform, int index);
    IkJointTransform setStartPointAndReturnCalculatedEnd(IkJointTransform transform, int index);
    IkJointTransform getEndPoint(int index);
    IkJointTransform getStartPoint(int index);
    float getTotalLength();
    int getLinkAmount();

}
