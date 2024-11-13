package edu.bw.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeptForm {
    private Integer id;

    private String deptName;

    private String tel;

    private String email;

    private String desc;
}
