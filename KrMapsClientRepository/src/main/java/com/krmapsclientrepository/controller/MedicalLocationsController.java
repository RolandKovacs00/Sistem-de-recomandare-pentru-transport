package com.krmapsclientrepository.controller;

import com.krmapsclientrepository.dto.MedicalInstitutionDTO;
import com.krmapsclientrepository.exceptions.utils.APIError;
import com.krmapsclientrepository.service.MedicalInstitutionService;
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
@RequestMapping("/medical/locations")
@Tag(name = "MedicalLocationsController", description = "This controller is used to retrieve data about every " +
        "medical institution from a given city (for now is just Timisoara)")
public class MedicalLocationsController {

    private final MedicalInstitutionService medicalInstitutionService;

    @Autowired
    public MedicalLocationsController(MedicalInstitutionService medicalInstitutionService) {
        this.medicalInstitutionService = medicalInstitutionService;
    }

    @ResponseBody
    @GetMapping("/get")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves all medical locations from a city.",
            description = "This method is of type GET which you can access on the endpoint of /api/medical/locations/get" +
                    "that will return every medical institution available in the database.",
                    parameters = @Parameter(name = "NONE", description = "None needed"),
                    responses = {
                            @ApiResponse(responseCode = "OK - 200", description = "List of medical institutions",
                                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MedicalInstitutionDTO.class)), mediaType = "JSON",
                                            examples = @ExampleObject(
                                                    name = "/api/medical/locations/get",
                                                    value = "[\n" +
                                                            "    {\n" +
                                                            "        \"name\": \"Avicenna\",\n" +
                                                            "        \"code\": \"pharmacy\",\n" +
                                                            "        \"latitude\": 45.7493014548189,\n" +
                                                            "        \"longitude\": 21.208001411361693\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"name\": \"Catena - Ciprian Porumbescu\",\n" +
                                                            "        \"code\": \"pharmacy\",\n" +
                                                            "        \"latitude\": 45.739003055098536,\n" +
                                                            "        \"longitude\": 21.21940612753906\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"name\": \"Help Net 36 - Naturii\",\n" +
                                                            "        \"code\": \"pharmacy\",\n" +
                                                            "        \"latitude\": 45.7583091,\n" +
                                                            "        \"longitude\": 21.2260092\n" +
                                                            "    },\n" +
                                                            "    {\n" +
                                                            "        \"name\": \"Catena - Badea Cartan\",\n" +
                                                            "        \"code\": \"pharmacy\",\n" +
                                                            "        \"latitude\": 45.76169193612952,\n" +
                                                            "        \"longitude\": 21.247584105896067\n" +
                                                            "    }," +
                                                            "...." +
                                                            "]"
                                            )),
                                    links = @Link(name = "NONE")),
                            @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                                    content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                            examples = @ExampleObject(name = "/api/medical/locations/get"
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
                                            examples = @ExampleObject(name = "/api/medical/locations/get"
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
                                            examples = @ExampleObject(name = "/api/medical/locations/get"
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
                                            examples = @ExampleObject(name = "/api/medical/locations/get"
                                                    , value = "Sever related error")),
                                    links = @Link(name = "NONE"))})
    public List<MedicalInstitutionDTO> getAllMedicalLocations(){
        return medicalInstitutionService.getAllMedicalLocations();
    }

    @ResponseBody
    @GetMapping("/get/{type}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves a type of medical location from a city.",
            description = "This method is of type GET which you can access on the endpoint of /api/medical/locations/get/{type}" +
                    "where type represents the type of medical institution like hospital and so on." +
                    "It will return every given type of medical institution available in the database from that city.",
            parameters = @Parameter(name = "type", description = "The type of medical institution", required = true,
                    example = "hospital",
            schema = @Schema(implementation = String.class, type = "string"),
            in = ParameterIn.PATH),
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "List of a type of medical institution",
                            content = @Content(array = @ArraySchema(schema = @Schema(implementation = MedicalInstitutionDTO.class)), mediaType = "JSON",
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{type}",
                                            value = "[\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"Adrian Petre - doctor\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.77045822363855,\n" +
                                                    "        \"longitude\": 21.21882227116396\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Cardiovest\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.75454019215455,\n" +
                                                    "        \"longitude\": 21.22144999206546\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dalmed\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.77404032011598,\n" +
                                                    "        \"longitude\": 21.238858839811655\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dermatomed\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.730636315826686,\n" +
                                                    "        \"longitude\": 21.210565923278864\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dermadent\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.75697397530617,\n" +
                                                    "        \"longitude\": 21.242195474729897\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dispensar XXII\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.76266928250933,\n" +
                                                    "        \"longitude\": 21.259274501049845\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Alina Babeu\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.755231699389626,\n" +
                                                    "        \"longitude\": 21.226047300663822\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Ana Ioana\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.76359361116812,\n" +
                                                    "        \"longitude\": 21.254811264418095\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Ardeleanu Elena\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.75578291423356,\n" +
                                                    "        \"longitude\": 21.234903955541995\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Barbos Carmen\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.76368725767846,\n" +
                                                    "        \"longitude\": 21.218483448410097\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Badea Rodica\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.75695583056295,\n" +
                                                    "        \"longitude\": 21.251277494442775\n" +
                                                    "    },\n" +
                                                    "    {\n" +
                                                    "        \"name\": \"C. M. Dr. Bacean\",\n" +
                                                    "        \"code\": \"hospital\",\n" +
                                                    "        \"latitude\": 45.75516902842982,\n" +
                                                    "        \"longitude\": 21.221892510836824\n" +
                                                    "    }," +
                                                    "...." +
                                                    "]")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{type}"
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
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{type}"
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
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{type}"
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
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{type}"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public List<MedicalInstitutionDTO> getPreferredMedicalLocations(@PathVariable("type") String code){
        return medicalInstitutionService.getPreferredMedicalLocations(code);
    }

    @ResponseBody
    @GetMapping("/get/{code}/name/{name}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves a type of medical location from a city by it's name from the database.",
            description = "This method is of type GET which you can access on the endpoint of /api/medical/locations/get/{code}/name/{name}" +
                    " where code represents the type of medical institution like hospital, pharmacy and so on, and name represents " +
                    "the name of the medical institution.It will return the medical institution available in the database from that city by that name.",
            parameters = {
                        @Parameter(name = "code", description = "The type of medical institution", required = true,
                                example = "hospital",
                                schema = @Schema(implementation = String.class, type = "string"),
                                in = ParameterIn.PATH),
                          @Parameter(name = "name", description = "The name of the medical institution", required = true,
                                  example = "Policlinica Sanatatea",
                                  schema = @Schema(implementation = String.class, type = "JSON"),
                                  in = ParameterIn.PATH)},
            responses = {
                    @ApiResponse(responseCode = "OK - 200", description = "A medical institution",
                            content = @Content(schema = @Schema(implementation = MedicalInstitutionDTO.class), mediaType = "JSON",
                            examples = @ExampleObject(name = "/api/medical/locations/get/{code}/name/{name}",
                                    value = "{\n" +
                                            "    \"name\": \"C. M. Dalmed\",\n" +
                                            "    \"code\": \"hospital\",\n" +
                                            "    \"latitude\": 45.77404032011598,\n" +
                                            "    \"longitude\": 21.238858839811655\n" +
                                            "}")),
                            links = @Link(name = "NONE")),
                    @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                            content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{code}/name/{name}"
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
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{code}/name/{name}"
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
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{code}/name/{name}"
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
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{code}/name/{name}"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))})
    public MedicalInstitutionDTO getMedicalInstitutionLocationByName(@PathVariable(name = "code") String code,
                                                        @PathVariable(name = "name") String name){
        return medicalInstitutionService.getMedicalInstitutionByName(code,name);
    }

    @ResponseBody
    @GetMapping("/get/{code}/id/{id}")
    @ResponseStatus(value = HttpStatus.OK, code = HttpStatus.OK)
    @Operation(summary = "Retrieves a type of medical location from a city by it's id from the database.",
            description = "This method is of type GET which you can access on the endpoint of /api/medical/locations/get/{code}/id/{id}" +
                    " where code represents the type of medical institution like hospital, pharmacy and so on, and id represents " +
                    "the id of the medical institution.It will return the medical institution available in the database from that city by that id.",
            parameters = {
                    @Parameter(name = "code", description = "The type of medical institution", required = true,
                                    example = "hospital",
                                    schema = @Schema(implementation = String.class, type = "string", format = "string"),
                                    in = ParameterIn.PATH),
                    @Parameter(name = "id", description = "The name of the medical institution", required = true,
                            example = "12",
                            schema = @Schema(implementation = Long.class, type = "long", format = "long"),
                            in = ParameterIn.PATH)},
            responses = {
                            @ApiResponse(responseCode = "OK - 200", description = "A medical institution",
                                            content = @Content(schema = @Schema(implementation = MedicalInstitutionDTO.class), mediaType = "JSON",
                                            examples = @ExampleObject(name = "/api/medical/locations/get/{code}/id/{id}",
                                                    value = "{\n" +
                                                            "    \"name\": \"C. M. Dermadent\",\n" +
                                                            "    \"code\": \"hospital\",\n" +
                                                            "    \"latitude\": 45.75697397530617,\n" +
                                                            "    \"longitude\": 21.242195474729897\n" +
                                                            "}")),
                                            links = @Link(name = "NONE")),
                            @ApiResponse(responseCode = "400", description = "If the object isn't complete or bad.",
                                    content = @Content(schema = @Schema(implementation = APIError.class), mediaType = "String",
                                            examples = @ExampleObject(name = "/api/medical/locations/get/{code}/id/{id}"
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
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{code}/id/{id}"
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
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{code}/id/{id}"
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
                                    examples = @ExampleObject(name = "/api/medical/locations/get/{code}/id/{id}"
                                            , value = "Sever related error")),
                            links = @Link(name = "NONE"))}
            )
    public MedicalInstitutionDTO getMedicalInstitutionLocationById(@PathVariable(name = "code") String code,
                                                                @PathVariable("id") Long id){
        return medicalInstitutionService.getMedicalInstitutionById(code,id);
    }
}
