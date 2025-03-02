package engine.data.map.hub;

import engine.data.map.Infrastructure;
import engine.data.map.InfrastructureRepository;

import java.util.HashMap;

public class WorkHub extends Hub {
    public WorkHub(int line, int column, HashMap<String, Infrastructure> infrastructures) {
        super(line, column, InfrastructureRepository.getInstance().getWorkHub());
    }
}
