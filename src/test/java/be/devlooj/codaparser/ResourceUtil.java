package be.devlooj.codaparser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceUtil {

    public static List<String> readLinesFromResource(final Class<?> clazz, final String resource) {
        List<String> result = new ArrayList<>();

        try (InputStream inputStream = getResourceFromClassPackage(clazz, resource); BufferedReader r = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = r.readLine()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            result = null;
        }
        return result;
    }

    private static InputStream getResourceFromClassPackage(Class<?> clazz, String fileName) {
        return clazz.getClassLoader().getResourceAsStream(fileName);
    }
}
