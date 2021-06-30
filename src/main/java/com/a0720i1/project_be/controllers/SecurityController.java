package com.a0720i1.project_be.controllers;
import com.a0720i1.project_be.dto.JwtResponse;
import com.a0720i1.project_be.dto.PasswordDTO;
import com.a0720i1.project_be.dto.teacher.TeacherUpdateDTO;
import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.jwt.JwtUtility;
import com.a0720i1.project_be.jwt.LoginRequest;
import com.a0720i1.project_be.models.Account;
import com.a0720i1.project_be.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
//@RequestMapping("api/public")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class SecurityController {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;
    @Autowired
    public JavaMailSender emailSender;
    @PostMapping("/api/public/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
        } catch (Exception e
        ) {
            if (accountService.findByUsername(loginRequest.getUsername()) != null){
                throw new BadCredentialsException("Mật khẩu không chính xác", e);
            } else {
                throw new UsernameNotFoundException("Tên đăng nhập không tồn tại", e);
            }

        }

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
//        AccountDetailsImpl userDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        List<String> roles = userDetails.getAuthorities().stream()
////                .map(GrantedAuthority::getAuthority)
////                .collect(Collectors.toList());
        Account account = accountService.findByUsername(loginRequest.getUsername());
        List<String> roles = account.getAccountRoleList().stream()
                .map(role -> (role.getRole().getName()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new JwtResponse(jwt, account, roles)
        );
    }
    @PutMapping(value="/api/teacher/change-password/{username}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> ChangePassword(@PathVariable("username") String userName ,
                                            @RequestBody PasswordDTO passwordDTO) {
        Account account = accountService.findByUsername(userName);
        if (account == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        if(accountService.checkChangePassword(account,passwordDTO.getOldPassword(),passwordDTO.getNewPassword(),passwordDTO.getConfirmPassword())){
            accountService.changePassword(account,passwordDTO.getOldPassword(),passwordDTO.getNewPassword(),passwordDTO.getConfirmPassword());
            return new ResponseEntity<>(HttpStatus.OK);
        }else {
            return ResponseEntity.badRequest()
                    .body("Mật khẩu không đúng");
        }

    }
    @GetMapping("/api/teacher/info/{username}")
    public ResponseEntity<TeacherViewDTO> ChangePassword(@PathVariable("username") String username) {
        return new ResponseEntity<>(accountService.getInfoAccount(username),HttpStatus.OK);
    }

    @PutMapping(value = "/api/teacher/update-info/{username}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTeacher(@PathVariable("username") String username,
                                                          @RequestBody TeacherUpdateDTO teacherUpdateDTO) {
        this.accountService.updateInfoAccount(teacherUpdateDTO,username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/teacher/forgot-password/{username}")
    public ResponseEntity<?> sendMailConfirmChangePassword(@PathVariable("username") String username,
                                                           @RequestParam("code") Integer code) throws MessagingException {
        String email = accountService.getMailByUsername(username);
        if (email == null || code == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        MimeMessage message = emailSender.createMimeMessage();

        boolean multipart = true;

        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");

        String htmlMsg = "<h3>Your code is <i style='color: green'>" + code + "<i></h3>" +
                "<p style='color: red; font-size: 25px;'>" +
                "A0720I1 <p>";
        message.setContent(htmlMsg, "text/html");

        helper.setTo(email);

        helper.setSubject("A0720I1 hỗ trợ lấy lại mật khẩu");

        emailSender.send(message);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
