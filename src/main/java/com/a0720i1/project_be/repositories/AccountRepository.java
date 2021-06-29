package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    // Đôn: Tìm kiếm account bằng username
//    @Query(value = "SELECT * FROM account WHERE account.username = ?1 limit 1", nativeQuery = true)
    Account findAccountByUsername(String username);

    // Đôn: Tìm kiếm id account bằng username
    @Query(value = "select id from account where username = ?1 limit 1",nativeQuery = true)
    int findIdUserByUsername(String username);

    //Đôn: Thêm mới tài khoản
    @Modifying
    @Query(value = "insert into account(username, password, is_enabled) values (?1, ?2, ?3)", nativeQuery = true)
    void add(String username, String password, int isEnable);

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
