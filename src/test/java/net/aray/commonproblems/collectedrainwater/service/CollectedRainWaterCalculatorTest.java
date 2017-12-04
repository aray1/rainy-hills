package net.aray.commonproblems.collectedrainwater.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import net.aray.commonproblems.collectedrainwater.model.Hill;

@RunWith(Parameterized.class)
public class CollectedRainWaterCalculatorTest {

    //given
    @Parameterized.Parameters(name = "Evaluating {0}")
    public static ExpectedTotalVolumeOfCollectedRainWaterForAHill[] data() {
        return new ExpectedTotalVolumeOfCollectedRainWaterForAHill[] {
                new ExpectedTotalVolumeOfCollectedRainWaterForAHill(new int[] {3, 2, 4, 1, 2}, 2),
                new ExpectedTotalVolumeOfCollectedRainWaterForAHill(new int[] {4, 1, 1, 0, 2, 3}, 8),
                new ExpectedTotalVolumeOfCollectedRainWaterForAHill(new int[] {1, 5, 2, 3, 1, 7, 2, 4}, 11)};
    }

    private final ExpectedTotalVolumeOfCollectedRainWaterForAHill expectedTotalVolumeOfCollectedRainWaterForAHill;

    public CollectedRainWaterCalculatorTest(
            ExpectedTotalVolumeOfCollectedRainWaterForAHill expectedTotalVolumeOfCollectedRainWaterForAHill) {
        this.expectedTotalVolumeOfCollectedRainWaterForAHill = expectedTotalVolumeOfCollectedRainWaterForAHill;
    }

    private final CollectedRainWaterCalculator underTest = new CollectedRainWaterCalculator();

    @Test
    public void getVolumeOfWaterCollected() {
        //when
        int actualTotalRainWaterVolume =
                underTest.getVolumeOfWaterCollected(expectedTotalVolumeOfCollectedRainWaterForAHill.getHill());

        //then
        assertEquals("Incorrect total rain water volume",
                expectedTotalVolumeOfCollectedRainWaterForAHill.getExpectedTotalVolumeOfCollectedRainWater(),
                actualTotalRainWaterVolume);
    }

    private static class ExpectedTotalVolumeOfCollectedRainWaterForAHill {
        private final Hill hill;
        private final int expectedTotalVolumeOfCollectedRainWater;

        private ExpectedTotalVolumeOfCollectedRainWaterForAHill(int[] hillSurface,
                int expectedTotalVolumeOfCollectedRainWater) {
            this.hill = new Hill(hillSurface);
            this.expectedTotalVolumeOfCollectedRainWater = expectedTotalVolumeOfCollectedRainWater;
        }

        public Hill getHill() {
            return hill;
        }

        public int getExpectedTotalVolumeOfCollectedRainWater() {
            return expectedTotalVolumeOfCollectedRainWater;
        }

        @Override
        public String toString() {
            return hill.toString();
        }
    }

}