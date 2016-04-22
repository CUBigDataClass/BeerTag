import re

from streamparse.bolt import Bolt

class TweetSplitterBolt(Bolt):

    def process(self, tup):
        tweet = tup.values[0]  # extract the sentence
        tweet = re.sub(r"[,.;!\?]", "", tweet)  # get rid of punctuation
        words = [[word.strip()] for word in tweet.split(" ") if word.strip()]
        if not words:
            # no words to process in the sentence, fail the tuple
            self.fail(tup)
            return

        self.emit_many(words)
        # tuple acknowledgement is handled automatically