package net.aray.commonproblems.collectedrainwater.model;

import java.util.Arrays;

/**
 * An immutable abstraction of a hill,defined as a collection of surface points.
 * The length of the array, represents the cross sectional length of the hill surface.
 * The integer in each index of the array, represents the vertical height of the hill surface at that point.
 */
public class Hill {

    private final int[] surface;

    /**
     * Creates a {@link Hill} with the provided surface.
     *
     * @param surface
     *         int array that should represent the surface of the hill.
     */
    public Hill(int[] surface) {
        this.surface = surface;
    }

    /**
     * Returns the surface of the hill.
     */
    public int[] getSurface() {
        return Arrays.copyOf(surface, surface.length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Hill)) {
            return false;
        }
        Hill that = (Hill) o;
        return Arrays.equals(surface, that.surface);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(surface);
    }

    @Override
    public String toString() {
        return "Hill with surface" + Arrays.toString(surface);
    }
}
