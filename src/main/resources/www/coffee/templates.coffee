templateCache={}

templateString=(name)->
  re=templateCache[name]
  basePath='./template'
  re ?=  (()-> 
    got = null
    $.ajax({
          url:basePath+'/'+name+'.html',
          async:false,
          cache:false,
          success:(data)->
            got=data
        })
    got
  )()
  re
