package net.tabplus.api.modules.vo;

import net.tabplus.api.modules.pojo.Admin;
import net.tabplus.api.modules.pojo.Country;
import net.tabplus.api.modules.pojo.SearchEngine;

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
