package com.example.tvshowtime.repository;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AppExecutors {

    private static AppExecutors sInstance;
    private ExecutorService mDiskAndNetworkExecutor;
    private ExecutorService mDiskExecutor;
    private ExecutorService mNetworkExecutor;

    public AppExecutors() {
        this(Executors.newSingleThreadExecutor(), Executors.newSingleThreadExecutor(),Executors.newSingleThreadExecutor());
    }

    public AppExecutors(ExecutorService mDiskAndNetworkExecutor, ExecutorService mDiskExecutor, ExecutorService mNetworkExecutor) {
        this.mDiskAndNetworkExecutor = mDiskAndNetworkExecutor;
        this.mDiskExecutor = mDiskExecutor;
        this.mNetworkExecutor = mNetworkExecutor;
    }

    public static synchronized AppExecutors getInstance(){
        if(sInstance==null){
            sInstance = new AppExecutors();
        }
        return sInstance;
    }

    public ExecutorService diskAndNetworkExecutor() {
        return mDiskAndNetworkExecutor;
    }

    public ExecutorService diskExecutor(){
        return mDiskExecutor;
    }

    public ExecutorService networkExecutor(){
        return mNetworkExecutor;
    }
}
