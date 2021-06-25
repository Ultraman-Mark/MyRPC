package com.RPC.Server;

import com.RPC.Pojo.User;
import com.RPC.Server.Impl.UserServiceImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class RPCServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket = new ServerSocket(8899);
            System.out.println("服务端启动");
            while (true){
                Socket socket = serverSocket.accept();
                new Thread(()->{
                    try{
                        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                        ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                        //读取客户端ID
                        Integer id = ois.readInt();
                        User userByUserId = userService.getUserByUserId(id);
                        //写入User对象给客户端
                        oos.writeObject(userByUserId);
                        oos.flush();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                        System.out.println("从IO中读取数据错误");
                    }
                }).start();
            }
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("启动服务器失败");
        }
    }
}
