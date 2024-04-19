package edu.gonzaga.Farkle;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    @Test
    public void testSetandGetName() {
        Player player = new Player();
        String expected = "Jeff ! ";

        player.setName("Jeff ! ");
        String actual = player.getName();
        assertEquals(expected, actual);
    }

    @Test
    public void testDefaultName() {
        Player player = new Player();
        String expected = "Unkown Player";

        String actual = player.getName();
        assertEquals(expected, actual);
    }
}
