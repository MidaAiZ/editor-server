package com.mida.chromeext.modules.service;

import com.alibaba.fastjson.JSONObject;
import com.mida.chromeext.modules.dao.SiteTrackerDAO;
import com.mida.chromeext.modules.dto.SiteTrackerDto;
import com.mida.chromeext.modules.pojo.SiteTracker;
import com.mida.chromeext.modules.pojo.SiteTrackerExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SiteTrackerService {
    @Autowired
    private SiteTrackerDAO siteTrackerDAO;

    public SiteTracker getOneByCountryCode(String code) {
        return siteTrackerDAO.selectByCountyCode(code);
    }

    public SiteTracker getOneById(Integer id) {
        return siteTrackerDAO.selectByPrimaryKey(id);
    }

    public List<SiteTracker> getTrackerList() {
        return siteTrackerDAO.selectByExample(new SiteTrackerExample());
    }

    public List<SiteTracker> getAllList() {
        return siteTrackerDAO.selectByExample(new SiteTrackerExample());
    }

    public SiteTracker getOne(Integer id) {
        return siteTrackerDAO.selectByPrimaryKey(id);
    }

    public Boolean updateOne(SiteTrackerDto record) {
        return siteTrackerDAO.updateByPrimaryKeySelective(JSONObject.parseObject(JSONObject.toJSONString(record), SiteTracker.class)) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer updateList(List<SiteTrackerDto> list) {
        int count = 0;
        for (SiteTrackerDto record : list) {
            if (updateOne(record)) { count++; }
        }
        return count;
    }

    public Boolean createOne(SiteTrackerDto record) {
        SiteTracker tracker = JSONObject.parseObject(JSONObject.toJSONString(record), SiteTracker.class);
        if (siteTrackerDAO.insertSelective(tracker) > 0) {
            record.setTid(tracker.getTid());
            return true;
        }
        return false;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<SiteTrackerDto> createList(List<SiteTrackerDto> list) {
        List<SiteTrackerDto> trackers = new ArrayList<>();
        for (SiteTrackerDto record : list) {
            if (createOne(record)) { trackers.add(record); }
        }
        return trackers;
    }

    public Boolean deleteOne(Integer id) {
        return siteTrackerDAO.deleteByPrimaryKey(id) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public int deleteList(List<Integer> ids) {
        int count = 0;
        for (int id : ids) {
            if (deleteOne(id)) { count++; };
        }
        return count;
    }
}
