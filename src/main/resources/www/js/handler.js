function handler(canvas,particleSystem, callbacks) {
	var onCliked = callbacks["clicked"]
	return {
		clicked : function(e) {
			var pos = $(canvas).offset();
			_mouseP = arbor.Point(e.pageX - pos.left, e.pageY - pos.top)
			dragged = particleSystem.nearest(_mouseP);

			if (dragged && dragged.node !== null) {
				// while we're dragging, don't let physics move the node
				dragged.node.fixed = true
			}
			onCliked(dragged.node);
			dragged.node.data['selected']=true;
			var multiSelect=false
			if(!multiSelect)
				particleSystem.eachNode(function(node,pt){
					if(node != dragged.node)
					node.data['selected']=false;
				})
			//$(canvas).bind('mousemove', handler.dragged)
			//$(window).bind('mouseup', handler.dropped)

			return false
		}//,
		/*dragged : function(e) {
			var pos = $(canvas).offset();
			var s = arbor.Point(e.pageX - pos.left, e.pageY - pos.top)

			if (dragged && dragged.node !== null) {
				var p = particleSystem.fromScreen(s)
				dragged.node.p = p
			}

			return false
		},

		dropped : function(e) {
			if (dragged === null || dragged.node === undefined)
				return
			if (dragged.node !== null)
				dragged.node.fixed = false
			dragged.node.tempMass = 1000
			dragged = null
			$(canvas).unbind('mousemove', handler.dragged)
			$(window).unbind('mouseup', handler.dropped)
			_mouseP = null
			return false
		}*/
	}
}