"use strict"

/**
 *
 * @param data - Format : {
 *  [
 *     column_name: "name",
 *     column_value: "value"
 *  ]
 * }
 * @param properties - Object shared amongst views containing axis, scales, data, and other information
 * @param init - Flag to render extra components if it's the first rendering
 */
function renderBarchart(data, properties, init){

	// Calculate maximum height for each rendering
	let max_height = 0
	for (let i = 0; i < data.length; ++i)
        if(max_height < data[i].column_value)
        	max_height = data[i].column_value

	// Domain of Y AXIS
	properties.heightScale.domain([0,max_height])

	// Domain of X AXIS
    const width_domain = []

    for (let i = 0; i < data.length; ++i)
        width_domain.push(data[i].column_name)

	properties.widthScale.domain(width_domain)

	// SET scales for each axis
	properties.xAxis.scale(properties.widthScale)
	properties.yAxis.scale(properties.heightScale)

	// tooltip that will pop up when mouseover columns
    const tip = properties.tip

    // Connect tooltip to SVG
    properties.svg.call(tip)

    // Create bars for each value
	const bar = properties.svg.selectAll(".bar")
		.data(data)

	bar.exit().remove()
	bar.enter()
		.append("rect").merge(bar)
		.attr("class", "bar")
		.attr("id", (d,i) => d.id = properties.curr_data_type+"_bar_" + i)
		.attr("pointer-events","all")
		.on("mouseover", function(d) {
			// Fade
			d3.select("#" + d.id)
				.transition()
				.style("opacity", 0.5)
			tip.show(d,this)
		})
		.on("mouseout", function(d) {
			// Fade
			d3.select("#" + d.id)
				.transition()
				.style("opacity", 1)
			tip.hide(d,this)
		})
		.transition("bar").duration(500)
		.attr("fill", "#158896")
		.attr("x", d => properties.margins.left + properties.widthScale(d.column_name))
		.attr("y", d => properties.margins.top + properties.heightScale(d.column_value))
		.attr("width", properties.widthScale.bandwidth())
		.attr("height",  d => properties.height - properties.heightScale(d.column_value))

	// If first render add the axes
	if (init) {
        properties.svg.append("g")
            .attr("id", "axis-x")
            .attr("class", "x axis")
            .style("font-size", "10px")
            .attr("transform", "translate(" + properties.margins.left + "," + (properties.height + properties.margins.top) + ")")
            .call(properties.xAxis)
            .selectAll("text")
            .attr("y", 0)
            .attr("x", 9)
            .attr("dy", ".35em")
            .attr("transform", "rotate(45)")
            .style("text-anchor", "start")

        properties.svg.append("g")
            .attr("id", "axis-y")
            .attr("class", "y axis")
            .style("font-size", "14px")
            .attr("transform", "translate(" + properties.margins.left + "," + properties.margins.top + ")")
            .call(properties.yAxis)

		properties.svg.append("text")
            .attr("id","text_axis_y")
            .attr("transform", "rotate(-90)")
            .attr("y", 0 - properties.margins.left)
            .attr("x",0 - (properties.height / 2))
            .attr("dy", "1em")
            .style("text-anchor", "middle")
            .style("font-size", "20px")
            .text("p-value")
    }
    // Else update current axis
	else {
		properties.svg.select("#axis-x").transition("xaxis_bar_"+properties.curr_data_type).duration(500).call(properties.xAxis)
            .selectAll("text")
            .attr("y", 0)
            .attr("x", 9)
            .attr("dy", ".35em")
            .attr("transform", "rotate(45)")
            .style("text-anchor", "start")

		properties.svg.select("#axis-y").transition("yaxis_bar_"+properties.curr_data_type).duration(500).call(properties.yAxis)
        if(properties.displayingAll)
        	d3.select("#h3_"+properties.curr_data_type).text(properties.curr_data_type)
		else
			d3.select("h3").text(properties.curr_data_type)
	}
}
