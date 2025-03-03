package ressources;


import entities.RendezVous;
import metiers.RendezVousBusiness;

import javax.ws.rs.Consumes;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class RendezVousRessources {

    public static RendezVousBusiness R = new RendezVousBusiness();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("add")
    public Response addRendezVous(RendezVous rendezVous) {
        if (R.addRendezVous(rendezVous)) {
            return Response.status(200).entity("RendezVous added successfully").build();
        }
        return Response.status(404).entity("Failed to add RendezVous").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("all")
    public Response getAllRendezVous() {
        List<RendezVous> liste = R.getListeRendezVous();
        return Response.status(200).entity(liste).build();
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("get/{id}")
    public Response getRendezVousById(@PathParam("id") int id) {
        RendezVous rendezVous = R.getRendezVousById(id);
        if (rendezVous != null) {
            return Response.status(200).entity(rendezVous).build();
        }
        return Response.status(404).entity("RendezVous not found").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("logement/{reference}")
    public Response getRendezVousByLogement(@PathParam("reference") int reference) {
        List<RendezVous> liste = R.getListeRendezVousByLogementReference(reference);
        if (!liste.isEmpty()) {
            return Response.status(200).entity(liste).build();
        }
        return Response.status(404).entity("No RendezVous found for this logement").build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    @Path("modify/{id}")
    public Response modifyRendezVous(@PathParam("id") int id, RendezVous updatedRendezVous) {
        if (R.updateRendezVous(id, updatedRendezVous)) {
            return Response.status(200).entity("RendezVous updated successfully").build();
        }
        return Response.status(404).entity("Failed to update RendezVous").build();
    }

    @DELETE
    @Produces(MediaType.TEXT_PLAIN)
    @Path("delete/{id}")
    public Response deleteRendezVous(@PathParam("id") int id) {
        if (R.deleteRendezVous(id)) {
            return Response.status(200).entity("RendezVous deleted successfully").build();
        }
        return Response.status(404).entity("Failed to delete RendezVous").build();
    }




}
