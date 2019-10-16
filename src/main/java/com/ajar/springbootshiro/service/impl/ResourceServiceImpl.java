package com.ajar.springbootshiro.service.impl;

import com.ajar.springbootshiro.dao.*;
import com.ajar.springbootshiro.model.Resource;
import com.ajar.springbootshiro.model.Role;
import com.ajar.springbootshiro.model.RoleResource;
import com.ajar.springbootshiro.model.UserRole;
import com.ajar.springbootshiro.service.ResourceService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.transform.Source;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: Ajar
 * @time: 2019/9/30 16:49
 */
@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    UserRoleDao userRoleDao;

    @Autowired
    ResourceDao resourceDao;

    @Autowired
    RoleResourceDao roleResourceDao;

    /*查询用户的权限表示符*/
    @Override
    public Set<String> selectUserPerms(Integer userId) {
        /*获取角色Id*/
        List<Integer> roleIdS=new ArrayList<>();
        List<UserRole> userRoles=userRoleDao.findByUserId(userId);
        userRoles.forEach(o->{
            roleIdS.add(o.getRoleId());
        });


        /*获取资源Id*/
        List<Integer> resourceIds=new ArrayList<>();
        List<RoleResource> roleResources=roleResourceDao.findByRoleIdIn(roleIdS);
        roleResources.forEach(o->{
            resourceIds.add(o.getResourceId());
        });

        /*获取权限表示符*/
        Set<String> prems=new HashSet<>();
        List<Resource> resources=resourceDao.findAllById(resourceIds);
        resources.forEach(o->{
            prems.add(o.getPerms());
        });

        return  prems;

    }
}
