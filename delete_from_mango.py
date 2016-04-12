''' Python script to insert tweets in mongodb
'''
import pymongo
import sys
import json

connection = pymongo.MongoClient("")
db=connection.tags
tweets = db.tweets


def main(jsonfile):
	f = open(jsonfile)

	for line in f.readlines():
		try:
			tweet_dict = json.loads(line)
			if 'timestamp_ms' in tweet_dict:
			    timestamp_ms = tweet_dict['timestamp_ms']
			    remove('timestamp_ms',timestamp_ms)
			else:
			    timestamp = tweet_dict['limit']['timestamp_ms']	
			    remove('limit.timestamp_ms',timestamp)		
		except 	Exception as e:
			print "Unexpected error:", type(e), e
			sys.exit()
        

def remove(key,id):
    '''Return all of the teams in western confence
    '''
    query = {key:id}

    print tweets.remove(query)




if __name__ == '__main__':
	if len(sys.argv) != 2:
		print('Please provide the json file to delete data from')
		sys.exit()

	main(sys.argv[1])

