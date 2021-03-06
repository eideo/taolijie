package com.fh.taolijie.service.impl;

import com.fh.taolijie.component.ListResult;
import com.fh.taolijie.dao.mapper.ApplicationIntendModelMapper;
import com.fh.taolijie.dao.mapper.MemberModelMapper;
import com.fh.taolijie.dao.mapper.ResumeModelMapper;
import com.fh.taolijie.domain.ApplicationIntendModel;
import com.fh.taolijie.domain.MemberModel;
import com.fh.taolijie.domain.ResumeModel;
import com.fh.taolijie.domain.middle.ResumePostRecord;
import com.fh.taolijie.service.ResumeService;
import com.fh.taolijie.utils.CollectionUtils;
import com.fh.taolijie.utils.Constants;
import com.fh.taolijie.utils.ObjWrapper;
import com.fh.taolijie.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wanghongfei on 15-6-6.
 */
@Service
@Transactional(readOnly = true)
public class DefaultResumeService implements ResumeService {
    @Autowired
    ResumeModelMapper reMapper;

    @Autowired
    ApplicationIntendModelMapper intendMapper;

    @Autowired
    MemberModelMapper memMapper;

    @Override
    public List<ResumeModel> getAllResumeList(int firstResult, int capacity) {
        return reMapper.getAll(firstResult, CollectionUtils.determineCapacity(capacity));
    }

    @Override
    public List<ResumeModel> getAllResumeList(Constants.AccessAuthority authority, int firstResult, int capacity) {
        ResumeModel model = new ResumeModel(firstResult, CollectionUtils.determineCapacity(capacity));
        model.setAccessAuthority(authority.toString());

        return reMapper.findBy(model);
    }

    @Override
    public ListResult<ResumeModel> findByAuthes(int pageNumber, int pageSize, Constants.AccessAuthority... authList) {
        List<String> authStrings = Arrays.stream(authList)
                .map(auth -> auth.toString())
                .collect(Collectors.toList());

        List<ResumeModel> list = reMapper.findByAuth(authStrings, pageNumber, pageSize);
        long tot = reMapper.countFindByAuth(authStrings);

        return new ListResult<>(list, tot);
    }

    @Override
    public List<ResumeModel> getResumeList(Integer memId, int firstResult, int capacity) {
        ResumeModel model = new ResumeModel(firstResult, CollectionUtils.determineCapacity(capacity));
        model.setMemberId(memId);

        return reMapper.findBy(model);
    }

    @Override
    public List<ResumeModel> getResumeList(Integer memId, Constants.AccessAuthority authority, int firstResult, int capacity) {
        ResumeModel model = new ResumeModel(firstResult, CollectionUtils.determineCapacity(capacity));
        model.setMemberId(memId);
        model.setAccessAuthority(authority.toString());

        return reMapper.findBy(model);
    }

    @Override
    public ListResult<ResumeModel> getResumeListByIntend(Integer categoryId, int firstResult, int capacity) {
        List<ApplicationIntendModel> intendList = intendMapper.getByIntend(categoryId, firstResult, CollectionUtils.determineCapacity(capacity));
        // 如果为空，直接返回空List
        // 继续执行会导致错误的SQL
        if (intendList.isEmpty()) {
            return new ListResult<>(new ArrayList<>(), 0);
        }

        List<Integer> idList = intendList.stream().map(ApplicationIntendModel::getResumeId).collect(Collectors.toList());


        List<ResumeModel> list = reMapper.getInBatch(idList);
        long tot = list.size();

        return new ListResult<>(list, tot);
    }

    @Override
    public ListResult<ResumeModel> getResumeByGender(String gender, int pageNumber, int pageSize) {
        ResumeModel example = new ResumeModel(pageNumber, pageSize);
        example.setGender(gender);

        List<ResumeModel> list = reMapper.findBy(example);
        long tot=  reMapper.countFindBy(example);

        return new ListResult<>(list, tot);
    }

    @Override
    public ListResult<ResumeModel> findBy(ResumeModel example) {
        List<ResumeModel> list = reMapper.findBy(example);
        long tot = reMapper.countFindBy(example);
        
        return new ListResult<>(list, tot);
    }

    @Override
    public ListResult<ResumeModel> findByGenderAndIntend(Integer intendId, String gender, int pageNumber, int pageSize) {
        List<ResumeModel> list = reMapper.filterByIntendAndGender(intendId, gender, pageNumber, pageSize);
        long tot = reMapper.countFilterByIntendAndGender(intendId, gender);

        return new ListResult<>(list, tot);
    }

    @Override
    public List<ApplicationIntendModel> getIntendByResume(Integer resumeId) {
        return intendMapper.getByResume(resumeId);
    }

    @Override
    public ListResult<ResumePostRecord> getPostRecord(Integer memId, int page, int capacity) {
        List<ResumePostRecord> list = reMapper.findPostRecordByMember(memId, page, CollectionUtils.determineCapacity(capacity));
        long tot = reMapper.countFindPostRecordByMember(memId);

        return new ListResult<>(list, tot);
    }

    @Override
    public List<ResumeModel> getResumesByIds(int page, int capacity, ObjWrapper wrapper, Integer... ids) {
        return reMapper.getInBatch(Arrays.asList(ids));
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updateResume(Integer resumeId, ResumeModel model) {
        model.setId(resumeId);

        int row = reMapper.updateByPrimaryKeySelective(model);

        return row <= 0 ? false : true;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean refresh(Integer resumeId) {
        reMapper.updateTime(resumeId);

        return true;
    }

    @Override
    @Transactional(readOnly = false)
    public void favoriteResume(Integer memId, Integer resumeId) {
        MemberModel mem = memMapper.selectByPrimaryKey(memId);

        String oldIds = mem.getFavoriteResumeIds();
        String newIds = StringUtils.addToString(oldIds, resumeId.toString());
        mem.setFavoriteResumeIds(newIds);

        memMapper.updateByPrimaryKeySelective(mem);

    }

    @Override
    @Transactional(readOnly = false)
    public void unFavorite(Integer memId, Integer resumeId) {
        MemberModel mem = memMapper.selectByPrimaryKey(memId);

        String oldIds = mem.getFavoriteResumeIds();
        String newIds = StringUtils.removeFromString(oldIds, resumeId.toString());
        mem.setFavoriteResumeIds(newIds);

        memMapper.updateByPrimaryKeySelective(mem);

    }

    @Override
    public boolean isAlreadyFavorite(Integer memId, Integer resumeId) {
        MemberModel mem = memMapper.selectByPrimaryKey(memId);

        String oldIds = mem.getFavoriteResumeIds();
        return StringUtils.checkIdExists(oldIds, resumeId.toString());
    }

    @Override
    @Transactional(readOnly = false)
    public int addResume(ResumeModel model) {
        return reMapper.insert(model);
    }

    @Override
    @Transactional(readOnly = false)
    public void addIntend(ApplicationIntendModel intend) {
        intendMapper.insert(intend);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteIntend(ApplicationIntendModel intendModel) {
        intendMapper.delete(intendModel);
    }

    @Override
    public ResumeModel findResume(Integer resumeId) {
        return reMapper.selectByPrimaryKey(resumeId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean deleteResume(Integer resumeId) {
        int row = reMapper.deleteByPrimaryKey(resumeId);

        return row <= 0 ? false : true;
    }

    @Override
    @Transactional(readOnly = false)
    public void increasePageView(Integer id) {
        reMapper.increasePageView(id);
    }
}
