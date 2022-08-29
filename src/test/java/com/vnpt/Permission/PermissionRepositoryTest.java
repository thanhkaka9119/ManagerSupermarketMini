package com.vnpt.Permission;

import com.vnpt.data_access.IPermissionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PermissionRepositoryTest {
    @Autowired
    private IPermissionRepository permissionRepository;

    @Test
    public void getPermissionKeyByUserId(){
        List<String> permissionKeys = permissionRepository.findPermissionKeyByUserId(1L);
        Assertions.assertThat(permissionKeys).size().isGreaterThan(0);
    }
}
