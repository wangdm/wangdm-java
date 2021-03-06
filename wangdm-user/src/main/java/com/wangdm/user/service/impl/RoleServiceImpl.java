package com.wangdm.user.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wangdm.core.constraint.Constraint;
import com.wangdm.core.constraint.ConstraintFactory;
import com.wangdm.core.dao.Dao;
import com.wangdm.core.dto.Dto;
import com.wangdm.core.query.Query;
import com.wangdm.core.query.QueryResult;
import com.wangdm.core.service.BaseService;
import com.wangdm.user.dto.PermissionDto;
import com.wangdm.user.dto.RoleDto;
import com.wangdm.user.entity.Permission;
import com.wangdm.user.entity.Role;
import com.wangdm.user.entity.RolePermission;
import com.wangdm.user.service.RoleService;

@Service("roleService")
@Transactional
public class RoleServiceImpl extends BaseService<Role> implements RoleService {

    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Autowired
    private Dao<Role> baseDao;
    
    @Autowired
    private Dao<RolePermission> rolePermissionDao;
    
    @Autowired
    private ConstraintFactory constraintFactory;
    
    @Override
    public Dto findById(Serializable id) {

        Role entiry = baseDao.findById(id, Role.class);
        if(entiry == null){
            log.info("No such role[id="+id+"] is found");
            return null;
        }
        
        RoleDto dto = new RoleDto();
        dto.fromEntity(entiry);
        return dto;
    }

    @Override
    public QueryResult query(Query q) {
        
        Constraint constraint = constraintFactory.createConstraint(Role.class);

        List<Role> entityList = baseDao.findByConstraint(constraint);
        if(entityList == null || entityList.size()<=0){
            return null;
        }
        
        List<Dto> dtoList = new ArrayList<Dto>(entityList.size());
        for(Role entity : entityList){
            Dto dto = new RoleDto();
            dto.fromEntity(entity);
            dtoList.add(dto);
        }

        return new QueryResult(1,dtoList.size(),dtoList.size(),dtoList);
    }

    @Override
    public List<PermissionDto> getPermission(Long roleId) {
        
        Constraint constraint = constraintFactory.createConstraint(RolePermission.class);

        constraint.addEqualCondition("role.id", roleId);
        
        List<RolePermission> rolePermissionList = rolePermissionDao.findByConstraint(constraint);
        if(rolePermissionList == null || rolePermissionList.size()<=0){
            return null;
        }
        
        List<PermissionDto> dtoList = new ArrayList<PermissionDto>(rolePermissionList.size());
        for(RolePermission rolePermission : rolePermissionList){
            PermissionDto dto = new PermissionDto();
            dto.fromEntity(rolePermission.getPermission());
            dtoList.add(dto);
        }
        
        return dtoList;
    }
    
    @Override
    public void setPermission(Long roleId, List<PermissionDto> permList) {
        
        if(roleId <= 0 || permList==null || permList.size()<=0){
            return;
        }
        
        Role role = baseDao.findById(roleId, Role.class);
        if(role == null){
            return;
        }
        //先删除
        Constraint constraint = constraintFactory.createConstraint(RolePermission.class);

        constraint.addEqualCondition("role.id", roleId);
        
        List<RolePermission> rolePermissionList = rolePermissionDao.findByConstraint(constraint);
        if(rolePermissionList != null && rolePermissionList.size()>0){
            for(RolePermission rolePermission : rolePermissionList){
                rolePermissionDao.delete(rolePermission);
            }
        }
        //在添加
        rolePermissionList = new ArrayList<RolePermission>(permList.size());
        for(PermissionDto dto : permList){
            Permission permEntity = (Permission)dto.toEntity(Permission.class);
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermission(permEntity);
            rolePermission.setRole(role);
            rolePermissionDao.create(rolePermission);
        }
    }

    @Override
    public void resetPermission(Long roleId) {
        // TODO Auto-generated method stub
        log.warn("Unimplement Service Method!");
        
    }

}
