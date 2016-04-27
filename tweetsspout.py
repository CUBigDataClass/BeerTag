import itertools

from streamparse.spout import Spout


class TweetSpout(Spout):

    def initialize(self, stormconf, context):
        self.tweet = [
            "Vodka",
            "Beer",
            "Rum",
            "Mum",
        ]
        self.tweet = itertools.cycle(self.tweet)

    def next_tuple(self):
        tweet = next(self.tweet)
        self.emit([tweet])

    def ack(self, tup_id):
        pass  # if a tuple is processed properly, do nothing

    def fail(self, tup_id):
        pass  # if a tuple fails to process, do nothing