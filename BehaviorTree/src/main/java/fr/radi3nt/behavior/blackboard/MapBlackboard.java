package fr.radi3nt.behavior.blackboard;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapBlackboard implements Blackboard {

    private final Map<BlackboardVariableId<?>, Object> infoMap = new ConcurrentHashMap<>();

    @Override
    public <T> void put(BlackboardVariableId<T> blackboardVariableValue, T value) {
        infoMap.put(blackboardVariableValue, value);
    }

    @Override
    public <T> T get(BlackboardVariableId<T> type) {
        return (T) infoMap.get(type);
    }
}
