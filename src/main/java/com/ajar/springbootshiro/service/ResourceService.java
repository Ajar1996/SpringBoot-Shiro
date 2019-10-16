package com.ajar.springbootshiro.service;

import java.util.Set;

/**
 * @description:
 * @author: Ajar
 * @time: 2019/9/30 16:45
 */
public interface ResourceService {
    Set<String> selectUserPerms(Integer userId);
}
