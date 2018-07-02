
package com.ai.slack.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "fallback",
    "color",
    "pretext",
    "author_name",
    "author_link",
    "author_icon",
    "title",
    "title_link",
    "text",
    "fields",
    "image_url",
    "thumb_url",
    "footer",
    "footer_icon",
    "ts"
})
public class Attachment {

    @JsonProperty("fallback")
    private String fallback;
    @JsonProperty("color")
    private String color;
    @JsonProperty("pretext")
    private String pretext;
    @JsonProperty("author_name")
    private String authorName;
    @JsonProperty("author_link")
    private String authorLink;
    @JsonProperty("author_icon")
    private String authorIcon;
    @JsonProperty("title")
    private String title;
    @JsonProperty("title_link")
    private String titleLink;
    @JsonProperty("text")
    private String text;
    @JsonProperty("fields")
    private List<Field> fields = null;
    @JsonProperty("image_url")
    private String imageUrl;
    @JsonProperty("thumb_url")
    private String thumbUrl;
    @JsonProperty("footer")
    private String footer;
    @JsonProperty("footer_icon")
    private String footerIcon;
    @JsonProperty("ts")
    private Integer ts;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("fallback")
    public String getFallback() {
        return fallback;
    }

    @JsonProperty("fallback")
    public void setFallback(String fallback) {
        this.fallback = fallback;
    }

    @JsonProperty("color")
    public String getColor() {
        return color;
    }

    @JsonProperty("color")
    public void setColor(String color) {
        this.color = color;
    }

    @JsonProperty("pretext")
    public String getPretext() {
        return pretext;
    }

    @JsonProperty("pretext")
    public void setPretext(String pretext) {
        this.pretext = pretext;
    }

    @JsonProperty("author_name")
    public String getAuthorName() {
        return authorName;
    }

    @JsonProperty("author_name")
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    @JsonProperty("author_link")
    public String getAuthorLink() {
        return authorLink;
    }

    @JsonProperty("author_link")
    public void setAuthorLink(String authorLink) {
        this.authorLink = authorLink;
    }

    @JsonProperty("author_icon")
    public String getAuthorIcon() {
        return authorIcon;
    }

    @JsonProperty("author_icon")
    public void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }

    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty("title_link")
    public String getTitleLink() {
        return titleLink;
    }

    @JsonProperty("title_link")
    public void setTitleLink(String titleLink) {
        this.titleLink = titleLink;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

    @JsonProperty("fields")
    public List<Field> getFields() {
        return fields;
    }

    @JsonProperty("fields")
    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    @JsonProperty("image_url")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("image_url")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("thumb_url")
    public String getThumbUrl() {
        return thumbUrl;
    }

    @JsonProperty("thumb_url")
    public void setThumbUrl(String thumbUrl) {
        this.thumbUrl = thumbUrl;
    }

    @JsonProperty("footer")
    public String getFooter() {
        return footer;
    }

    @JsonProperty("footer")
    public void setFooter(String footer) {
        this.footer = footer;
    }

    @JsonProperty("footer_icon")
    public String getFooterIcon() {
        return footerIcon;
    }

    @JsonProperty("footer_icon")
    public void setFooterIcon(String footerIcon) {
        this.footerIcon = footerIcon;
    }

    @JsonProperty("ts")
    public Integer getTs() {
        return ts;
    }

    @JsonProperty("ts")
    public void setTs(Integer ts) {
        this.ts = ts;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
