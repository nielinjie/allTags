// Generated by CoffeeScript 1.3.3
var clickNode;

clickNode = function(node) {
  return console.log(node);
};

$(function() {
  return $(document).ready(function() {
    var tags;
    tags = new AllTags('#viewport', clickNode);
    _.each(fack.nodes, function(node) {
      return tags.node(new Tag(node[0], node[1]));
    });
    return _.each(fack.edges, function(edge) {
      return tags.edge(edge[0], edge[1]);
    });
  });
});
