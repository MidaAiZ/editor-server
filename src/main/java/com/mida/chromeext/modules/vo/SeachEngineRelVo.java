package com.mida.chromeext.modules.vo;

import com.mida.chromeext.modules.pojo.Admin;
import com.mida.chromeext.modules.pojo.Country;
import com.mida.chromeext.modules.pojo.SearchEngine;

public class SeachEngineRelVo extends SearchEngine {
    private Admin createdAdmin;
    private Country country;

    public Admin getCreatedAdmin() {
        return createdAdmin;
    }

    public void setCreatedAdmin(Admin createdAdmin) {
        this.createdAdmin = createdAdmin;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
