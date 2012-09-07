class Tag 
  constructor: (@name, @text)-> 

class Resource 
  constructor: (@id,@tags)->
    
class Resources 
  constructor: (@resources)->
  tags : ->
    _.uniq(_.flatten(_.union(_.pluck(@resources,'tags'))),false,(t)->t.name)

  byTagName : (tagName) ->
    _.filter(@resources, (re) ->
      _.contains(_.pluck(re.tags,'name'),tagName))
      
  tagRelations: ->
    result=[]
    pai= _.flatten(_.map(@resources,(re)->
      pairs(_.pluck(re.tags,'name'))),true)
    for pair in pai
      result.push(pair)
    result
    
      
      
