import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class HorseTest {

@Test
public void testHorseWithNullNameIsException() {
    Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
            () -> new Horse(null, 0));
    Assertions.assertEquals("Name cannot be null.", exception.getMessage());

    Throwable exceptionSecond = Assertions.assertThrows(IllegalArgumentException.class,
            () -> new Horse(null, 0, 0));
    Assertions.assertEquals("Name cannot be null.", exceptionSecond.getMessage());

}


    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", ""})
    public void testHorseWithEmptyNameIsException(String name) {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, 0));
        Assertions.assertEquals("Name cannot be blank.", exception.getMessage());

        Throwable exceptionSecond = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 0, 0));
        Assertions.assertEquals("Name cannot be null.", exceptionSecond.getMessage());
    }


    @Test
    public void testHorseWithNegativeSpeedIsException() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("test", -1));
        Assertions.assertEquals("Speed cannot be negative.", exception.getMessage());

        Throwable exceptionSecond = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("test2", -1, 0));
        Assertions.assertEquals("Speed cannot be negative.", exceptionSecond.getMessage());


    }


    @Test
    public void testHorseWithNegativeDistanceIsException() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse("test", 0, -1));
        Assertions.assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    public void testHorseGetNameIsFirstParameter() {
        Horse horse = new Horse("test", 0);
        Horse horseSecond = new Horse("test2", 0, 0);
        Assertions.assertAll(
                "Проверить 2 способа создания",
                () -> Assertions.assertEquals("test", horse.getName()),
                () -> Assertions.assertEquals("test2", horseSecond.getName())
        );
    }

    @Test
    public void testHorseGetSpeedTestIsSecondParameter() {
        Horse horse = new Horse("test", 1);
        Horse horseSecond = new Horse("test2", 2, 0);
        Assertions.assertAll(
                "Проверить 2 способа создания",
                () -> Assertions.assertEquals(1, horse.getSpeed()),
                () -> Assertions.assertEquals(2, horseSecond.getSpeed())
        );
    }


    @Test
    public void testHorseGetDistanceisThirdParameter() {
        Horse horse = new Horse("test", 1);
        Horse horseSecond = new Horse("test2", 2, 1);
        Assertions.assertAll(
                "Проверить 2 способа создания",
                () -> Assertions.assertEquals(0, horse.getDistance()),
                () -> Assertions.assertEquals(1, horseSecond.getDistance())
        );
    }

    @Test
    public void testHorseMoveCheckUseMethodGetRandomDouble() {
        Horse horse = new Horse("test", 1, 1);
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9), Mockito.times(1));
        }
    }


    @ParameterizedTest
    @CsvSource({"1.0,2.0",
            "2.0,2.0",
            "44.1,55.9"
    })
    public void testHorseMoveIsCorrectFormula(double speed, double distance) {

        try (MockedStatic<Horse> mockObject = Mockito.mockStatic(Horse.class)) {
            Mockito.when(Horse.getRandomDouble(0.2, 0.9)).thenReturn(1.0);
            Horse horse = new Horse("test", speed, distance);
            horse.move();
            Assertions.assertEquals(distance + speed * 1, horse.getDistance());
        }
    }


}