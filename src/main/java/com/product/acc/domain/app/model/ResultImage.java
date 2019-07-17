package com.product.acc.domain.app.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class ResultImage {
    @Getter
    @Setter
    /** 画像リスト*/
    private List<String> image;
}
