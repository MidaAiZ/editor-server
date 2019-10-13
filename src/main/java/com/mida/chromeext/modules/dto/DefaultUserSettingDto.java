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
    private String maskOpacityValue;
    private String bgBlurValue;
    private String newSiteNewTabValue;
    private String searchResultNewTabValue;
    private String openMarkNewTabValue;
    private String historyNewTabValue;
    private String randomBgBtn;
    private String showOftenUsedBar;
    private String showEmailBtn;
    private String showBookMarkBar;
    private String hideAllIcons;
    private String iconRadiusValue;
    private String iconSizeValue;
    private String hideSearchBarValue;
    private String hideSearchTypeValue;
    private String searchBarRadiusValue;
    private String searchBarSizeValue;
    private String searchBarOpacityValue;
    private String fontSizeValue;
    private String fontColorValue;
    private LayoutSetting iconLayout;

    public String getBgSrc() {
        return bgSrc;
    }

    public void setBgSrc(String bgSrc) {
        this.bgSrc = bgSrc;
    }

    public String getMaskOpacityValue() {
        return maskOpacityValue;
    }

    public void setMaskOpacityValue(String maskOpacityValue) {
        this.maskOpacityValue = maskOpacityValue;
    }

    public String getBgBlurValue() {
        return bgBlurValue;
    }

    public void setBgBlurValue(String bgBlurValue) {
        this.bgBlurValue = bgBlurValue;
    }

    public String getNewSiteNewTabValue() {
        return newSiteNewTabValue;
    }

    public void setNewSiteNewTabValue(String newSiteNewTabValue) {
        this.newSiteNewTabValue = newSiteNewTabValue;
    }

    public String getSearchResultNewTabValue() {
        return searchResultNewTabValue;
    }

    public void setSearchResultNewTabValue(String searchResultNewTabValue) {
        this.searchResultNewTabValue = searchResultNewTabValue;
    }

    public String getOpenMarkNewTabValue() {
        return openMarkNewTabValue;
    }

    public void setOpenMarkNewTabValue(String openMarkNewTabValue) {
        this.openMarkNewTabValue = openMarkNewTabValue;
    }

    public String getHistoryNewTabValue() {
        return historyNewTabValue;
    }

    public void setHistoryNewTabValue(String historyNewTabValue) {
        this.historyNewTabValue = historyNewTabValue;
    }

    public String getRandomBgBtn() {
        return randomBgBtn;
    }

    public void setRandomBgBtn(String randomBgBtn) {
        this.randomBgBtn = randomBgBtn;
    }

    public String getShowOftenUsedBar() {
        return showOftenUsedBar;
    }

    public void setShowOftenUsedBar(String showOftenUsedBar) {
        this.showOftenUsedBar = showOftenUsedBar;
    }

    public String getShowEmailBtn() {
        return showEmailBtn;
    }

    public void setShowEmailBtn(String showEmailBtn) {
        this.showEmailBtn = showEmailBtn;
    }

    public String getShowBookMarkBar() {
        return showBookMarkBar;
    }

    public void setShowBookMarkBar(String showBookMarkBar) {
        this.showBookMarkBar = showBookMarkBar;
    }

    public String getHideAllIcons() {
        return hideAllIcons;
    }

    public void setHideAllIcons(String hideAllIcons) {
        this.hideAllIcons = hideAllIcons;
    }

    public String getIconRadiusValue() {
        return iconRadiusValue;
    }

    public void setIconRadiusValue(String iconRadiusValue) {
        this.iconRadiusValue = iconRadiusValue;
    }

    public String getIconSizeValue() {
        return iconSizeValue;
    }

    public void setIconSizeValue(String iconSizeValue) {
        this.iconSizeValue = iconSizeValue;
    }

    public String getHideSearchBarValue() {
        return hideSearchBarValue;
    }

    public void setHideSearchBarValue(String hideSearchBarValue) {
        this.hideSearchBarValue = hideSearchBarValue;
    }

    public String getHideSearchTypeValue() {
        return hideSearchTypeValue;
    }

    public void setHideSearchTypeValue(String hideSearchTypeValue) {
        this.hideSearchTypeValue = hideSearchTypeValue;
    }

    public String getSearchBarRadiusValue() {
        return searchBarRadiusValue;
    }

    public void setSearchBarRadiusValue(String searchBarRadiusValue) {
        this.searchBarRadiusValue = searchBarRadiusValue;
    }

    public String getSearchBarSizeValue() {
        return searchBarSizeValue;
    }

    public void setSearchBarSizeValue(String searchBarSizeValue) {
        this.searchBarSizeValue = searchBarSizeValue;
    }

    public String getSearchBarOpacityValue() {
        return searchBarOpacityValue;
    }

    public void setSearchBarOpacityValue(String searchBarOpacityValue) {
        this.searchBarOpacityValue = searchBarOpacityValue;
    }

    public String getFontSizeValue() {
        return fontSizeValue;
    }

    public void setFontSizeValue(String fontSizeValue) {
        this.fontSizeValue = fontSizeValue;
    }

    public String getFontColorValue() {
        return fontColorValue;
    }

    public void setFontColorValue(String fontColorValue) {
        this.fontColorValue = fontColorValue;
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