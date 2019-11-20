package net.tabplus.api.modules.service;

import net.tabplus.api.modules.pojo.SiteTracker;
import net.tabplus.api.modules.pojo.SiteTrackerExample;
import net.tabplus.api.modules.dao.SiteTrackerDAO;
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

    public Boolean updateByCountryCode(SiteTracker record) {
        SiteTrackerExample example = new SiteTrackerExample();
        example.createCriteria().andCountryCodeEqualTo(record.getCountryCode());
        return siteTrackerDAO.updateByExampleSelective(record, example) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public Integer updateList(List<SiteTracker> list) {
        int count = 0;
        for (SiteTracker record : list) {
            if (updateByCountryCode(record)) {
                count++;
            }
        }
        return count;
    }

    public Boolean createOne(SiteTracker record) {
        return siteTrackerDAO.insertSelective(record) > 0;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<SiteTracker> createList(List<SiteTracker> list) {
        List<SiteTracker> trackers = new ArrayList<>();
        for (SiteTracker record : list) {
            if (createOne(record)) {
                trackers.add(record);
            }
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
            if (deleteOne(id)) {
                count++;
            }
            ;
        }
        return count;
    }
}
