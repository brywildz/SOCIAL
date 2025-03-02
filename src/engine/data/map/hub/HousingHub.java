package engine.data.map.hub;

import engine.data.map.InfrastructureRepository;

public class HousingHub extends Hub {
    public HousingHub(int line, int column) {
        super(line, column, InfrastructureRepository.getInstance().getHousingHub());
    }

}
