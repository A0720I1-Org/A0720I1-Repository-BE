package com.a0720i1.project_be.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordDTO {
    String oldPassword ;
    String newPassword ;
    String confirmPassword ;
}
