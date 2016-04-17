from operator import itemgetter

f = open('news.txt')

text = f.readlines()


count = 0

items = []
for i in text:
	title_count = i.split()
	count = count + int(title_count[1])

for i in text:
	title_count = i.split()
	items.append( (title_count[0],round(int(title_count[1])*100.0/count,2)) )

print items

sorted_by_share = sorted(items, key=itemgetter(1),reverse=True)

f.close()

f = open('ranking_news_channels.txt','w')

for i in sorted_by_share:
	o = str(i[0])+'-'+str(i[1])
	f.write(o+'\n')

f.flush()
f.close()

print 'done' 

