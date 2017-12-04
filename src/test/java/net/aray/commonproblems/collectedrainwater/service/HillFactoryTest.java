package net.aray.commonproblems.collectedrainwater.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import net.aray.commonproblems.collectedrainwater.model.Hill;

public class HillFactoryTest {

    private final HillFactory hillFactory = new HillFactory();

    @Test
    public void canCreateHill() {
        //given
        String surface = "4,0,5";

        //when
        Hill actualHill = hillFactory.createHill(surface);

        //then
        Hill expectedHill = new Hill(new int[] {4, 0, 5});
        assertEquals("Incorrect hill", expectedHill, actualHill);
    }

    @Test(expected = IllegalArgumentException.class)
    public void cannotCreateHill() {
        //given
        String surface = "abc,0,5";

        //when
        hillFactory.createHill(surface);
    }

}