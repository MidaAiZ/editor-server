package com.mida.chromeext.modules.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
@Lazy
@Scope("prototype")
@ConfigurationProperties(prefix = "user-setting")
@PropertySource(value = "classpath:user-setting-default.properties")
public class DefaultUserSettingDto implements Serializable {
    private String bgSrc;
    private Float maskOpacityValue;
    private Float bgBlurValue;
    private Boolean newSiteNewTabValue;
    private Boolean searchResultNewTabValue;
    private Boolean openMarkNewTabValue;
    private Boolean historyNewTabValue;
    private Boolean randomBgBtn;
    private Boolean showOftenUsedBar;
    private Boolean showEmailBtn;
    private Boolean showBookMarkBar;
    private Boolean hideAllIcons;
    private Float iconRadiusValue;
    private Float iconSizeValue;
    private Boolean hideSearchBarValue;
    private Boolean hideSearchTypeValue;
    private Float searchBarRadiusValue;
    private Float searchBarSizeValue;
    private Float searchBarOpacityValue;
    private Float fontSizeValue;
    private String fontColorValue;
    private Boolean cloudSave;
    private LayoutSetting iconLayout;

    public String getBgSrc() {
        return bgSrc;
    }

    public void setBgSrc(String bgSrc) {
        this.bgSrc = bgSrc;
    }

    public Float getMaskOpacityValue() {
        return maskOpacityValue;
    }

    public void setMaskOpacityValue(Float maskOpacityValue) {
        this.maskOpacityValue = maskOpacityValue;
    }

    public Float getBgBlurValue() {
        return bgBlurValue;
    }

    public void setBgBlurValue(Float bgBlurValue) {
        this.bgBlurValue = bgBlurValue;
    }

    public Boolean getNewSiteNewTabValue() {
        return newSiteNewTabValue;
    }

    public void setNewSiteNewTabValue(Boolean newSiteNewTabValue) {
        this.newSiteNewTabValue = newSiteNewTabValue;
    }

    public Boolean getSearchResultNewTabValue() {
        return searchResultNewTabValue;
    }

    public void setSearchResultNewTabValue(Boolean searchResultNewTabValue) {
        this.searchResultNewTabValue = searchResultNewTabValue;
    }

    public Boolean getOpenMarkNewTabValue() {
        return openMarkNewTabValue;
    }

    public void setOpenMarkNewTabValue(Boolean openMarkNewTabValue) {
        this.openMarkNewTabValue = openMarkNewTabValue;
    }

    public Boolean getHistoryNewTabValue() {
        return historyNewTabValue;
    }

    public void setHistoryNewTabValue(Boolean historyNewTabValue) {
        this.historyNewTabValue = historyNewTabValue;
    }

    public Boolean getRandomBgBtn() {
        return randomBgBtn;
    }

    public void setRandomBgBtn(Boolean randomBgBtn) {
        this.randomBgBtn = randomBgBtn;
    }

    public Boolean getShowOftenUsedBar() {
        return showOftenUsedBar;
    }

    public void setShowOftenUsedBar(Boolean showOftenUsedBar) {
        this.showOftenUsedBar = showOftenUsedBar;
    }

    public Boolean getShowEmailBtn() {
        return showEmailBtn;
    }

    public void setShowEmailBtn(Boolean showEmailBtn) {
        this.showEmailBtn = showEmailBtn;
    }

    public Boolean getShowBookMarkBar() {
        return showBookMarkBar;
    }

    public void setShowBookMarkBar(Boolean showBookMarkBar) {
        this.showBookMarkBar = showBookMarkBar;
    }

    public Boolean getHideAllIcons() {
        return hideAllIcons;
    }

    public void setHideAllIcons(Boolean hideAllIcons) {
        this.hideAllIcons = hideAllIcons;
    }

    public Float getIconRadiusValue() {
        return iconRadiusValue;
    }

    public void setIconRadiusValue(Float iconRadiusValue) {
        this.iconRadiusValue = iconRadiusValue;
    }

    public Float getIconSizeValue() {
        return iconSizeValue;
    }

    public void setIconSizeValue(Float iconSizeValue) {
        this.iconSizeValue = iconSizeValue;
    }

    public Boolean getHideSearchBarValue() {
        return hideSearchBarValue;
    }

    public void setHideSearchBarValue(Boolean hideSearchBarValue) {
        this.hideSearchBarValue = hideSearchBarValue;
    }

    public Boolean getHideSearchTypeValue() {
        return hideSearchTypeValue;
    }

    public void setHideSearchTypeValue(Boolean hideSearchTypeValue) {
        this.hideSearchTypeValue = hideSearchTypeValue;
    }

    public Float getSearchBarRadiusValue() {
        return searchBarRadiusValue;
    }

    public void setSearchBarRadiusValue(Float searchBarRadiusValue) {
        this.searchBarRadiusValue = searchBarRadiusValue;
    }

    public Float getSearchBarSizeValue() {
        return searchBarSizeValue;
    }

    public void setSearchBarSizeValue(Float searchBarSizeValue) {
        this.searchBarSizeValue = searchBarSizeValue;
    }

    public Float getSearchBarOpacityValue() {
        return searchBarOpacityValue;
    }

    public void setSearchBarOpacityValue(Float searchBarOpacityValue) {
        this.searchBarOpacityValue = searchBarOpacityValue;
    }

    public Float getFontSizeValue() {
        return fontSizeValue;
    }

    public void setFontSizeValue(Float fontSizeValue) {
        this.fontSizeValue = fontSizeValue;
    }

    public String getFontColorValue() {
        return fontColorValue;
    }

    public void setFontColorValue(String fontColorValue) {
        this.fontColorValue = fontColorValue;
    }

    public Boolean getCloudSave() {
        return cloudSave;
    }

    public void setCloudSave(Boolean cloudSave) {
        this.cloudSave = cloudSave;
    }

    public LayoutSetting getIconLayout() {
        return iconLayout;
    }

    public void setIconLayout(LayoutSetting iconLayout) {
        this.iconLayout = iconLayout;
    }

    @Component
    @Scope("prototype")
    @ConfigurationProperties(prefix = "user-setting.icon-layout")
    @PropertySource(value = "classpath:user-setting-default.properties")
    static class LayoutSetting implements Serializable {
        private String name;
        private int row;
        private int col;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getRow() {
            return row;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }
    }
}