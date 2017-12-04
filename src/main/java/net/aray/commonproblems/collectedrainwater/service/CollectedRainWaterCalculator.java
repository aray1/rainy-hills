package net.aray.commonproblems.collectedrainwater.service;

import javax.ejb.Stateless;

import net.aray.commonproblems.collectedrainwater.model.Hill;

/**
 * Service to calculate expected rainwater accumulated over a hill after a rainfall.
 */
@Stateless
public class CollectedRainWaterCalculator {

    /**
     * Gets the total volume of expected rain water that will be collected over the hill after a rainfall.
     * For calculation of the total water collected, we have to calculate the water collected over each surface point.
     * To calculate the water above each surface point we have to get the max surface height to its left and to its
     * right.Then we calculate the water collected over each surface point as
     * {@code max(maxHeightToLeftOfSurfacePoint,maxHeightToRightOfSurfacePoint) - currentSurfacePointHeight}.
     * To fetch the max height to the left and right of each surface point we have to iterate the surface twice
     * {@code O(2*N) == O(N)}.
     *
     * @param hill
     *         the hill to calculate the total volume of rain water collected during rain fall.
     */
    public int getVolumeOfWaterCollected(Hill hill) {
        int[] hillSurface = hill.getSurface();
        int[] maxHeightToTheRightOfEachSurfacePoint = getMaxHeightToTheRightOfEachSurfacePoint(hillSurface);

        int maxHeightToTheLeftOfSurfacePoint = 0;
        int collectedRainWater = 0;
        for (int surfacePoint = 0; surfacePoint < hillSurface.length; surfacePoint++) {
            int currentSurfacePointHeight = hillSurface[surfacePoint];
            int maxHeightToTheRightOfSurfacePoint = maxHeightToTheRightOfEachSurfacePoint[surfacePoint];

            int waterVolumeOverSurfacePoint =
                    getWaterVolumeOverSurfacePoint(maxHeightToTheLeftOfSurfacePoint, maxHeightToTheRightOfSurfacePoint,
                            currentSurfacePointHeight);

            collectedRainWater = collectedRainWater + waterVolumeOverSurfacePoint;

            //update maxHeightInLeft
            if (currentSurfacePointHeight > maxHeightToTheLeftOfSurfacePoint) {
                maxHeightToTheLeftOfSurfacePoint = currentSurfacePointHeight;
            }

        }

        return collectedRainWater;
    }

    private int getWaterVolumeOverSurfacePoint(int maxHeightToLeftOfSurfacePoint, int maxHeightToRightOfSurfacePoint,
            int currentSurfacePointHeight) {
        //Calculate the water volume over a given surface point.
        int waterVolumeOverSurfacePoint =
                Math.min(maxHeightToLeftOfSurfacePoint, maxHeightToRightOfSurfacePoint) - currentSurfacePointHeight;
        //handle the first tower since the max height to left will be 0.
        return Math.max(waterVolumeOverSurfacePoint, 0);
    }

    private int[] getMaxHeightToTheRightOfEachSurfacePoint(int[] hillSurface) {
        int[] maxHeightToTheRightOfEachSurfacePoint = new int[hillSurface.length];
        int maxHeightToTheRightOfSurfacePoint = 0;

        for (int surfacePoint = hillSurface.length - 1; surfacePoint >= 0; surfacePoint--) {
            int currentSurfacePointHeight = hillSurface[surfacePoint];
            if (maxHeightToTheRightOfSurfacePoint < currentSurfacePointHeight) {
                maxHeightToTheRightOfSurfacePoint = currentSurfacePointHeight;
            }
            maxHeightToTheRightOfEachSurfacePoint[surfacePoint] = maxHeightToTheRightOfSurfacePoint;
        }

        return maxHeightToTheRightOfEachSurfacePoint;
    }

}
