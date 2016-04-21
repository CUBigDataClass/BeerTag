from senti_classifier import senti_classifier
import json
import tqdm

keywords=['sanders','clinton','trump','kasich','cruz']

f=open('input.json')

count_dict ={}

def computeSentiment(text):

	p,n=senti_classifier.polarity_scores(text)
	
	if p == n:
		return 0
	elif p > n:
		return 1
	else:
		return -1		
		
for i in tqdm.tqdm(f.readlines()):
	
	json_text = json.loads(i)
	if not 'text' in json_text:
		continue
	json_text = json_text['text']
	tweet_text = [json_text]

	for keyword in keywords:
		if keyword in json_text.lower():
			sentiment = computeSentiment(tweet_text)
			if keyword in count_dict:
				if sentiment in count_dict[keyword]:
					count_dict[keyword][sentiment] = count_dict[keyword][sentiment] + 1
				else:
					count_dict[keyword][sentiment] = 1
			else:
				count_dict[keyword] = {}
				count_dict[keyword][sentiment]=1

					
print count_dict


