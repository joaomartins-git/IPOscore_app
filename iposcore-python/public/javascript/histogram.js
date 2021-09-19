"use strict"

function renderHistogram(properties, init){
    init = true
    let svg = properties.svg

    let min_val = properties.containerHistogram.hists_data[0][0]
    let max_val = 0
    for(let i = 0; i < properties.containerHistogram.hists_data.length; ++i){
        let curr_min = d3.min(properties.containerHistogram.hists_data[i])
        let curr_max = d3.max(properties.containerHistogram.hists_data[i])
        if(curr_min < min_val)
            min_val = curr_min
        if(curr_max > max_val)
            max_val = curr_max
    }
    let ticks = []
    if(!(properties.curr_data_type === "Numerical_Continuous")) {
        for (let i = properties.widthScaleLinear.domain()[0]; i <= properties.widthScaleLinear.domain()[1]; i += properties.threshold_spacing)
            ticks.push(i)
        if(ticks.length > 40)
            ticks = properties.widthScaleLinear.ticks(40)
    }
    else{
        ticks = properties.widthScaleLinear.ticks(40)
    }
    let histogram = d3.histogram()

    histogram
        .domain(properties.widthScaleLinear.domain())
        .thresholds(ticks)
        .value(d => d)

    const lineFunction = d3.line()
        .x(d => properties.margin.left + properties.widthScaleLinear(ticks[d.indice]))
        .y(d => properties.heightScaleLinear(d.value))


    let max = 0
    for(let i = 0; i < properties.containerHistogram.hists_data.length; ++i)
        if (d3.max(histogram(properties.containerHistogram.hists_data[i]), d => d.length) > max)
            max = d3.max(histogram(properties.containerHistogram.hists_data[i]), d => d.length)

    const lines = []
    for(let x = 0; x < properties.containerHistogram.hists_data.length; ++x) {
        let lineData = []
        let bins = histogram(properties.containerHistogram.hists_data[x])
        let curr_max = 0
        for (let i = 0; i < bins.length; ++i)
            if (curr_max < bins[i].length)
                curr_max = bins[i].length

        const mean = properties.containerHistogram.hists_data_extra_info[x].mean
        const std = properties.containerHistogram.hists_data_extra_info[x].std
        const uniques = ticks
        // This is the first part of the formula for the norm
        const ni1 = 1 / (Math.sqrt(2 * Math.PI * std * std))

        for (let c = 0; c < uniques.length; c++) {
            // This is the second part of the formula for the norm
            let ni2 = Math.exp(-1 * ((uniques[c] - mean) * (uniques[c] - mean)) / (2 * (std * std)))

            const value = ni1 * ni2
            lineData.push(Math.round(value * max))
            if(lineData[c] > max)
                max = lineData[c]
        }
        lines.push(lineData)
    }

    properties.heightScaleLinear.domain([0,max + Math.round(max/5)])

    const colors = ["Red", "Green", "Blue", "Yellow", "Magenta", "Cyan", "Orange", "Purple", "BLACK"]

    for(let x = 0; x < properties.containerHistogram.hists_data.length; ++x) {
        let bins = histogram(properties.containerHistogram.hists_data[x])

        // append the bars for series i
        let bars = svg.selectAll("rect"+x)
            .data(bins)

        bars.exit().remove()
        bars.enter()
            .append("rect").merge(bars)
            .attr("id", (d, i) => "rect_" + i)
            .attr("x", 1)
            .attr("transform", d =>
                "translate(" + (properties.margin.left + properties.widthScaleLinear(d.x0)) + "," + properties.heightScaleLinear(d.length) + ")"
            )
            .attr("width", d => properties.widthScaleLinear(d.x1) - properties.widthScaleLinear(d.x0))
            .attr("height", d => properties.height - properties.heightScaleLinear(d.length))
            .style("fill", "#158896")
            .style("opacity", 0.4)
            .on('mouseover', function (d, i) {
                d3.selectAll('#rect' + x).select('#rect_' + i)
                    .transition()
                    .style('opacity', 0.2)
                properties.containerHistogram.tip.show(d, this)
            })
            .on('mouseout', function (d, i) {
                d3.selectAll('#rect' + x).select('#rect_' + i)
                    .transition()
                    .style('opacity', 0.4)
                properties.containerHistogram.tip.hide(d, this)
            })

        lines[x] = lines[x].map( function(d, i)
        {
            return {
                value: d,
                indice: i
            }
        })
        svg.append("path")
            .attr("d", lineFunction(lines[x]))
            .attr("stroke", colors[x])
            .attr("stroke-width", 2)
            .attr("fill", "none")

        svg.append("circle").attr("cx",800).attr("cy",30+30*x).attr("r", 6).style("fill", colors[x])
        svg.append("text").attr("x", 820).attr("y", 30+30*x).text(properties.yAxisDomain[properties.curr_class_label][x]).style("font-size", "15px").attr("alignment-baseline","middle")
    }

    if(init) {
        svg.append("g")
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

        svg.append("g")
            .attr("id", "axis-y")
            .attr("class", "y axis")
            .style("font-size", "14px")
            .attr("transform", "translate(" + properties.margin.left + ",0)")
            .call( properties.yAxisLinear)

        properties.svg.append("text")
            .attr("id","text_axis_y")
            .attr("transform", "rotate(-90)")
            .attr("y", 0 - properties.margin.left)
            .attr("x",0 - (properties.height / 2))
            .attr("dy", "1em")
            .style("text-anchor", "middle")
            .style("font-size", "20px")
            .text("Number of Occurances")
    }
    else {
        properties.svg.select("#axis-x").transition("xaxis_violin").duration(500).call(properties.xAxisLinear)
        properties.svg.select("#axis-y").transition("yaxis_violin").duration(500).call(properties.yAxisLinear)
        properties.svg.select("#text_axis_x").text(properties.curr_attribute)
    }
}