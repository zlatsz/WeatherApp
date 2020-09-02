package weather.service;

import weather.entity.Location;
import weather.entity.LocationBindingModel;

import java.io.IOException;

public interface LocationService {

    boolean areImported();

    String readFileContent() throws IOException;

    void importJson() throws IOException;

    void add(LocationBindingModel locationBindingModel);

    Location findLast();

}
