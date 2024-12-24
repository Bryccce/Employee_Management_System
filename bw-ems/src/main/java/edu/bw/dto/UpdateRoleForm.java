package edu.bw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRoleForm {

    private Integer id;

    private String roleName;

    private String permissions;

    private String desc;
}