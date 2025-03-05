package engine.data.map.hub;

import config.GameConfiguration;
import engine.data.map.Block;
import engine.data.map.Infrastructure;
import engine.data.map.InfrastructureRepository;

import java.util.HashMap;

public class WorkHub extends Hub {
    public WorkHub(int line, int column, HashMap<String, Infrastructure> infrastructures) {
        super(line, column, InfrastructureRepository.getInstance().getWorkHub());
        for (int x = 0; x < GameConfiguration.WORK_HUB_X; x++) {
            for (int y = 0; y < GameConfiguration.WORK_HUB_Y; y++) {
                super.blocks[x][y] = new Block(x,y);
            }
        }
    }
}
