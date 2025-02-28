package engine.data.carte.hub;

import engine.data.carte.InfrastructureRepository;

public class HousingHub extends Hub {
    public HousingHub(int line, int column) {
        super(line, column, InfrastructureRepository.getInstance().getHousingHub());
    }

}
