"use strict"

/**
 * Create one or multiple dropdowns
 * @param list_of_functions - array of functions executed when an option is selected, each function corresponds to 1 dropdown
 * @param list_of_dropdowns_id - array of HTML id's, each id corresponds to 1 dropdown
 * @param list_of_data - array of arrays of names, each array of names will correspond to all the options displayed in the dropdown
 */
function dropdown(list_of_functions, list_of_dropdowns_id, list_of_data){
    for(let i = 0; i<list_of_dropdowns_id.length; ++i) {
        let dropdown_class = d3.select(list_of_dropdowns_id[i])

        dropdown_class
            .append("select")
            .selectAll("option")
            .data(list_of_data[i])
            .enter()
            .append("option")
            .attr("value", d => d)
            .text(d => d[0].toUpperCase() + d.slice(1, d.length))// capitalize 1st letter

        dropdown_class.on("change", function () {
            let value = d3.select(this).select("select").property("value")
            list_of_functions[i](value)
        })
    }
}