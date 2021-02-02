package com.example.store.executor;

import com.example.store.exception.ExecutorNotFoundException;

public interface ExecutorFactory {

    Executor getExecutor(String command) throws ExecutorNotFoundException;
}
