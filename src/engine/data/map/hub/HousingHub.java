package engine.data.map.hub;

import config.GameConfiguration;
import engine.data.map.Block;
import engine.data.map.InfrastructureRepository;

public class HousingHub extends Hub {
    public HousingHub(int line, int column) {
        super(line, column, InfrastructureRepository.getInstance().getHousingHub());
        for (int x = 0; x < GameConfiguration.HOUSING_HUB_X; x++) {
            for (int y = 0; y < GameConfiguration.HOUSING_HUB_Y; y++) {
                super.blocks[x][y] = new Block(x,y);
            }
        }

    }
}
