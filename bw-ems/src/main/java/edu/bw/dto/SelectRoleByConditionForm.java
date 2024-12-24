package edu.bw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SelectRoleByConditionForm {

    private String roleName;

    private Integer page;

    private Integer length;
}