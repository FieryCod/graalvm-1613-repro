# Minimal reproduction for GraalVM Oracle issue #1613

## Issue link
- https://github.com/oracle/graal/issues/1613

## Environment 
  - GraalVM 21.1.0 Java 8 CE (Java Version 1.8.0_292-b09)
  - OS:
  
  ```
    Linux 64-bit 
    Distributor ID: Pop
    Description:    Pop!_OS 20.10
    Release:        20.10
    Codename:       groovy
  ```
  
## Steps to reproduce
  1. Install `clojure` command
     ```
     brew install clojure/tools/clojure
     ```
  2. Install GraalVM. Download, then unzip.
     ```
     https://github.com/graalvm/graalvm-ce-builds/releases/download/vm-21.1.0/graalvm-ce-java8-linux-amd64-21.1.0.tar.gz
     ```
  3. Go to unzipped folder to `/bin` directory and install `native-image` via `./gu install native-image`
  
  4. Create a symlink in `/usr/local/bin` between `native-image` and `place-you-did-download-the-graalvm`
  
  5. `make clean compile graalvm`
 
  
  
  
## Full verbose stack trace after running `make clean compile graalvm`

``` sh
❯ make clean compile graalvm
[main] INFO hf.depstar.uberjar - Compiling com.company.example.core ...
[main] INFO hf.depstar.uberjar - Building uber jar: target/output.jar
Executing [
/home/fierycod/.graalvm/jre/bin/java \
-XX:+UseParallelGC \
-XX:+UnlockExperimentalVMOptions \
-XX:+EnableJVMCI \
-Dtruffle.TrustAllTruffleRuntimeProviders=true \
-Dtruffle.TruffleRuntime=com.oracle.truffle.api.impl.DefaultTruffleRuntime \
-Dgraalvm.ForcePolyglotInvalid=true \
-Dgraalvm.locatorDisabled=true \
-d64 \
-XX:-UseJVMCIClassLoader \
-XX:+UseJVMCINativeLibrary \
-Xss10m \
-Xms1g \
-Xmx13107960216 \
-Duser.country=US \
-Duser.language=en \
-Djava.awt.headless=true \
-Dorg.graalvm.version=21.1.0 \
-Dorg.graalvm.config=CE \
-Dcom.oracle.graalvm.isaot=true \
-Djava.system.class.loader=com.oracle.svm.hosted.NativeImageSystemClassLoader \
-Xshare:off \
-Djvmci.class.path.append=/home/fierycod/.graalvm/jre/lib/jvmci/graal.jar \
-Djdk.internal.lambda.disableEagerInitialization=true \
-Djdk.internal.lambda.eagerlyInitialize=false \
-Djava.lang.invoke.InnerClassLambdaMetafactory.initializeLambdas=false \
-javaagent:/home/fierycod/.graalvm/jre/lib/svm/builder/svm.jar \
-Xbootclasspath/a:/home/fierycod/.graalvm/jre/lib/boot/graaljs-scriptengine.jar:/home/fierycod/.graalvm/jre/lib/boot/graal-sdk.jar \
-cp \
/home/fierycod/.graalvm/jre/lib/svm/builder/llvm-platform-specific-shadowed.jar:/home/fierycod/.graalvm/jre/lib/svm/builder/pointsto.jar:/home/fierycod/.graalvm/jre/lib/svm/builder/llvm-wrapper-shadowed.jar:/home/fierycod/.graalvm/jre/lib/svm/builder/javacpp-shadowed.jar:/home/fierycod/.graalvm/jre/lib/svm/builder/svm.jar:/home/fierycod/.graalvm/jre/lib/svm/builder/svm-llvm.jar:/home/fierycod/.graalvm/jre/lib/svm/builder/objectfile.jar:/home/fierycod/.graalvm/jre/lib/jvmci/jvmci-hotspot.jar:/home/fierycod/.graalvm/jre/lib/jvmci/graal-truffle-jfr-impl.jar:/home/fierycod/.graalvm/jre/lib/jvmci/graal-management.jar:/home/fierycod/.graalvm/jre/lib/jvmci/graal.jar:/home/fierycod/.graalvm/jre/lib/jvmci/jvmci-api.jar:/home/fierycod/.graalvm/jre/lib/resources.jar \
com.oracle.svm.hosted.NativeImageGeneratorRunner \
-watchpid \
96995 \
-imagecp \
/home/fierycod/.graalvm/jre/lib/boot/graaljs-scriptengine.jar:/home/fierycod/.graalvm/jre/lib/boot/graal-sdk.jar:/home/fierycod/.graalvm/jre/lib/svm/library-support.jar:/home/fierycod/graalvm-1613-repro/target/output.jar \
-H:Path=/home/fierycod/graalvm-1613-repro \
'-H:Class@manifest from file:///home/fierycod/graalvm-1613-repro/target/output.jar=com.company.example.core' \
'-H:Name@manifest from file:///home/fierycod/graalvm-1613-repro/target/output.jar=output' \
-H:+AllowIncompleteClasspath \
-H:+ReportUnsupportedElementsAtRuntime \
-H:FallbackThreshold=0 \
-H:EnableURLProtocols=http,https \
-H:ClassInitialization=:build_time \
-H:CLibraryPath=/home/fierycod/.graalvm/jre/lib/svm/clibraries/linux-amd64 \
]
[output:97019]    classlist:   1,491.81 ms,  1.23 GB
[output:97019]        (cap):     474.41 ms,  1.23 GB
[output:97019]        setup:   1,892.67 ms,  1.23 GB
2021-05-28 19:04:41.178:INFO::ForkJoinPool-4-worker-15: Logging initialized @9246ms to org.eclipse.jetty.util.log.StdErrLog
[output:97019]     (clinit):     558.26 ms,  2.77 GB
[output:97019]   (typeflow):  17,010.48 ms,  2.77 GB
[output:97019]    (objects):  23,695.88 ms,  2.77 GB
[output:97019]   (features):     906.66 ms,  2.77 GB
[output:97019]     analysis:  44,286.29 ms,  2.77 GB
[output:97019]     universe:   1,572.47 ms,  2.77 GB
[output:97019]      (parse):   4,115.88 ms,  2.78 GB
[output:97019]     (inline):   7,012.38 ms,  4.63 GB
[output:97019]    (compile):  36,008.99 ms,  4.29 GB
[output:97019]      compile:  49,418.84 ms,  4.29 GB
[output:97019]        image:   2,403.79 ms,  5.14 GB
Fatal error:com.oracle.svm.core.util.VMError$HostedError: com.oracle.svm.core.util.VMError$HostedError: java_time.amount$days.invokeStatic(Object): has no code address offset set.
        at com.oracle.svm.core.util.VMError.shouldNotReachHere(VMError.java:72)
        at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:694)
        at com.oracle.svm.hosted.NativeImageGenerator.lambda$run$2(NativeImageGenerator.java:495)
        at java.util.concurrent.ForkJoinTask$AdaptedRunnableAction.exec(ForkJoinTask.java:1386)
        at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
        at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
        at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
        at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:175)
Caused by: com.oracle.svm.core.util.VMError$HostedError: java_time.amount$days.invokeStatic(Object): has no code address offset set.
        at com.oracle.svm.core.util.VMError.shouldNotReachHere(VMError.java:68)
        at com.oracle.svm.hosted.meta.HostedMethod.getCodeAddressOffset(HostedMethod.java:149)
        at com.oracle.svm.hosted.image.LIRNativeImageCodeCache.patchMethods(LIRNativeImageCodeCache.java:186)
        at com.oracle.svm.hosted.image.NativeImage$NativeTextSectionImpl.writeTextSection(NativeImage.java:945)
        at com.oracle.svm.hosted.image.NativeImage.build(NativeImage.java:439)
        at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:683)
        ... 6 more
Error: Image build request failed with exit status 1
com.oracle.svm.driver.NativeImage$NativeImageError: Image build request failed with exit status 1
        at com.oracle.svm.driver.NativeImage.showError(NativeImage.java:1772)
        at com.oracle.svm.driver.NativeImage.build(NativeImage.java:1519)
        at com.oracle.svm.driver.NativeImage.performBuild(NativeImage.java:1480)
        at com.oracle.svm.driver.NativeImage.main(NativeImage.java:1467)
make: *** [Makefile:12: graalvm] Error 1
```
