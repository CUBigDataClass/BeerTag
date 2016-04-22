{"tweet-splitter" (python-bolt-spec
                      ;; topology options passed in
                      options
                      ;; inputs, where does this bolt recieve it's tuples from?
                      {"Tweet-spout-1" :shuffle
                       "Tweet-spout-2" :shuffle}
                      ;; class to run
                      "bolts.TweetSplitter"
                      ;; output spec, what tuples does this bolt emit?
                      ["tweet"]
                      ;; configuration parameters
                      :p 2)
 "tweet-word-counter" (python-bolt-spec
                 options
                 ;; recieves tuples from "sentence-splitter", grouped by word
                 {"tweet-splitter" ["tweet"]}
                 "bolts.tweetCounter"
                 ["tweet" "count"])
 "tweet-count-saver" (python-bolt-spec
                     ;; topology options passed in
                     options
                     {"tweet-counter" :shuffle}
                     "bolts.tweetSaver"
                     ;; does not emit any fields
                     [])}