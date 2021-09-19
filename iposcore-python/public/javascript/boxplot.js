"use strict"

function renderBoxplot(properties, init){
    init = true

    let barWidth = properties.heightScale.bandwidth() / 2

    // Setup the svg and group we will draw the box plot in
    let svg = properties.svg.append("g").attr("transform", "translate(" + properties.margin.left + ", 0 )")

    // Draw the box plot vertical lines
    let verticalLines = svg.selectAll(".verticalLines")
        .data(properties.containerBoxplot.boxPlotData)
        .enter()
        .append("line")
        .attr("x1",(d) => properties.widthScaleLinear(d.whiskers[0]))
        .attr("y1", (d, i) => properties.heightScale(properties.containerBoxplot.height_domain[i]) + barWidth)
        .attr("x2", (d) => properties.widthScaleLinear(d.whiskers[1]))
        .attr("y2", (d, i) => properties.heightScale(properties.containerBoxplot.height_domain[i]) + barWidth)
        .attr("stroke", "#000")
        .attr("stroke-width", 1)
        .attr("fill", "none")

    // Draw the boxes of the box plot, filled in white and on top of vertical lines
    let rects = svg.selectAll("rect")
        .data(properties.containerBoxplot.boxPlotData)
        .enter()
        .append("rect")
        .attr("id", (d, i) => "rect_"+i)
        .attr("height", barWidth)
        .attr("width", (d) => properties.widthScaleLinear(d.quartile[2]) - properties.widthScaleLinear(d.quartile[0]))
        .attr("x", (d) => properties.widthScaleLinear(d.quartile[0]))
        .attr("y", (d, i) => properties.heightScale(properties.containerBoxplot.height_domain[i]) + barWidth / 2)
        .attr("fill", "#238443")
        .attr("stroke", "#000")
        .attr("stroke-width", 1)
        .on('mouseover', function(d, i) {
            d3.select('#rect_' + i)
                .transition()
                .style('opacity', 0.5)
            properties.containerBoxplot.tip.show(d, this)
        })
        .on('mouseout', function(d, i) {
            d3.select('#rect_' + i)
                .transition()
                .style('opacity', 1)
            properties.containerBoxplot.tip.hide(d, this)
        })

    // Now renderBarchart all the horizontal lines at once - the whiskers and the median
    let horizontalLineConfigs = [
        // Top whisker
        {
            x1: function(datum, i) { return properties.widthScaleLinear(datum.whiskers[0]) },
            y1: function(datum, i) { return properties.heightScale(properties.containerBoxplot.height_domain[i]) + barWidth - barWidth / 2 },
            x2: function(datum, i) { return properties.widthScaleLinear(datum.whiskers[0]) },
            y2: function(datum, i) { return properties.heightScale(properties.containerBoxplot.height_domain[i]) + barWidth + barWidth / 2 }
        },
        // Median line
        {
            x1: function(datum, i) { return properties.widthScaleLinear(datum.quartile[1]) },
            y1: function(datum, i) { return properties.heightScale(properties.containerBoxplot.height_domain[i]) + barWidth - barWidth / 2},
            x2: function(datum, i) { return properties.widthScaleLinear(datum.quartile[1]) },
            y2: function(datum, i) { return properties.heightScale(properties.containerBoxplot.height_domain[i]) + barWidth + barWidth / 2}
        },
        // Bottom whisker
        {
            x1: function(datum, i) { return properties.widthScaleLinear(datum.whiskers[1]) },
            y1: function(datum, i) { return properties.heightScale(properties.containerBoxplot.height_domain[i]) + barWidth - barWidth / 2 },
            x2: function(datum, i) { return properties.widthScaleLinear(datum.whiskers[1]) },
            y2: function(datum, i) { return properties.heightScale(properties.containerBoxplot.height_domain[i]) + barWidth + barWidth / 2 }
        }
    ]

    for(let i=0; i < horizontalLineConfigs.length; i++) {
        let lineConfig = horizontalLineConfigs[i]

        // Draw the whiskers at the min for this series
        let horizontalLine = svg.selectAll(".whiskers")
            .data(properties.containerBoxplot.boxPlotData)
            .enter()
            .append("line")
            .attr("x1", lineConfig.x1)
            .attr("y1", lineConfig.y1)
            .attr("x2", lineConfig.x2)
            .attr("y2", lineConfig.y2)
            .attr("stroke", "#000")
            .attr("stroke-width", 1)
            .attr("fill", "none")
    }

    if (init) {
        properties.svg.append("g")
            .attr("id", "axis-x")
            .attr("class", "x axis")
            .style("font-size", "14px")
            .attr("transform", "translate(" + properties.margin.left + "," + properties.height + ")")
            .call(properties.xAxisLinear)

        properties.svg.append("text")
            .attr("id","text_axis_x")
            .attr("transform","translate(" + (properties.margin.left + properties.width/2) + " ," + (properties.height + properties.margin.top + 20) + ")")
            .style("text-anchor", "middle")
            .style("font-size", "20px")
            .text(properties.curr_attribute)

        properties.svg.append("g")
            .attr("id", "axis-y")
            .attr("class", "y axis")
            .style("font-size", "14px")
            .attr("transform", "translate(" + properties.margin.left + ",0)")
            .call(properties.yAxis)

        properties.svg.append("text")
            .attr("id","text_axis_y")
            .attr("transform", "rotate(-90)")
            .attr("y", 0 - properties.margin.left)
            .attr("x",0 - (properties.height / 2))
            .attr("dy", "1em")
            .style("text-anchor", "middle")
            .style("font-size", "20px")
            .text(properties.curr_class_label + (properties.curr_class_label === properties.class_labels[1]? "(Grade)":""))

    } else {
        properties.svg.select("#axis-x").transition("xaxis_violin").duration(500).call(properties.xAxisLinear)
        properties.svg.select("#axis-y").transition("yaxis_violin").duration(500).call(properties.yAxis)
        if(properties.curr_class_label === properties.class_labels[1])
            properties.svg.select("#text_axis_y").text(properties.curr_class_label+"(Grade)")
        else
            properties.svg.select("#text_axis_y").text(properties.curr_class_label)
    }

}