class AllTags
   node: (tag)->
     @sys.addNode(tag.name,{text:tag.text})
   edge: (a,b)->
     @sys.addEdge(a,b)

   constructor: (@canvas, @callback,@size,@resources)->
     @sys=arbor.ParticleSystem(@size[0],@size[1],0.5)
     @sys.parameters({gravity:true})
     @sys.renderer=Renderer(@canvas,{'clicked':@callback})
     that=this
     _.each(@resources.tags(),(tag)->that.node(tag))
     _.each(@resources.tagRelations(),(r)->that.edge(r[0],r[1]))

  





