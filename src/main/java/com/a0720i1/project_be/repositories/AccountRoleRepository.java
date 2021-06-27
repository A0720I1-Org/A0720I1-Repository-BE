package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.AccountRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AccountRoleRepository extends JpaRepository<AccountRole, Integer> {
    // Đôn: Thêm mới role cho tài khoản
    @Transactional
    @Modifying
    @Query(value = "insert into account_role(account_id, role_id) values (?1, ?2)", nativeQuery = true)
    void createAccountRole(int accountId, int roleId);
}
