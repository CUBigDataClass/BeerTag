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

	for keyword in keywords:
		if keyword in json_text.lower():
			if keyword in count_dict:
				count_dict[keyword] = count_dict[keyword] + 1
			else:
				count_dict[keyword]=1

					
print count_dict


