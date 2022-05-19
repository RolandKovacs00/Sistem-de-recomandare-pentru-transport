package com.krmapsclientrepository.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name = "public_institution")
@Schema(type = "POJO, JSON",
        description = "A POJO that holds every public institution and because it has the 'implements Serializable'," +
                " it can be given to the web app as JSON.",
        name = "Public Institution Object",
        example = "{\n" +
                "        \"id\": 47,\n" +
                "        \"name\": \"Scoala Generala nr. 26\",\n" +
                "        \"code\": \"school\",\n" +
                "        \"latitude\": 45.766180493702095,\n" +
                "        \"longitude\": 21.21715439590912\n" +
                "}",
        title = "Public Institution",
        required = true
)
public class PublicInstitution extends BaseEntity implements Serializable {

    @Column(name = "nume")
    @NotNull(message = "The string name may not be null")
    @NotEmpty(message = "The string name may not be empty")
    @NotBlank(message = "The string name may not be blank")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    @Schema(name = "name",
            description = "The name field that will hold the public institution name.",
            type = "String",
            required = true,
            example = "Scoala Generala nr. 26"
    )
    private String name;

    @Column(name = "code")
    @NotNull(message = "The string code may not be null")
    @NotEmpty(message = "The string code may not be empty")
    @NotBlank(message = "The string code may not be blank")
    @Pattern(regexp="^[A-Za-z]*$",message = "Invalid Input")
    @Schema(name = "code",
            description = "The code field that will hold the public institution type like : school, university etc.",
            type = "String",
            required = true,
            example = "school"
    )
    private String code;

    @Column(name = "latitude")
    @NotNull(message = "The string latitude may not be null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Schema(name = "latitude",
            description = "The latitude field that will hold the public institution latitude.",
            type = "BigDecimal",
            required = true,
            example = "45.766134681679894"
    )
    private BigDecimal latitude;

    @Column(name = "longitude")
    @NotNull(message = "The string longitude may not be null")
    @NumberFormat(style = NumberFormat.Style.NUMBER)
    @Schema(name = "longitude",
            description = "The longitude field that will hold the public institution longitude.",
            type = "BigDecimal",
            required = true,
            example = "21.218982373016388"
    )
    private BigDecimal longitude;
}
