{"Tweet-spout-1" (python-spout-spec
                     ;; topology options passed in
                     options
                     ;; name of the python class to ``run``
                     "spouts.firsttweetSpout"
                     ;; output specification, what named fields will this spout emit?
                     ["tweet"]
                     ;; configuration parameters, can specify multiple
                     :p 2)
 "Tweet-spout-2" (shell-spout-spec
                     options
                     "spouts.secondtweetSpout"
                     ["tweet"])}