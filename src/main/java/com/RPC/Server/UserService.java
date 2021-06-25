package com.RPC.Server;

import com.RPC.Pojo.User;

public interface UserService {
    User getUserByUserId(Integer id);
}
