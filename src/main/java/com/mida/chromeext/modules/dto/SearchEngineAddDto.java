package com.mida.chromeext.modules.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/10/7 20:08
 */
public class SearchEngineAddDto implements Serializable {

    private String countryCode;

    private List<EngineDto> engineDtoList;


    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<EngineDto> getEngineDtoList() {
        return engineDtoList;
    }

    public void setEngineDtoList(List<EngineDto> engineDtoList) {
        this.engineDtoList = engineDtoList;
    }
}

