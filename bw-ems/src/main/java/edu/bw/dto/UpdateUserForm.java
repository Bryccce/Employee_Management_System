package edu.bw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserForm {

    private Integer userId;

    private String username;

    private String password;

    private String name;

    private String sex;

    private String tel;

    private String email;

    private String hiredate;

    private String role;

    private Integer deptId;

    /**
     * 状态
     * 1 离职
     * 2 在职
     */
    private Byte status;
}