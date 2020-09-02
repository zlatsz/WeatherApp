package weather.config;

import java.io.IOException;
import java.util.List;

public interface FileUtil {

    List<String> readFileData(String filePath) throws IOException;

    void write(String content, String filePath)  throws IOException;
}
