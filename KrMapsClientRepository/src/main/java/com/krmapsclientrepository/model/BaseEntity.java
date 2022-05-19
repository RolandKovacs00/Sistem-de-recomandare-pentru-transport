package com.krmapsclientrepository.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@MappedSuperclass
@Schema(implementation = BaseEntity.class,
        description = "A base entity which holds the id and extends the other classes," +
        " that use an id.",
        name = "BaseEntity",
        required = true,
        accessMode = Schema.AccessMode.READ_ONLY,
        type = "JSON",
        title = "Base Entity"
)
public class BaseEntity implements Serializable {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(name = "id",description = "The id of the every POJO given to the web application.")
    private Long id;
}
