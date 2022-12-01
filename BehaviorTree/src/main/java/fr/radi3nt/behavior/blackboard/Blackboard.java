package fr.radi3nt.behavior.blackboard;

public interface Blackboard {

    <T> void put(BlackboardVariableId<T> blackboardVariableValue, T value);

    <T> T get(BlackboardVariableId<T> type);

}
