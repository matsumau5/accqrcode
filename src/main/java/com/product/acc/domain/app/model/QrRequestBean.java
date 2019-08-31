package com.product.acc.domain.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class QrRequestBean {
    @Size(max=10, min=2)
    @NotNull
    private String requestKey;
}
