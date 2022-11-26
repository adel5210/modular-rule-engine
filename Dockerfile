FROM openjdk:11-jdk-oracle
MAINTAINER Adel
COPY modular-rule-engine-backend/target/modular-rulesGroup-engine-0.0.1-SNAPSHOT.jar RuleSpring.jar
ENTRYPOINT ["java","-jar","/RuleSpring.jar"]
