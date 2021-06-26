package com.RPC.Client;

import com.RPC.Pojo.User;
import com.RPC.Server.UserService;

public class RPCClient {
    public static void main(String[] args) {
        ClientProxy clientProxy = new ClientProxy("127.0.0.1",8899);
        UserService proxy = clientProxy.getProxy(UserService.class);

        //服务方法 1
        User userByUserId = proxy.getUserByUserId(10);
        System.out.println("从服务端得到的user为："+userByUserId);
        //服务方法 2
        User user = User.builder().userName("张三").id(100).sex(true).build();
        Integer integer = proxy.insertUserId(user);
        System.out.println("从服务端插入数据："+integer);
    }
}
