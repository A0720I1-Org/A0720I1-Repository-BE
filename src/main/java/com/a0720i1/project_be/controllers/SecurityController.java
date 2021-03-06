package com.a0720i1.project_be.controllers;
import com.a0720i1.project_be.dto.JwtResponse;
import com.a0720i1.project_be.dto.PasswordDTO;
import com.a0720i1.project_be.dto.teacher.TeacherUpdateDTO;
import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.jwt.JwtUtility;
import com.a0720i1.project_be.jwt.LoginRequest;
import com.a0720i1.project_be.models.Account;
import com.a0720i1.project_be.services.AccountService;
import com.a0720i1.project_be.services.impl.AccountDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class SecurityController {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountService accountService;
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
                throw new BadCredentialsException("M???t kh???u kh??ng ch??nh x??c", e);
            } else {
                throw new UsernameNotFoundException("T??n ????ng nh???p kh??ng t???n t???i", e);
            }

        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
        AccountDetailsImpl userDetails = (AccountDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        Account account = accountService.findByUsername(loginRequest.getUsername());
        account.setPassword("");
        return ResponseEntity.ok(
                new JwtResponse(jwt, account, roles)
        );
    }
    //PhatDT
    @PutMapping(value="/api/student/change-password/{username}",consumes = MediaType.APPLICATION_JSON_VALUE)
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
                    .body("M???t kh???u c?? kh??ng ch??nh x??c");
        }

    }
    //PhatDT
    @GetMapping("/api/teacher/info/{username}")
    public ResponseEntity<TeacherViewDTO> ChangePassword(@PathVariable("username") String username) {
        return new ResponseEntity<>(accountService.getInfoAccount(username),HttpStatus.OK);
    }
    //PhatDT
    @PutMapping(value = "/api/teacher/update-info/{username}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateTeacher(@PathVariable("username") String username,
                                                          @RequestBody TeacherUpdateDTO teacherUpdateDTO) {
        this.accountService.updateInfoAccount(teacherUpdateDTO,username);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
