package web.app;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("animals") // /api/animals
@Produces(MediaType.APPLICATION_JSON)
public class AnimalsResource {

    private static ArrayList<String> animals
            = new ArrayList<>();

    @GET
    public Response getanimals(
            @DefaultValue("") @QueryParam("starsWith") String startsWith
    ) {
        ArrayList<String> filteredOutAnimals = new ArrayList<>();

        for (int i = 0; i < animals.size(); i++) {
            if(animals.get(i).startsWith(startsWith))
                filteredOutAnimals.add(animals.get(i));
        }

        return Response.ok(filteredOutAnimals).build();
    }

    @GET
    @Path("{id}") // api/animals/{id}
    public Response getDetail(
            @PathParam("id") int animalIndex
    ) {
        String foundAnimal = animals.get(animalIndex);
        return Response.ok(foundAnimal).build();
    }

    @POST
    public Response addAnimal(
            @FormParam("username") String newAnimal
    ) {
        if (newAnimal == null)
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();

        animals.add(newAnimal);
        return Response.ok().build();
    }
    @DELETE
    @Path("{id}") // api/animals/{id}
    public Response deleteAnimal(
            @PathParam("id") int animalIndex
    ) {
        String foundAnimal = animals.get(animalIndex);
        animals.remove(animalIndex);
        return Response.ok().build();
    }

    @PUT
    @Path("{id}") //api/animals/{id}
    public Response updateAnimal(
            @PathParam("id") int animalIndex,
            @FormParam("username") String changeAnimal
    ){
        if (changeAnimal == null)
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();

        animals.set(animalIndex, changeAnimal);
        return Response.ok(changeAnimal).build();
    }

}