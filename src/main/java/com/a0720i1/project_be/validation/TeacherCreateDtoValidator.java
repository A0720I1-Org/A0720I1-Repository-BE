package com.a0720i1.project_be.validation;

import com.a0720i1.project_be.dto.teacher.TeacherCreateDTO;
import com.a0720i1.project_be.services.AccountService;
import com.a0720i1.project_be.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class TeacherCreateDtoValidator implements Validator {
    @Autowired
    AccountService accountService;

    @Autowired
    TeacherService teacherService;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object object, Errors errors) {
        TeacherCreateDTO teacher = (TeacherCreateDTO) object;
        if (teacher.getUsername() == null){
            errors.rejectValue("username", "username.null", "Tên đăng nhập không được để trống");
        } else if (teacher.getUsername().length() < 4) {
            errors.rejectValue("username", "username.minlength", "Tên đăng nhập dài tối thiểu 4 ký tự");
        } else if (teacher.getUsername().length() > 32) {
            errors.rejectValue("username", "username.maxlength", "Tên đăng nhập dài tối đa 32 ký tự");
        } else if (accountService.findByUsername(teacher.getUsername()) != null) {
            errors.rejectValue("username", "username.exists", "Tên đăng nhập đã tồn tại");
        }
        if (teacher.getPassword() == null){
            errors.rejectValue("password", "password.null", "Mật khẩu không được để trống");
        } else if (teacher.getPassword().length() < 4) {
            errors.rejectValue("password", "password.minlength", "Mật khẩu dài tối thiểu 4 ký tự");
        } else if (teacher.getPassword().length() > 32) {
            errors.rejectValue("password", "password.maxlength", "Mật khẩu dài tối đa 32 ký tự");
        }
        if (teacher.getName() == null){
            errors.rejectValue("name", "name.null", "Họ tên không được để trống");
        } else if (teacher.getName().length() > 300){
            errors.rejectValue("name", "maxlength", "Họ tên dài tối đa 300 ký tự");
        } else if (!teacher.getName().matches("^[^`|\\~|\\!|\\@|\\#|\\$|\\%|\\^|\\&|\\*|\\(|\\)|\\+|\\=|\\[|\\{|\\]|\\}|\\||\\\\|\\'|\\<|\\,|\\.|\\>|\\?|\\/|\\\"\"|\\;|\\:]*$")) {
            errors.rejectValue("name", "name.pattern", "Họ tên không bao gồm ký tự đặc biệt");
        }
        if (teacher.getGender() == null) {
            errors.rejectValue("gender", "gender.null", "Giới tính không được để trống");
        }
        if (teacher.getEmail() == null) {
            errors.rejectValue("email", "email.null", "Email không được để trống");
        } else if (teacher.getEmail().matches("^[a-z][a-z0-9_\\\\.]{5,32}@[a-z0-9]{2,}(\\\\.[a-z0-9]{2,4}){1,2}$")) {
            errors.rejectValue("email", "email.pattern", "Email không đúng định dạng");
        } else if (teacherService.getTeacherByEmail(teacher.getEmail()) != null) {
            errors.rejectValue("email", "email.exists", "Email đã tồn tại");
        }
        if (teacher.getBirthday() == null) {
            errors.rejectValue("birthday", "birthday.null", "Ngày sinh không được để trống");
        }
    }
}
