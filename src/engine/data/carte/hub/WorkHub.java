package engine.data.carte.hub;

import engine.data.carte.Infrastructure;
import engine.data.carte.InfrastructureRepository;

import java.util.HashMap;

public class WorkHub extends Hub {
    public WorkHub(int line, int column, HashMap<String, Infrastructure> infrastructures) {
        super(line, column, InfrastructureRepository.getInstance().getWorkHub());
    }
}
