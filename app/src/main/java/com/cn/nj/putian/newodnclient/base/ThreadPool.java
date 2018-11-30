package com.cn.nj.putian.newodnclient.base;

import android.support.annotation.IntRange;
import android.util.Log;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 整个项目的线程池
 */
public final class ThreadPool {

    private static ThreadPool instance = new ThreadPool();

    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final byte TYPE_SINGLE = -1;
    private static final byte TYPE_CACHED = -2;
    private static final byte TYPE_IO     = -4;
    private static final byte TYPE_CPU    = -8;
    private static final byte TYPE_FIX    = -10;

    private final Map<Integer,ExecutorService> TYPE_POOLS = new ConcurrentHashMap<>();//不同类型，放不同的线程池

    private ThreadPool() {

    }

    public static ThreadPool getInstance() {
        //饿汉模式的单利
        return instance;
    }

    /**
     * 根据类型得到线程池
     * @param type 类型
     * @param size 线程池大小
     * @return
     */
    private ExecutorService getPoolByType(final int type,@IntRange(from = 1) final int size) {
        ExecutorService pool = TYPE_POOLS.get(type);

        if (pool == null) {
            Log.i("ThreadPool","创建新的线程池！");
            pool = createPoolByType(type,size);
            TYPE_POOLS.put(type, pool);
        }
        return pool;
    }

    /**
     * 根据类型创建线程池
     * @param type 类型
     * @param size 线程池大小
     * @return
     */
    private ExecutorService createPoolByType(final int type,@IntRange(from = 1) final int size) {
        switch (type) {
            case TYPE_SINGLE:
                //单个工作的线程池（需要保证顺序执行的场景，并且只有一个线程在执行）
                return Executors.newSingleThreadExecutor();

            case TYPE_CACHED:
                //没有核心线程，但非核心线程无限大(处理执行时间比较短的任务)
                //执行快，但数量多
                return Executors.newCachedThreadPool();

            case TYPE_IO:
                //IO 密集型任务：由于 IO 操作速度远低于 CPU 速度，那么在运行这类任务时，CPU 绝大多数时间处于空闲状态，
                //那么线程池可以配置尽量多些的线程，以提高 CPU 利用率，推荐配置为 (2 * CPU 核心数 + 1)；
                //线程多
                return new ThreadPoolExecutor(2 * CPU_COUNT + 1,
                        2 * CPU_COUNT + 1,
                        30, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(128)
                );
            case TYPE_CPU:
                //CPU 密集型任务（任务执行时间短）
                //线程少
                return new ThreadPoolExecutor(CPU_COUNT + 1,
                        2 * CPU_COUNT + 1,
                        30, TimeUnit.SECONDS,
                        new LinkedBlockingQueue<Runnable>(128)
                );
            default:
                //得到固定大小的线程池
                return Executors.newFixedThreadPool(size);

        }
    }

    /**
     * 得到固定大小的线程池
     * @param size 大小从1开始
     * @return
     */
    public ExecutorService getFixedPool(@IntRange(from = 1) final int size) {

        return getPoolByType(TYPE_FIX,size);
    }

    /**
     * 得到默认大小的固定线程池(大小10个)
     * @return
     */
    public ExecutorService getFixedPool() {
        int defaultSize = 10;
        return getFixedPool(defaultSize);
    }

    /**
     * 得到单线程池
     * @return
     */
    public ExecutorService getSinglePool() {
        return getPoolByType(TYPE_SINGLE,1);
    }

    /**
     * 获取缓冲线程池
     * @return
     */
    public ExecutorService getCachedPool() {
        return getPoolByType(TYPE_CACHED,1);
    }

    /**
     * io型的线程池
     * @return
     */
    public ExecutorService getIoPool() {
        return getPoolByType(TYPE_IO,1);
    }

    /**
     * cpu型的线程池
     * @return
     */
    public ExecutorService getCpuPool() {
        return getPoolByType(TYPE_CPU,1);
    }
}
