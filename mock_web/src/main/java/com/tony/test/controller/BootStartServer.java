package com.tony.test.controller;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Resource;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.tony.test.protocol.MockServer;

@Lazy(false) @Service public class BootStartServer implements InitializingBean, DisposableBean {

    @Resource(name = "dubboMockServer") MockServer server;

    AtomicBoolean                                  isStarted = new AtomicBoolean(false);

    public void start() {
        if (!isStarted.get()) {
            server.start();
            isStarted.set(true);
        }
    }

    public void stop() {
        if (isStarted.get()) {
            server.stop();
            isStarted.set(false);
        }
    }

    public boolean isRunning() {
        return isStarted.get();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        start();
    }

    @Override
    public void destroy() throws Exception {
        stop();
    }

}
