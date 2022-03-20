package com.epam.fitness.connection;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final AtomicBoolean CREATED = new AtomicBoolean(false);
    private static final Lock LOCK = new ReentrantLock();

    private static ConnectionPool instance;

    private final ReentrantLock connectionsLock = new ReentrantLock();
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;

    private ConnectionPool() {
        availableConnections = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
    }

    public static ConnectionPool getInstance() { // TODO: 20.03.2022 Must created through --> ConnectionFactory.create() 
        if (!CREATED.get()) {
            try {
                LOCK.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    CREATED.set(true);
                }
            } finally {
                LOCK.unlock();
            }
        }
        return instance;
    }

    public void returnConnection(ProxyConnection connection) {
        connectionsLock.lock();
        try {
            if (connectionsInUse.contains(connection)) {
                availableConnections.offer(connection);
            }
        } finally {
            connectionsLock.unlock();
        }
    }

    public ProxyConnection getConnection() {
        return ConnectionFactory.create();
        // TODO: 20.03.2022 Must return free connection from availableConnections
    }
}
