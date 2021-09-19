"use strict"

function renderParallelCoordinate(properties){

    let x = d3.scalePoint().range([0, properties.width], 1),
        y = {},
        dragging = {};

    let line = d3.line(),
        axis = d3.axisLeft(),
        background,
        foreground,
        extents

    let dimensions = d3.keys(properties.curr_data[0]).filter(d =>
        y[d] = d3.scaleLinear()
            .domain([0, d3.extent(properties.curr_data, p => +p[d])[1]])
            .range([properties.height, 0])
    )
    extents = dimensions.map(p => [0,0])
    // Extract the list of dimensions and create a scale for each
    x.domain(dimensions)

    // Add grey background lines for context.
    background = properties.svg.append("g")
        .attr("class", "background")
        .selectAll("path")
        .data(properties.curr_data)
        .enter().append("path")
        .attr("d", path)

    // Add blue foreground lines for focus.
    foreground = properties.svg.append("g")
        .attr("class", "foreground")
        .selectAll("path")
        .data(properties.curr_data)
        .enter().append("path")
        .attr("d", path);

    // Add a group element for each dimension.
    let g = properties.svg.selectAll(".dimension")
        .data(dimensions)

    g.exit().remove()
    let g_axis = g.enter()
        .append("g").merge(g)
        .attr("class", "dimension")
        .attr("transform", d => "translate(" + x(d) + ")")
        .call(d3.drag()
            .subject(d => {x: x(d)})
            .on("start", function(d) {
                dragging[d] = x(d);
                background.attr("visibility", "hidden");
            })
            .on("drag", function(d) {
                dragging[d] = Math.min(properties.width, Math.max(0, d3.event.x));
                foreground.attr("d", path);
                dimensions.sort(function(a, b) { return position(a) - position(b); });
                x.domain(dimensions);
                g.attr("transform", function(d) { return "translate(" + position(d) + ")"; })
            })
            .on("end", function(d) {
                delete dragging[d];
                transition(d3.select(this)).attr("transform", "translate(" + x(d) + ")");
                transition(foreground).attr("d", path);
                background
                    .attr("d", path)
                    .transition()
                    .delay(500)
                    .duration(0)
                    .attr("visibility", null);
            })
        )

    // Add an axis and title.
    g_axis.append("g").merge(g_axis)
        .attr("class", "axis")
        .each(function(d) { d3.select(this).call(axis.scale(y[d])); })
        .append("text")
        .attr("fill", "black")
        .style("text-anchor", "middle")
        .attr("y", -9)
        .text(function(d) { return d; })

    // Add and store a brush for each axis.
    g_axis.append("g").merge(g_axis)
        .attr("class", "brush")
        .each(function(d) {
            d3.select(this)
                .call(y[d].brush =
                    d3.brushY()
                        .extent([[-8, 0], [8,properties.height]])
                        .on("brush start", brushstart)
                        .on("brush", ()=> d3.event.sourceEvent.stopPropagation())
                        .on("brush", brush_parallel_chart)
                        .on("end", brush_end));

            //d3.select(this).call(y[d].brush = d3.brushY(y[d]).on("start", brushstart).on("brush", brush))
        })
        .selectAll("rect")
        .attr("x", -8)
        .attr("width", 16);

    function position(d) {
        return dragging[d] == null ? x(d) : dragging[d]
    }

    function transition(g) {
        return g.transition().duration(500);
    }

    // Returns the path for a given data point.
    function path(d) {
        return line(dimensions.map(function(p) { return [position(p), y[p](d[p])]; }));
    }


    function brushstart(selectionName) {
        foreground.style("display", "none")

        let dimensionsIndex = dimensions.indexOf(selectionName);

        extents[dimensionsIndex] = [0, 0];

        foreground.style("display", d =>
            dimensions.every((p, i) =>
                (extents[i][0]===0 && extents[i][0]===0) ? true : extents[i][1] <= d[p] && d[p] <= extents[i][0]
            ) ? null : "none"
        )
    }

    function brush_parallel_chart() {

        for(let i=0;i<dimensions.length;++i)
            if(d3.event.target===y[dimensions[i]].brush)
                extents[i]=d3.event.selection.map(y[dimensions[i]].invert,y[dimensions[i]]);

        foreground.style("display", d =>
            dimensions.every((p, i) =>
                (extents[i][0]===0 && extents[i][0]===0) ? true : extents[i][1] <= d[p] && d[p] <= extents[i][0]
            ) ? null : "none"
        )
    }

    function brush_end(){
        if (!d3.event.sourceEvent) return; // Only transition after input.
        if (!d3.event.selection) return; // Ignore empty selections.

        for(let i=0;i<dimensions.length;++i)
            if(d3.event.target===y[dimensions[i]].brush) {
                extents[i]=d3.event.selection.map(y[dimensions[i]].invert,y[dimensions[i]]);
                extents[i][0] = Math.round( extents[i][0] * 10 ) / 10;
                extents[i][1] = Math.round( extents[i][1] * 10 ) / 10;
                d3.select(this).transition().call(d3.event.target.move, extents[i].map(y[dimensions[i]]));
            }
    }
    /*
    // Handles a brush event, toggling the display of foreground lines.
    function brush() {
        let actives = dimensions.filter(function(p) { return !y[p].brush.empty(); }),
        extents = actives.map(function(p) { return y[p].brush.extent(); });
        foreground.style("display", function(d) {
            return actives.every(function(p, i) {
            return extents[i][0] <= d[p] && d[p] <= extents[i][1];
            }) ? null : "none";
        });
    }
    */
}
