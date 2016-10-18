package com.sanecki.war.sets;

        import org.junit.Test;

        import static org.junit.Assert.*;

/**
 * Created by Jeremy on 10/17/2016.
 */
public class FaceTest {
    @Test
    public void shouldReturnAPostiveValueWhenComparedToALessValue() throws Exception {
        assertEquals(Face.FOUR.compareTo(Face.THREE), 1);
    }

    @Test
    public void shouldReturnANegtiveValueWhenComparedToAGreaterValue() throws Exception {
        assertEquals(Face.THREE.compareTo(Face.FOUR), -1);
    }

    @Test
    public void shouldCompareAceToBeGreaterThanQueen() throws Exception {
        assertEquals(Face.QUEEN.compareTo(Face.ACE), 12);
    }
}