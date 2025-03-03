package ressources;


import entities.Logement;
import metiers.LogementBusiness;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;


@Path("logement")
    public class LogementRessources {
    public static LogementBusiness L = new LogementBusiness();


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("list")
    public Response logementList() {
        return Response.status(200).entity(L.getLogements()).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{ref}")
    public Response getLogementByRef(@PathParam("ref") int logRef) {
        return Response.status(200).entity(L.getLogementsByReference(logRef)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("")
    public Response getLogementsByDeleg(@QueryParam("del") String delegation) {
        return Response.status(200).entity(L.getLogementsByDeleguation(delegation)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("add")
    public Response addLogement(Logement logement) {
        if (L.addLogement(logement)) {
            return Response.status(200).entity("Added with success").build();
        }
        return Response.status(404).entity("not added").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("modify/{reference}")
    public Response modifyLogement(@PathParam("reference") int reference, Logement logement) {
        if (L.updateLogement(reference, logement)) {
            return Response.status(200).entity("Modified successfully").build();
        }
        return Response.status(404).entity("Modification failed").build();
    }



    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("delete/{reference}")
    public Response deleteLogement(@PathParam("reference") int reference) {
        if (L.deleteLogement(reference)) {
            return Response.status(200).entity("Deleted successfully").build();
        }
        return Response.status(404).entity("Deletion failed").build();
    }



}
