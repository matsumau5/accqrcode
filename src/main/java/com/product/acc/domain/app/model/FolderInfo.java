package com.product.acc.domain.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FolderInfo {
    /** アクセスキー */
    private ResultKey resultKey;
    /** フォルダーパス */
    private String path;
}
