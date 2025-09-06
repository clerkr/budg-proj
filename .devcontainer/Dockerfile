FROM ubuntu:24.04

RUN apt-get update && apt-get install -y \
    curl \
    tar \
    unzip \
    maven

RUN mkdir -p /opt

# Install OpenJDK 24.0.2 - https://openjdk.org/install/
RUN curl -L https://download.java.net/java/GA/jdk24.0.2/fdc5d0102fe0414db21410ad5834341f/12/GPL/openjdk-24.0.2_linux-x64_bin.tar.gz \
    | tar -xz -C /usr/lib/jvm

ENV JAVA_HOME=/usr/lib/jvm/jdk-24.0.2
ENV PATH="${JAVA_HOME}/bin:${PATH}"

WORKDIR /workspace/budg