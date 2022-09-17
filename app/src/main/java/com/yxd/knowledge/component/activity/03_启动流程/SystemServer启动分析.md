# SystemServer启动分析

#### ZygoteInit.java

``` java
private static Runnable forkSystemServer(String abiList, String socketName,
    ZygoteServer zygoteServer) {
    // ...
    
    try {
     // ...
       pid = Zygote.forkSystemServer(
                    parsedArgs.mUid, parsedArgs.mGid,
                    parsedArgs.mGids,
                    parsedArgs.mRuntimeFlags,
                    null,
                    parsedArgs.mPermittedCapabilities,
                    parsedArgs.mEffectiveCapabilities);
    } catch (IllegalArgumentException ex) {
        throw new RuntimeException(ex);
    }

    if (pid == 0) {
        if (hasSecondZygote(abiList)) {
            waitForSecondaryZygote(socketName);
        }

        zygoteServer.closeServerSocket();
        return handleSystemServerProcess(parsedArgs);
    }
}
```

``` java
// 处理systemserver进程
private static Runnable handleSystemServerProcess(ZygoteArguments parsedArgs) {
    // ...
    return ZygoteInit.zygoteInit(parsedArgs.mTargetSdkVersion,
                    parsedArgs.mDisabledCompatChanges,
                    parsedArgs.mRemainingArgs, cl);
}
```

``` java
public static Runnable zygoteInit(int targetSdkVersion, long[] disabledCompatChanges,
        String[] argv, ClassLoader classLoader) {

    RuntimeInit.redirectLogStreams();
    // 初始化运行环境
    RuntimeInit.commonInit();
    // 启动Binder
    ZygoteInit.nativeZygoteInit();
    // 通过反射创建程序入口函数的Method对象，并返回Runnable
    return RuntimeInit.applicationInit(targetSdkVersion, disabledCompatChanges, argv,
            classLoader);
}
```