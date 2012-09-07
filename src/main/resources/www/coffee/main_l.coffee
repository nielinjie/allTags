clickNode= (node)->
  console.log(node)
  
$ ()->
  $(document).ready ()->
    tags=new AllTags('#viewport',clickNode)
    _.each(fack.nodes,(node)->
      tags.node(new Tag(node[0],node[1])))
    _.each(fack.edges, (edge)->
      tags.edge(edge[0],edge[1]))
