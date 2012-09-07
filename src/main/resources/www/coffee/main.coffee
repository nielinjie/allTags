

tags = {}

$ ()->
  $(document).ready ()->
    size=[$('#canvas-container').width(),$('#canvas-container').height()]
    $('#viewport').attr({ width: size[0], height: size[1] })
    resources=getResources()
    tags=new AllTags('#viewport',clickNode,size,new Resources(resources))
    

getResources = ()->
  syncGet("./resources")

appView= Backbone.View.extend({
  el:$('#all-tags-app')
})

resourceTemplateString=templateString('resource')

ResourcesView=Backbone.View.extend({
  el:$('#resources')
  render:() ->
    this.$el.empty()
    that=this
    _.each(this.model,(resource)->
      that.$el.append($(_.template(resourceTemplateString, resource))))
})


clickNode= (node)->
  (new ResourcesView({model:tags.resources.byTagName(node.name)})).render()