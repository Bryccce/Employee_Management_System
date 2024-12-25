package edu.bw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectUserByConditionForm {
    private Integer page;

    private Integer length;

    private String name;

    private String sex;

    private String role;

    private Integer deptId;

    private Integer status;
}