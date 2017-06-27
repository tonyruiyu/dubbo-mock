package com.tony.test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.tony.test.ReflectUtil;
import com.tony.test.mock.po.MockService;

public class Test {
    @SuppressWarnings({ "resource", "unused" })
    public static void main(String[] args) throws IOException {
        MockService mockService = new MockService();
        mockService.setId(1);
        ReflectUtil.setFieldValue(mockService, "id", 3);
        System.out.println(mockService.getId());
        ServerSocket serverSocket = new ServerSocket(80);
        Socket socket = serverSocket.accept();
    }
}
