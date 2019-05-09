package com.example.tvshowtime.repository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {
    private static AppExecutors sInstance;
    private Executor mDiskAndNetworkExecutor;
    private Executor mDatabaseExecutor;
    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newSingleThreadExecutor());
    }

    public AppExecutors(Executor mDiskAndNetworkExecutor, Executor mDatabaseExecutor) {
        this.mDiskAndNetworkExecutor = mDiskAndNetworkExecutor;
        this.mDatabaseExecutor = mDatabaseExecutor;
    }

    public static synchronized AppExecutors getInstance(){
        if(sInstance==null){
            sInstance = new AppExecutors();
        }
        return sInstance;
    }

    public Executor diskAndNetworkExecutor() {
        return mDiskAndNetworkExecutor;
    }

    public Executor databaseExecutor(){
        return mDatabaseExecutor;
    }
}
