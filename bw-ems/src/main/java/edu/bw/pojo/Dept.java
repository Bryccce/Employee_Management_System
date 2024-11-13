package edu.bw.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dept {

    private Integer id;

    private String deptName;

    private String tel;

    private String email;

    private String desc;
}