##Steps to configure mongoDb cluster Locally

Creating Mongodb Config Server
```
mongod --configsvr --port 27010 --dbpath ~/mongodb/data1
```

Setting up Query Routers
```
mongos -configdb localhost:27010 --port 27011 &
```

Creating mondoDb shards

```
mongod --port 27012 --dbpath ~/mongodb/data2 &

mongod --port 27013 --dbpath ~/mongodb/data3 &
```

Registering the shards with mongos

```
mongo --port 27011 --host localhost
sh.addShard("localhost:27012")
sh.addShard("localhost:27013")
```

Define sharding key for the collection

```
sh.enableSharding("tags")
db.tweets.ensureIndex( { _id : "hashed" } )
sh.shardCollection("tags.tweets", { "_id": "hashed" } )
```
