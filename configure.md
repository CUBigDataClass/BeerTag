##Steps to configure cloud machine

* Install MongoDB 

1: Configure the package management system (yum).

``` 
 Create a /etc/yum.repos.d/mongodb-org-3.2.repo file so that you can install MongoDB directly, using yum.
 
[mongodb-org-3.2]
name=MongoDB Repository
baseurl=https://repo.mongodb.org/yum/amazon/2013.03/mongodb-org/3.2/x86_64/
gpgcheck=0
enabled=1
 ```
 
2: Install the MongoDB packages and associated tools

`sudo yum install -y mongodb-org`

* Run MongoDB

1: Start mongodb using this command `sudo service mongod start`  
2: Start MongoDB on reboot `sudo chkconfig mongod on`  
3: Stop mongodb using this command `sudo service mongod stop`
