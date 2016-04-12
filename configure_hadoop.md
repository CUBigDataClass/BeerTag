#Hadoop Configuration on AWS

##Copy Java jdk and hadoop sdk

```
scp -v /home/rohit/work/libs/jdk-7u79-linux-i586.tar.gz master:  
scp -v /home/rohit/work/libs/jdk-7u79-linux-i586.tar.gz slave1:  
scp -v /home/rohit/work/libs/jdk-7u79-linux-i586.tar.gz slave2:  
scp -v /home/rohit/work/libs/libs/hadoop-2.7.2.tar.gz master:  
scp -v /home/rohit/work/libs/libs/hadoop-2.7.2.tar.gz slave1:  
scp -v /home/rohit/work/libs/libs/hadoop-2.7.2.tar.gz slave2:  
```

##Copy Private key to the machine so that they can talk to one-another
   
```    
scp -v ~/Downloads/beertag.pem master:  
scp -v ~/Downloads/beertag.pem slave1:  
scp -v ~/Downloads/beertag.pem slave2:  
```

##Configure Environment variables

```
JAVA_HOME=/usr/local/java/jdk1.7  
JRE_HOME=$JAVA_HOME/jre  
HADOOP_HOME=/home/ubuntu/hadoop  
export HADOOP_COMMON_LIB_NATIVE_DIR=$HADOOP_HOME/lib/native  
export HADOOP_OPTS="-Djava.library.path=$HADOOP_HOME/lib"  
PATH=$PATH:$JAVA_HOME/bin:$JRE_HOME/bin  
PATH=$PATH:$HADOOP_HOME/bin  
export HADOOP_HOME  
export JAVA_HOME  
export JRE_HOME  
export PATH  
```

##Unpack Hadoop-SDK

 tar -xvzf hadoop-2.7.2.tar.gz  
 mv hadoop-2.7.2 hadoop  
 cd hadoop/etc/hadoop/  

##Set Java_Home in  `etc/hadoop/hadoop-env.sh`


##Change the file `core-site.xml` as following
```
 <configuration>  
    <property>  
        <name>fs.defaultFS</name>  
        <value>hdfs://master:9000</value>  
    </property>  
</configuration>  
```

##Change the file `hdfs-site.xml` as following

```
<configuration>  
    <property>  
        <name>dfs.replication</name>  
        <value>1</value>  
    </property>  
</configuration>  
```

##Specify hostnames for master and slave servers in `masters` and `slaves` file




##Change the file `mapred-site.xml` as following
```
<configuration>  
    <property>  
        <name>mapreduce.framework.name</name>  
        <value>yarn</value>  
    </property>  
    <property>  
	<name>mapreduce.job.tracker</name>  
	<value>master:5431</value>  
</property>  
</configuration>  
```

##Change the file `yarn-site.xml` as following

```
<configuration>  
    <property>  
        <name>yarn.nodemanager.aux-services</name>  
        <value>mapreduce_shuffle</value>  
    </property>  
    <property>  
	<name>yarn.resourcemanager.resource-tracker.address</name>  
	<value>master:8025</value>  
</property>  
<property>  
	<name>yarn.resourcemanager.scheduler.address</name>  
	<value>master:8035</value>  
</property>  
<property>  
	<name>yarn.resourcemanager.address</name>  
	<value>master:8050</value>  
</property>  

</configuration>
```
