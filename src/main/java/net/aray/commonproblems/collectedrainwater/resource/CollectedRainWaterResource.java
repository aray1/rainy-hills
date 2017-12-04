package net.aray.commonproblems.collectedrainwater.resource;

import javax.ejb.EJB;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import net.aray.commonproblems.collectedrainwater.model.CollectedRainWaterResponse;
import net.aray.commonproblems.collectedrainwater.model.Hill;
import net.aray.commonproblems.collectedrainwater.service.CollectedRainWaterCalculator;
import net.aray.commonproblems.collectedrainwater.service.HillFactory;

/**
 * collectedrainwater REST resource.
 */
@Path("collectedrainwater")
public class CollectedRainWaterResource {

    @EJB
    private HillFactory hillFactory;

    @EJB
    private CollectedRainWaterCalculator collectedRainWaterCalculator;

    /**
     * Resource to get total expected volume of rain water collected over a hill represented by the surface provided.
     *
     * @param surface
     *         a comma separated string of integers representing the surface of a hill.
     */
    @GET
    @Path("/{surface}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTotalExpectedVolumeOfCollectedRainWater(@NotNull @PathParam("surface") String surface) {

        Hill hill = hillFactory.createHill(surface);

        int totalVolumeOfCollectedWaterOnHill = collectedRainWaterCalculator.getVolumeOfWaterCollected(hill);

        return Response.ok(new CollectedRainWaterResponse(hill, totalVolumeOfCollectedWaterOnHill)).build();
    }

}
