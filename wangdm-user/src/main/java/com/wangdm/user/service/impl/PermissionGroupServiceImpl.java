package com.wangdm.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.dao.BaseDao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.BaseQuery;
import com.wangdm.core.query.Query;
import com.wangdm.core.service.BaseService;
import com.wangdm.user.dto.GroupDto;
import com.wangdm.user.dto.PermissionGroupDto;
import com.wangdm.user.entity.PermissionGroup;
import com.wangdm.user.service.PermissionGroupService;

public class PermissionGroupServiceImpl extends BaseService<PermissionGroup> implements PermissionGroupService {

    private static final Logger log = LoggerFactory.getLogger(PermissionGroupServiceImpl.class);

    @Autowired
    private BaseDao<PermissionGroup> baseDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;
    
    @Override
    public Dto findById(Serializable id) {

        PermissionGroup entity = baseDao.findById(PermissionGroup.class, id);
        if(entity == null){
            log.info("No such PermissionGroup[id="+id+"] is found");
            return null;
        }
        
        PermissionGroupDto dto = new PermissionGroupDto();
        dto.fromEntity(entity);
        return dto;
    }

    @Override
    public List<Dto> query(Query q) {
        BaseQuery query = (BaseQuery)q;
        
        Constraint constraint = constraintFactory.createConstraint(PermissionGroup.class);
         
        if(query.getStatus()!=null)
            constraint.addEqualCondition("status", query.getStatus());

        if(query.getOrder()!=null)
            constraint.setOrderProperty(query.getOrder());
        
        constraint.setPageSize(query.getPageSize());
        
        constraint.setCurrentPage(query.getCurrentPage());
        
        List<PermissionGroup> entityList = baseDao.findByConstraint(constraint);
        if(entityList == null || entityList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>(entityList.size());
        for(PermissionGroup entity : entityList){
            Dto dto = new GroupDto();
            dto.fromEntity(entity);
            dtoList.add(dto);
        }
        
        return dtoList;
    }

}