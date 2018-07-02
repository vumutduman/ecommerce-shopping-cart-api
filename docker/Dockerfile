FROM maven:3.3.3-jdk-8
ADD target /target
WORKDIR /target
EXPOSE 8078
CMD java -Xmx2048M -Xms2048M -jar *.jar