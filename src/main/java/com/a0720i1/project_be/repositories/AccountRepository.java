package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    // Đôn
    //  @Query(value = "SELECT * FROM account WHERE account.username = ?1 limit 1", nativeQuery = true)
    Account findAccountByUsername(String username);

    // Đôn: Tìm kiếm id account bằng username
    @Query(value = "select id from account where username = ?1 limit 1", nativeQuery = true)
    int findIdUserByUsername(String username);
    //Đôn: Thêm mới tài khoản
    @Modifying
    @Query(value = "insert into account(username, password, is_enabled) values (?1, ?2, ?3)", nativeQuery = true)
    void add(String username, String password, int isEnable);

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

    @Transactional
    @Modifying
    @Query(value = "UPDATE account SET password = ?1 WHERE username = ?2", nativeQuery = true)
    void changePassword(String encodePw, String accountName);

    @Query(value = "select t.id, t.address, t.birthday, t.email, t.gender, t.hometown, t.name, t.phone, t.level, t.position, t.image_url as imageUrl\n " +
            "from teacher as t where t.account_id = ?1", nativeQuery = true)
    TeacherViewDTO getInfoAccount(Integer accountId);

    @Transactional
    @Modifying
    @Query(value = "update teacher as t set t.address = ?1, t.hometown = ?2, t.position = ?3,t.level =?4 ,t.image_url=?5 where t.account_id = ?6", nativeQuery = true)
    void updadeInfoAccount(String address, String hometown, String position, String level, String imgUrl, int accountId);
}

