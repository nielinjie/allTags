pairs= (items)->
  result=[]
  for a in items
    for b in items when b isnt a
      result.push [a,b]
  console.log(result)
  _.uniq(result,false,(a)->
    JSON.stringify(a.sort())
    )
  
syncGet = (url) ->
  got = null
  $.ajax({
        url:url,
        async:false,
        cache:false,
        success:(data)->
          got=data
      })
  JSON.parse(got)
