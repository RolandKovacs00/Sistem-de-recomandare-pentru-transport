package com.krmapsclientrepository.controller;

import com.krmapsclientrepository.dto.TransportInstitutionDTO;
import com.krmapsclientrepository.exceptions.utils.APIError;
import com.krmapsclientrepository.service.TransportInstitutionService;
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
@RequestMapping("/transport/locations")
@Tag(name = "TransportLocationsController", description = "This controller is used to retrieve data about every " +
        "transport institution from a given city (for now is just Timisoara)")
public class TransportLocationsController {

    private final TransportInstitutionService transportInstitutionService;

    @Autowired
    public TransportLocationsController(TransportInstitutionService transportInstitutionService) {
        this.transportInstitutionService = transportInstitutionService;
    }


    @ResponseBody
    @GetMapping("/get")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves all transport locations from a city.",
            description = "This method is of type GET which you can access on the endpoint of /api/transport/locations/get" +
                    " that will return every transport institution available in the database.",
            parameters = @Parameter(name = "NONE", description = "None needed"),
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "List of transport institutions",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransportInstitutionDTO.class)), mediaType = "JSON",
                                    examples = @ExampleObject(name = "/api/transport/locations/get",
                                            value = "[\n" +
                                                    "    {\n" +
                                                    "        \"id\": 1,\n" +
                                                    "        \"name\": \"Statie RATT - Biserica Mehala retur\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.76443110057343,\n" +
                                                    "        \"longitude\": 21.20489739748382\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 2,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul Cetatii colt cu str. Martir Cernaianu\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.76541927060987,\n" +
                                                    "        \"longitude\": 21.21298689814762\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 3,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul I. C. Bratianu\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.75322212281946,\n" +
                                                    "        \"longitude\": 21.228898533729534\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 4,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul Dambovita retur\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.7401462989375,\n" +
                                                    "        \"longitude\": 21.196118186508215\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 5,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul 3 August 1919\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.75682658735349,\n" +
                                                    "        \"longitude\": 21.244884072998957\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 6,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul Cetatii colt cu str. Matei Basarab\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.767927040416936,\n" +
                                                    "        \"longitude\": 21.217127771827677\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 7,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul Cetatii colt cu str. Gheorghe Lazar\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.762988732166725,\n" +
                                                    "        \"longitude\": 21.210656761324913\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 8,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul Regele Ferdinand colt cu str. Piatra Craiului\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.7521261926772,\n" +
                                                    "        \"longitude\": 21.222837314732487\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 9,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul Mihai Eminescu\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.75264524904354,\n" +
                                                    "        \"longitude\": 21.23258803802878}" +
                                                    "....." +
                                                    "]")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/transport/locations/get"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public List<TransportInstitutionDTO> getAllTransportLocations(){
        return transportInstitutionService.getAllTransportLocations();
    }

    @ResponseBody
    @GetMapping("/get/{type}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves a type of transport location from a city.",
            description = "This method is of type GET which you can access on the endpoint of /api/transport/locations/get/{type}" +
                    " where type represents the type of transport institution like bus stations and so on." +
                    "It will return every type of transport institution available in the database from that city.",
            parameters = @Parameter(name = "type", description = "The type of transport institution", required = true,
                    example = "bus-stop",
                    schema = @Schema(implementation = String.class, type = "string"),
                    in = ParameterIn.PATH),
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "List of a type of transport institution",
                                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = TransportInstitutionDTO.class)), mediaType = "JSON",
                                    examples = @ExampleObject(name = "/api/transport/locations/get/{type}",
                                            value = "[\n" +
                                                    "    {\n" +
                                                    "        \"id\": 1,\n" +
                                                    "        \"name\": \"Statie RATT - Biserica Mehala retur\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.76443110057343,\n" +
                                                    "        \"longitude\": 21.20489739748382\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 2,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul Cetatii colt cu str. Martir Cernaianu\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.76541927060987,\n" +
                                                    "        \"longitude\": 21.21298689814762\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 3,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul I. C. Bratianu\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.75322212281946,\n" +
                                                    "        \"longitude\": 21.228898533729534\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"id\": 4,\n" +
                                                    "        \"name\": \"Statie RATT - Bulevardul Dambovita retur\",\n" +
                                                    "        \"code\": \"bus-stop\",\n" +
                                                    "        \"latitude\": 45.7401462989375,\n" +
                                                    "        \"longitude\": 21.196118186508215\n" +
                                                    "    }," +
                                                    "...." +
                                                    "]")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/transport/locations/get/{type}"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get/{type}"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get/{type}"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get/{type}"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public List<TransportInstitutionDTO> getPreferredTransportLocations(@PathVariable("type") String code){
        return transportInstitutionService.getPreferredTransportLocations(code);
    }

    @ResponseBody
    @GetMapping("/get/bus/station/name/{name}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves a bus station location from a city by it's name from the database.",
            description = "This method is of type GET which you can access on the endpoint of /api/transport/locations/get/bus/station/name/{name}" +
                    " where code represents the type of transport institution like bus station and so on, and name represents " +
                    "the name of the transport institution.It will return the transport institution available in the database from that city by that name.",
            parameters = {@Parameter(name = "name", description = "The name of the transport institution", required = true,
                            example = "Statie RATT - Bulevardul Regele Ferdinand colt cu str. Piatra Craiului",
                            schema = @Schema(implementation = String.class, type = "string"),
                            in = ParameterIn.PATH)},
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "A transport institution",
                                    content = @Content(schema = @Schema(implementation = TransportInstitutionDTO.class), mediaType = "JSON",
                                    examples = @ExampleObject(name = "/api/transport/locations/get/bus/station/name/{name}",
                                            value = "{\n" +
                                                    "    \"id\": 8,\n" +
                                                    "    \"name\": \"Statie RATT - Bulevardul Regele Ferdinand colt cu str. Piatra Craiului\",\n" +
                                                    "    \"code\": \"bus-stop\",\n" +
                                                    "    \"latitude\": 45.7521261926772,\n" +
                                                    "    \"longitude\": 21.222837314732487\n" +
                                                    "}")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/transport/locations/get/bus/station/name/{name}"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get/bus/station/name/{name}"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get/bus/station/name/{name}"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get/bus/station/name/{name}"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public TransportInstitutionDTO getBusStationLocationByName(@PathVariable("name") String name){
        return transportInstitutionService.getBusStationLocationByName(name);
    }

    @ResponseBody
    @GetMapping("/get/bus/station/id/{id}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves a bus station location from a city by it's id from the database.",
            description = "This method is of type GET which you can access on the endpoint of /api/transport/locations/get/bus/station/id/{id}" +
                    " where code represents the type of transport institution like bus stations and so on, and id represents " +
                    "the id of the transport institution.It will return the transport institution available in the database from that city by that id.",
            parameters = {@Parameter(name = "id", description = "The name of the transport institution", required = true,
                            example = "12",
                            schema = @Schema(implementation = Long.class, type = "long", format = "long"),
                            in = ParameterIn.PATH)},
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "A transport institution",
                                    content = @Content(schema = @Schema(implementation = TransportInstitutionDTO.class), mediaType = "JSON",
                                    examples = @ExampleObject(name = "/api/transport/locations/get/bus/station/id/{id}",
                                            value = "{\n" +
                                                    "    \"id\": 12,\n" +
                                                    "    \"name\": \"Statie RATT - Bulevardul Cetatii colt cu str. Stelelor\",\n" +
                                                    "    \"code\": \"bus-stop\",\n" +
                                                    "    \"latitude\": 45.7678287240655,\n" +
                                                    "    \"longitude\": 21.216938891453765\n" +
                                                    "}")),
                                    links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/transport/locations/get/bus/station/id/{id}"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get/bus/station/id/{id}"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get/bus/station/id/{id}"
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
                                    examples = @ExampleObject(name = "/api/transport/locations/get/bus/station/id/{id}"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public TransportInstitutionDTO getBusStationLocationById(@PathVariable("id") Long id){
        return transportInstitutionService.getBusStationLocationById(id);
    }
}