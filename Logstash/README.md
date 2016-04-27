Since TwitterRiver is no more use Logstash to get raw tweets based on keywords

###thanks to http://david.pilato.fr/blog/2015/06/01/indexing-twitter-with-logstash-and-elasticsearch/

1: Create ES index using twitter_template.json 
2: nohup opt/logstash/bin/logstash -f twitter_logstash.conf &
3: open kibana and put twitter as index pattern