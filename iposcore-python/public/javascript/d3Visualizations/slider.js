"use strict"

/**
 * Create slider
 * @param properties - Object shared amongst views containing axis, scales, data, and other information
 * @param onChange - Function to be called when we change the value in the slidder
 */
function fillerSlider(properties, onChange){

    let values = d3.range(1, properties.maxSlidderValue)

    properties.sliderFill = d3.sliderBottom()
        .min(1)
        .max(properties.maxSlidderValue)
        .step(1)
        .width(500)
        .tickFormat(d3.format(""))
        .tickValues(values)
        .default(Math.round(properties.maxSlidderValue/3))
        .fill("#158896")
        .on("onchange", onChange)

    let gFill = d3
        .select("div#slider-fill")
        .append("svg")
        .attr("width", 600)
        .attr("height", 100)
        .append("g")
        .attr("transform", "translate(30,30)")
        .transition()
        .duration(200)
        .call(properties.sliderFill)

    gFill.call(properties.sliderFill)

    d3.select("#value-fill").text(d3.format("")(properties.sliderFill.value()))
}
