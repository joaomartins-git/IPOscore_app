"use strict"

function initExploration(graphData){

    const properties = {}

    properties.data = graphData
    properties.margin = {
        top: 50,
        right: 30,
        bottom: 30,
        left: 60
    }

    properties.class_labels = ["post_surgical_complication", "Clavien_Dindo_classification"]
    properties.data_types = Object.keys(graphData)
    properties.visualizations = [
        {
            name:"Violin Charts",
            renderChart: (properties, init) => renderViolin(properties, init),
            prep: (properties, init) => preparationViolin(properties, init),
            data_types: ["Categorical", "Numerical_Discrete", "Numerical_Continuous"]
        },
        {
            name:"Histogram",
            renderChart: (properties, init) => renderHistogram(properties, init),
            prep: (properties) => preparationHistogram(properties),
            data_types: ["Binary", "Categorical", "Numerical_Discrete", "Numerical_Continuous"]
        },
        {
            name:"Box Plot",
            renderChart: (properties, init) => renderBoxplot(properties, init),
            prep: (properties) => preparationBoxplot(properties),
            data_types: ["Numerical_Discrete", "Numerical_Continuous"]
        },
        {
            name: "Parallel Coordinates",
            renderChart: (properties, init) => renderParallelCoordinate(properties, init),
            prep: (properties) => preparationParallel(properties),
            data_types: ["Binary", "Categorical", "Numerical_Discrete", "Numerical_Continuous"]
        }
    ]
    // Init with default values
    properties.curr_data_type = properties.data_types[1]
    properties.curr_visualization = properties.visualizations[0]
    properties.curr_class_label = properties.class_labels[0]
    properties.curr_attribute = Object.keys(properties.data[properties.curr_data_type])[0]
    // First data selected
    properties.curr_data = properties.data[properties.curr_data_type][properties.curr_attribute].dataset
    // Setup available visualizations
    properties.available_visualizations = properties.visualizations.filter(curr => curr.data_types.includes(properties.curr_data_type))
    // SVG Setup
    const fullwidth = 1024
    const fullheight = 768
    properties.width = fullwidth - properties.margin.left - properties.margin.right - properties.margin.left
    properties.height = fullheight - properties.margin.top - properties.margin.bottom
    properties.svg = d3.select(".content-svgs")
        .append("div")
        .attr("class","row centered")
        .append("div")
        .attr("class","col-sm-12")
        .append("svg")
        .attr("width", properties.width + properties.margin.left + properties.margin.left + properties.margin.right)
        .attr("height", properties.height + properties.margin.top + properties.margin.bottom)
    // Axis for charts
    properties.yAxisDomain = {
        "post_surgical_complication": [],
        "Clavien_Dindo_classification": []
    }
    for(let i = 0; i<Object.keys(properties.yAxisDomain).length; ++i){
        const height_domain = []
        height_domain.push("All")
        if(Object.keys(properties.yAxisDomain)[i] === "post_surgical_complication") {
            height_domain.push("No")
            height_domain.push("Yes")
        }
        if(Object.keys(properties.yAxisDomain)[i] === "Clavien_Dindo_classification")
            for(let i = 0; i < 8; ++i)
                height_domain.push(""+i)
        properties.yAxisDomain[Object.keys(properties.yAxisDomain)[i]] = height_domain
    }
    properties.threshold_spacing = properties.curr_data_type === "Numerical_Continuous" ? 0.1 : 1
    // Initiate properties for each view
    // Common for each view
    properties.widthScaleLinear = d3.scaleLinear().range([0, properties.width])
    properties.heightScale = d3.scaleBand().rangeRound([properties.margin.top, properties.height]).padding(0.3)
    properties.heightScaleLinear = d3.scaleLinear().range([properties.height, 0])
    properties.xAxisLinear = d3.axisBottom(properties.widthScaleLinear)
    properties.yAxis = d3.axisLeft(properties.heightScale)
    properties.yAxisLinear = d3.axisLeft(properties.heightScaleLinear)
    properties.svg = d3.select(".content-svgs").select("svg")

    // Properties for violin
    properties.containerViolin = {}

    // Properties for Parallel
    properties.containerParallel = {}

    // Properties for Boxplot
    properties.containerBoxplot = {}

    // Properties for Histogram
    properties.containerHistogram = {}

    // Fill dropdowns
    dropdown(
        // List of functions for each dropdown
        [
            function(class_label){
                properties.curr_class_label = class_label
                properties.curr_data = properties.data[properties.curr_data_type][properties.curr_attribute].dataset
                properties.curr_visualization.prep(properties, false)
                properties.curr_visualization.renderChart(properties, false)
            },
            function(visualization){
                properties.curr_visualization  = properties.visualizations.find(d => d.name === visualization)
                properties.curr_data = properties.data[properties.curr_data_type][properties.curr_attribute].dataset
                properties.curr_visualization.prep(properties, true)
                properties.curr_visualization.renderChart(properties, true)
            },
            function(data_type){
                properties.curr_data_type = data_type
                properties.curr_attribute = Object.keys(properties.data[properties.curr_data_type])[0]
                properties.curr_data = properties.data[properties.curr_data_type][properties.curr_attribute].dataset
                properties.threshold_spacing = properties.curr_data_type === "Numerical_Continuous" ? 0.01 : 1
                properties.available_visualizations = properties.visualizations.filter(curr => curr.data_types.includes(properties.curr_data_type))
                // Update attribute
                let dropdown_attributes = d3.select("#attribute_selector")

                dropdown_attributes.selectAll("select").remove()
                dropdown_attributes.selectAll("option").remove()

                dropdown_attributes
                    .append("select")
                    .selectAll("option")
                    .data(Object.keys(properties.data[properties.curr_data_type]))
                    .enter()
                    .append("option")
                    .attr("value", d => d)
                    .text(d => d)
                // Update Available Visualizations
                let dropdown_visualizations = d3.select("#visualization")

                dropdown_visualizations.selectAll("select").remove()
                dropdown_visualizations.selectAll("option").remove()

                dropdown_visualizations
                    .append("select")
                    .selectAll("option")
                    .data(properties.available_visualizations.map((d) => d.name))
                    .enter()
                    .append("option")
                    .attr("value", d => d)
                    .text(d => d)
                // Render new vizualization
                if(!properties.available_visualizations.includes(properties.curr_visualization)) {
                    properties.curr_visualization = properties.available_visualizations[0]
                    properties.curr_visualization.prep(properties, true)
                    properties.curr_visualization.renderChart(properties, true)
                }
                else{
                    properties.curr_visualization.prep(properties, false)
                    properties.curr_visualization.renderChart(properties, false)
                }
            },
            function(attribute){
                properties.curr_attribute = attribute
                properties.curr_data = properties.data[properties.curr_data_type][properties.curr_attribute].dataset
                properties.curr_visualization.prep(properties, false)
                properties.curr_visualization.renderChart(properties, false)
            }
        ],
        // List of html element id for each dropdown
        [
            "#class_label_dropdown",
            "#visualization",
            "#data_type",
            "#attribute_selector"
        ],
        // List of arrays for dropdown options
        [
            properties.class_labels,
            properties.available_visualizations.map((d) => d.name),
            properties.data_types,
            Object.keys(properties.data[properties.curr_data_type])
        ]
    )

    preparationViolin(properties, true)
    renderViolin(properties, true)
}

function preparationViolin(properties, init){
    if(init) {
        d3.select(".content-svgs").selectAll("svg>*").remove()
        properties.svg = d3.select(".content-svgs").select("svg")
            .append("g")
            .attr("transform", "translate(" + properties.margin.left + ",0)")
    }

    // Retrieve height domain of current label
    properties.containerViolin.height_domain = properties.yAxisDomain[properties.curr_class_label]

    // Current selected data
    const dataset = properties.data[properties.curr_data_type][properties.curr_attribute].dataset

    // Get data for each violin displayed
    properties.containerViolin.data = []
    for(let x = 0; x < properties.containerViolin.height_domain.length; ++x) {
        // Push an empty array to fill with values
        properties.containerViolin.data.push([])
        // Select the data based on height domain, if in the first position ("all") all the dataset
        let compare
        if(properties.curr_class_label === properties.class_labels[0]) {
            if (properties.containerViolin.height_domain[x] === "No")
                compare = "0"
            else if (properties.containerViolin.height_domain[x] === "Yes")
                compare = "1"
        }
        else
            compare = properties.containerViolin.height_domain[x]
        const curr_data = x === 0 ? dataset : dataset.filter(d => d[properties.curr_class_label] == compare)
        for (let i = 0; i < curr_data.length; ++i) {
            properties.containerViolin.data[x].push(Number(curr_data[i][properties.curr_attribute]))
        }
        // Sort inserted values for future use
        properties.containerViolin.data[x].sort((a, b) => a - b)
    }

    // Get the maximum value displayed of all violins for the X-Axis range setter
    properties.containerViolin.max = 0
    properties.containerViolin.min = null
    for (let i = 0; i <properties.containerViolin.data.length; ++i) {
        const min_value = properties.containerViolin.data[i][0]
        const max_value_of_index = properties.containerViolin.data[i][properties.containerViolin.data[i].length - 1]
        if (properties.containerViolin.max < max_value_of_index)
            properties.containerViolin.max = max_value_of_index
        if(properties.containerViolin.min === null)
            properties.containerViolin.min = min_value
        if(properties.containerViolin.min > min_value)
            properties.containerViolin.min = min_value
    }

    // Thresholds for bins in histogram
    properties.containerViolin.thresholds = []
    for (let i = 0; i <= properties.containerViolin.max; i += properties.threshold_spacing)
        properties.containerViolin.thresholds.push(i)

    // Threshold spacing for between each bin in histogram
    let temp = properties.threshold_spacing
    properties.containerViolin.threshold_multiplier = 1
    while(temp < 1){
        temp *= 10
        properties.containerViolin.threshold_multiplier *= 10
    }

    // Setters for domain and ranges of axis and scales
    properties.widthScaleLinear.domain([properties.containerViolin.min, properties.containerViolin.max])
    properties.heightScale.domain(properties.containerViolin.height_domain)
    properties.xAxisLinear.scale(properties.widthScaleLinear)
    properties.yAxis.scale(properties.heightScale)

    // Tooltip displayed
    properties.containerViolin.tip = d3.tip()
        .attr("class", "d3-tip")
        .offset([-10, 0])
        .html(d => "<strong></strong>")
}

function preparationParallel(properties, init){
    d3.select(".content-svgs").select("svg>*").remove()
    properties.svg = d3.select(".content-svgs")
        .select("svg")
        .append("g")
        .attr("transform", "translate(" + properties.margin.left + "," + properties.margin.top + ")")
}

function preparationBoxplot(properties, init){
    d3.select(".content-svgs").selectAll("svg>*").remove()
    properties.svg = d3.select(".content-svgs").select("svg").append("g")
        .attr("transform", "translate(" + properties.margin.left + ",0)")

    properties.containerBoxplot.height_domain = properties.yAxisDomain[properties.curr_class_label]

    const dataset = properties.data[properties.curr_data_type][properties.curr_attribute].dataset

    const boxs_data = []
    for(let x = 0; x < properties.containerBoxplot.height_domain.length; ++x) {
        boxs_data.push([])
        let compare
        if(properties.containerBoxplot.height_domain[x] === "No")
            compare = "0"
        else if(properties.containerBoxplot.height_domain[x] === "Yes")
            compare = "1"
        else
            compare = properties.containerBoxplot.height_domain[x]
        const curr_data = x === 0 ? dataset : dataset.filter(d => d[properties.curr_class_label] == compare)
        for (let i = 0; i < curr_data.length; ++i) {
            boxs_data[x].push(Number(curr_data[i][properties.curr_attribute]))
        }
        boxs_data[x].sort((a, b) => a - b)
    }

    // Prepare the data for the box plots
    let min_whisker = 0
    let max_whisker = 0
    properties.containerBoxplot.boxPlotData = []
    for (let x = 0; x< boxs_data.length; ++x){

        let record = {}
        boxs_data[x] = boxs_data[x].sort((a, b) => a - b)

        record["key"] = x == 0 ? "All" : ""+x
        record["counts"] = boxs_data[x]
        record["quartile"] = [
            d3.quantile(boxs_data[x], 0.25),
            d3.quantile(boxs_data[x], 0.5),
            d3.quantile(boxs_data[x], 0.75)
        ]
        const IQR = record.quartile[2]-record.quartile[0]
        const lowerWhisker = record.quartile[0] - 1.5*IQR
        const higherWhisker = record.quartile[2] + 1.5*IQR
        record["whiskers"] = [
            lowerWhisker,
            higherWhisker
        ]
        if(min_whisker > lowerWhisker)
            min_whisker = lowerWhisker
        if(max_whisker < higherWhisker)
            max_whisker = higherWhisker
        properties.containerBoxplot.boxPlotData.push(record)
    }

    let max = 0
    for (let i = 0; i < properties.containerBoxplot.boxPlotData.length; ++i)
        if (max < properties.containerBoxplot.boxPlotData[i].counts[properties.containerBoxplot.boxPlotData[i].counts.length - 1])
            max = properties.containerBoxplot.boxPlotData[i].counts[properties.containerBoxplot.boxPlotData[i].counts.length - 1]


    properties.widthScaleLinear.domain([min_whisker < 0 ? min_whisker:0, max_whisker > max ? max_whisker : max])
    properties.heightScale.domain(properties.containerBoxplot.height_domain)
    properties.xAxisLinear.scale(properties.widthScaleLinear)
    properties.yAxis.scale(properties.heightScale)

    function average(array){
        let acc = 0
        for(let i = 0; i < array.length; ++i)
            acc += Number(array[i])
        return acc / array.length
    }
    // Tooltip displayed
    properties.containerBoxplot.tip = d3.tip()
        .attr("class", "d3-tip")
        .offset([-10, 0])
        .html(d => "<strong> Average: " + average(d.counts) + " / Median: " + d.quartile[1] + "<br>" +
            "</strong> Min: "+ d.whiskers[0]+ " / Max: " + d.whiskers[1] + " <br>" +
            "</strong> Quartile 1: "+ d.quartile[0]+ " / Quartile 3: " + d.quartile[2] + " <br>"
        )
    properties.svg.call(properties.containerBoxplot.tip)
}

function preparationHistogram(properties, init){
    d3.select(".content-svgs").selectAll("svg>*").remove()
    properties.svg = d3.select(".content-svgs").select("svg").append("g")
        .attr("transform", "translate(" + properties.margin.left + ",0)")

    const dataset = properties.data[properties.curr_data_type][properties.curr_attribute].dataset

    // Treated data for histogram bins
    properties.containerHistogram.hists_data = []
    // Array with object {mean ,standard_deviation}
    properties.containerHistogram.hists_data_extra_info = []
    for(let x = 0; x < properties.yAxisDomain[properties.curr_class_label].length; ++x) {
        properties.containerHistogram.hists_data.push([])
        let compare
        if(properties.curr_class_label === properties.class_labels[0]) {
            if (properties.yAxisDomain[properties.curr_class_label][x] === "No")
                compare = "0"
            else if (properties.yAxisDomain[properties.curr_class_label][x] === "Yes")
                compare = "1"
        }
        else
            compare = properties.yAxisDomain[properties.curr_class_label][x]
        const curr_data = x === 0 ? dataset : dataset.filter(d => d[properties.curr_class_label] == compare)
        // Calculate mean
        let mean = 0
        for (let i = 0; i < curr_data.length; ++i) {
            mean += Number(curr_data[i][properties.curr_attribute])
            properties.containerHistogram.hists_data[x].push(Number(curr_data[i][properties.curr_attribute]))
        }
        mean = mean / curr_data.length
        // Sort hist data
        properties.containerHistogram.hists_data[x].sort((a, b) => a - b)
        //Calculate Standard Deviation
        let acc = 0
        for (let i = 0; i < curr_data.length; ++i) {
            acc = acc + (mean - Number(curr_data[i][properties.curr_attribute]))*(mean - Number(curr_data[i][properties.curr_attribute]))
        }
        const std = Math.sqrt(acc/(curr_data.length-1))
        // Push extra info
        properties.containerHistogram.hists_data_extra_info.push({
            mean,
            std,
        })
    }

    let max = 0
    for (let i = 0; i < properties.containerHistogram.hists_data.length; ++i)
        if (max < properties.containerHistogram.hists_data[i][properties.containerHistogram.hists_data[i].length - 1])
            max = properties.containerHistogram.hists_data[i][properties.containerHistogram.hists_data[i].length - 1]

    properties.widthScaleLinear.domain([-1, max+properties.threshold_spacing*2])

    // Tooltip displayed
    properties.containerHistogram.tip = d3.tip()
        .attr("class", "d3-tip")
        .offset([-10, 0])
        .html(d => "<strong> Occurrences: " + d.length)

    properties.svg.call(properties.containerHistogram.tip)
}

