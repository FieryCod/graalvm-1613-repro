(ns com.company.example.core
  (:gen-class)
  (:require
   [cognitect.aws.client.api :as aws]
   [cognitect.aws.http.cognitect :as http]
   [java-time :as time]))

(def http-client (delay (http/create)))
(def s3          (delay (aws/client {:api         :s3
                                     :http-client @http-client})))

(defn -main
  [& _args]
  ;; The purpose of this repro is to show that some libraries in concjuction
  ;; with other libraries throws some cryptic GraalVM native-image compilation error:
  ;; ------------------------------------------------------------------------------------
  ;; Fatal error:com.oracle.svm.core.util.VMError$HostedError: com.oracle.svm.core.util.VMError$HostedError: java_time.amount$days.invokeStatic(Object): has no code address offset set.
  ;;         at com.oracle.svm.core.util.VMError.shouldNotReachHere(VMError.java:72)
  ;;         at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:694)
  ;;         at com.oracle.svm.hosted.NativeImageGenerator.lambda$run$2(NativeImageGenerator.java:495)
  ;;         at java.util.concurrent.ForkJoinTask$AdaptedRunnableAction.exec(ForkJoinTask.java:1386)
  ;;         at java.util.concurrent.ForkJoinTask.doExec(ForkJoinTask.java:289)
  ;;         at java.util.concurrent.ForkJoinPool$WorkQueue.runTask(ForkJoinPool.java:1056)
  ;;         at java.util.concurrent.ForkJoinPool.runWorker(ForkJoinPool.java:1692)
  ;;         at java.util.concurrent.ForkJoinWorkerThread.run(ForkJoinWorkerThread.java:175)
  ;; Caused by: com.oracle.svm.core.util.VMError$HostedError: java_time.amount$days.invokeStatic(Object): has no code address offset set.
  ;;         at com.oracle.svm.core.util.VMError.shouldNotReachHere(VMError.java:68)
  ;;         at com.oracle.svm.hosted.meta.HostedMethod.getCodeAddressOffset(HostedMethod.java:149)
  ;;         at com.oracle.svm.hosted.image.LIRNativeImageCodeCache.patchMethods(LIRNativeImageCodeCache.java:186)
  ;;         at com.oracle.svm.hosted.image.NativeImage$NativeTextSectionImpl.writeTextSection(NativeImage.java:945)
  ;;         at com.oracle.svm.hosted.image.NativeImage.build(NativeImage.java:439)
  ;;         at com.oracle.svm.hosted.NativeImageGenerator.doRun(NativeImageGenerator.java:683)
  ;;         ... 6 more
  ;; Error: Image build request failed with exit status 1
  ;; com.oracle.svm.driver.NativeImage$NativeImageError: Image build request failed with exit status 1
  ;;         at com.oracle.svm.driver.NativeImage.showError(NativeImage.java:1772)
  ;;         at com.oracle.svm.driver.NativeImage.build(NativeImage.java:1519)
  ;;         at com.oracle.svm.driver.NativeImage.performBuild(NativeImage.java:1480)
  ;;         at com.oracle.svm.driver.NativeImage.main(NativeImage.java:1467)
  ;; make: *** [Makefile:12: graalvm] Error 1
  ;; ------------------------------------------------------------------------------------
  ;; This is rather not cognitect.aws.client.api issue, since I successfully compiled this library with GraalVM on holy-lambda examples
  (println @s3))
