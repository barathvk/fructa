FROM frolvlad/alpine-oraclejdk8
WORKDIR /fructa
ADD ./build /fructa
ENTRYPOINT ["java","-Dconfig.file=application.conf","-jar","/fructa/fructa.jar"]