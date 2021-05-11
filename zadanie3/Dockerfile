FROM ubuntu:18.04

ENV TZ=Europe/Warsaw

RUN apt-get update && apt-get install -y \ 
    vim \
    git \ 
    wget \ 
    curl \
    unzip \
    gnupg2 

RUN apt-get install -y openjdk-8-jdk &&\
    export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

RUN wget https://downloads.lightbend.com/scala/2.12.3/scala-2.12.3.deb &&\
    dpkg -i scala-2.12.3.deb

RUN echo "deb https://dl.bintray.com/sbt/debian /" | tee -a /etc/apt/sources.list.d/sbt.list &&\
    curl -sL "https://keyserver.ubuntu.com/pks/lookup?op=get&search=0x2EE0EA64E40A89B84B2DF73499E82A75642AC823" | apt-key add &&\
    apt-get update -y &&\
    apt-get install -y sbt

RUN curl -fsSL https://rpm.nodesource.com/setup_current.x &&\
	apt install -y npm


EXPOSE 9000
EXPOSE 3000
EXPOSE 5000
EXPOSE 8080

RUN useradd -ms /bin/bash olgazalinska
RUN adduser olgazalinska sudo

USER olgazalinska

WORKDIR /home/olgazalinska

RUN mkdir /home/olgazalinska/ebiznes_volume/

VOLUME [ "/home/olgazalinska/ebiznes_volume/" ]
