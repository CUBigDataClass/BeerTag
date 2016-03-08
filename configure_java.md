##Configure Java on the AWS ubuntu machine

The following commands were used to install java on aws machine
```
scp  -i beertag.pem jdk-7u79-linux-i586.tar.gz   ubuntu@52.37.90.119:
sudo apt-get update
sudo mkdir /usr/local/java
sudp cp jdk-8u73-linux-x64.tar.gz /usr/local/java/
sudo tar xvzf jdk-7u45-linux-i586.tar.gz

sudo update-alternatives --install "/usr/bin/java" "java" "/usr/local/java/jdk1.7/jre/bin/java" 1
sudo update-alternatives --install "/usr/bin/javac" "javac" "/usr/local/java/jdk1.7/bin/javac" 1
sudo update-alternatives --set java /usr/local/java/jdk1.7/jre/bin/java
sudo update-alternatives --set javac /usr/local/java/jdk1.7/bin/javac 
apt-get install libc6-i386
```
