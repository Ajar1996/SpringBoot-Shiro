package com.ajar.springbootshiro.controller;

import com.ajar.springbootshiro.service.RoleService;
import com.ajar.springbootshiro.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Ajar
 * @time: 2019/10/13 17:10
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class RoleController {
    @Autowired
    RoleService roleService;

    @RequiresPermissions("sys:role:list")
    @GetMapping("/selectRoleList")
    public Result selectRoleList(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                 @RequestParam(value = "size", defaultValue = "10") Integer size,
                                 @RequestParam(value = "name",defaultValue = "") String name){

        Pageable pageable=PageRequest.of(page,size);
        return roleService.selectRoleList(name,pageable);
    }

}
