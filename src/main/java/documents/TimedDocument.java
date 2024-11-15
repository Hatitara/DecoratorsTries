package documents;

import lombok.AllArgsConstructor;

import java.time.Duration;
import java.time.LocalTime;

@AllArgsConstructor
public class TimedDocument implements Document {

    private Document document;

    @Override
    public String parse() throws InterruptedException {
        LocalTime startTime = LocalTime.now();
        String result = document.parse();
        LocalTime endTime = LocalTime.now();
        System.out.println("Time taken: " + Duration.between(startTime, endTime).getSeconds());
        return result;
    }

    @Override
    public String getGcsPath() {
        return document.getGcsPath();
    }
}
