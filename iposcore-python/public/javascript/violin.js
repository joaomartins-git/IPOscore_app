"use strict"

function renderViolin(properties, init) {

    properties.svg.call(properties.containerViolin.tip)

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
        properties.svg.select("#text_axis_x").text(properties.curr_attribute)
        if(properties.curr_class_label === properties.class_labels[1])
            properties.svg.select("#text_axis_y").text(properties.curr_class_label+"(Grade)")
        else
            properties.svg.select("#text_axis_y").text(properties.curr_class_label)
    }

    let curr = 0

    const threshholds = properties.curr_data_type === "Numerical_Continuous"? properties.widthScaleLinear.ticks(40) : properties.containerViolin.thresholds

    const histoChart = d3.histogram()

    histoChart
        .domain([properties.containerViolin.min, properties.containerViolin.max])
        .thresholds(threshholds)
        .value(d => d)

    let max_ocur = 0
    const area = d3.area()
        .y0(d => -((d.length) / (max_ocur) * properties.heightScale.bandwidth() / 2))
        .y1(d => d.length / max_ocur * properties.heightScale.bandwidth() / 2)
        .x(d => properties.widthScaleLinear(d.x0))
        .curve(d3.curveCatmullRom)

    const violins_graphs = properties.svg.selectAll(".violins")
        .data(properties.containerViolin.data)

    violins_graphs.exit().remove()
    violins_graphs.enter()
        .append("path")
        .attr("id", (d, i) => "path_" + i)
        .attr("class", "violins")
        .merge(violins_graphs)
        .style("stroke-width", 0)
        .attr("d", (d) => {
            max_ocur = 0
            let value = null
            let ocur = 0
            for (let x = 0; x < d.length; ++x) {
                let current_value = properties.curr_data_type === "Numerical_Continuous"? findCloseValue(d[x],threshholds):
                Math.round(d[x]*properties.containerViolin.threshold_multiplier)/properties.containerViolin.threshold_multiplier
                if (current_value !== value) {
                    if (ocur !== 1)
                        if (max_ocur < ocur) max_ocur = ocur
                    ocur = 1
                    value = current_value
                } else ++ocur
            }
            if (max_ocur < ocur) max_ocur = ocur
            return area(histoChart(d))
        })
        .on("mouseover", (d, i) => {
            d3.select("#path_" + i)
                .transition()
                .style("opacity", 0.5)
            curr = i
            properties.containerViolin.tip.show(d)
        })
        .on("mouseout", (d, i) => {
            d3.select("#path_" + i)
                .transition()
                .style("opacity", 1)
            curr = i
            properties.containerViolin.tip.hide(d)
        })
        .transition("violin").duration(500)
        .attr("transform", (d, i) =>
            "translate(" +
            properties.margin.left +
            "," +
            (properties.heightScale(properties.containerViolin.height_domain[i]) + properties.heightScale.bandwidth() / 2) +
            ")"
        )
        .style("fill", "#238443")
}

function findCloseValue(value, threshholds){
    for(let i = 0; i < threshholds.length; ++i){
        let diff = value - threshholds[i]
        if(diff < 0) return threshholds[i-1]
    }
    return threshholds[threshholds.length-1]
}