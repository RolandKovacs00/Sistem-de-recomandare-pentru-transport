package com.krmapsgeotools.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(type = "POJO, JSON",
        description = "A POJO that holds two points on the map and because it has the 'implements Serializable'," +
                " it can be given to the web app as JSON. This wrapped object is used to calculate different things like" +
                " the distance between two locations on the map",
        name = "ObjectWrapper Object",
        example = "{\n" +
                "  \"startingDistance\":{\n" +
                "    \"code\": \"user\",\n" +
                "    \"latitude\": 45.77045822363855,\n" +
                "    \"longitude\": 21.21882227116396\n" +
                "    },\n" +
                "  \"finishDestination\":{\n" +
                "    \"code\": \"school\",\n" +
                "    \"latitude\": 45.75529741882885,\n" +
                "    \"longitude\": 21.278155777194115\n" +
                "    }\n" +
                "}",
        title = "ObjectWrapper",
        required = true
)
public class ObjectWrapper {

    @NotNull(message = "The startingDistance can't be null")
    @Schema(description = "The starting point.",
            example = "\"startingDistance\":{\n" +
                    "    \"code\": \"user\",\n" +
                    "    \"latitude\": 45.77045822363855,\n" +
                    "    \"longitude\": 21.21882227116396\n" +
                    "    }")
    private Point startingDistance;

    @NotNull(message = "The finishDestination can't be null")
    @Schema(description = "The starting point.",
            example = "\"finishDestination\":{\n" +
                    "    \"code\": \"school\",\n" +
                    "    \"latitude\": 445.75529741882885,\n" +
                    "    \"longitude\": 21.278155777194115\n" +
                    "    }")
    private Point finishDestination;
}
