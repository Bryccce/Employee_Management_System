package edu.bw.pojo;

import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private Integer id;

    @NonNull
    private String username;

    @NonNull
    private String password;

    /**
     * 预留
     */
    private String openId;


    /**
     * 照片
     */
    private String photo;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 手机号码
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 入职日期
     */
    private Date hiredate;

    /**
     * 角色
     */
    private String role;

    /**
     * 是否是超级管理员
     */
    private Boolean root;

    /**
     * 部门编号
     */
    private Integer deptId;

    /**
     * 状态
     * 1 离职
     * 2 在职
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;
}

