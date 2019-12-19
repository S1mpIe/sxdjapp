package com.finals.sxdj.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author S1mpIe
 */
@Setter
@Getter
@ToString
public class Navigation {
    private String articleId;
    private String articleImageUrl;

    public Navigation() {
    }

    public Navigation(String articleId, String articleImageUrl) {
        this.articleId = articleId;
        this.articleImageUrl = articleImageUrl;
    }
}
