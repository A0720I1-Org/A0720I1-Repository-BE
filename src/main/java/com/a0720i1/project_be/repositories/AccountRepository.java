package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
//    @Query(value = "SELECT * FROM account WHERE account.username = ?1", nativeQuery = true)
    Account findAccountByUsername(String username);
    @Modifying
    @Query(value = "select account_id from account where username = ?1",nativeQuery = true)
    int findIdUserByUsername(String username);
//    PhatDT
    @Transactional
    @Modifying
    @Query(value ="UPDATE account SET password = :password WHERE username = :accountName",nativeQuery = true)
    void changePassword(@Param("password") String encodePw, @Param("accountName") String accountName);
    @Query(value ="select t.id, t.address, t.birthday, t.email, t.gender, t.hometown, t.name, t.phone, t.level, t.position, t.image_url as imageUrl\n" +
            "from teacher as t\n"+
            "where t.account_id = ?1",nativeQuery = true)
    TeacherViewDTO getInfoAccount(Integer accountId);
    //    PhatDT
    @Transactional
    @Modifying
    @Query(value = "update teacher as t set t.address = ?1, t.hometown = ?2, t.position = ?3,t.level =?4,t.phone=?5, t.email=?6 ,t.image_url=?7 where t.account_id = ?8",nativeQuery = true)
    void updadeInfoAccount(String address, String hometown,String position,String level,String phone ,String email,String imgUrl,int accountId);
//    PhatDT
@Query(value = "SELECT teacher.email FROM teacher \n" +
        "join account on teacher.account_id = account.id\n" +
        "where account.username = ?1",nativeQuery = true)
String getEmail(String username);
    @Transactional
    @Query(value = "update account\n " +
            "set password = ?2\n" +
            "where account.username = ?1",nativeQuery = true)
    void changePasswordByForgot(String username,String newPW);
}