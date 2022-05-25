package com.krmapsfeigngeotools.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ObjectWrapper {

    @NotNull(message = "The startingDistance can't be null")
    private Point startingDistance;

    @NotNull(message = "The finishDestination can't be null")
    private Point finishDestination;
}
