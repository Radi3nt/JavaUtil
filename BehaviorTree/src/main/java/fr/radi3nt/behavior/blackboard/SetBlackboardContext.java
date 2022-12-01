package fr.radi3nt.behavior.blackboard;

public class SetBlackboardContext implements BlackboardContext {

    private Blackboard blackboard;

    @Override
    public Blackboard getBlackboard() {
        return blackboard;
    }

    public void setBlackboard(Blackboard blackboard) {
        this.blackboard = blackboard;
    }
}
