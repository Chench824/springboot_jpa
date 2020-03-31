package com.sise.cch.service;

import com.sise.cch.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    public Page<User> findAll(Pageable pageable);
}
