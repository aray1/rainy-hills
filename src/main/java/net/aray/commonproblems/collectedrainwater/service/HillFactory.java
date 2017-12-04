package net.aray.commonproblems.collectedrainwater.service;

import java.util.stream.Stream;

import javax.ejb.Stateless;

import net.aray.commonproblems.collectedrainwater.model.Hill;

/**
 * Factory to create a {@link Hill} object out of a comma separated string of integers.
 */
@Stateless
public class HillFactory {

    /**
     * Creates a {@link Hill}.
     *
     * @param surface
     *         A comma separated string of integers representing the surface of the hill.
     */
    public Hill createHill(String surface) {
        try {
            int[] convertedSurface = parseIntArray(surface);
            return new Hill(convertedSurface);
        } catch (NumberFormatException exception) {
            throw new IllegalArgumentException("Invalid surface provided :: " + surface);
        }
    }

    private int[] parseIntArray(String surface) {
        return Stream.of(surface.split(",")).mapToInt(Integer::parseInt).toArray();
    }

}
