import io.github.pitzzahh.util.utilities.FileUtil;
import io.github.pitzzahh.util.utilities.Print;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Arrays;
import java.io.File;

public class LocationsTest {

    @Test
    void shouldAddAllLocationsToSet() throws IOException {
        var fileContents = FileUtil.getFileContents(
                new File(
                        "src/main/resources/io/github/pitzzahh/libraryManagementSystem/locations/locations.txt"
                ),
                0,
                ","
        );
        fileContents.stream()
                .map(Arrays::toString)
                .map(e -> e.replaceAll("[\\[\\]]", ""))
                .forEach(Print::println);
    }
}








