import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {

    @Test
    public void testHippodromeWithNullHorsesIsException() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        Assertions.assertEquals("Horses cannot be null.", exception.getMessage());

    }

    @Test
    public void testHippodromeWithEmptyListHorsesIsException() {
        Throwable exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<Horse>()));
        Assertions.assertEquals("Horses cannot be empty.", exception.getMessage());

    }

    @Test
    public void testHippodromeGetHorsesEqualParameter() {
        ArrayList<Horse> horses = new ArrayList<>();
        while (horses.size() <= 30) {
            double num = (Math.random() * 100);
            horses.add(new Horse("test" + Double.toString(num), num));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    public void testHippodromeCountMoveEqualCountHorsesInList() {
        ArrayList<Horse> horses = new ArrayList<>();
        Horse horse = Mockito.mock(Horse.class);
        while (horses.size() < 50) {
            double num = (Math.random() * 100);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Mockito.verify(horse, Mockito.times(50)).move();

    }


    @Test
    public void testHippodromeWinnerIsHorseWithMaxDistance() {
        ArrayList<Horse> horses = new ArrayList<>();
        Horse horseWithMaxDistance = new Horse("winner", 555);
        horses.add(horseWithMaxDistance);
        for (int i = 0; i < 9; i++) {
            Horse horse = new Horse("test" + i, i);
            horses.add(horse);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        Assertions.assertSame(horseWithMaxDistance, hippodrome.getWinner());
    }


}