package net.aray.commonproblems.collectedrainwater.resource;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import javax.ws.rs.core.Response;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import net.aray.commonproblems.collectedrainwater.model.CollectedRainWaterResponse;
import net.aray.commonproblems.collectedrainwater.model.Hill;
import net.aray.commonproblems.collectedrainwater.service.CollectedRainWaterCalculator;
import net.aray.commonproblems.collectedrainwater.service.HillFactory;

@RunWith(MockitoJUnitRunner.class)
public class CollectedRainWaterResourceTest {

    @Mock
    private HillFactory hillFactory;
    @Mock
    private CollectedRainWaterCalculator collectedRainWaterCalculator;
    @InjectMocks
    private CollectedRainWaterResource collectedRainWaterResource;

    @Test
    public void getTotalExpectedVolumeOfCollectedRainWater() {
        String surface = "4,0,5";
        Hill hill = new Hill(new int[] {4, 0, 5});
        int expectedTotalVolume = 4;

        //given
        when(hillFactory.createHill(eq(surface))).thenReturn(hill);
        when(collectedRainWaterCalculator.getVolumeOfWaterCollected(eq(hill))).thenReturn(expectedTotalVolume);

        //when
        Response response = collectedRainWaterResource.getTotalExpectedVolumeOfCollectedRainWater(surface);

        //then
        assertEquals("Incorrect response status", Response.Status.OK.getStatusCode(), response.getStatus());
        CollectedRainWaterResponse collectedRainWaterResponse = (CollectedRainWaterResponse) response.getEntity();
        assertEquals("Incorrect calculated total volume", expectedTotalVolume,
                collectedRainWaterResponse.getCollectedRainWaterVolume());
    }

}