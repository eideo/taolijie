package com.fh.taolijie.service.impl;

import com.fh.taolijie.component.ListResult;
import com.fh.taolijie.dao.mapper.BaseMapper;
import com.fh.taolijie.dao.mapper.JobPostCategoryModelMapper;
import com.fh.taolijie.dao.mapper.JobPostModelMapper;
import com.fh.taolijie.domain.JobPostCategoryModel;
import com.fh.taolijie.exception.checked.CategoryNotEmptyException;
import com.fh.taolijie.service.JobPostCateService;
import com.fh.taolijie.utils.CollectionUtils;
import com.fh.taolijie.utils.Constants;
import com.fh.taolijie.utils.ObjWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by wanghongfei on 15-6-6.
 */
@Service
@Transactional(readOnly = true)
public class DefaultJobPostCategoryService extends AbstractBaseService<JobPostCategoryModel> implements JobPostCateService {
    @Autowired
    JobPostCategoryModelMapper cateMapper;

    @Override
    public ListResult<JobPostCategoryModel> getCategoryList(int firstResult, int capacity, ObjWrapper wrapper) {
        List<JobPostCategoryModel> list =  cateMapper.getAll(firstResult, CollectionUtils.determineCapacity(capacity));
        long tot = cateMapper.countGetAll();

        return new ListResult<>(list, tot);
    }

/*    @Override
    @Transactional(readOnly = false)
    public void addCategory(JobPostCategoryModel model) {
        cateMapper.insert(model);
    }*/

    @Override
    @Transactional(readOnly = false)
    public boolean deleteCategory(Integer cateId) throws CategoryNotEmptyException {
        if (false == cateMapper.isCategoryEmpty(cateId)) {
            throw new CategoryNotEmptyException(Constants.ErrorType.CATE_NOT_EMPTY);
        }

        int row = cateMapper.deleteByPrimaryKey(cateId);

        return row <= 0 ? false : true;
    }

/*    @Override
    @Transactional(readOnly = false)
    public boolean updateCategory(Integer cateId, JobPostCategoryModel model) {
        model.setId(cateId);
        return cateMapper.updateByPrimaryKey(model) <= 0 ? false : true;
    }*/

/*    @Override
    public JobPostCategoryModel findCategory(Integer cateId) {
        return cateMapper.selectByPrimaryKey(cateId);
    }*/

    @Override
    public JobPostCategoryModel findByName(String name) {
        return cateMapper.findByName(name);
    }

    @Override
    protected BaseMapper<JobPostCategoryModel> getMapper() {
        return this.cateMapper;
    }
}
