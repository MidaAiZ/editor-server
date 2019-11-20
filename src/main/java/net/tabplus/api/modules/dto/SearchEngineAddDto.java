package net.tabplus.api.modules.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

/**
 * @author lihaoyu
 * @date 2019/10/7 20:08
 */
public class SearchEngineAddDto implements Serializable {
    @NotBlank
    private String countryCode;
    @Valid
    private List<SearchEngineItemDto> engines;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public List<SearchEngineItemDto> getEngines() {
        return engines;
    }

    public void setEngines(List<SearchEngineItemDto> engines) {
        this.engines = engines;
    }
}

