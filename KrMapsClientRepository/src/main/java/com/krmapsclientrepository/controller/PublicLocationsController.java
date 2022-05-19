package com.krmapsclientrepository.controller;

import com.krmapsclientrepository.dto.PublicInstitutionDTO;
import com.krmapsclientrepository.exceptions.utils.APIError;
import com.krmapsclientrepository.service.PublicInstitutionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.links.Link;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public/locations")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "PublicLocationsController", description = "This controller is used to retrieve data about every " +
        "public institution from a given city (for now is just Timisoara)")
public class PublicLocationsController {

    private final PublicInstitutionService publicInstitutionService;

    @Autowired
    public PublicLocationsController(PublicInstitutionService publicInstitutionService) {
        this.publicInstitutionService = publicInstitutionService;
    }

    @ResponseBody
    @GetMapping("/get")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves all public locations from a city.",
            description = "This method is of type GET which you can access on the endpoint of /api/public/locations/get" +
                    " that will return every public institution available in the database.",
            parameters = @Parameter(name = "NONE", description = "None needed"),
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "List of public institutions",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PublicInstitutionDTO.class)), mediaType = "JSON",
                                            examples = @ExampleObject(name = "/api/public/locations/get",
                                                    value = "[\n" +
                                                            "    {\n" +
                                                            "        \"id\": 1,\n" +
                                                            "        \"name\": \"Facultatea de Muzica si Teatru\",\n" +
                                                            "        \"code\": \"university\",\n" +
                                                            "        \"latitude\": 45.75604817104084,\n" +
                                                            "        \"longitude\": 21.22730183809813\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"id\": 2,\n" +
                                                            "        \"name\": \"Facultatea de M.T.H.C.\",\n" +
                                                            "        \"code\": \"university\",\n" +
                                                            "        \"latitude\": 45.75585777122083,\n" +
                                                            "        \"longitude\": 21.238401556097415\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"id\": 3,\n" +
                                                            "        \"name\": \"Facultatea de Stiinte Economice - Tibiscus\",\n" +
                                                            "        \"code\": \"university\",\n" +
                                                            "        \"latitude\": 45.75058001974072,\n" +
                                                            "        \"longitude\": 21.243556690737933\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"id\": 4,\n" +
                                                            "        \"name\": \"ExperimentariumTm\",\n" +
                                                            "        \"code\": \"university\",\n" +
                                                            "        \"latitude\": 45.749836875772516,\n" +
                                                            "        \"longitude\": 21.231097373016382\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"id\": 5,\n" +
                                                            "        \"name\": \"Facultatea de Automatica & Calculatoare\",\n" +
                                                            "        \"code\": \"university\",\n" +
                                                            "        \"latitude\": 45.74733548275581,\n" +
                                                            "        \"longitude\": 21.226691065576233\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"id\": 6,\n" +
                                                            "        \"name\": \"Facultatea de Agricultura - USAMVB\",\n" +
                                                            "        \"code\": \"university\",\n" +
                                                            "        \"latitude\": 45.78253410066694,\n" +
                                                            "        \"longitude\": 21.216906307904082\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"id\": 7,\n" +
                                                            "        \"name\": \"Facultatea de Arhitectura sI Urbanism\",\n" +
                                                            "        \"code\": \"university\",\n" +
                                                            "        \"latitude\": 45.745444151932965,\n" +
                                                            "        \"longitude\": 21.23002231481928\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"id\": 8,\n" +
                                                            "        \"name\": \"Facultatea de Calculatoare si Informatica Aplicata\",\n" +
                                                            "        \"code\": \"university\",\n" +
                                                            "        \"latitude\": 45.750535101312735,\n" +
                                                            "        \"longitude\": 21.24363179259035\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"id\": 9,\n" +
                                                            "        \"name\": \"Facultatea de Automatica si Calculatoare\",\n" +
                                                            "        \"code\": \"university\",\n" +
                                                            "        \"latitude\": 45.74682222619067,\n" +
                                                            "        \"longitude\": 21.227007479098575\n" +
                                                            "    }," +
                                                            "...." +
                                                            "]")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/public/locations/get"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Bad Object Request.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Bad Object Request. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "404", description = "If it doesn't exist.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/public/locations/get"
                                            , value = "{\n" +
                                            "    \"status\": \"NOT_FOUND\",\n" +
                                            "    \"message\": \"Object not found\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Object not found because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "406", description = "If the type doesn't exist.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/public/locations/get"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Operation not permitted.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Operation not permitted. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "500", description = "If it is server related",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/public/locations/get"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public List<PublicInstitutionDTO> getAllPublicLocations(){
        return publicInstitutionService.getAllPublicLocations();
    }

    @ResponseBody
    @GetMapping("/get/{type}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves a type of public location from a city.",
            description = "This method is of type GET which you can access on the endpoint of /api/public/locations/get/{type}" +
                    " where type represents the type of public institution like hospital and so on." +
                    "It will return every type of public institution available in the database from that city.",
            parameters = @Parameter(name = "type", description = "The type of public institution", required = true,
                    example = "school",
                    schema = @Schema(implementation = String.class, type = "string"),
                    in = ParameterIn.PATH),
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "List of a type of public institution",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = PublicInstitutionDTO.class)), mediaType = "JSON",
                                    examples = @ExampleObject(name = "/api/public/locations/get/school",
                                            value = "[\n" +
                                                    "    {\n" +
                                                    "        \"id\": 1,\n" +
                                                    "        \"name\": \"Centrul Scolar Pentru Educatie Incluziva Constantin Pufan\",\n" +
                                                    "        \"code\": \"school\",\n" +
                                                    "        \"latitude\": 45.74435105105962,\n" +
                                                    "        \"longitude\": 21.24379815211489\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 2,\n" +
                                                    "        \"name\": \"Centrul Scolar pentru Educatie Incluziva Dumitru Ciumagianu\",\n" +
                                                    "        \"code\": \"school\",\n" +
                                                    "        \"latitude\": 45.74373116648005,\n" +
                                                    "        \"longitude\": 21.212711370391844\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 3,\n" +
                                                    "        \"name\": \"Colegiul Tehnic Henri Coanda\",\n" +
                                                    "        \"code\": \"school\",\n" +
                                                    "        \"latitude\": 45.75605427352437,\n" +
                                                    "        \"longitude\": 21.218317105526694\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 4,\n" +
                                                    "        \"name\": \"Colegiul Tehnic Ion Mincu\",\n" +
                                                    "        \"code\": \"school\",\n" +
                                                    "        \"latitude\": 45.75906524900567,\n" +
                                                    "        \"longitude\": 21.222394114047233\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 5,\n" +
                                                    "        \"name\": \"Colegiul Tehnic de Vest din Timisoara\",\n" +
                                                    "        \"code\": \"school\",\n" +
                                                    "        \"latitude\": 45.745112867704336,\n" +
                                                    "        \"longitude\": 21.21228585382846\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 6,\n" +
                                                    "        \"name\": \"Colegiul Național Coriolan Brediceanu Lugoj\",\n" +
                                                    "        \"code\": \"school\",\n" +
                                                    "        \"latitude\": 45.684232337197066,\n" +
                                                    "        \"longitude\": 21.902713644180267\n" +
                                                    "    }," +
                                                    "....." +
                                                    "]")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/public/locations/get/{type}"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Bad Object Request.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Bad Object Request. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "404", description = "If it doesn't exist.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/public/locations/get/{type}"
                                            , value = "{\n" +
                                            "    \"status\": \"NOT_FOUND\",\n" +
                                            "    \"message\": \"Object not found\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Object not found because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "406", description = "If the type doesn't exist.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/public/locations/get/{type}"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Operation not permitted.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Operation not permitted. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "500", description = "If it is server related",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/public/locations/get/{type}"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public List<PublicInstitutionDTO> getPreferredPublicLocations(@PathVariable("type") String code){
        return publicInstitutionService.getPreferredPublicLocations(code);
    }

    @ResponseBody
    @GetMapping("/get/{code}/name/{name}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves a type of public location from a city by it's name from the database.",
            description = "This method is of type GET which you can access on the endpoint of /api/public/locations/get/{code}/name/{name}" +
                    " where code represents the type of public institution like school, university and so on, and name represents " +
                    "the name of the public institution.It will return the public institution available in the database from that city by that name.",
            parameters = {@Parameter(name = "code", description = "The type of public institution", required = true,
                    example = "school",
                    schema = @Schema(implementation = String.class, type = "string"),
                    in = ParameterIn.PATH),
                          @Parameter(name = "name", description = "The name of the public institution", required = true,
                            example = "Centrul Scolar Pentru Educatie Incluziva Constantin Pufan",
                            schema = @Schema(implementation = String.class, type = "string"),
                            in = ParameterIn.PATH)},
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "A public institution",
                                    content = @Content(schema = @Schema(implementation = PublicInstitutionDTO.class), mediaType = "JSON",
                                    examples = @ExampleObject(
                                            name = "/api/public/locations/get/{code}/name/{name}",
                                            value = "{\n" +
                                                    "    \"id\": 1,\n" +
                                                    "    \"name\": \"Centrul Scolar Pentru Educatie Incluziva Constantin Pufan\",\n" +
                                                    "    \"code\": \"school\",\n" +
                                                    "    \"latitude\": 45.74435105105962,\n" +
                                                    "    \"longitude\": 21.24379815211489\n" +
                                                    "}")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "api/geo-tools/user/location/distance"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Bad Object Request.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Bad Object Request. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "404", description = "If it doesn't exist.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "api/geo-tools/user/location/distance"
                                            , value = "{\n" +
                                            "    \"status\": \"NOT_FOUND\",\n" +
                                            "    \"message\": \"Object not found\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Object not found because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "406", description = "If the type doesn't exist.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "api/geo-tools/user/location/distance"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Operation not permitted.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Operation not permitted. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "500", description = "If it is server related",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "api/geo-tools/user/location/distance"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public PublicInstitutionDTO getPublicInstitutionLocationByName(@PathVariable("code") String code,
                                                     @PathVariable("name") String name){
        return publicInstitutionService.getPublicInstitutionByName(code, name);
    }

    @ResponseBody
    @GetMapping("/get/{code}/id/{id}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves a type of public location from a city by it's id from the database.",
            description = "This method is of type GET which you can access on the endpoint of /api/public/locations/get/{code}/id/{id}" +
                    " where code represents the type of public institution like school, university and so on, and id represents " +
                    "the id of the public institution.It will return the public institution available in the database from that city by that id.",
            parameters = {@Parameter(name = "code", description = "The type of public institution", required = true,
                    example = "school",
                    schema = @Schema(implementation = String.class, type = "string"),
                    in = ParameterIn.PATH),
                    @Parameter(name = "id", description = "The name of the public institution", required = true,
                            example = "12",
                            schema = @Schema(implementation = Long.class, type = "long", format = "long"),
                            in = ParameterIn.PATH)},
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "A public institution",
                                    content = @Content(schema = @Schema(implementation = PublicInstitutionDTO.class), mediaType = "JSON",
                                    examples = @ExampleObject(name = "/api/public/locations/get/{code}/id/{id}",
                                            value = "{\n" +
                                                    "    \"id\": 50,\n" +
                                                    "    \"name\": \"Scoala Generala nr. 24\",\n" +
                                                    "    \"code\": \"school\",\n" +
                                                    "    \"latitude\": 45.76583250346273,\n" +
                                                    "    \"longitude\": 21.220950298008688\n" +
                                                    "}")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "api/geo-tools/user/location/distance"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Bad Object Request.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Bad Object Request. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "404", description = "If it doesn't exist.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "api/geo-tools/user/location/distance"
                                            , value = "{\n" +
                                            "    \"status\": \"NOT_FOUND\",\n" +
                                            "    \"message\": \"Object not found\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Object not found because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "406", description = "If the type doesn't exist.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "api/geo-tools/user/location/distance"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Operation not permitted.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Operation not permitted. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "500", description = "If it is server related",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "api/geo-tools/user/location/distance"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public PublicInstitutionDTO getPublicInstitutionLocationById(@PathVariable("code") String code,
                                                   @PathVariable("id") Long id){
        return publicInstitutionService.getPublicInstitutionById(code, id);
    }
}
