package net.aray.commonproblems.collectedrainwater.model;

/**
 * Response object containing the {@link Hill} created out of the provided surface in the request and the expected water
 * to be collected after a rainfall.
 */
public class CollectedRainWaterResponse {

    private final Hill hill;
    private final int collectedRainWaterVolume;

    public CollectedRainWaterResponse(Hill hill, int collectedRainWaterVolume) {
        this.hill = hill;
        this.collectedRainWaterVolume = collectedRainWaterVolume;
    }

    public Hill getHill() {
        return hill;
    }

    public int getCollectedRainWaterVolume() {
        return collectedRainWaterVolume;
    }
}
