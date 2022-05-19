package com.krmapsclientrepository.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Schema(type = "POJO, JSON",
        description = "A POJO that holds every medical institution and because it has the 'implements Serializable'," +
                " it can be given to the web app as JSON.",
        name = "Medical Institution Object",
        example = "{\n" +
                "        \"name\": \"C. M. Dr. Fara Lucian\",\n" +
                "        \"code\": \"hospital\",\n" +
                "        \"latitude\": 45.766134681679894,\n" +
                "        \"longitude\": 21.218982373016388\n" +
                "}",
        title = "Medical Institution",
        required = true
)
public class MedicalInstitutionDTO {

    @NotNull(message = "The string name may not be null")
    @NotEmpty(message = "The string name may not be empty")
    @NotBlank(message = "The string name may not be blank")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    @Schema(name = "name",
            description = "The name field that will hold the medical institution name.",
            type = "String",
            required = true,
            example = "C. M. Dr. Fara Lucian"
    )
    private String name;

    @NotNull(message = "The string code may not be null")
    @NotEmpty(message = "The string code may not be empty")
    @NotBlank(message = "The string code may not be blank")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    @Schema(name = "code",
            description = "The code field that will hold the medical institution type like : hospital, pharmacy etc.",
            type = "String",
            required = true,
            example = "hospital"
    )
    private String code;

    @NotNull(message = "The string latitude may not be null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Schema(name = "latitude",
            description = "The latitude field that will hold the medical institution latitude.",
            type = "BigDecimal",
            required = true,
            example = "45.766134681679894"
    )
    private BigDecimal latitude;

    @NotNull(message = "The string longitude may not be null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Schema(name = "longitude",
            description = "The longitude field that will hold the medical institution longitude.",
            type = "BigDecimal",
            required = true,
            example = "21.218982373016388"
    )
    private BigDecimal longitude;
}
