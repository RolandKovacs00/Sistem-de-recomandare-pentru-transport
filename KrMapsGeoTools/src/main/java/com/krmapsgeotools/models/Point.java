package com.krmapsgeotools.models;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(type = "POJO, JSON",
        description = "A POJO that holds a point on the map and because it has the 'implements Serializable'," +
                " it can be given to the web app as JSON.",
        name = "Point Object",
        example = "{\n" +
                "    \"code\": \"hospital\",\n" +
                "    \"radius\":500,\n" +
                "    \"latitude\": 45.77045822363855,\n" +
                "    \"longitude\": 21.21882227116396\n" +
                "}",
        title = "Point",
        required = true
)
public class Point{

    @NotNull(message = "The string code may not be null")
    @NotEmpty(message = "The string code may not be empty")
    @NotBlank(message = "The string code may not be blank")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    @Schema(name = "code",
            description = "The code field that will hold the institution type like : hospital, pharmacy etc.",
            type = "String",
            required = true,
            example = "hospital"
    )
    private String code;

    @NotNull(message = "The string radius may not be null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Schema(name = "radius",
            description = "The radius of the search area.",
            type = "Long",
            required = true,
            example = "300"
    )
    private Long radius;

    @NotNull(message = "The string latitude may not be null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Schema(name = "latitude",
            description = "The latitude field that will hold the institution type latitude.",
            type = "double",
            required = true,
            example = "45.766134681679894"
    )
    private BigDecimal latitude;

    @NotNull(message = "The string longitude may not be null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Schema(name = "longitude",
            description = "The longitude field that will hold the institution type longitude.",
            type = "double",
            required = true,
            example = "21.218982373016388"
    )
    private BigDecimal longitude;
}
