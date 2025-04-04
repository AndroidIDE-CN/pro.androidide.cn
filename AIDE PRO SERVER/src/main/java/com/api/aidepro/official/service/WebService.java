package com.api.aidepro.official.service;

import com.api.aidepro.console.HandleResults;
import com.api.aidepro.official.dao.*;
import com.api.aidepro.official.model.HistoricalModel;
import org.springframework.stereotype.Service;
import com.api.aidepro.official.model.FriendsModel;
import com.api.aidepro.official.model.DownloadModel;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class WebService {

    private final TeamDao teamDao;
    private final FriendsDao friendsDao;
    private final ContactDao contactDao;
    private final DownloadDao downloadDao;
    private final HistoricalDao historicalDao;

    @Autowired
    public WebService(FriendsDao friendsDao, TeamDao teamDao, ContactDao contactDao, DownloadDao downloadDao, HistoricalDao historicalDao) {
        this.teamDao = teamDao;
        this.friendsDao = friendsDao;
        this.contactDao = contactDao;
        this.downloadDao = downloadDao;
        this.historicalDao = historicalDao;
    }

    public List<FriendsModel> getWebLinks() {
        return friendsDao.selectList(null);
    }

    public List<DownloadModel> getWebDownload() {
        return downloadDao.selectList(null);
    }

    public Map<String, Object> getWebTeam() {
        return new HandleResults().handleResultByCode(200, teamDao.selectList(null), "获取成功");
    }

    public Map<String, Object> getWebContact() {
        return new HandleResults().handleResultByCode(200, contactDao.selectList(null), "获取成功");
    }

    public Map<String, Object> getWebHistorical() {
        List<HistoricalModel> historyList = historicalDao.selectList(null);
        Collections.reverse(historyList); // 倒序
        return new HandleResults().handleResultByCode(200, historyList, "获取成功");
    }
}
