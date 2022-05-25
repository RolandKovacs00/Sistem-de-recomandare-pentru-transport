package com.krmapsfeignclient.model.util;

import lombok.*;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ObjectWrapper{

    @NotNull(message = "The startingDistance can't be null")
    private Point startingDistance;

    @NotNull(message = "The finishDestination can't be null")
    private Point finishDestination;
}
