package com.mida.chromeext.dto;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Map;

@ConfigurationProperties(prefix = "user-setting")
@Component
public class DefaultUserSettingDto implements Serializable {
    private int menuCols;
    private int menuRows;
    private Map wallpaper;

    public int getMenuCols() {
        return menuCols;
    }

    public void setMenuCols(int menuCols) {
        this.menuCols = menuCols;
    }

    public int getMenuRows() {
        return menuRows;
    }

    public void setMenuRows(int menuRows) {
        this.menuRows = menuRows;
    }

    public Map getWallpaper() {
        return wallpaper;
    }

    public void setWallpaper(Map wallpaper) {
        this.wallpaper = wallpaper;
    }
}
