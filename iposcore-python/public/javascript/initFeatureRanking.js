"use strict"

/**
 * Main function called by HTML Script, initializes all the interactions of the page and visualizations
 * @param graphData - Contains the data received from the backend in JSON format (documented in Github)
 */
function init(graphData){
    //Initialization of main properties used by the visualizations
    const properties ={}

    properties.data = graphData

    properties.margins = {
            top: 30,
            right: 20,
            bottom: 30,
            left: 60
    }

    properties.MAX_LABEL_SIZE_Y = 200
    properties.MAX_LABEL_SIZE_X = 150
    properties.displayingAll = false

    properties.data_types = Object.keys(graphData)
    properties.scoring_functions = Object.keys(graphData[properties.data_types[0]])
    properties.class_labels = Object.keys(graphData[properties.data_types[0]][properties.scoring_functions[0]])

    properties.curr_class_label = properties.class_labels[0]
    properties.curr_scoring_function = properties.scoring_functions[0]
    properties.curr_data_type = properties.data_types[0]

    // On click functions for existing buttons
    d3.select("#prev").on("click",()=>get_next_prev(-1, properties))
    d3.select("#next").on("click",()=>get_next_prev(1, properties))
    d3.select("#displayall").on("click",()=>renderMultipleBarcharts(properties, true))

    // Fill dropdowns
    dropdown(
        // List of functions for each dropdown
        [
            function(class_label){
		        d3.selectAll(".d3-tip").remove()
                properties.curr_class_label =class_label
                properties.displayingAll ? renderMultipleBarcharts(properties, false) : renderSingleBarchart(properties, false, undefined)
            },
            function(score_function){
		        d3.selectAll(".d3-tip").remove()
                properties.curr_scoring_function  = score_function
                properties.displayingAll ? renderMultipleBarcharts(properties, false) : renderSingleBarchart(properties, false, undefined)
            }
        ],
        // List of html element id for each dropdown
        [
            "#class_label_dropdown",
            "#score_function_dropdown"
        ],
        // List of arrays for dropdown options
        [
            properties.class_labels,
            properties.scoring_functions
        ]
    )

    // Default view for single barchart
    renderSingleBarchart(properties, true, undefined)
}

/**
 * Function called to display a single bar chart in detail
 * @param properties - Object shared amongst views containing axis, scales, data, and other information
 * @param init - Flag to render extra components if it's the first rendering
 * @param clicked_data_type - If user clicked from multiple bar chart visualization then this field has information
 */
function renderSingleBarchart(properties, init, clicked_data_type){
	if(!init)
		d3.select("div#slider-fill > *").remove()

    // Change flag to inform the system that only one bar chart is displayed
    properties.displayingAll = false

    // Width and Height of SVG
	const fullwidth = 1024
	const fullheight = 768

    // If user clicked from multiple bar chart visualization then this field has information
    // Change the flag according to the information
	if(clicked_data_type)
		properties.curr_data_type = clicked_data_type

    // Retrieve selected data according to flags
    const current_data = properties.data[properties.curr_data_type][properties.curr_scoring_function][properties.curr_class_label]
    // Maximum value for slider
    properties.maxSlidderValue = current_data.length

	if(init){
	    // Remove previous content inside content container
		d3.selectAll(".content-svgs>*").remove()
		d3.selectAll(".d3-tip").remove()
        // HTML row where svgs will be put
		let currRow = d3.select(".content-svgs").append("div").attr("class","row centered")
		// Create contaner where slider will be
        defineBoxForSlider(properties, currRow)
		//Create container where title and bar chart will appear
        defineBoxForSVG(properties, properties.curr_data_type, currRow, fullwidth, fullheight, "col-sm-12")
	}
	// Find out maximum value for the height scale
	let max = getMax(current_data, "column_value")

    // Change flags and objects of properties for single bar chart visualization
	setUpProperties(properties, fullwidth, fullheight, max)

	// Add the display all button and activate the prev and next buttons
	d3.select("#displayall").on("click",()=>renderMultipleBarcharts(properties, true)).text("Display All")
	d3.select("#prev").attr("disabled",null)
	d3.select("#next").attr("disabled",null)

	fillerSlider(
	    properties,
        val => {
            d3.select("#value-fill").text(d3.format("")(val))
            d3.selectAll(".d3-tip").remove()
            renderBarchart(
                properties.data[properties.curr_data_type][properties.curr_scoring_function][properties.curr_class_label].slice(0,val),
                properties,
                false
            )
        })
	// Render barchart
	renderBarchart(current_data.slice(0, properties.sliderFill.value()), properties, init)
}


/**
 * Function called to display all the bar chart of all data types
 * @param properties - Object shared amongst views containing axis, scales, data, and other information
 * @param init - Flag to render extra components if it's the first rendering
 */
function renderMultipleBarcharts(properties, init){
    // Change flag to inform the system that multiple bar charts are displayed
    properties.displayingAll = true

    // Width and Height of each SVG
    const fullwidth = 500
	const fullheight = 700

    // HTML row where svgs will be put
	let currRow
	if(init){
	    // Remove previous content inside content container
	    d3.selectAll(".content-svgs>*").remove()
		d3.selectAll(".d3-tip").remove()
        // Disable buttons and change existing buttons
        d3.select("#displayall")
            .on("click",
                () => renderSingleBarchart(
                    properties,
                    true,
                    properties.data_types[properties.data_types.indexOf(properties.curr_data_type)]
                )
            ).text("Hide All")
        d3.select("#prev").attr("disabled", "disabled")
        d3.select("#next").attr("disabled", "disabled")
        // Create row for svg containers
		currRow = d3.select(".content-svgs").append("div").attr("class","row centered")
	}

	// Find out maximum value for the height scale
	let max = 0
	for(let i = 0; i<properties.data_types.length; ++i){
	    let temp = getMax(properties.data[properties.data_types[i]][properties.curr_scoring_function][properties.curr_class_label], "column_value")
		if(temp > max)
		    max = temp
	}

	// Change flags and objects of properties for multiple barchart visualization
	setUpProperties(properties, fullwidth, fullheight, max)

    // Render a bar chart for each data type
	for (let i = 0; i < properties.data_types.length; ++i) {
		properties.curr_data_type = properties.data_types[i]
        // If first render then render additional components
		if(init) {
		    //Create container where title and bar chart will appear
		    defineBoxForSVG(properties, properties.data_types[i], currRow, fullwidth, fullheight, "col-sm-6")
            // On click render clicked barchart
			properties.svg.on("click", () => renderSingleBarchart(properties, true, properties.data_types[i]))
			renderBarchart(
				properties.data[properties.data_types[i]][properties.curr_scoring_function][properties.curr_class_label].slice(0,10),
				properties,
				init
			)
		}
		// Else update existing barcharts
		else{
			properties.svg = d3.select("#svg_"+properties.data_types[i])
			renderBarchart(
				properties.data[properties.data_types[i]][properties.curr_scoring_function][properties.curr_class_label].slice(0,10),
				properties,
				init
			)
		}
	}
}

/**
 * Method used to get the next or the previous data type in the array of data types
 * @param value - Takes value of either -1 or 1
 * @param properties - Object shared amongst views containing axis, scales, data, and other information
 */
function get_next_prev(value, properties){
    let curr = properties.data_types.indexOf(properties.curr_data_type)
    curr = (curr + value) % properties.data_types.length
    if(curr < 0)
        curr = properties.data_types.length - 1
    properties.curr_data_type = properties.data_types[curr]
    d3.selectAll(".d3-tip").remove()
    renderSingleBarchart(properties, false, undefined)
}

/**
 * Retrieve maximum value in current data
 * @param current_data - An array of objects
 * @param object_field - Field used to compare and find max
 * @returns {number}
 */
function getMax(current_data, object_field){
    let max = 0
	for(let i = 0; i < current_data.length; ++i)
		if(max < current_data[i][object_field])
			max = current_data[i][object_field]
    return max
}

/**
 * Set up the properties object with scales, axis, and sizes of graphics rendered inside SVG
 * @param properties - Object shared amongst views containing axis, scales, data, and other information
 * @param width - width of rendered chart
 * @param height - height of rendered chart
 * @param max - max height value
 */
function setUpProperties(properties, width, height, max){
	properties.heightMax = max
	properties.width = width - properties.margins.left - properties.margins.right - properties.MAX_LABEL_SIZE_X
	properties.height = height - properties.margins.top - properties.margins.bottom - properties.MAX_LABEL_SIZE_Y
	properties.heightScale = d3.scaleSqrt().range([properties.height, 0])
	properties.widthScale = d3.scaleBand().rangeRound([0, properties.width]).padding(0.3)
	properties.yAxis = d3.axisLeft(properties.heightScale).tickFormat(d3.format(".4~s"))
	properties.xAxis = d3.axisBottom(properties.widthScale)
    // Tool tip
    properties.tip = d3.tip()
		.attr("class", "d3-tip")
		.offset([-10, 0])
		.html(d =>
            "<strong>Column name: </strong>" + d.column_name + "</br>" +
            "<strong>Value: </strong>" +
            	(Math.round(d.column_value*1000000)/1000000) * 1
		)
}

/**
 * Create a box and an SVG inside
 * @param properties - Object shared amongst views containing axis, scales, data, and other information
 * @param data_type - Current data data type
 * @param currRow - Container for the svg
 * @param fullwidth - Width of SVG
 * @param fullheight - Height of SVG
 * @param column_size - Size of HTML column for SVG
 */
function defineBoxForSVG(properties, data_type, currRow, fullwidth, fullheight, column_size){
    properties.svg = currRow
        .append("div")
        .attr("class", column_size)

    properties.svg.append("h3")
        .attr("id","h3_"+ data_type)
        .attr("align", "center")
        .style("text-decoration", "underline")
        .text(properties.curr_data_type)

    properties.svg = properties.svg
        .append("svg")
        .attr("id", "svg_" + data_type)
        .attr("width", fullwidth)
        .attr("height", fullheight)
        .attr("class", "centered")
		.append("g")
		.attr("transform", "translate(" + properties.margins.left + ",0)")
}

/**
 *
 * @param properties - Object shared amongst views containing axis, scales, data, and other information
 * @param currRow - Container
 */
function defineBoxForSlider(properties, currRow){
	// Add a Column for the container
    currRow.append("div").attr("class", "col-sm-12").attr("id", "slider-div")
    // Create container for slider
    properties.slidderContainer = d3.select("#slider-div")
        .append("div")
        .attr("class", "row align-items-center")
    // Create container for value of slider
    properties.slidderContainer
        .append("div")
        .attr("class", "col-sm")
        .append("p")
        .attr("id", "value-fill")
        .style("border-style", "solid")
        .style("border-width", "thin")
    // Create container for slider
    properties.slidderContainer
        .append("div")
        .attr("class", "col-sm")
        .append("div")
        .attr("id", "slider-fill")
}
