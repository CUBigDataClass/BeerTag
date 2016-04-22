(ns wordcount
  (:use     [streamparse.specs])
  (:gen-class))

(defn tweetcount [options]
   [
    ;; spout configuration
    {"tweet-spout" (python-spout-spec
          options
          "spouts.tweets.TweetSpout"
          ["tweet"]
          )
    }
    ;; bolt configuration
    {"count-bolt" (python-bolt-spec
          options
          {"tweet-spout" :shuffle}
          "bolts.tweetcount.TweetCounter"
          ["tweet" "count"]
          :p 2
          )
    }
  ]
)