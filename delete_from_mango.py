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
			tweet_id = tweet_dict['id']
			remove(tweet_id)
		except 	Exception as e:
			print "Unexpected error:", type(e), e
			sys.exit()
        

def remove(id):
    '''Return all of the teams in western confence
    '''
    print id
    query = {'id':long(id)}

    print tweets.remove(query)




if __name__ == '__main__':
	if len(sys.argv) != 2:
		print('Please provide the json file to delete data from')
		sys.exit()

	main(sys.argv[1])

