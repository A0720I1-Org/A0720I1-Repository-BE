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
    @Transactional
    @Modifying
    @Query(value ="UPDATE account SET password = :password WHERE account_name = :accountName",nativeQuery = true)
    void changePassword(@Param("password") String encodePw, @Param("accountName") String accountName);
    @Query(value ="select * from teacher where teacher.account_id = ?1",nativeQuery = true)
    TeacherViewDTO getInfoAccount(Integer accountId);
    @Transactional
    @Modifying
    @Query(value = "update teacher as t set t.address = ?1, t.hometown = ?2, t.position = ?3,t.level =?4 ,t.image_url=?5 where t.account_id = ?6",nativeQuery = true)
    void updadeInfoAccount(String address, String hometown, String position, String level, String imgUrl, int accountId);
}
