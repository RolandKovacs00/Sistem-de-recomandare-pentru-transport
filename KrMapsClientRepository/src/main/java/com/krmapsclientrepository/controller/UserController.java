package com.krmapsclientrepository.controller;

import com.krmapsclientrepository.dto.InstitutionDTO;
import com.krmapsclientrepository.dto.MedicalInstitutionDTO;
import com.krmapsclientrepository.exceptions.utils.APIError;
import com.krmapsclientrepository.model.util.ObjectWrapper;
import com.krmapsclientrepository.model.util.Point;
import com.krmapsclientrepository.service.UserService;
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
import org.springframework.web.client.HttpClientErrorException;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/user/location")
@Tag(name = "UserController", description = "This controller is used to manipulate data given by the POST method " +
        "calculating things like : distance between 2 points, all locations from a specific zone by a given radius etc.")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @PostMapping("/distance")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves the distance between 2 points on the map.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "A wrapped" +
                    " object that contains 2 Point.java objects.", content = @Content(schema = @Schema(implementation = ObjectWrapper.class),
                    examples = @ExampleObject(name = "/api/user/location/distance"
                            , value = "{\n" +
                            "  \"startingDistance\":{\n" +
                            "    \"code\": \"user\",\n" +
                            "    \"radius\": 0,\n" +
                            "    \"latitude\": 45.77045822363855,\n" +
                            "    \"longitude\": 21.21882227116396\n" +
                            "    },\n" +
                            "  \"finishDestination\":{\n" +
                            "    \"code\": \"scool\",\n" +
                            "    \"radius\": 0,\n" +
                            "    \"latitude\": 45.75529741882885,\n" +
                            "    \"longitude\": 21.278155777194115\n" +
                            "    }\n" +
                            "}"))),
            description = "This method is of type POST which you can access on the endpoint of /api/user/location/distance" +
                    " that will return the distance between 2 given points by a RequestBody in JSON format which holds" +
                    " the 2 points.",
            parameters = @Parameter(required = true, schema = @Schema(implementation = ObjectWrapper.class, name = "ObjectWrapper"),
            name = "ObjectWrapper", content = @Content(mediaType = "JSON", schema = @Schema(implementation = ObjectWrapper.class, name = "ObjectWrapper")),
            description = "An object wrapper that contains 2 objects Point.java. Each object contains the latitude and longitude",
            in = ParameterIn.QUERY, example = "/api/user/location/distance",examples = @ExampleObject(name = "/api/user/location/distance"
                    , value = "{\n" +
                    "  \"startingDistance\":{\n" +
                    "    \"code\": \"user\",\n" +
                    "    \"radius\": 0,\n" +
                    "    \"latitude\": 45.77045822363855,\n" +
                    "    \"longitude\": 21.21882227116396\n" +
                    "    },\n" +
                    "  \"finishDestination\":{\n" +
                    "    \"code\": \"scool\",\n" +
                    "    \"radius\": 0,\n" +
                    "    \"latitude\": 45.75529741882885,\n" +
                    "    \"longitude\": 21.278155777194115\n" +
                    "    }\n" +
                    "}")),
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "Distance between two given points.",
                                    content = @Content(schema = @Schema(implementation = BigDecimal.class), mediaType = "JSON",
                                            examples = @ExampleObject(name = "/api/user/location/distance"
                                                    , value = "4.901447742483356")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/distance"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Bad Object Request.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Bad Object Request. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "404", description = "If it doesn't exist.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/distance"
                                            , value = "{\n" +
                                            "    \"status\": \"NOT_FOUND\",\n" +
                                            "    \"message\": \"Object not found\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Object not found because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "406", description = "If the type doesn't exist.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/distance"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Operation not permitted.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Operation not permitted. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "500", description = "If it is server related",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/distance"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public BigDecimal calculateDistance(@Valid @RequestBody ObjectWrapper objectWrapper) {
        return userService.calculateDistance(objectWrapper);
    }

    @ResponseBody
    @PostMapping("/zone")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves specific locations from a given zone by a point in the map.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "An object" +
                    " that contains a latitude, longitude, radius and the code where : latitude and longitude speak" +
                    " for themselves, code which represents the institution and radius the circle size where the location are.",
                    content = @Content(schema = @Schema(implementation = Point.class),
                            examples = @ExampleObject(name = "/api/user/location/zone"
                                    , value = "{\n" +
                                    "    \"code\": \"hospital\",\n" +
                                    "    \"radius\":500,\n" +
                                    "    \"latitude\": 45.77045822363855,\n" +
                                    "    \"longitude\": 21.21882227116396\n" +
                                    "}"))),
            description = "This method is of type POST which you can access on the endpoint of /api/user/location/zone" +
                    " that will return locations from a specific institution and a specific radius.",
            parameters = @Parameter(required = true, schema = @Schema(implementation = Point.class, name = "Point"),
                    name = "Point", content = @Content(mediaType = "JSON", schema = @Schema(implementation = Point.class, name = "Point")),
                    description = "An object (Point ) that contains latitude, longitude, radius and the code.",
                    in = ParameterIn.QUERY, example = "/api/user/location/zone",examples = @ExampleObject(name = "/api/user/location/zone"
                    , value = "{\n" +
                    "    \"code\": \"hospital\",\n" +
                    "    \"radius\":500,\n" +
                    "    \"latitude\": 45.77045822363855,\n" +
                    "    \"longitude\": 21.21882227116396\n" +
                    "}")),
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "Locations from a specific zone.",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = InstitutionDTO.class)), mediaType = "JSON",
                                            examples = @ExampleObject(name = "/api/user/location/zone"
                                                    , value = "[\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"Adrian Petre - doctor\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.77045822363855,\n" +
                                                    "        \"longitude\": 21.21882227116396\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"Clinica Medvarix\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.770411796381275,\n" +
                                                    "        \"longitude\": 21.21892330821231\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Fara Lucian\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.766134681679894,\n" +
                                                    "        \"longitude\": 21.218982373016388\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Mihalceanu Ioan\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.77380181601158,\n" +
                                                    "        \"longitude\": 21.2187516576721\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Motateanu Mihaela\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.773596916478965,\n" +
                                                    "        \"longitude\": 21.22068583254088\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Nova Avramed\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.76624204893813,\n" +
                                                    "        \"longitude\": 21.21894251904905\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"Centrul Medical Dr. Popa\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.77135397598815,\n" +
                                                    "        \"longitude\": 21.223291663507098\n" +
                                                    "    }," +
                                                    "...." +
                                                    "]")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/zone"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Bad Object Request.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Bad Object Request. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "404", description = "If it doesn't exist.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/zone"
                                            , value = "{\n" +
                                            "    \"status\": \"NOT_FOUND\",\n" +
                                            "    \"message\": \"Object not found\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Object not found because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "406", description = "If the type doesn't exist.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/zone"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Operation not permitted.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Operation not permitted. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "500", description = "If it is server related",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/zone"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public List<InstitutionDTO> getSpecificLocationsFromZone(@Valid @RequestBody Point point){
        return userService.getLocationsFromZone(point);
    }

    @ResponseBody
    @PostMapping("/all")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves every type of locations from a given zone by a point in the map.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "An object" +
                    " that contains a latitude, longitude, radius and the code where : latitude and longitude speak" +
                    " for themselves, code which represents the institution and radius the circle size where the location are. " +
                    "Here the code isn't used because I return every type of location in that zone.",
                    content = @Content(schema = @Schema(implementation = InstitutionDTO.class),
                            examples = @ExampleObject(name = "/api/user/location/all"
                                    , value = "{\n" +
                                    "    \"code\": \"hospital\",\n" +
                                    "    \"radius\":500,\n" +
                                    "    \"latitude\": 45.77045822363855,\n" +
                                    "    \"longitude\": 21.21882227116396\n" +
                                    "}"))),
            description = "This method is of type POST which you can access on the endpoint of /api/user/location/all" +
                    " that will return locations from every institution and a specific radius.",
            parameters = @Parameter(required = true, schema = @Schema(implementation = Point.class, name = "Point"),
                    name = "Point", content = @Content(mediaType = "JSON", schema = @Schema(implementation = Point.class, name = "Point")),
                    description = "An object (Point ) that contains latitude, longitude, radius and the code.",
                    in = ParameterIn.QUERY, example = "/user/location/all",examples = @ExampleObject(name = "/user/location/all"
                    , value = "{\n" +
                    "    \"code\": \"hospital\",\n" +
                    "    \"radius\":500,\n" +
                    "    \"latitude\": 45.77045822363855,\n" +
                    "    \"longitude\": 21.21882227116396\n" +
                    "}")),
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "Every type of institution locations from a zone.",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = InstitutionDTO.class)), mediaType = "JSON",
                                            examples = @ExampleObject(name = "/user/location/all"
                                                    , value = "[...." +
                                                    "{\n" +
                                                    "        \"name\": \"Statie RATT - Calea Torontalului colt cu Liege retur\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.7723693297404,\n" +
                                                    "        \"longitude\": 21.216583982803286\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"Adrian Petre - doctor\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.77045822363855,\n" +
                                                    "        \"longitude\": 21.21882227116396\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"Clinica Medvarix\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.770411796381275,\n" +
                                                    "        \"longitude\": 21.21892330821231\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Fara Lucian\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.766134681679894,\n" +
                                                    "        \"longitude\": 21.218982373016388\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Mihalceanu Ioan\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.77380181601158,\n" +
                                                    "        \"longitude\": 21.2187516576721\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Motateanu Mihaela\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.773596916478965,\n" +
                                                    "        \"longitude\": 21.22068583254088\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Nova Avramed\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.76624204893813,\n" +
                                                    "        \"longitude\": 21.21894251904905\n" +
                                                    "    }," +
                                                    "...]")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/all"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Bad Object Request.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Bad Object Request. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "404", description = "If it doesn't exist.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/all"
                                            , value = "{\n" +
                                            "    \"status\": \"NOT_FOUND\",\n" +
                                            "    \"message\": \"Object not found\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Object not found because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "406", description = "If the type doesn't exist.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/all"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Operation not permitted.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Operation not permitted. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "500", description = "If it is server related",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/all"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public List<InstitutionDTO> getAllLocationsFromZone(@Valid @RequestBody Point point) {
        return userService.getAllLocationsFromZone(point);
    }

    @ResponseBody
    @PostMapping("/shortest")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves the specific location type location closest to the given point",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, description = "An object" +
                    " that contains a latitude, longitude, radius and the code where : latitude and longitude speak" +
                    " for themselves, code which represents the institution and radius the circle size where the location are. ",
                    content = @Content(schema = @Schema(implementation = Point.class),
                    examples = @ExampleObject(name = "/api/user/location/shortest"
                            , value = "{\n" +
                            "    \"code\": \"hospital\",\n" +
                            "    \"radius\":500,\n" +
                            "    \"latitude\": 45.77045822363855,\n" +
                            "    \"longitude\": 21.21882227116396\n" +
                            "}"))),
            description = "This method is of type POST which you can access on the endpoint of /api/user/location/shortest" +
                    " that will returns the specific location type location closest to the given point in that radius.",
            parameters = @Parameter(required = true, schema = @Schema(implementation = Point.class, name = "Point"),
                    name = "Point", content = @Content(mediaType = "JSON", schema = @Schema(implementation = Point.class, name = "Point")),
                    description = "An object (Point) that contains latitude, longitude, radius and the code.",
                    in = ParameterIn.QUERY, example = "/api/user/location/shortest",examples = @ExampleObject(name = "/api/user/location/shortest"
                    , value = "{\n" +
                    "    \"code\": \"hospital\",\n" +
                    "    \"radius\":500,\n" +
                    "    \"latitude\": 45.77045822363855,\n" +
                    "    \"longitude\": 21.21882227116396\n" +
                    "}")),
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "The specific location type location closest to the given point.",
                                    content = @Content(schema = @Schema(implementation = InstitutionDTO.class), mediaType = "JSON",
                                            examples = @ExampleObject(name = "/api/user/location/shortest"
                                                    , value = "{\n" +
                                                    "    \"name\": \"Spitalul Municipal Lugoj\",\n" +
                                                    "    \"code\": \"hospital\",\n" +
                                                    "    \"latitude\": 45.690420066015456,\n" +
                                                    "    \"longitude\": 21.88938388095096\n" +
                                                    "}")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/shortest"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Bad Object Request.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Bad Object Request. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "404", description = "If it doesn't exist.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/shortest"
                                            , value = "{\n" +
                                            "    \"status\": \"NOT_FOUND\",\n" +
                                            "    \"message\": \"Object not found\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Object not found because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "406", description = "If the type doesn't exist.",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/shortest"
                                            , value = "{\n" +
                                            "    \"status\": \"BAD_REQUEST\",\n" +
                                            "    \"message\": \"Operation not permitted.\",\n" +
                                            "    \"errors\": [\n" +
                                            "        \"Operation not permitted. because something inside the object or the object was : null\"\n" +
                                            "    ]\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "500", description = "If it is server related",
                            content = @Content(schema = @Schema(implementation = HttpClientErrorException.BadRequest.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/shortest"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public InstitutionDTO getShortestLocationFromZone(@Valid @RequestBody Point point){
        return userService.getShortestLocationFromZone(point);
    }

    @ResponseBody
    @GetMapping("/get/name/{name}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves a location of any type from a city by it's name from the database.",
            description = "This method is of type GET which you can access on the endpoint of /api/user/location/name/{name}" +
                    " where the name of a institution.It will return the institution available" +
                    " in the database from that city by that name.",
            parameters = {
                    @Parameter(name = "name", description = "The name of the institution", required = true,
                            example = "Policlinica Sanatatea",
                            schema = @Schema(implementation = String.class, type = "JSON"),
                            in = ParameterIn.PATH)},
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "An institution",
                            content = @Content(schema = @Schema(implementation = InstitutionDTO.class), mediaType = "JSON",
                                    examples = @ExampleObject(name = "/api/user/location/name/{name}",
                                            value = "{\n" +
                                                    "    \"name\": \"C. M. Dalmed\",\n" +
                                                    "    \"code\": \"hospital\",\n" +
                                                    "    \"latitude\": 45.77404032011598,\n" +
                                                    "    \"longitude\": 21.238858839811655\n" +
                                                    "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/user/location/name/{name}"
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
                                    examples = @ExampleObject(name = "/api/user/location/name/{name}"
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
                                    examples = @ExampleObject(name = "/api/user/location/name/{name}"
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
                                    examples = @ExampleObject(name = "/api/user/location/name/{name}"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public InstitutionDTO getLocationByName(@PathVariable(name = "name") String name) {
        return this.userService.getLocationByName(name);
    }
}
