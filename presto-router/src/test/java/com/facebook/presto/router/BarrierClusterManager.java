package com.facebook.presto.router;

import com.facebook.presto.router.cluster.ClusterManager;
import com.facebook.presto.router.cluster.RemoteInfoFactory;
import com.facebook.presto.router.cluster.RemoteStateConfig;

import java.util.concurrent.CyclicBarrier;

public class BarrierClusterManager
        extends ClusterManager
{
    private final CyclicBarrier barrier;

    public BarrierClusterManager(RouterConfig config, RemoteInfoFactory remoteInfoFactory,
            RemoteStateConfig remoteStateConfig, CyclicBarrier barrier)
    {
        super(config, remoteInfoFactory, remoteStateConfig);
        this.barrier = barrier;

        super.onConfigChangeDetection = () -> {
            try {
                System.out.println("Barrier cluster manager active");
                super.onConfigChangeDetection.apply();
                barrier.await();
            }
            catch (Exception e) {
                throw new RuntimeException("Error while awaiting cyclic barrier", e);
            }
        };
    }

    @Override
    public void startConfigReloadTaskFileWatcher()
    {
        super.startConfigReloadTaskFileWatcher();
    }
}
