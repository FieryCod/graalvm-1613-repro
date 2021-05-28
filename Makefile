SHELL := /bin/bash
PWD=$$(pwd)
USER_GID=$$(id -u):$$(id -g)

clean:
	@rm -Rf .cpcache target output output.build_artifacts.txt

compile:
	@clojure -X:uberjar :aot [com.company.example.core] :jvm-opts '["-Dclojure.compiler.direct-linking=true" "-Dclojure.spec.skip-macros=true"]' :jar target/output.jar :main-class com.company.example.core

graalvm:
	@native-image -jar target/output.jar \
		 -H:+AllowIncompleteClasspath \
		 --report-unsupported-elements-at-runtime \
		 --no-fallback \
		 --verbose \
		 --enable-url-protocols=http,https \
		 --no-server \
		 --initialize-at-build-time
