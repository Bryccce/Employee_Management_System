package edu.bw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role{
    private Integer id;

    private String roleName;

    private String permissions;

    private String desc;

    private String defaultPermissions;

    /*
    是否是系统内置角色：
        1. 是
        0. 否
    * */
    private Boolean systemic;
}