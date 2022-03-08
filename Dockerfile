FROM ubuntu

RUN apt update &&  \
    DEBIAN_FRONTEND="noninteractive" apt -y install wget gnupg2 ufw systemctl openjdk-8-jre&& \
	wget -q -O - https://pkg.jenkins.io/debian-stable/jenkins.io.key | apt-key add - && \
	sh -c 'echo deb http://pkg.jenkins.io/debian-stable binary/ > /etc/apt/sources.list.d/jenkins.list' &&  \
	apt update &&  \
	apt -y install jenkins

EXPOSE 8080