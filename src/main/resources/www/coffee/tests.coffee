tagNames=(tags)->
  _.pluck(tags,'name')

aResource=new Resource('resourceA',[new Tag('A','TagA'),new Tag('B','TagB')])
bResource=new Resource('resourceB',[new Tag('A','TagA'),new Tag('C','TagC')])
cResource=new Resource('resourceC',[new Tag('D','TagD'),new Tag('E','TagE')])
dResource=new Resource('resourceC',[new Tag('A','TagA'),new Tag('E','TagE'),new Tag('B','TagB')])

test('resources', ->
  resources=new Resources([aResource])
  
  deepEqual(tagNames(resources.tags()),['A','B'])
  deepEqual(resources.byTagName('A'),[aResource])
  
)

test('multiple resources', ->
  resources=new Resources([aResource])
  deepEqual(resources.tagRelations(),[['A','B']])
  res2=new Resources([aResource,bResource])
  deepEqual(res2.tagRelations(), [['A','B'],['A','C']])
  res3=new Resources([aResource,cResource])
  deepEqual(res3.tagRelations(), [['A','B'],['D','E']])
  res4=new Resources([aResource,bResource,cResource])
  deepEqual(res4.tagRelations(), [['A','B'],['A','C'],['D','E']])
  deepEqual(tagNames(res4.tags()),['A','B','C','D','E'])
  deepEqual(res4.byTagName('A'),[aResource,bResource])
  
  res5=new Resources([aResource,bResource,cResource,dResource])
  #'A','B' duplicated, saying A and B have more related
  deepEqual(res5.tagRelations(), [['A','B'],['A','C'],['D','E'],['A','E'],['A','B'],['B','E']])
  deepEqual(tagNames(res5.tags()),['A','B','C','D','E'])
  deepEqual(res5.byTagName('A'),[aResource,bResource,dResource])
  
)


test('pairs', ->
  deepEqual(pairs(['1','2','3']),[['1','2'],['1','3'],['2','3']] )
)
