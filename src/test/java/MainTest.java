import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Disabled
    @Test
    void testCheckRuntime() throws InterruptedException {
        Assertions.assertTimeout(Duration.ofSeconds(22), () -> {
            Main.main(new String[0]);
        });
    }
}